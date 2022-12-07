package edu.ucmo.studentenrollment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("faculty")
public class Faculty {
    @Id
    private String id;
    private String name;
    private String number;
    private String email;
    private String courses;
    private String password;
    private String department;

    public Faculty(String id, String name, String number, String email, String courses, String password, String department) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.courses = courses;
        this.password = password;
        this.department = department;
    }
}
