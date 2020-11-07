package mystars.data.course;

import mystars.data.course.lesson.Lesson;
import mystars.data.exception.MyStarsException;
import mystars.data.mail.EmailSender;
import mystars.data.user.Student;
import mystars.parser.Parser;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Course {

    private final String indexNumber;
    private int initialVacancy;
    private LessonList lessonList;
    private String courseCode;
    private String school;
    private int numOfAUs;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Student> waitlistedStudents;

    public Course(String courseCode, String school, String indexNumber, int initialVacancies, int numOfAUs,
                  LessonList lessonList) {
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

    public String getSchool() {
        return school;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public int getVacancies() {
        return initialVacancy - registeredStudents.size();
    }

    public int getWaitlistedSize() {
        return waitlistedStudents.size();
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

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), "Course Code: " + courseCode, "School: " + school,
                "Index Number: " + indexNumber, "AU: " + numOfAUs, System.lineSeparator());
    }

    public int getInitialVacancy() {
        return initialVacancy;
    }

    public boolean isSameCourseCode(Course course) {
        return course.getCourseCode().equals(getCourseCode());
    }

    public boolean isSameIndexNo(Course course) {
        return course.getIndexNumber().equals(getIndexNumber());
    }

    public String getStorageString() {
        return String.join(Parser.LINE_SEPARATOR, courseCode, school, indexNumber, Integer.toString(initialVacancy),
                Integer.toString(numOfAUs), lessonList.getLessons().stream().map(Lesson::getStorageString)
                        .collect(Collectors.joining(Parser.ASTERISK_SEPARATOR)));
    }

    public boolean isClash(Course courseToAdd) {
        return courseToAdd.lessonList.getLessons().stream()
                .anyMatch(lesson -> lessonList.getLessons().stream().anyMatch(lesson::isClash));
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

    public void dropWaitlistedStudent(Student student) {
        waitlistedStudents.remove(student);
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

    public String getVacancyString() {
        return "Index: " + getIndexNumber() + " Vacancies/Waitlist Size: " + getVacancies() + "/" + getWaitlistedSize();
    }

    public boolean checkWaitlist() {
        boolean isTransfer = false;
        int i = 0;
        while (isThereWaitlistedStudents() && isVacancy()) {
            Student studentToNotify = getWaitlistedStudents().get(i);
            try {
                studentToNotify.dropWaitlistedCourse(this);
                studentToNotify.addCourseToRegistered(this);
            } catch (MyStarsException e) {
                i++;
                continue;
            }

            dropWaitlistedStudent(studentToNotify);
            addRegisteredStudent(studentToNotify);

            sendEmailToStudent(studentToNotify);
            isTransfer = true;
        }
        return isTransfer;
    }

    private void sendEmailToStudent(Student studentToNotify) {
        new EmailSender().sendMail(studentToNotify.getEmail(), getCourseCode(), getIndexNumber(),
                studentToNotify.getName());
    }
}
