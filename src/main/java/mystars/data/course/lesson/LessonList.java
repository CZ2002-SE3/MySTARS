package mystars.data.course.lesson;

import java.util.ArrayList;

/**
 * List of lessons.
 */
public class LessonList {

    /**
     * List of lessons.
     */
    private final ArrayList<Lesson> lessons;

    /**
     * Initialises LessonList object.
     */
    public LessonList() {
        lessons = new ArrayList<>();
    }

    /**
     * Returns list of lessons.
     *
     * @return List of lessons.
     */
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Adds lesson to list of lessons.
     *
     * @param newLesson Lesson to add.
     */
    public void addLesson(Lesson newLesson) {
        lessons.add(newLesson);
    }

    /**
     * Checks if lessons clash.
     *
     * @param lesson Lesson to check.
     * @return True if lessons clash, false otherwise.
     */
    public boolean isClash(Lesson lesson) {
        return getLessons().stream().anyMatch(lesson::isClash);
    }
}
