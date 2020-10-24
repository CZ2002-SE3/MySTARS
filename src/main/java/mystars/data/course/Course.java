package mystars.data.course;

import mystars.data.LessonList;
import mystars.parser.Parser;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Course {

    private final int initialVacancy;
    private String courseCode;
    // Might be wise to change school to enum afterwards
    private String school;
    private String indexNumber;
    private int vacancy;
    private int numOfAUs;
    private LessonList lessonList;

    public Course(String courseCode, String school, String indexNumber, int vacancy, int numOfAUs
            , LessonList lessonList) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.initialVacancy = vacancy;
        this.vacancy = vacancy;
        this.numOfAUs = numOfAUs;
        this.lessonList = lessonList;
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

    @Override
    public String toString() {
        return "Course Code: " + courseCode + System.lineSeparator() +
                "School: " + school + System.lineSeparator() +
                "Index Number: " + indexNumber + System.lineSeparator() +
                "AU: " + numOfAUs + System.lineSeparator() + System.lineSeparator();
    }

    public int getInitialVacancy() {
        return initialVacancy;
    }

    public boolean isSameCourseCode(Course course) {
        return course.getCourseCode().equals(getCourseCode());
    }

    public String getFormattedString() {
        return courseCode + Parser.TILDE_SEPARATOR + indexNumber;
    }

    public String getStorageString() {
        return String.join(Parser.LINE_SEPARATOR, courseCode, school, indexNumber, Integer.toString(initialVacancy)
                , Integer.toString(numOfAUs), lessonList.getLessons().stream().map(Lesson::getStorageString)
                        .collect(Collectors.joining(Parser.ASTERISK_SEPERATOR)));
    }
}
