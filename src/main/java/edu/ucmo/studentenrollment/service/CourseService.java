package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.exceptions.CannotAssignException;
import edu.ucmo.studentenrollment.exceptions.ClassFullException;
import edu.ucmo.studentenrollment.model.*;
import edu.ucmo.studentenrollment.model.common.AssignCourse;
import edu.ucmo.studentenrollment.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    ScheduleDetailsRepository scheduleDetailsRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    EnrolledRepository enrolledRepository;
    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UtilCollectionRepository utilCollectionRepository;

    public Course getCourse(String code) {
        return courseRepository.findCourseByCode(code);
    }

    public Course getCourseById(String courseId) {
        return courseRepository.findCourseById(courseId);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        UtilCollection utilCollection = utilCollectionRepository.findUtilCollectionByName("usernum");
        int courseNum = utilCollection.getCourseId() + 1;
        utilCollection.setCourseId(courseNum);
        utilCollectionRepository.save(utilCollection);
        course.setCourseId(String.valueOf(courseNum));
        return courseRepository.save(course);
    }

    public Enrolled assignCourse(AssignCourse assignCourse) {
        List<Enrolled> enrolled = enrolledRepository.findEnroledByStudent(assignCourse.getStudentId());
        Student student = studentRepository.findStudentByNumber(assignCourse.getStudentId());
        ScheduleDetail scheduleDetail = scheduleDetailsRepository.findScheduleDetailById(assignCourse.getScheduleId());
        if(enrolled != null && enrolled.size() > 0) {
            if(enrolled.size() >= 2) throw new CannotAssignException("Student already has maximum allowed courses");
            ClassRoom classRoom = classRoomRepository.findClassRoomByName(scheduleDetail.getRoomId());
            List<Enrolled> enrolledInRoom = enrolledRepository.findEnroledByRoom(scheduleDetail.getRoomId());
            if(enrolledInRoom.size() >= Integer.parseInt(classRoom.getCapacity())) throw new ClassFullException("This class is fully booked");
        }
        Enrolled enrolledNew = new Enrolled();
        enrolledNew.setStudentId(student.getNumber());
        enrolledNew.setScheduleId(scheduleDetail.getId());
        enrolledNew.setRoomId(scheduleDetail.getRoomId());
        student.setNumCourses(String.valueOf(Integer.parseInt(student.getNumCourses()) + 1));
        studentRepository.save(student);
        return enrolledRepository.save(enrolledNew);
    }

    public Course deleteCourse(String courseId) {
        return courseRepository.deleteCourseByCode(courseId);
    }
}
