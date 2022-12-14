package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.common.LoginRequest;
import edu.ucmo.studentenrollment.model.common.LoginResponse;
import edu.ucmo.studentenrollment.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="login", produces = "application/json", consumes = "application/json")
public class SecurityController {
    @Autowired
    SecurityService securityService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return securityService.login(loginRequest);
    }

    @GetMapping
    public LoginResponse check_token(@PathVariable String sp_token) {
        return securityService.tokenCheck(sp_token);
    }
}
