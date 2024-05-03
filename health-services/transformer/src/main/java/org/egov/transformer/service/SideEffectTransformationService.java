package org.egov.transformer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.egov.common.models.project.Project;
import org.egov.common.models.project.ProjectBeneficiary;
import org.egov.common.models.project.Task;
import org.egov.common.models.referralmanagement.sideeffect.SideEffect;
import org.egov.transformer.config.TransformerProperties;
import org.egov.transformer.enums.Operation;
import org.egov.transformer.models.downstream.SideEffectsIndexV1;
import org.egov.transformer.producer.Producer;
import org.egov.transformer.service.transformer.Transformer;
import org.egov.transformer.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static org.egov.transformer.Constants.*;

@Slf4j
public abstract class SideEffectTransformationService implements TransformationService<SideEffect> {
    protected final SideEffectIndexV1Transformer transformer;

    protected final Producer producer;

    protected final TransformerProperties properties;

    @Autowired
    protected SideEffectTransformationService(SideEffectTransformationService.SideEffectIndexV1Transformer transformer,
                                              Producer producer, TransformerProperties properties) {
        this.transformer = transformer;
        this.producer = producer;
        this.properties = properties;
    }

    @Override
    public void transform(List<SideEffect> payloadList) {
        log.info("transforming for ids {}", payloadList.stream()
                .map(SideEffect::getId).collect(Collectors.toList()));
        List<SideEffectsIndexV1> transformedPayloadList = payloadList.stream()
                .map(transformer::transform)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("transformation successful");
        producer.push(getTopic(),
                transformedPayloadList);
    }

    public abstract String getTopic();

    @Override
    public Operation getOperation() {
        return Operation.SIDE_EFFECT;
    }

    @Component
    static class SideEffectIndexV1Transformer implements Transformer<SideEffect, SideEffectsIndexV1> {
        private final SideEffectService sideEffectService;
        private final IndividualService individualService;
        private final ProjectService projectService;
        private final UserService userService;
        private final CommonUtils commonUtils;

        private final ObjectMapper objectMapper;

        @Autowired
        SideEffectIndexV1Transformer(SideEffectService sideEffectService, ProjectService projectService, IndividualService individualService, UserService userService, CommonUtils commonUtils, ObjectMapper objectMapper) {
            this.sideEffectService = sideEffectService;
            this.individualService = individualService;
            this.projectService = projectService;
            this.userService = userService;
            this.commonUtils = commonUtils;
            this.objectMapper = objectMapper;
        }

        @Override
        public List<SideEffectsIndexV1> transform(SideEffect sideEffect) {
            String tenantId = sideEffect.getTenantId();
            List<SideEffectsIndexV1> sideEffectsIndexV1List = new ArrayList<>();
            Task task = null;
            Integer cycleIndex = null;
            ProjectBeneficiary projectBeneficiary = null;
            Map<String, Object> individualDetails = new HashMap<>();
            ObjectNode boundaryHierarchy = null;
            List<Task> taskList = sideEffectService.getTaskFromTaskClientReferenceId(sideEffect.getTaskClientReferenceId(), tenantId);
            if (!CollectionUtils.isEmpty(taskList)) {
                task = taskList.get(0);
            }
            if (task != null) {
                try {
                    boundaryHierarchy = sideEffectService.getBoundaryHierarchyFromTask(task, tenantId);
                } catch (Exception e) {
                    log.error("error while fetching Boundary Details: {}", ExceptionUtils.getStackTrace(e));
                }
                List<ProjectBeneficiary> projectBeneficiaries = projectService
                        .searchBeneficiary(task.getProjectBeneficiaryClientReferenceId(), tenantId);

                if (!CollectionUtils.isEmpty(projectBeneficiaries)) {
                    projectBeneficiary = projectBeneficiaries.get(0);
                }
            }
            if (projectBeneficiary != null) {
                individualDetails = individualService.findIndividualByClientReferenceId(projectBeneficiary.getBeneficiaryClientReferenceId(), tenantId);
                if (projectBeneficiary.getProjectId() != null) {
                    Project project = projectService.getProject(projectBeneficiary.getProjectId(), tenantId);
                    cycleIndex = commonUtils.fetchCycleIndex(tenantId, project.getProjectTypeId(), sideEffect.getAuditDetails());
                }
            }

            ObjectNode additionalDetails = objectMapper.createObjectNode();
            additionalDetails.put(CYCLE_INDEX, cycleIndex);

            Map<String, String> userInfoMap = userService.getUserInfo(sideEffect.getTenantId(), sideEffect.getClientAuditDetails().getCreatedBy());

            SideEffectsIndexV1 sideEffectsIndexV1 = SideEffectsIndexV1.builder()
                    .sideEffect(sideEffect)
                    .dateOfBirth(individualDetails.containsKey(DATE_OF_BIRTH) ? (Long) individualDetails.get(DATE_OF_BIRTH) : null)
                    .age(individualDetails.containsKey(AGE) ? (Integer) individualDetails.get(AGE) : null)
                    .boundaryHierarchy(boundaryHierarchy)
                    .gender(individualDetails.containsKey(GENDER) ? (String) individualDetails.get(GENDER) : null)
                    .individualId(individualDetails.containsKey(INDIVIDUAL_ID) ? (String) individualDetails.get(INDIVIDUAL_ID) : null)
                    .symptoms(String.join(COMMA, sideEffect.getSymptoms()))
                    .userName(userInfoMap.get(USERNAME))
                    .role(userInfoMap.get(ROLE))
                    .userAddress(userInfoMap.get(CITY))
                    .taskDates(commonUtils.getDateFromEpoch(sideEffect.getClientAuditDetails().getLastModifiedTime()))
                    .syncedDate(commonUtils.getDateFromEpoch(sideEffect.getAuditDetails().getLastModifiedTime()))
                    .additionalDetails(additionalDetails)
                    .build();
            sideEffectsIndexV1List.add(sideEffectsIndexV1);
            log.info("sideEffectsIndexV1List {}", sideEffectsIndexV1List);
            return sideEffectsIndexV1List;
        }
    }
}
