package edu.ucmo.studentenrollment.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignCourse {
    private String scheduleId;
    private String studentId;
}
