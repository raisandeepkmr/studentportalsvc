package edu.ucmo.studentenrollment.util;

import edu.ucmo.studentenrollment.model.ClassRoom;
import edu.ucmo.studentenrollment.model.Course;
import edu.ucmo.studentenrollment.model.Faculty;
import edu.ucmo.studentenrollment.model.Section;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CommonUtil {
    static String[] weekdays = "SUN MON TUE WED THU FRI SAT".split(" ");
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String paddedNumber(Long num) {
        return "1" + String.format("%011d", num);
    }

    public static List<Section> generateTimetable(List<Course> courses, List<ClassRoom> classRooms) {
        Date startTime = new Date();
        Instant instant = startTime.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        Instant dayInstant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date day = Date.from(dayInstant);
        Date classTime = new Date(day.getTime() + TimeUnit.DAYS.toMillis(1) + TimeUnit.HOURS.toMillis(9));
        Date classStartTime = new Date(classTime.getTime());

        List<Section> sections = new ArrayList<>();
        List<Date> weekDays = threeMonthWeekDays(classStartTime);
        boolean lastDay = false;
        for(Date eachDay: weekDays) {
            for(Course course: courses) {

            }
        }

        while(classTime.getTime() <= new Date(classStartTime.getTime() + TimeUnit.HOURS.toMillis(12)).getTime()) {
            Section section = new Section();
            section.setTime(classTime.toString());
            classTime = new Date(classTime.getTime() + TimeUnit.HOURS.toMillis(2) + TimeUnit.MINUTES.toMillis(30));
        }
        return new ArrayList<>();
    }

    public static List<Date> threeMonthWeekDays(Date starTime) {
        Calendar calendar = Calendar.getInstance();
        List<Date> weekDays = new ArrayList<>();
        Date endTime = new Date(starTime.getTime() + TimeUnit.DAYS.toMillis(90));
        for(Date eachDay = starTime;eachDay.getTime() <= endTime.getTime(); eachDay = new Date(starTime.getTime() + TimeUnit.DAYS.toMillis(1))){
            calendar.setTime(eachDay);
            if(calendar.get(Calendar.DAY_OF_WEEK) != 1 || calendar.get(Calendar.DAY_OF_WEEK) != 7) {
                weekDays.add(eachDay);
            }
        }
        return weekDays;
    }

    public static String getDaynameFromDate(Date starTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(starTime);
        return weekdays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }
}
