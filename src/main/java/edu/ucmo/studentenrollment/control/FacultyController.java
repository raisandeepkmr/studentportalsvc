package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Faculty;
import edu.ucmo.studentenrollment.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "faculty", produces = "application/json", consumes = "application/json")
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @GetMapping(path = "/{name}")
    public Faculty getStudent(@PathVariable String name) {
        return facultyService.getFaculty(name);
    }

    @PostMapping
    public Faculty saveStudent(@RequestBody Faculty faculty) {
        return facultyService.saveFaculty(faculty);
    }

    @DeleteMapping("/{number}")
    public Faculty deleteStudent(@PathVariable String number) {
        return facultyService.deleteFaculty(number);
    }
}
