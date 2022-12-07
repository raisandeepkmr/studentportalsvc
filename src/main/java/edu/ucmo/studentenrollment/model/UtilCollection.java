package edu.ucmo.studentenrollment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("util_collection")
public class UtilCollection {
    @Id
    private String _id;
    private String name;
    private Integer userId;
    private Integer courseId;
    private Integer sectionId;

    public UtilCollection(String _id, String name, Integer userId, Integer courseId, Integer sectionId) {
        this._id = _id;
        this.name = name;
        this.userId = userId;
        this.courseId = courseId;
        this.sectionId = sectionId;
    }
}
