package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Campus;
import edu.ucmo.studentenrollment.model.ClassRoom;
import edu.ucmo.studentenrollment.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "campus", produces = "application/json", consumes = "application/json")
public class CampusController {
    @Autowired
    CampusService campusService;

    @GetMapping(path = "/{name}")
    public Campus getCampus(@PathVariable String name) {
        return campusService.getCampus(name);
    }

    @GetMapping(path = "/rooms")
    public List<ClassRoom> getClassRooms() {
        return campusService.getClassrooms();
    }

    @PostMapping(path = "/rooms")
    public ClassRoom saveClassRoom(@RequestBody ClassRoom classRoom) {
        return campusService.saveClassroom(classRoom);
    }

    @PostMapping
    public Campus saveCampus(@RequestBody Campus campus) {
        return campusService.saveCampus(campus);
    }

//    @DeleteMapping("/{campusId}")
//    public Campus deleteCampus(@PathVariable String campusId) {
//        return campusService.deleteCampus(campusId);
//    }
    @DeleteMapping("/room/{roomId}")
    public ClassRoom deleteRoom(@PathVariable String roomId) {
        return campusService.deleteRoom(roomId);
    }
}
