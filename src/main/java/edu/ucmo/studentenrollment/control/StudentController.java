package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Student;
import edu.ucmo.studentenrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "student", produces = "application/json", consumes = "application/json")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping(path = "/{name}")
    public Student getStudent(@PathVariable String name) {
        return studentService.getStudent(name);
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/{number}")
    public Student deleteStudent(@PathVariable String number) {
        return studentService.deleteStudent(number);
    }
}
