package edu.ucmo.studentenrollment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("login_session")
public class LoginSession {
    @Id
    private String id;
    private String email;
    private String token;
    private String expiry;

    public LoginSession(String id, String email, String token, String expiry) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.expiry = expiry;
    }
}
