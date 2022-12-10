package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.ScheduleDetail;
import edu.ucmo.studentenrollment.model.Student;
import edu.ucmo.studentenrollment.model.common.ScheduleVO;
import edu.ucmo.studentenrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "student", produces = "application/json", consumes = "application/json")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping(path = "/name/{name}")
    public Student getStudentByName(@PathVariable String name) {
        return studentService.getStudentByName(name);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/number/{number}")
    public Student getStudentByNumber(@PathVariable String number) {
        return studentService.getStudentByNumber(number);
    }

    @GetMapping(path = "/schedule/{number}")
    public List<ScheduleVO> getScheduleByStudent(@PathVariable String number) {
        return studentService.getScheduleByStudent(number);
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{number}")
    public Student deleteStudent(@PathVariable String number) {
        return studentService.deleteStudent(number);
    }
}
