package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.exceptions.NotFoundException;
import edu.ucmo.studentenrollment.exceptions.ResourceConflictException;
import edu.ucmo.studentenrollment.model.*;
import edu.ucmo.studentenrollment.model.common.ScheduleVO;
import edu.ucmo.studentenrollment.repo.*;
import edu.ucmo.studentenrollment.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    EnrolledRepository enrolledRepository;
    @Autowired
    ScheduleDetailsRepository scheduleDetailsRepository;
    @Autowired
    UtilCollectionRepository utilCollectionRepository;

    public Student getStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByNumber(String number) {
        return studentRepository.findStudentByNumber(number);
    }

    public List<ScheduleVO> getScheduleByStudent(String number) {
        List<Enrolled> enrolleds = enrolledRepository.findEnroledByStudent(number);
        List<ScheduleVO> scheduleDetails = new ArrayList<>();
        for(Enrolled enrolled: enrolleds) {
            ScheduleDetail detail = scheduleDetailsRepository.findScheduleDetailById(enrolled.getScheduleId());
            Course course = courseRepository.findCourseById(detail.getCourseId());
            Faculty faculty = facultyRepository.findFacultyByNumber(detail.getFacultyId());
            ScheduleVO vo = ScheduleVO.builder()
                    .schedulId(detail.getScheduleId())
                    .classTime(detail.getTime())
                    .courseName(course.getName())
                    .facultyName(faculty.getName())
                    .classDate(detail.getDate().toString())
                    .build();
            scheduleDetails.add(vo);
        }
        return scheduleDetails;
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    public Student saveStudent(Student student) {
        Student existingStudent = getStudentByEmail(student.getEmail());
        if(existingStudent != null) throw new ResourceConflictException("Email address already in use.");
        UtilCollection utilCollection = utilCollectionRepository.findUtilCollectionByName("usernum");
        int studentNum = utilCollection.getUserId() + 1;
        utilCollection.setUserId(studentNum);
        utilCollectionRepository.save(utilCollection);
        student.setNumber(CommonUtil.paddedNumber((long) studentNum));
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        Student existingStudent = getStudentByEmail(student.getEmail());
        if(existingStudent == null) throw new NotFoundException("User not found to update!");
        existingStudent.setName(student.getName());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setPhone(student.getPhone());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setDob(student.getDob());
        return studentRepository.save(existingStudent);
    }

    public Student deleteStudent(String number) {
        Student existingStudent = studentRepository.findStudentByNumber(number);
        if(existingStudent == null || existingStudent.getNumber() == null) throw new NotFoundException("Student not found.");
        return studentRepository.deleteStudentByNumber(number);
    }
}
