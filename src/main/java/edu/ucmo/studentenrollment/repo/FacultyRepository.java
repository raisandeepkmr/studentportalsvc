package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Faculty;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FacultyRepository extends MongoRepository<Faculty, String> {
    @Query("{name: '?0'}")
    Faculty findFacultyByName(String name);
    @Query("{number: '?0'}")
    Faculty findFacultyByNumber(String number);
    @Query("{email: '?0'}")
    Faculty findFacultyByEmail(String email);
    List<Faculty> findAll();
    @DeleteQuery("{number: '?0'}")
    Faculty deleteFacultyByNumber(String number);
}
