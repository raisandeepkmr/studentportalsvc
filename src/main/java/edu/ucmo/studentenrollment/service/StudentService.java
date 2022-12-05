package edu.ucmo.studentenrollment.service;

import edu.ucmo.studentenrollment.exceptions.NotFoundException;
import edu.ucmo.studentenrollment.exceptions.ResourceConflictException;
import edu.ucmo.studentenrollment.model.Student;
import edu.ucmo.studentenrollment.model.UtilCollection;
import edu.ucmo.studentenrollment.repo.StudentRepository;
import edu.ucmo.studentenrollment.repo.UtilCollectionRepository;
import edu.ucmo.studentenrollment.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UtilCollectionRepository utilCollectionRepository;

    public Student getStudent(String name) {
        return studentRepository.findStudentByName(name);
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    public Student saveStudent(Student student) {
        Student existingStudent = getStudentByEmail(student.getEmail());
        if(existingStudent != null) throw new ResourceConflictException("Email address already in use.");
        UtilCollection utilCollection = utilCollectionRepository.findUtilCollectionByName("usernum");
        int studentNum = utilCollection.getUserId() + 1;
        utilCollection.setUserId(studentNum);
        utilCollectionRepository.save(utilCollection);
        student.setNumber(CommonUtil.paddedNumber((long) studentNum));
        return studentRepository.save(student);
    }

    public Student deleteStudent(String number) {
        Student existingStudent = studentRepository.findStudentByNumber(number);
        if(existingStudent == null || existingStudent.getNumber() == null) throw new NotFoundException("Student not found.");
        return studentRepository.deleteStudentByNumber(number);
    }
}
