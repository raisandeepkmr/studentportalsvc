package edu.ucmo.studentenrollment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("schedule_details")
public class ScheduleDetail {
    @Id
    private String id;
    private String scheduleId;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date startDate;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date endDate;
    private String facultyId;
    private String courseId;
    private String roomId;
    private Date date;
    private String time;
}
