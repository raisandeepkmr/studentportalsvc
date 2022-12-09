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
    private String scheduleId;
    private String studentId;
    private String roomId;
}
