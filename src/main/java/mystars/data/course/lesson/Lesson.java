package mystars.data.course.lesson;

import mystars.parser.Parser;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Lesson object.
 */
public class Lesson {

    /**
     * Week of lesson (Odd/Even/Both).
     */
    private final Week week;

    /**
     * Tutorial/Lecture/Lab Group.
     */
    private final String group;

    /**
     * Tutorial/Lecture/Lab.
     */
    private final LessonType lessonType;

    /**
     * Venue of lesson.
     */
    private final String venue;

    /**
     * Lesson start time.
     */
    private final LocalTime startTime;

    /**
     * Lesson end time.
     */
    private final LocalTime endTime;

    /**
     * Day of lesson.
     */
    private final DayOfWeek day;

    /**
     * Initialise lesson.
     *
     * @param lessonType Tutorial/Lecture/Lab.
     * @param venue      Venue of lesson.
     * @param startTime  Lesson start time.
     * @param endTime    Lesson end time.
     * @param day        Day of lesson.
     * @param week       Week of lesson (Odd/Even/Both).
     * @param group      Tutorial/Lecture/Lab Group.
     */
    public Lesson(LessonType lessonType, String venue, LocalTime startTime, LocalTime endTime, DayOfWeek day, Week week,
                  String group) {
        this.lessonType = lessonType;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.week = week;
        this.group = group;
    }

    /**
     * Returns start time of lesson.
     *
     * @return Start time.
     */
    private LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Returns end time of lesson.
     *
     * @return End time.
     */
    private LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Returns day of lesson.
     *
     * @return Day of week.
     */
    private DayOfWeek getDay() {
        return day;
    }

    /**
     * Returns week (Odd/Even/Both).
     *
     * @return Week.
     */
    private Week getWeek() {
        return week;
    }

    /**
     * Returns lesson formatted for storage.
     *
     * @return Lesson formmated for storage.
     */
    public String getStorageString() {
        return String.join(Parser.TILDE_SEPARATOR, lessonType.name(), venue, getStartTime().toString(),
                getEndTime().toString(), getDay().toString(), getWeek().name(), group);
    }

    /**
     * Returns if lessons clashes.
     *
     * @param newLesson Lesson to check.
     * @return True if lessons clashes, false otherwise.
     */
    public boolean isClash(Lesson newLesson) {
        return (newLesson.getWeek() == Week.BOTH || getWeek() == Week.BOTH || newLesson.getWeek() == getWeek())
                && getStartTime().isBefore(newLesson.getEndTime()) && getEndTime().isAfter(newLesson.getStartTime())
                && newLesson.getDay() == getDay();
    }
}
