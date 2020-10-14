package mystars.data.course;

public class Course {

    private String courseCode;
    private String school;
    private String indexNumber;
    private int vacancy;
    private int numAu;

    public Course(String courseCode, String school, String indexNumber, int vacancy, int numAu) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
        this.numAu = numAu;
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

    public int getNumAu() { return numAu; }

    public void setNumAu(int numAu) {
        this.numAu = numAu;
    }
}
