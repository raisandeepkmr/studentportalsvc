package edu.ucmo.studentenrollment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("section")
public class Section {
    @Id
    private String id;
    private String courseId;
    private String roomId;
    private String availableSpace;
    private String facultyId;
    private String time;

    public Section(String id, String courseId, String roomId, String availableSpace, String facultyId, String time) {
        this.id = id;
        this.courseId = courseId;
        this.roomId = roomId;
        this.availableSpace = availableSpace;
        this.facultyId = facultyId;
        this.time = time;
    }
}
