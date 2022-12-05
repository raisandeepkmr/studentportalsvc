package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.exceptions.NotFoundException;
import edu.ucmo.studentenrollment.exceptions.ResourceConflictException;
import edu.ucmo.studentenrollment.model.Faculty;
import edu.ucmo.studentenrollment.model.Student;
import edu.ucmo.studentenrollment.model.UtilCollection;
import edu.ucmo.studentenrollment.repo.FacultyRepository;
import edu.ucmo.studentenrollment.repo.UtilCollectionRepository;
import edu.ucmo.studentenrollment.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UtilCollectionRepository utilCollectionRepository;

    public Faculty getFaculty(String name) {
        return facultyRepository.findFacultyByName(name);
    }

    public Faculty getFacultyByEmail(String email) {
        return facultyRepository.findFacultyByEmail(email);
    }

    public Faculty saveFaculty(Faculty faculty) {
        Faculty existingFaculty = getFacultyByEmail(faculty.getEmail());
        if(existingFaculty != null) throw new ResourceConflictException("Email address already in use.");
        UtilCollection utilCollection = utilCollectionRepository.findUtilCollectionByName("usernum");
        int facultyNum = utilCollection.getUserId() + 1;
        utilCollection.setUserId(facultyNum);
        utilCollectionRepository.save(utilCollection);
        faculty.setNumber(CommonUtil.paddedNumber((long) facultyNum));
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFaculty(String number) {
        Faculty existingFaculty = facultyRepository.findFacultyByNumber(number);
        if(existingFaculty == null || existingFaculty.getNumber() == null) throw new NotFoundException("Student not found.");
        return facultyRepository.deleteFacultyByNumber(number);
    }
}
