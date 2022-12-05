package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Enrolled;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EnrolledRepository extends MongoRepository<Enrolled, String> {
    @Query("{year: '?0'}")
    Enrolled findEnrooledByYear(String year);
    List<Enrolled> findAll();
}
