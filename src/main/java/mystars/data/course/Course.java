package mystars.data.course;

import java.util.ArrayList;

public class Course {

    private final int initialVacancy;
    private String courseCode;
    // Might be wise to change school to enum afterwards
    private String school;
    private String indexNumber;
    private int vacancy;
    private int numOfAUs;
    private ArrayList<Lesson> lessons;

    public Course(String courseCode, String school, String indexNumber, int vacancy, int numOfAUs, ArrayList<Lesson> lessons) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.initialVacancy = vacancy;
        this.vacancy = vacancy;
        this.numOfAUs = numOfAUs;
        this.lessons = lessons;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public int getNumOfAUs() {
        return numOfAUs;
    }

    public void setNumOfAUs(int numOfAUs) {
        this.numOfAUs = numOfAUs;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\n" +
                "School: " + school + "\n" +
                "Index Number: " + indexNumber + "\n" +
                "AU: " + numOfAUs + "\n" + "\n" ;
    }

    public int getInitialVacancy() {
        return initialVacancy;
    }

    public boolean isSameCourseCode(Course course) {
        return course.getCourseCode().equals(getCourseCode());
    }
}
