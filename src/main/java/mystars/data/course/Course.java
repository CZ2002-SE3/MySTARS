package mystars.data.course;

public class Course {

    private String courseCode;
    // Might be wise to change school to enum afterwards
    private String school;
    private String indexNumber;
    private int vacancy;
    private int numOfAUs;

    public Course(String courseCode, String school, String indexNumber, int vacancy, int numOfAUs) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
        this.numOfAUs = numOfAUs;
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

    public int getNumOfAUs() { return numOfAUs; }

    public void setNumOfAUs(int numOfAUs) {
        this.numOfAUs = numOfAUs;
    }
}
