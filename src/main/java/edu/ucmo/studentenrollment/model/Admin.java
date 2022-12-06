package edu.ucmo.studentenrollment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("admin")
public class Admin {
    @Id
    private String id;
    private String name;
    private String number;
    private String email;
    private String password;

    public Admin(String id, String name, String number, String email, String password) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.password = password;
    }
}
