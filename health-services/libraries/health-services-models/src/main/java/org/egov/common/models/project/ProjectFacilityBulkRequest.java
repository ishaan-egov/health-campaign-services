package org.egov.common.models.project;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egov.common.contract.request.RequestInfo;
import org.springframework.validation.annotation.Validated;

/**
 * ProjectStaffRequest
 */
@Validated


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectFacilityBulkRequest {
    @JsonProperty("RequestInfo")
    @NotNull
    @Valid
    private RequestInfo requestInfo = null;

    @JsonProperty("ProjectFacilities")
    @NotNull
    @Valid
    @Size(min=1)
    private List<ProjectFacility> projectFacilities = new ArrayList<>();

    public ProjectFacilityBulkRequest addProjectFacilityItem(ProjectFacility projectFacilityItem) {
        this.projectFacilities.add(projectFacilityItem);
        return this;
    }
}
