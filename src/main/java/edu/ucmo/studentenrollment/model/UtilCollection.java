package edu.ucmo.studentenrollment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("util_collection")
public class UtilCollection {
    @Id
    private String _id;
    private String name;
    private Integer userId;

    public UtilCollection(String _id, String name, Integer userId) {
        this._id = _id;
        this.name = name;
        this.userId = userId;
    }
}
