package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.model.Faculty;
import edu.ucmo.studentenrollment.model.LoginSession;
import edu.ucmo.studentenrollment.model.Student;
import edu.ucmo.studentenrollment.model.common.LoginRequest;
import edu.ucmo.studentenrollment.model.common.LoginResponse;
import edu.ucmo.studentenrollment.repo.LoginSessionRepository;
import edu.ucmo.studentenrollment.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class SecurityService {
    @Autowired
    StudentService studentService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    LoginSessionRepository loginSessionRepository;

    public LoginResponse login(LoginRequest request) {
        Date now = new Date();
        LoginSession session = new LoginSession("", request.getEmail(), CommonUtil.getUUID(), new Date(now.getTime() + TimeUnit.HOURS.toMillis(2)).toString());
        LoginResponse response = LoginResponse.builder()
                .token(session.getToken())
                .token_expiry(session.getExpiry())
                .build();

        switch (request.getUser_type().toLowerCase()) {
            case "student":
                Student student = studentService.getStudentByEmail(request.getEmail());
                if(student == null || student.getPassword() == null || student.getPassword().length() < 1) return null;
                else if(student.getPassword().equals(request.getPassword())) {
                    loginSessionRepository.save(session);
                    response.setNumber(student.getNumber());
                    return response;
                }
                break;
            case "professor":
                Faculty faculty = facultyService.getFacultyByEmail(request.getEmail());
                if(faculty == null || faculty.getPassword() == null || faculty.getPassword().length() < 1) return null;
                else if(faculty.getPassword().equals(request.getPassword())) {
                    loginSessionRepository.save(session);
                    response.setNumber(faculty.getNumber());
                    return response;
                }
                break;
        }
        return null;
    }

    public LoginResponse tokenCheck(String sp_token) {
        LoginSession session = loginSessionRepository.findLoginSessionByToken(sp_token);
        if(session != null && session.getToken() != null && session.getToken().length() > 0) {
            LoginResponse response = LoginResponse.builder()
                    .token(session.getToken())
                    .build();
            return response;
        }
        return null;
    }
}
