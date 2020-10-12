package mystars.data.course;

public class Course {

    private String courseCode;
    private String school;
    private int indexNumber;
    private int vacancy;

    public Course(String courseCode, String school, int indexNumber, int vacancy) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
    }
}
