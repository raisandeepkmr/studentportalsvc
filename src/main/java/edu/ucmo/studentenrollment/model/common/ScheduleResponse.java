package edu.ucmo.studentenrollment.model.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleResponse {
    String courseId;
    String token;
    String token_expiry;
    String userType;
}
