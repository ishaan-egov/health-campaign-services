package org.egov.project.validator.staff;

import lombok.extern.slf4j.Slf4j;
import org.egov.common.models.Error;
import org.egov.common.validator.Validator;
import org.egov.project.web.models.ProjectStaff;
import org.egov.project.web.models.ProjectStaffBulkRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.egov.common.utils.CommonUtils.validateForNullId;
import static org.egov.project.Constants.GET_STAFF;

@Component
@Order(value = 1)
@Slf4j
public class PsNullIdValidator implements Validator<ProjectStaffBulkRequest, ProjectStaff> {

    @Override
    public Map<ProjectStaff, List<Error>> validate(ProjectStaffBulkRequest request) {
        return validateForNullId(request, GET_STAFF);
    }
}
