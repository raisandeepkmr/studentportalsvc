package edu.ucmo.studentenrollment.control;

import edu.ucmo.studentenrollment.model.Campus;
import edu.ucmo.studentenrollment.model.ClassRoom;
import edu.ucmo.studentenrollment.model.Enrolled;
import edu.ucmo.studentenrollment.model.ScheduleDetail;
import edu.ucmo.studentenrollment.model.common.ScheduleVO;
import edu.ucmo.studentenrollment.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "enrolled", produces = "application/json", consumes = "application/json")
public class EnrolledController {
    @Autowired
    CampusService campusService;

    @GetMapping(path = "/{name}")
    public Campus getCampus(@PathVariable String name) {
        return campusService.getCampus(name);
    }

    @GetMapping(path = "/schedule/get")
    public ScheduleDetail getScheduleDetails() {
        return campusService.getScheduleDetails();
    }

    @GetMapping(path = "/schedule/all")
    public List<ScheduleVO> getAllSchedules() {
        return campusService.getAllSchedules();
    }

    @PostMapping(path = "/schedule")
    public ScheduleDetail saveScheduleDetails(@RequestBody ScheduleDetail scheduleDetail) {
        return campusService.saveScheduleDetails(scheduleDetail);
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
    public Enrolled saveEnroll(@RequestBody Enrolled enrolled) {
//        return campusService.saveCampus(campus);
        return null;
    }

    @DeleteMapping("/{enrollId}")
    public Campus deleteEnrollment(@PathVariable String enrollId) {
        return campusService.deleteCampus(enrollId);
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public ScheduleDetail deleteSchedule(@PathVariable String scheduleId) {
        return campusService.deleteSchedule(scheduleId);
    }
}
