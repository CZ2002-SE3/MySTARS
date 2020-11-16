package mystars.ui;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.user.Student;
import mystars.parser.Parser;

/**
 * Student user interface.
 */
public class StudentUi extends Ui {

    /**
     * Menu.
     */
    private static final String MENU = String.join(System.lineSeparator(), "1. Add Course", "2. Drop Course",
            "3. Check/Print Courses Registered", "4. Check Vacancies Available", "5. Change Index Number of Course",
            "6. Swop Index Number with Another Student", "7. Logout", "Please select an item:");

    /**
     * Welcome message.
     */
    private static final String WELCOME_MESSAGE = "Hello Student!";

    /**
     * Initializes Ui with parser.
     *
     * @param parser Parser object.
     */
    public StudentUi(Parser parser) {
        super(parser);
    }

    /**
     * Empty constructor.
     */
    public StudentUi() {

    }

    /**
     * Prints menu.
     */
    @Override
    public void showMenu() {
        showToUser(MENU);
    }

    /**
     * Greets user.
     */
    @Override
    public void greetUser() {
        printNicely(WELCOME_MESSAGE);
    }

    /**
     * Prints all student's courses.
     *
     * @param student Student to show courses.
     */
    public void showAllCourses(Student student) {
        printNicely("Here are your registered courses:");
        printNicely("Total No. of Courses: " + student.getRegisteredCourses().getNoOfCourses());
        printNicely("Total No. of AUs: " + student.getRegisteredCourses().getTotalNoOfAUs());
        showCourseList(student.getRegisteredCourses());
        printNicely();
        printNicely("Here are your waitlisted courses:");
        printNicely("Total No. of Courses: " + student.getWaitlistedCourses().getNoOfCourses());
        printNicely("Total No. of AUs: " + student.getWaitlistedCourses().getTotalNoOfAUs());
        showCourseList(student.getWaitlistedCourses());
    }

    /**
     * Prints added course.
     *
     * @param course Course to add.
     * @param type   Type of course.
     */
    public void showAddedCourse(Course course, String type) {
        printNicely();
        printNicely("Following course " + type + ":");
        showCourse(course);
    }

    /**
     * Prints deleted course.
     *
     * @param course Course that was deleted.
     * @param type   Type of course.
     */
    public void showDroppedCourse(Course course, String type) {
        printNicely();
        printNicely("Following " + type + " course dropped:");
        showCourse(course);
    }

    /**
     * Prints course index change.
     *
     * @param desiredCourse New course.
     * @param currentCourse Old course.
     */
    public void showIndexNoChanged(Course desiredCourse, Course currentCourse) {
        printNicely();
        printNicely("Successfully changed!");
        printNicely("Old course:");
        showCourse(currentCourse);
        printNicely("New course:");
        showCourse(desiredCourse);
    }

    /**
     * Prints course index swap.
     *
     * @param currentCourse Old course.
     * @param peerCourse    New course.
     * @param student       Student.
     * @param peer          Friend of student.
     */
    public void showIndexSwop(Course currentCourse, Course peerCourse, Student student, Student peer) {
        printNicely();
        printNicely("Successfully changed!");
        printNicely("Current course of " + student.getMatricNo() + ":");
        showCourse(peerCourse);
        printNicely();
        printNicely("Current course of " + peer.getMatricNo() + ":");
        showCourse(currentCourse);
    }

    /**
     * Prints course vacancy (All indexes).
     *
     * @param courses    List of courses.
     * @param courseCode Course code to show vacancies.
     */
    public void showCourseVacancy(CourseList courses, String courseCode) {
        printNicely();
        printNicely("Here are the indexes' vacancies/waitlist sizes");
        printNicely(String.format(Course.VACANCY_FORMAT, "Index", "Vacancies", "Waitlist Size"));
        courses.getCourses().stream().filter((course) -> course.getCourseCode().equals(courseCode))
                .map(Course::getVacancyString).forEach(this::printNicely);
    }
}
