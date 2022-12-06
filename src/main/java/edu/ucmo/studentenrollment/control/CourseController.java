package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Course;
import edu.ucmo.studentenrollment.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "course", produces = "application/json", consumes = "application/json")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping(path = "/{name}")
    public Course getCourse(@PathVariable String code) {
        return courseService.getCourse(code);
    }

    @PostMapping
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/{number}")
    public Course deleteCourse(@PathVariable String code) {
        return courseService.deleteCourse(code);
    }
}
