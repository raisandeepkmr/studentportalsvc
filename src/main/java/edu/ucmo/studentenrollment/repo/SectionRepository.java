package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SectionRepository extends MongoRepository<Section, String> {
    @Query("{sectionId: '?0'}")
    Section findSectionBySectionId(String sectionId);
    List<Section> findAll();
    Section save(Section section);
    Section save(List<Section> sections);
}
