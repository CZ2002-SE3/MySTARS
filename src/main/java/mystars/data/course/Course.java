package mystars.data.course;

import mystars.MyStars;
import mystars.data.course.lesson.Lesson;
import mystars.data.course.lesson.LessonList;
import mystars.data.exception.MyStarsException;
import mystars.data.sender.EmailSender;
import mystars.data.sender.Sender;
import mystars.data.user.Student;
import mystars.parser.Parser;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Course object.
 */
public class Course {

    /**
     * Table format for course.
     */
    public static final String FORMAT = "%-15s %-10s %-10s %-5s";

    /**
     * Table format for vacancy.
     */
    public static final String VACANCY_FORMAT = "%-10s %-15s %-20s";

    /**
     * Vacancy error message.
     */
    private static final String VACANCY_ERROR = "More students registered for course than vacancies available!";

    /**
     * Index number.
     */
    private final String indexNumber;

    /**
     * Number of vacancies.
     */
    private int initialVacancies;

    /**
     * List of lessons.
     */
    private LessonList lessonList;

    /**
     * Course code.
     */
    private String courseCode;

    /**
     * School code.
     */
    private String school;

    /**
     * Number of AUs.
     */
    private int numOfAUs;

    /**
     * List of registered students.
     */
    private ArrayList<Student> registeredStudents;

    /**
     * List of waitlisted students.
     */
    private ArrayList<Student> waitlistedStudents;

