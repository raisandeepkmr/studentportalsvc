package edu.ucmo.studentenrollment.util;

import edu.ucmo.studentenrollment.model.common.DayAndRoom;
import edu.ucmo.studentenrollment.model.common.TimeInRoom;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TimeTableUtil {
    private List<DayAndRoom> dayAndRooms = new ArrayList<>();
    Map<Integer, Date> weekDays = new HashMap<>();
    Date classStartTime = null;
    List<String> courses;
    List<String> rooms;

    public TimeTableUtil(List<String> courses, List<String> rooms) {
        this.courses = courses;
        this.rooms = rooms;
        Date startTime = new Date();
        Instant instant = startTime.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        Instant dayInstant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date day = Date.from(dayInstant);
        classStartTime = new Date(day.getTime() + TimeUnit.DAYS.toMillis(1) + TimeUnit.HOURS.toMillis(8));
        Date refStartTime = new Date(classStartTime.getTime());
        weekDays = threeMonthWeekDays(refStartTime);
        assignRoomAndTime(this.rooms);
//        assignRoomAndTime();
    }

    public List<String> getTimings() {
        List<String> classesWeekly = new ArrayList<>();
        int dayInd = 0;
        for(String sub: courses) {
            for (Integer dDay: getDayForCourse(dayInd)) {
                String classWeek = sub + "," + dDay;
                classesWeekly.add(classWeek);
            }
            if(dayInd >= 5)dayInd = 0; else dayInd++;
        }
        return classesWeekly;
//        timeTableTiming(classStartTime);
    }

    //Use this method to get all the class timing
    public List<Date> timeTableTiming() {
        List<Date> daySched = new ArrayList<>();
        Date endTime = new Date(classStartTime.getTime() + TimeUnit.HOURS.toMillis(12));
        Date eachClass = new Date(classStartTime.getTime());
        while(eachClass.getTime() <= endTime.getTime()) {
            daySched.add(eachClass);
            eachClass = new Date(eachClass.getTime() + TimeUnit.HOURS.toMillis(2) + TimeUnit.MINUTES.toMillis(50));
        }
        return daySched;
    }

    private Map<Integer, Date> threeMonthWeekDays(Date starTime) {
        Calendar calendar = Calendar.getInstance();
        Map<Integer, Date> weekDays = new HashMap<>();
        Date endTime = new Date(starTime.getTime() + TimeUnit.DAYS.toMillis(90));
        boolean skipStartBuffer = true;
        int dayInd = 0;
        for(Date eachDay = starTime;eachDay.getTime() <= endTime.getTime(); eachDay = new Date(eachDay.getTime() + TimeUnit.DAYS.toMillis(1))){
            calendar.setTime(eachDay);
            if(calendar.get(Calendar.DAY_OF_WEEK) == 2) skipStartBuffer = false;
            if(skipStartBuffer || calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) continue;
            else {
                weekDays.put(dayInd, eachDay);
                dayInd++;
            }
        }
        Map<Integer, Date> newWeekDays = new HashMap<>();
        for(Integer ind: weekDays.keySet()) {
            calendar.setTime(weekDays.get(ind));
            if(calendar.get(Calendar.DAY_OF_WEEK) == 2) {
                if(ind + 4 > weekDays.size()) break;
            }
            newWeekDays.put(ind, weekDays.get(ind));
        }
        return newWeekDays;
    }

    private List<Integer> getDayForCourse(int forDayOfWeek) {
        List<Integer> twoDaysInd = new ArrayList<>();
        if(forDayOfWeek <= 4) {
            twoDaysInd.add(forDayOfWeek);
            twoDaysInd.add(forDayOfWeek+1);
        } else {
            twoDaysInd.add(forDayOfWeek);
            twoDaysInd.add(forDayOfWeek - 4);
        }
        return twoDaysInd;
    }

    private void assignRoomAndTime(List<String> rooms) {
        for(String room: rooms) {
            Map<Integer, Boolean> timeRecords = new HashMap<>();
            timeRecords.put(0, false);
            timeRecords.put(1, false);
            timeRecords.put(2, false);
            timeRecords.put(3, false);
            timeRecords.put(4, false);
            TimeInRoom timeInRoom = new TimeInRoom();
            timeInRoom.setTimeBooked(timeRecords);

            Map<Integer, TimeInRoom> dayTimes = new HashMap<>();
            dayTimes.put(0, timeInRoom);
            dayTimes.put(1, timeInRoom);
            dayTimes.put(2, timeInRoom);
            dayTimes.put(3, timeInRoom);
            dayTimes.put(4, timeInRoom);
            DayAndRoom dayAndRoom = new DayAndRoom();
            dayAndRoom.setTimeBooked(room, dayTimes);
            this.dayAndRooms.add(dayAndRoom);
        }
    }

    public String getRoomSlot(Integer dayInd) {
        int ind = 0;
        for(DayAndRoom dayAndRoom: dayAndRooms) {
            String room = dayAndRoom.getRoomCode();
            Map<Integer, TimeInRoom> timesInRoom = dayAndRoom.getDayBooked();
            for(Integer theDay: timesInRoom.keySet()) {
                if(theDay == dayInd) {
                    Map<Integer, Boolean> timesAvailable = timesInRoom.get(theDay).getTimeBooked();
                    for(Integer availableTime: timesAvailable.keySet()) {
                        if(timesAvailable.get(availableTime)) return room + "," + availableTime;//timesAvailable.put(availableTime, true);
                    }
                }
            }
            ind++;
        }
        return "";
    }

    public Map<Integer, Date> getWeekDays() {
        return weekDays;
    }
}
