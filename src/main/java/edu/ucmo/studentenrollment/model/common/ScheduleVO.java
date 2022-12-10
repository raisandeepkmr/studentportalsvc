package edu.ucmo.studentenrollment.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVO {
    private String schedulId;
    private String courseName;
    private String classDate;
    private String facultyName;
    private String roomName;
    private String classTime;
}
