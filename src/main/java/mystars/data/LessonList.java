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

    public void addLesson(Lesson newLesson) {
        //TODO: check clash
        for (Lesson lesson : lessons) {
            //if newLesson.
        }
    }
}
