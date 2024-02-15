package org.egov.transformer.models.downstream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectTaskIndexV1 {
    @JsonProperty("id")
    private String id;
    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("taskType")
    private String taskType;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("nameOfUser")
    private String nameOfUser;
    @JsonProperty("role")
    private String role;
    @JsonProperty("userAddress")
    private String userAddress;
    @JsonProperty("productVariant")
    private String productVariant;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("quantity")
    private Double quantity;
    @JsonProperty("deliveredTo")
    private String deliveredTo;
    @JsonProperty("isDelivered")
    private boolean isDelivered;
    @JsonProperty("deliveryComments")
    private String deliveryComments;
    @JsonProperty("administrationStatus")
    private String administrationStatus;
    @JsonProperty("boundaryHierarchy")
    private ObjectNode boundaryHierarchy;
    @JsonProperty("tenantId")
    private String tenantId;
    @JsonProperty("projectType")
    private String projectType;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("locationAccuracy")
    private Double locationAccuracy;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("lastModifiedBy")
    private String lastModifiedBy;
    @JsonProperty("createdTime")
    private Long createdTime;
    @JsonProperty("lastModifiedTime")
    private Long lastModifiedTime;
    @JsonProperty("projectBeneficiaryClientReferenceId")
    private String projectBeneficiaryClientReferenceId;
    @JsonProperty("householdId")
    private String householdId;
    @JsonProperty("memberCount")
    private Integer memberCount;
    @JsonProperty("clientReferenceId")
    private String clientReferenceId;
    @JsonProperty("syncedTimeStamp")
    private String syncedTimeStamp;
    @JsonProperty("syncedDate")
    private String syncedDate;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("syncedTime")
    private Long syncedTime;
    @JsonProperty("dateOfBirth")
    private Long dateOfBirth;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("individualId")
    private String individualId;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("additionalDetails")
    private ObjectNode additionalDetails;
    @JsonProperty("geoPoint")
    private List<Double> geoPoint;
    @JsonProperty("taskDates")
    private String taskDates;

}
