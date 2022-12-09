package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SectionRepository extends MongoRepository<Section, String> {
    @Query("{id: '?0'}")
    Section findSectionBySectionId(String sectionId);
    @Query("{roomId: '?0'}")
    List<Section> findSectionByRoomId(String roomId);
    List<Section> findAll();
    Section save(Section section);
    Section save(List<Section> sections);
}
