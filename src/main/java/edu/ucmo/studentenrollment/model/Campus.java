package edu.ucmo.studentenrollment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("campus_site")
public class Campus {
    @Id
    private String id;
    private String name;
    private String description;
    private String location;

    public Campus(String id, String name, String description, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }
}
