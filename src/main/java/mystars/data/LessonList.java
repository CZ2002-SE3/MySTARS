package mystars.data;

import mystars.data.course.Lesson;

import java.util.ArrayList;

public class LessonList {
    private final ArrayList<Lesson> lessons;

    public LessonList() {
        lessons = new ArrayList<>();
    }

    public LessonList(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public boolean addLesson(Lesson newLesson) {
        boolean isClash = getLessons().stream().anyMatch(newLesson::isClash);
        if (isClash) {
            return false;
        }
        lessons.add(newLesson);
        return true;
    }
}
