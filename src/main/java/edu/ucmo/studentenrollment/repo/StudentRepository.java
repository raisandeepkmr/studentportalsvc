package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Student;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    @Query("{name: '?0'}")
    Student findStudentByName(String name);
    @Query("{number: '?0'}")
    Student findStudentByNumber(String number);
    @Query("{email: '?0'}")
    Student findStudentByEmail(String email);
    List<Student> findAll();
    Student save(Student student);
    @DeleteQuery("{number: '?0'}")
    Student deleteStudentByNumber(String number);
}
