package edu.ucmo.studentenrollment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("courses")
public class Course {
    @Id
    private String id;
    private String courseId;
    private String facultyId;
    private String minimumChrs;
    private String code;
    private String name;
    private String description;
}
