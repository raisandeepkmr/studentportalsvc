package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Section;
import edu.ucmo.studentenrollment.model.common.LoginRequest;
import edu.ucmo.studentenrollment.model.common.LoginResponse;
import edu.ucmo.studentenrollment.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="login", produces = "application/json", consumes = "application/json")
public class SecurityController {
    @Autowired
    SecurityService securityService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return securityService.login(loginRequest);
    }

    @GetMapping
    public LoginResponse checkToken(@PathVariable String sp_token) {
        return securityService.tokenCheck(sp_token);
    }

    @GetMapping(path = "/timetable")
    public List<Section> generateSchedule() {
        return securityService.generateSchedule();
    }

    @GetMapping(path = "/timetable/all")
    public List<Section> getAllSchedule() {
        return securityService.getAllSchedule();
    }

    @DeleteMapping(path = "/timetable")
    public String deleteSchedule() {
        return securityService.deleteSchedule();
    }
}
