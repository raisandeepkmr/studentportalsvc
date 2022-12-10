package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.model.*;
import edu.ucmo.studentenrollment.model.common.ScheduleVO;
import edu.ucmo.studentenrollment.repo.CampusRepository;
import edu.ucmo.studentenrollment.repo.ClassRoomRepository;
import edu.ucmo.studentenrollment.repo.ScheduleDetailsRepository;
import edu.ucmo.studentenrollment.repo.StudentRepository;
import edu.ucmo.studentenrollment.util.TimeTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CampusService {
    @Autowired
    FacultyService facultyService;
    @Autowired
    CampusRepository campusRepository;
    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    ScheduleDetailsRepository scheduleDetailsRepository;
    @Autowired
    CourseService courseService;

    public Campus getCampus(String name) {
        return campusRepository.findCampusByName(name);
    }

    public ScheduleDetail getScheduleDetails() {
        List<ClassRoom> classRooms = classRoomRepository.findAll();
        List<String> classRoomsStr = classRooms.stream().map(x -> x.getName()).toList();
        List<Course> courses = courseService.getAllCourses();
        List<String> coursesStr = courses.stream().map(x -> x.getCourseId()).toList();
        TimeTableUtil util = new TimeTableUtil(coursesStr, classRoomsStr);
        ScheduleDetail detail = new ScheduleDetail();
        detail.setStartDate(util.getWeekDays().get(0));
        detail.setEndDate(util.getWeekDays().get(util.getWeekDays().size()-1));
        return detail;
    }

    public List<ScheduleVO> getAllSchedules() {
        List<ScheduleVO> voList = new ArrayList<>();
        List<ScheduleDetail> scheduleDetails = scheduleDetailsRepository.findAll();
        for(ScheduleDetail detail: scheduleDetails) {
            ClassRoom room = classRoomRepository.findClassRoomByName(detail.getRoomId());
            Course course = courseService.getCourseById(detail.getCourseId());
            if(course == null) continue;
            Faculty faculty = facultyService.getFacultyByNumber(detail.getFacultyId());
            if(faculty == null) continue;

            ScheduleVO scheduleVO = new ScheduleVO();
            scheduleVO.setSchedulId(detail.getId());
            scheduleVO.setClassDate(detail.getDate().toString());
            scheduleVO.setClassTime(detail.getTime());
            scheduleVO.setCourseName(course.getName());
            scheduleVO.setRoomName(room.getName());
            scheduleVO.setFacultyName(faculty.getName());
            voList.add(scheduleVO);
        }
        return voList;
    }

    public ScheduleDetail saveScheduleDetails(ScheduleDetail scheduleDetail) {
//        ScheduleDetail detail = ScheduleDetail.builder()
//                .courseId(scheduleDetail.getCourseId())
//                .date(new Date(scheduleDetail.getDate()))
//                .endDate(new Date(scheduleDetail.getEndDate()))
//                .facultyId(scheduleDetail.getFacultyId())
//                .roomId(scheduleDetail.getRoomId())
//                .startDate(new Date(scheduleDetail.getStartDate()))
//                .time(scheduleDetail.getTime())
//                .build();
        scheduleDetail.setTime(TimeTableUtil.getDayTiming(Integer.parseInt(scheduleDetail.getTime())));
        return scheduleDetailsRepository.save(scheduleDetail);
    }

    public List<ClassRoom> getClassrooms() {
        return classRoomRepository.findAll();
    }

    public ClassRoom saveClassroom(ClassRoom room) {
        return classRoomRepository.save(room);
    }

    public Campus saveCampus(Campus student) {
        return campusRepository.save(student);
    }

    public Campus deleteCampus(String id) {
        return campusRepository.deleteCampusById(id);
    }

    public ScheduleDetail deleteSchedule(String id) {
        return scheduleDetailsRepository.deleteScheduleById(id);
    }

    public ClassRoom deleteRoom(String name) {
        return classRoomRepository.deleteByRoomName(name);
    }
}
