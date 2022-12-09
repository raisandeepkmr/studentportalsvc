package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.model.Campus;
import edu.ucmo.studentenrollment.model.ClassRoom;
import edu.ucmo.studentenrollment.model.Student;
import edu.ucmo.studentenrollment.repo.CampusRepository;
import edu.ucmo.studentenrollment.repo.ClassRoomRepository;
import edu.ucmo.studentenrollment.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusService {
    @Autowired
    CampusRepository campusRepository;
    @Autowired
    ClassRoomRepository classRoomRepository;

    public Campus getCampus(String name) {
        return campusRepository.findCampusByName(name);
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

    public ClassRoom deleteRoom(String name) {
        return classRoomRepository.deleteByRoomName(name);
    }
}
