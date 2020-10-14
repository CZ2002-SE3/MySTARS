package mystars.data.course;

public class Course {

    private String courseCode;
    private String school;
    private String indexNumber;
    private int vacancy;

    public Course(String courseCode, String school, String indexNumber, int vacancy) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
    }

    public String getCourseCode() { return courseCode; }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSchool() { return school; }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIndexNumber() { return indexNumber; }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getVacancy() { return vacancy; }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }
}
