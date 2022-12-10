package edu.ucmo.studentenrollment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document("student")
public class Student {
    @Id
    private String id;
    private String name;
    private String number;
    private String email;
    private String numCourses;
    private String password;
    private String address;
    private String phone;
    private Date dob;

    public Student(String id, String name, String number, String email, String numCourses, String password) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.numCourses = numCourses;
        this.password = password;
    }
}
