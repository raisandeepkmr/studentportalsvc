package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.UtilCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UtilCollectionRepository extends MongoRepository<UtilCollection, String> {
    @Query("{name: '?0'}")
    UtilCollection findUtilCollectionByName(String name);
    UtilCollection save(UtilCollection utilCollection);
}
