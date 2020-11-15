package mystars.data.course;

import mystars.MyStars;
import mystars.data.course.lesson.Lesson;
import mystars.data.exception.MyStarsException;
import mystars.data.sender.EmailSender;
import mystars.data.sender.Sender;
import mystars.data.user.Student;
import mystars.parser.Parser;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Course {

    public static final String FORMAT = "%-15s %-10s %-10s %-5s";

    private static final String VACANCY_ERROR = "More students registered for course than vacancies available!";
    private final String indexNumber;
    private int initialVacancies;
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
        this.initialVacancies = initialVacancies;
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
        return initialVacancies - registeredStudents.size();
    }

    public int getWaitlistedSize() {
        return waitlistedStudents.size();
    }

    public int getNumOfAUs() {
        return numOfAUs;
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public ArrayList<Student> getWaitlistedStudents() {
        return waitlistedStudents;
    }

    public int getInitialVacancies() {
        return initialVacancies;
    }

    public void setLessonList(LessonList lessonList) {
        this.lessonList = lessonList;
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

    public boolean isSameCourseCode(Course course) {
        return course.getCourseCode().equals(getCourseCode());
    }

    public boolean isSameIndexNo(Course course) {
        return course.getIndexNumber().equals(getIndexNumber());
    }

    public boolean isClash(Course courseToAdd) {
        return courseToAdd.lessonList.getLessons().stream()
                .anyMatch(lesson -> lessonList.getLessons().stream().anyMatch(lesson::isClash));
    }

    @Override
    public String toString() {
        return String.format(FORMAT, courseCode, school, indexNumber, numOfAUs);
    }

    public String getRegisteredFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, indexNumber, registeredStudents.stream().map(Student::getMatricNo)
                .collect(Collectors.joining(Parser.LINE_SEPARATOR)));
    }

    public String getWaitlistedFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, indexNumber, waitlistedStudents.stream().map(Student::getMatricNo)
                .collect(Collectors.joining(Parser.LINE_SEPARATOR)));
    }

    public String getVacancyString() {
        return String.format("%-10s %-15s %-20s", getIndexNumber(), getVacancies(), getWaitlistedSize());
    }

    public String getStorageString() {
        return String.join(Parser.LINE_SEPARATOR, courseCode, school, indexNumber, Integer.toString(initialVacancies),
                Integer.toString(numOfAUs), lessonList.getLessons().stream().map(Lesson::getStorageString)
                        .collect(Collectors.joining(Parser.ASTERISK_SEPARATOR)));
    }

    public void addRegisteredStudents(ArrayList<Student> students) {
        registeredStudents = students;
    }

    public void addRegisteredStudent(Student student) {
        registeredStudents.add(student);
    }

    public void dropRegisteredStudent(Student student) {
        registeredStudents.remove(student);
    }

    public void addWaitlistedStudents(ArrayList<Student> students) {
        waitlistedStudents = students;
    }

    public void addWaitlistedStudent(Student student) {
        waitlistedStudents.add(student);
    }

    public void dropWaitlistedStudent(Student student) {
        waitlistedStudents.remove(student);
    }

    public void checkEnoughVacancies(int vacancy) throws MyStarsException {
        if (getRegisteredStudents().size() > vacancy) {
            throw new MyStarsException(VACANCY_ERROR);
        }
    }

    public boolean checkWaitlist() throws MyStarsException {
        boolean isTransfer = false;
        for (int i = 0; isThereWaitlistedStudents() && isVacancy(); i++) {
            Student studentToNotify = getWaitlistedStudents().get(i);
            try {
                studentToNotify.dropWaitlistedCourse(this);
                studentToNotify.addCourseToRegistered(this);
            } catch (MyStarsException e) {
                MyStars.logger.log(Level.WARNING, e.getMessage());
                continue;
            }
            dropWaitlistedStudent(studentToNotify);
            addRegisteredStudent(studentToNotify);

            sendToStudent(studentToNotify, new EmailSender(studentToNotify.getEmail()));
            i--;
            isTransfer = true;
        }
        return isTransfer;
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Course;

        return indexNumber.equals(((Course) obj).getIndexNumber());
    }

    public void copyCourseDetails(Course newCourse) {
        courseCode = newCourse.courseCode;
        school = newCourse.getSchool();
        initialVacancies = newCourse.getInitialVacancies();
        numOfAUs = newCourse.getNumOfAUs();
        lessonList = newCourse.lessonList;
    }

    private void sendToStudent(Student studentToNotify, Sender sender) throws MyStarsException {
        sender.send(getCourseCode(), getIndexNumber(), studentToNotify.getName());
    }
}