    /**
     * Initializes course object.
     *
     * @param courseCode       Course code.
     * @param school           School code.
     * @param indexNumber      Index number.
     * @param initialVacancies Number of vacancies.
     * @param numOfAUs         Number of AUs.
     * @param lessonList       List of lessons.
     */
    public Course(String courseCode, String school, String indexNumber, int initialVacancies, int numOfAUs,
                  LessonList lessonList) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexNumber = indexNumber;
        this.initialVacancies = initialVacancies;
        this.numOfAUs = numOfAUs;
        setLessonList(lessonList);
        setRegisteredStudents(new ArrayList<>());
        setWaitlistedStudents(new ArrayList<>());
    }

    /**
     * Returns course code.
     *
     * @return Course code.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Returns index number of course.
     *
     * @return Index number.
     */
    public String getIndexNumber() {
        return indexNumber;
    }

    /**
     * Returns number of available vacancies.
     *
     * @return Number of available vacancies.
     */
    public int getVacancies() {
        return getInitialVacancies() - getRegisteredStudents().size();
    }

    /**
     * Returns number of waitlisted students.
     *
     * @return Number of waitlisted students.
     */
    private int getWaitlistedSize() {
        return getWaitlistedStudents().size();
    }

    /**
     * Returns number of AUs.
     *
     * @return Number of AUs.
     */
    public int getNumOfAUs() {
        return numOfAUs;
    }

    /**
     * Returns total course vacancies.
     *
     * @return Total course vacancies.
     */
    public int getInitialVacancies() {
        return initialVacancies;
    }

    /**
     * Returns list of registered students.
     *
     * @return Number of AUs.
     */
    ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    /**
     * Sets registered students.
     *
     * @param students Registered students to set.
     */
    public void setRegisteredStudents(ArrayList<Student> students) {
        registeredStudents = students;
    }

    /**
     * Returns list of waitlisted students.
     *
     * @return Number of AUs.
     */
    ArrayList<Student> getWaitlistedStudents() {
        return waitlistedStudents;
    }

    /**
     * Sets waitlisted students.
     *
     * @param students Waitlisted students to set.
     */
    public void setWaitlistedStudents(ArrayList<Student> students) {
        waitlistedStudents = students;
    }

    /**
     * Sets list of lessons.
     *
     * @param lessonList List of lessons to set.
     */
    public void setLessonList(LessonList lessonList) {
        this.lessonList = lessonList;
    }

    /**
     * Returns if there is at least 1 vacancy.
     *
     * @return True if there is vacancy, false otherwise.
     */
    public boolean isVacancy() {
        return getVacancies() > 0;
    }

    /**
     * Returns if there is students registered.
     *
     * @return True if there is at least one registered student, false otherwise.
     */
    public boolean isThereRegisteredStudents() {
        return !getRegisteredStudents().isEmpty();
    }

    /**
     * Returns if there is students on waitlist.
     *
     * @return True if there is at least one student on waitlist, false otherwise.
     */
    public boolean isThereWaitlistedStudents() {
        return !getWaitlistedStudents().isEmpty();
    }


    /**
     * Returns if the courses have the same code.
     *
     * @param course Course to compare.
     * @return True if the courses have the same code, false otherwise.
     */
    public boolean isSameCourseCode(Course course) {
        return course.getCourseCode().equals(getCourseCode());
    }

    /**
     * Returns if courses clash.
     *
     * @param courseToAdd Course to check.
     * @return True if the courses clash, false otherwise.
     */
    public boolean isClash(Course courseToAdd) {
        return courseToAdd.lessonList.getLessons().stream()
                .anyMatch(lesson -> lessonList.getLessons().stream().anyMatch(lesson::isClash));
    }

    /**
     * Returns if the courses have the same index.
     *
     * @param course Course to compare.
     * @return True if the courses have the same index, false otherwise.
     */
    boolean isSameIndexNo(Course course) {
        return course.getIndexNumber().equals(getIndexNumber());
    }

    /**
     * Checks if number of AUs between 2 indexes with same course code are the same.
     *
     * @param course Course to check.
     * @return True if same number of AUs, false otherwise.
     */
    public boolean isValidNumOfAUs(Course course) {
        return !isSameCourseCode(course) || getNumOfAUs() == course.getNumOfAUs();
    }

    /**
     * Course formatted for table.
     *
     * @return Course formatted for table.
     */
    @Override
    public String toString() {
        return String.format(FORMAT, getCourseCode(), school, getIndexNumber(), getNumOfAUs());
    }

    /**
     * Returns string formatted for storage in registered.txt.
     *
     * @return String formatted for storage in registered.txt.
     */
    public String getRegisteredFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, getIndexNumber(), getRegisteredStudents().stream()
                .map(Student::getMatricNo).collect(Collectors.joining(Parser.LINE_SEPARATOR)));
    }

    /**
     * Returns string formatted for storage in waitlisted.txt.
     *
     * @return String formatted for storage in waitlisted.txt.
     */
    public String getWaitlistedFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, getIndexNumber(), getWaitlistedStudents().stream()
                .map(Student::getMatricNo).collect(Collectors.joining(Parser.LINE_SEPARATOR)));
    }

    /**
     * Vacancy formatted for table.
     *
     * @return Vacancy formatted for table.
     */
    public String getVacancyString() {
        return String.format(VACANCY_FORMAT, getIndexNumber(), getVacancies(), getWaitlistedSize());
    }

    /**
     * Returns string formatted for storage in courses.txt.
     *
     * @return String formatted for storage in courses.txt.
     */
    public String getStorageString() {
        return String.join(Parser.LINE_SEPARATOR, getCourseCode(), school, getIndexNumber(),
                Integer.toString(getInitialVacancies()), Integer.toString(getNumOfAUs()), lessonList.getLessons()
                        .stream().map(Lesson::getStorageString).collect(Collectors.joining(Parser.ASTERISK_SEPARATOR)));
    }

    /**
     * Adds student to registered list.
     *
     * @param student Student to add.
     */
    public void addRegisteredStudent(Student student) {
        getRegisteredStudents().add(student);
    }

    /**
     * Drops student from registered list.
     *
     * @param student Student to drop.
     */
    public void dropRegisteredStudent(Student student) {
        getRegisteredStudents().remove(student);
    }

    /**
     * Adds student to waitlisted list.
     *
     * @param student Student to add.
     */

    public void addWaitlistedStudent(Student student) {
        getWaitlistedStudents().add(student);
    }

    /**
     * Drops student from waitlisted list.
     *
     * @param student Student to drop.
     */
    public void dropWaitlistedStudent(Student student) {
        getWaitlistedStudents().remove(student);
    }


    /**
     * Checks if there is enough vacancies for students.
     *
     * @param vacancy Vacancy to check.
     * @throws MyStarsException If there is not enough vacancies.
     */
    public void checkEnoughVacancies(int vacancy) throws MyStarsException {
        if (getRegisteredStudents().size() > vacancy) {
            throw new MyStarsException(VACANCY_ERROR);
        }
    }

    /**
     * Returns if there is waitlisted student to transfer to registered list.
     * True also means notification is sent.
     *
     * @return If there is waitlisted student to transfer to registered list.
     * @throws MyStarsException If notification fails to send.
     */
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

            // This could be called in a loop iterating through an ArrayList of Senders.
            // As such, we can loop through this statement to send via other methods.
            sendToStudent(studentToNotify, new EmailSender(studentToNotify.getEmail()));

            i--;
            isTransfer = true;
        }
        return isTransfer;
    }

    /**
     * Checks if two course are identical in terms of index.
     *
     * @param obj Course to check.
     * @return True if courses have the same index.
     */
    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Course;

        return getIndexNumber().equals(((Course) obj).getIndexNumber());
    }

    /**
     * Copies course details.
     *
     * @param newCourse Course to copy from.
     */
    void copyCourseDetails(Course newCourse) {
        courseCode = newCourse.getCourseCode();
        school = newCourse.school;
        initialVacancies = newCourse.getInitialVacancies();
        numOfAUs = newCourse.getNumOfAUs();
        setLessonList(newCourse.lessonList);
    }

    /**
     * Send notification to student.
     *
     * @param studentToNotify Student to notify.
     * @param sender          Type of sender.
     * @throws MyStarsException If there is problem sending.
     */
    private void sendToStudent(Student studentToNotify, Sender sender) throws MyStarsException {
        sender.send(getCourseCode(), getIndexNumber(), studentToNotify.getName());
    }
}
