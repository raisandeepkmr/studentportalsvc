package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.model.*;
import edu.ucmo.studentenrollment.model.common.LoginRequest;
import edu.ucmo.studentenrollment.model.common.LoginResponse;
import edu.ucmo.studentenrollment.repo.ClassRoomRepository;
import edu.ucmo.studentenrollment.repo.LoginSessionRepository;
import edu.ucmo.studentenrollment.repo.SectionRepository;
import edu.ucmo.studentenrollment.util.CommonUtil;
import edu.ucmo.studentenrollment.util.TimeTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SecurityService {
    @Autowired
    AdminService adminService;
    @Autowired
    StudentService studentService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    LoginSessionRepository loginSessionRepository;

    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    CourseService courseService;

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
                if(faculty == null) {
                    Admin admin = adminService.getAdminyByEmail(request.getEmail());
                    if(admin == null) return null;
                    loginSessionRepository.save(session);
                    response.setUserType("admin");
                    response.setNumber(admin.getNumber());
                    return response;
                } else if(faculty.getPassword().equals(request.getPassword())) {
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

    public List<Section> generateSchedule() {
        List<ClassRoom> classRooms = classRoomRepository.findAll();
        List<String> classRoomsStr = classRooms.stream().map(x -> x.getName()).toList();
        List<Course> courses = courseService.getAllCourses();
        List<String> coursesStr = courses.stream().map(x -> x.getCourseId()).toList();

        TimeTableUtil timeTableUtil = new TimeTableUtil(coursesStr, classRoomsStr);

        List<String> courseAndDays = timeTableUtil.getTimings();
        List<Date> dailyTimings = timeTableUtil.timeTableTiming();
        List<Section> sections = new ArrayList<>();
        for(String str: courseAndDays) {
            String courseName = str.split(",")[0];
            Integer dayInd = Integer.parseInt(str.split(",")[1]);
            Date courseTime = dailyTimings.get(dayInd);
            String room = timeTableUtil.getRoomSlot(dayInd);

            Section section = new Section();
            section.setTime(courseTime.toString());
            section.setCourseId(courseName);
            section.setRoomId(room);
            section.setAvailableSpace(classRooms.stream().filter(x-> x.getId().equals(room)).findFirst().get().getCapacity());
            section.setDays(CommonUtil.getDaynameFromDate(courseTime));
            section.setFacultyId(courses.stream().filter(x-> x.getCourseId().equals(courseName)).findFirst().get().getFacultyId());
            sections.add(section);
        }
//        List<String> courseAndTiming = new ArrayList<>();
//
//        Map<Integer, Date> classAndDays = timeTableUtil.getWeekDays();
//
//        Section session = sectionRepository.save(new Section());
//        if(session != null && session.getToken() != null && session.getToken().length() > 0) {
//            LoginResponse response = LoginResponse.builder()
//                    .token(session.getToken())
//                    .build();
//            return response;
//        }
        return sections;
    }
}
