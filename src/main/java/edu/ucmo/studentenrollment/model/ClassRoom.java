package edu.ucmo.studentenrollment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("class_room")
public class ClassRoom {
    @Id
    private String id;
    private String name;
    private String campusName;
    private String capacity;
    private String floor;
    private String description;

    public ClassRoom(String id, String campusName, String capacity, String floor, String description) {
        this.id = id;
        this.campusName = campusName;
        this.capacity = capacity;
        this.floor = floor;
        this.description = description;
    }
}
