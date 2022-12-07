package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.model.Course;
import edu.ucmo.studentenrollment.model.UtilCollection;
import edu.ucmo.studentenrollment.repo.CourseRepository;
import edu.ucmo.studentenrollment.repo.UtilCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UtilCollectionRepository utilCollectionRepository;

    public Course getCourse(String code) {
        return courseRepository.findCourseByCode(code);
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

    public Course deleteCourse(String courseId) {
        return courseRepository.deleteCourseByCode(courseId);
    }
}
