package mystars.ui;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.user.Student;
import mystars.parser.Parser;

import java.util.Arrays;

public class StudentUi extends Ui {

    private static final String MENU = String.join(System.lineSeparator(), "1. Add Course", "2. Drop Course"
            , "3. Check/Print Courses Registered", "4. Check Vacancies Available", "5. Change Index Number of Course"
            , "6. Swop Index Number with Another Student", "7. Logout", "Please select an item:");

    public StudentUi(Parser parser) {
        super(parser);
    }

    public StudentUi() {

    }

    @Override
    public void showMenu() {
        showToUser(MENU);
    }

    @Override
    public void greetUser() {
        printNicely("Hello student!");
    }

    public void printAllCourses(Student student) {
        printNicely("Here are your registered courses:");
        printNicely(student.getRegisteredCourses().toString());
        printNicely("Here are your waitlisted courses:");
        printNicely(student.getWaitlistedCourses().toString());
    }

    public void showCourseRegistered(Course course) {
        printNicely("Course Registered!");
        showCourse(course);
    }

    public void showRegisteredCourseDropped(Course course) {
        printNicely("Registered Course Dropped!");
        showCourse(course);
    }

    public void showWaitlistedCourseDropped(Course course) {
        printNicely("Waitlisted Course Dropped!");
        showCourse(course);
    }

    public void showCourseWaitlisted(Course course) {
        printNicely("Course Waitlisted!");
        showCourse(course);
    }

    public void showIndexNoChanged(Course desiredCourse, Course currentCourse) {
        printNicely("Index No changed from: " + currentCourse.getIndexNumber() + " to " +
                desiredCourse.getIndexNumber());
        showCourse(desiredCourse);
    }

    public void showIndexSwop(Course currentCourse, Course peerCourse, Student student, Student peer) {
        printNicely(student.getMatricNo() + "-Index No changed from: " + currentCourse.getIndexNumber() + " to " +
                peerCourse.getIndexNumber());
        printNicely(peer.getMatricNo() + "-Index No changed from: " + peerCourse.getIndexNumber() + " to " +
                currentCourse.getIndexNumber());
    }

    public void showCourseVacancy(CourseList courseList, String courseCode) {
        courseList.getCourses().stream().filter((course) -> course.getCourseCode().equals(courseCode)).map(Course::getVacancyString).forEach(this::printNicely);
    }
}
