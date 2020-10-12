package mystars.data.course;

public class Course {

    private final String courseCode;
    private final String school;
    private final int indexNumber;
    private final int vacancy;

    public Course(String courseCode, String school, int indexNumber, int vacancy) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
    }
}
