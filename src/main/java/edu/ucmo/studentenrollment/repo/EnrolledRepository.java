package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Enrolled;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EnrolledRepository extends MongoRepository<Enrolled, String> {
    @Query("{studentId: '?0'}")
    List<Enrolled> findEnroledByStudent(String studentId);
    @Query("{scheduleId: '?0'}")
    List<Enrolled> findEnroledByScheduleId(String scheduleId);
    @Query("{roomId: '?0'}")
    List<Enrolled> findEnroledByRoom(String roomId);
    @Query("{scheduleId: '?0'}")
    Enrolled findEnroledBySchdule(String scheduleId);
    List<Enrolled> findAll();
    Enrolled save(Enrolled enrolled);
}
