package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SectionRepository extends MongoRepository<Section, String> {
    @Query("{courseId: '?0'}")
    Section findSectionByCourseId(String courseId);
    List<Section> findAll();
}
