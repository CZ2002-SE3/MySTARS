package mystars.data.course;

import mystars.parser.Parser;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Lesson {
    private LessonType lessonType;
    private String venue;
    private LocalTime startTime;
    private LocalTime endTime;
    private Day day;
    private String group;

    public Lesson(LessonType lessonType, String venue, LocalTime startTime, LocalTime endTime, Day day, String group) {
        this.lessonType = lessonType;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.group = group;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStorageString() {
        return String.join(Parser.TILDE_SEPARATOR, lessonType.toString(), venue
                , startTime.format(DateTimeFormatter.ofPattern("HHmm")) + "-"
                        + endTime.format(DateTimeFormatter.ofPattern("HHmm"))
                , day.toString(), group);
    }
}
