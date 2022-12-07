package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Course;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    @Query("{code: '?0'}")
    Course findCourseByCode(String code);
    List<Course> findAll();
    Course save(Course course);
    @DeleteQuery("{courseId: '?0'}")
    Course deleteCourseByCode(String courseId);
}
