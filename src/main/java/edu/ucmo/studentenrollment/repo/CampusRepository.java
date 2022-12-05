package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Campus;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CampusRepository extends MongoRepository<Campus, String> {
    @Query("{name: '?0'}")
    Campus findCampusByName(String name);
    List<Campus> findAll();
    @DeleteQuery("campus_id: '?0'")
    Campus deleteCampusById(String campus_id);
}
