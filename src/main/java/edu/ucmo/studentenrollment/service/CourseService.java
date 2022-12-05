package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.model.Course;
import edu.ucmo.studentenrollment.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public Course getCourse(String code) {
        return courseRepository.findCourseByCode(code);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course deleteCourse(String code) {
        return courseRepository.deleteCourseByCode(code);
    }
}
