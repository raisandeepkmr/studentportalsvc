package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Faculty;
import edu.ucmo.studentenrollment.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "faculty", produces = "application/json", consumes = "application/json")
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @GetMapping
    public List<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @GetMapping(path = "/name/{name}")
    public Faculty getFacultyByName(@PathVariable String name) {
        return facultyService.getFacultyByName(name);
    }

    @GetMapping(path = "/number/{number}")
    public Faculty getFacultyByNumber(@PathVariable String number) {
        return facultyService.getFacultyByNumber(number);
    }

    @PostMapping
    public Faculty saveFaculty(@RequestBody Faculty faculty) {
        return facultyService.saveFaculty(faculty);
    }

    @DeleteMapping("/{number}")
    public Faculty deleteFaculty(@PathVariable String number) {
        return facultyService.deleteFaculty(number);
    }
}
