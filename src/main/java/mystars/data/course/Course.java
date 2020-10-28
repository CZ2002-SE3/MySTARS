package mystars.data.course;

import mystars.data.course.lesson.Lesson;
import mystars.data.user.Student;
import mystars.parser.Parser;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Course {

    private int initialVacancy;
    private LessonList lessonList;
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
        registeredStudents = new ArrayList<>();
        waitlistedStudents = new ArrayList<>();
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

    public boolean isVacancy() {
        return getVacancies() > 0;
    }

    public boolean isThereRegisteredStudents() {
        return !getRegisteredStudents().isEmpty();
    }

    public boolean isThereWaitlistedStudents() {
        return !getWaitlistedStudents().isEmpty();
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

    public void addRegisteredStudent(Student student) {
        registeredStudents.add(student);
    }

    public void dropRegisteredStudent(Student student) {
        registeredStudents.remove(student);
    }

    public void addWaitlistedStudent(Student student) {
        waitlistedStudents.add(student);
    }

    public void copyCourseDetails(Course newCourse) {
        courseCode = newCourse.courseCode;
        school = newCourse.getSchool();
        initialVacancy = newCourse.getInitialVacancy();
        numOfAUs = newCourse.getNumOfAUs();
        lessonList = newCourse.lessonList;
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Course;

        return indexNumber.equals(((Course) obj).getIndexNumber());
    }
}
