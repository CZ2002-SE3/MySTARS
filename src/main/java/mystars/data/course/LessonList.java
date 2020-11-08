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

    public void addLesson(Lesson newLesson) {
        lessons.add(newLesson);
    }

    public boolean isClash(Lesson lesson) {
        return getLessons().stream().anyMatch(lesson::isClash);
    }
}
