package mystars.data.course.lesson;

import mystars.parser.Parser;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson {
    private final Week week;
    private final String group;
    private final LessonType lessonType;
    private final String venue;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final DayOfWeek day;

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public DayOfWeek getDay() {
        return day;
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
