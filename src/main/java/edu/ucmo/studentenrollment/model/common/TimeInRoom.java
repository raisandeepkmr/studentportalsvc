package edu.ucmo.studentenrollment.model.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TimeInRoom implements Serializable {
    Map<Integer, Boolean> timeBooked = new HashMap<>();

    public void setTimeBooked(Map<Integer, Boolean> timeBooked) {
        this.timeBooked = timeBooked;
    }

    public Map<Integer, Boolean> getTimeBooked() {
        return timeBooked;
    }

    @Override
    protected TimeInRoom clone() throws CloneNotSupportedException {
        return (TimeInRoom) super.clone();
    }
}
