package edu.ucmo.studentenrollment.model.common;

import java.util.HashMap;
import java.util.Map;

public class TimeInRoom {
    Map<Integer, Boolean> timeBooked = new HashMap<>();

    public void setTimeBooked(Map<Integer, Boolean> timeBooked) {
        this.timeBooked = timeBooked;
    }

    public Map<Integer, Boolean> getTimeBooked() {
        return timeBooked;
    }
}
