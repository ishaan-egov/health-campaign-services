package org.egov.transformer.models.downstream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egov.common.models.individual.Name;
import org.egov.transformer.models.attendance.AttendanceLog;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceLogIndexV1 {
    @JsonProperty("attendanceLog")
    private AttendanceLog attendanceLog;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("familyName")
    private String familyName;
    @JsonProperty("attendeeName")
    private Name attendeeName;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("role")
    private String role;
    @JsonProperty("attendanceTime")
    private String attendanceTime;
    @JsonProperty("registerServiceCode")
    private String registerServiceCode;
    @JsonProperty("registerName")
    private String registerName;
    @JsonProperty("registerNumber")
    private String registerNumber;

}