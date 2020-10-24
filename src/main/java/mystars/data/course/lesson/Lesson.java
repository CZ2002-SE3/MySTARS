package mystars.data.course.lesson;

import mystars.parser.Parser;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson {
    private final Week week;
    private LessonType lessonType;
    private String venue;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek day;
    private String group;

    public Lesson(LessonType lessonType, String venue, LocalTime startTime, LocalTime endTime, DayOfWeek day, Week week
            , String group) {
        this.lessonType = lessonType;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.week = week;
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

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStorageString() {
        return String.join(Parser.TILDE_SEPARATOR, lessonType.name(), venue, startTime.toString(), endTime.toString()
                , day.toString(), getWeek().name(), group);
    }

    public Week getWeek() {
        return week;
    }

    public boolean isClash(Lesson newLesson) {
        return (newLesson.getWeek() == Week.BOTH || getWeek() == Week.BOTH || newLesson.getWeek() == getWeek())
                && getStartTime().isBefore(newLesson.getEndTime()) && getEndTime().isAfter(newLesson.getStartTime())
                && newLesson.getDay() == getDay();
    }
}
