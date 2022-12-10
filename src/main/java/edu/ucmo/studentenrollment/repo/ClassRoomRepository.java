package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.ClassRoom;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ClassRoomRepository extends MongoRepository<ClassRoom, String> {
    @Query("{name: '?0'}")
    ClassRoom findClassRoomByName(String name);
    @DeleteQuery("{name: '?0'}")
    ClassRoom deleteByRoomName(String name);
    List<ClassRoom> findAll();
}
