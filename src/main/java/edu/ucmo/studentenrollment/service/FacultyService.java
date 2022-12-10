package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.exceptions.NotFoundException;
import edu.ucmo.studentenrollment.exceptions.ResourceConflictException;
import edu.ucmo.studentenrollment.model.*;
import edu.ucmo.studentenrollment.repo.EnrolledRepository;
import edu.ucmo.studentenrollment.repo.FacultyRepository;
import edu.ucmo.studentenrollment.repo.ScheduleDetailsRepository;
import edu.ucmo.studentenrollment.repo.UtilCollectionRepository;
import edu.ucmo.studentenrollment.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    ScheduleDetailsRepository scheduleDetailsRepository;
    @Autowired
    EnrolledRepository enrolledRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    UtilCollectionRepository utilCollectionRepository;

    public Faculty getFacultyByName(String name) {
        return facultyRepository.findFacultyByName(name);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyByNumber(String number) {
        return facultyRepository.findFacultyByNumber(number);
    }

    public List<Student> getStudentsByFaculty(String number) {
        List<ScheduleDetail> scheduleDetails = scheduleDetailsRepository.findScheduleDetailByFacultyId(number);
        List<Student> students = new ArrayList<>();
        for(ScheduleDetail detail: scheduleDetails) {
            List<Enrolled> enrolleds = enrolledRepository.findEnroledByScheduleId(detail.getId());
            for (Enrolled enrolled: enrolleds) {
                students.add(studentService.getStudentByNumber(enrolled.getStudentId()));
            }
        }
        return students;
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

    public Faculty updateFaculty(Faculty faculty) {
        Faculty existingFaculty = getFacultyByEmail(faculty.getEmail());
        if(existingFaculty == null) throw new ResourceConflictException("Email address not in use.");
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFaculty(String number) {
        Faculty existingFaculty = facultyRepository.findFacultyByNumber(number);
        if(existingFaculty == null || existingFaculty.getNumber() == null) throw new NotFoundException("Student not found.");
        return facultyRepository.deleteFacultyByNumber(number);
    }
}
