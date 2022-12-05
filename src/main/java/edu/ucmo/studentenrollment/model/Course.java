package edu.ucmo.studentenrollment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("courses")
public class Course {
    @Id
    private String id;
    private String minimumChrs;
    private String code;
    private String name;
    private String description;

    public Course(String id, String minimumChrs, String code, String name, String description) {
        this.id = id;
        this.minimumChrs = minimumChrs;
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
