package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Course;
import edu.ucmo.studentenrollment.model.Enrolled;
import edu.ucmo.studentenrollment.model.common.AssignCourse;
import edu.ucmo.studentenrollment.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "course", produces = "application/json", consumes = "application/json")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping(path = "/name/{name}")
    public Course getCourse(@PathVariable String code) {
        return courseService.getCourse(code);
    }

    @GetMapping
    public List<Course> getAllCourse() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/assign")
    public Enrolled assignCourse(@RequestBody AssignCourse assignCourse) {
        return courseService.assignCourse(assignCourse);
    }

    @DeleteMapping("/courseId/{courseId}")
    public Course deleteCourse(@PathVariable String courseId) {
        return courseService.deleteCourse(courseId);
    }
}
