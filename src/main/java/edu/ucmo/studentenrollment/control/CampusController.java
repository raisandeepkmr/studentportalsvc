package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Campus;
import edu.ucmo.studentenrollment.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "campus", produces = "application/json", consumes = "application/json")
public class CampusController {
    @Autowired
    CampusService campusService;

    @GetMapping(path = "/{name}")
    public Campus getCampus(@PathVariable String name) {
        return campusService.getCampus(name);
    }

    @PostMapping
    public Campus saveCampus(@RequestBody Campus campus) {
        return campusService.saveCampus(campus);
    }

    @DeleteMapping("/{number}")
    public Campus deleteCampus(@PathVariable String campus_id) {
        return campusService.deleteCampus(campus_id);
    }
}
