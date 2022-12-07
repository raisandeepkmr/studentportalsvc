package edu.ucmo.studentenrollment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("enrolled")
public class Enrolled {
    @Id
    private String id;
    private String date;
    private String time;
    private String year;
    private String grade;

    public Enrolled(String id, String date, String time, String year, String grade) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.year = year;
        this.grade = grade;
    }
}
