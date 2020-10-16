package mystars.data.course;

public class Lesson {
    private LessonType lessonType;
    private String venue;
    private String time;
    private Day day;
    private String group;

    public Lesson(LessonType lessonType, String venue, String time, Day day, String group) {
        this.lessonType = lessonType;
        this.venue = venue;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
