package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.model.Campus;
import edu.ucmo.studentenrollment.model.Student;
import edu.ucmo.studentenrollment.repo.CampusRepository;
import edu.ucmo.studentenrollment.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampusService {
    @Autowired
    CampusRepository campusRepository;

    public Campus getCampus(String name) {
        return campusRepository.findCampusByName(name);
    }

    public Campus saveCampus(Campus student) {
        return campusRepository.save(student);
    }

    public Campus deleteCampus(String id) {
        return campusRepository.deleteCampusById(id);
    }
}
