package mystars.data.course;

import mystars.data.course.lesson.Lesson;
import mystars.data.user.Student;
import mystars.parser.Parser;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Course {

    private final int initialVacancy;
    private final LessonList lessonList;
    private String courseCode;
    // Might be wise to change school to enum afterwards
    private String school;
    private String indexNumber;
    private int numOfAUs;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Student> waitlistedStudents;

    public Course(String courseCode, String school, String indexNumber, int initialVacancies, int numOfAUs
            , LessonList lessonList) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.initialVacancy = initialVacancies;
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

    public int getVacancies() {
        return initialVacancy - registeredStudents.size();
    }

    public boolean isThereVacancy() {
        return getVacancies() > 0;
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

    public boolean isClash(Course courseToAdd) {
        return courseToAdd.lessonList.getLessons().stream()
                .anyMatch(lesson -> lessonList.getLessons().stream().anyMatch(lesson::isClash));
        /*for (Lesson lesson : lessonList.getLessons()) {
            for (Lesson lessonToAdd : courseToAdd.lessonList.getLessons()) {
                if (lesson.isClash(lessonToAdd)) {
                    return true;
                }
            }
        }
        return false;*/
    }

    public String getRegisteredFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, indexNumber, registeredStudents.stream().map(Student::getMatricNo)
                .collect(Collectors.joining(Parser.LINE_SEPARATOR)));
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public ArrayList<Student> getWaitlistedStudents() {
        return waitlistedStudents;
    }

    public void addRegisteredStudents(ArrayList<Student> students) {
        registeredStudents = students;
    }

    public void addWaitlistedStudents(ArrayList<Student> students) {
        waitlistedStudents = students;
    }

    public String getWaitlistedFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, indexNumber, waitlistedStudents.stream().map(Student::getMatricNo)
                .collect(Collectors.joining(Parser.LINE_SEPARATOR)));
    }
}
