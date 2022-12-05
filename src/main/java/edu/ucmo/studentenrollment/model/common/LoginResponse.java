package edu.ucmo.studentenrollment.model.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    String number;
    String token;
    String token_expiry;
}
