package edu.ucmo.studentenrollment.model.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DayAndRoom implements Serializable {
    String roomCode;
    Map<Integer, TimeInRoom> dayBooked = new HashMap<>();

    public void setTimeBooked(String roomCode, Map<Integer, TimeInRoom> dayBooked) {
        this.roomCode = roomCode;
        this.dayBooked = dayBooked;
    }

    public Map<Integer, TimeInRoom> getDayBooked(){
        return this.dayBooked;
    }

    public String getRoomCode() {
        return roomCode;
    }
}
