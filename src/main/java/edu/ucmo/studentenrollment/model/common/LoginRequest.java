package edu.ucmo.studentenrollment.model.common;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
    String user_type;
}
