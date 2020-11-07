package mystars.data.course;

import mystars.data.course.lesson.Lesson;

import java.util.ArrayList;

public class LessonList {
    private final ArrayList<Lesson> lessons;

    public LessonList() {
        lessons = new ArrayList<>();
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public boolean tryAddLesson(Lesson newLesson) {
        boolean isClash = getLessons().stream().anyMatch(newLesson::isClash);

        if (isClash) {
            return false;
        }

        lessons.add(newLesson);
        return true;
    }
}
