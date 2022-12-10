package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.ScheduleDetail;
import edu.ucmo.studentenrollment.model.UtilCollection;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ScheduleDetailsRepository extends MongoRepository<ScheduleDetail, String> {
    @Query("{id: '?0'}")
    ScheduleDetail findScheduleDetailById(String id);
    @Query("{roomId: '?0'}")
    List<ScheduleDetail> findScheduleDetailByRoomId(String roomId);
    @Query("{courseId: '?0'}")
    List<ScheduleDetail> findScheduleDetailByCourseId(String courseId);
    @Query("{facultyId: '?0'}")
    List<ScheduleDetail> findScheduleDetailByFacultyId(String facultyId);
    ScheduleDetail save(ScheduleDetail scheduleDetail);
    @DeleteQuery("{id: '?0'}")
    ScheduleDetail deleteScheduleById(String id);
}
