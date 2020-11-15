package mystars.ui;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.user.Student;
import mystars.parser.Parser;

public class StudentUi extends Ui {

    private static final String MENU = String.join(System.lineSeparator(), "1. Add Course", "2. Drop Course",
            "3. Check/Print Courses Registered", "4. Check Vacancies Available", "5. Change Index Number of Course",
            "6. Swop Index Number with Another Student", "7. Logout", "Please select an item:");

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

    public void showAllCourses(Student student) {
        printNicely("Here are your registered courses:");
        printNicely("Total No. of Courses: " + student.getRegisteredCourses().getNoOfCourses());
        printNicely("Total No. of AUs: " + student.getRegisteredCourses().getTotalNoOfAUs());
        //printNicely(student.getRegisteredCourses().toString());
        showCourseList(student.getRegisteredCourses());
        printNicely();
        printNicely("Here are your waitlisted courses:");
        printNicely("Total No. of Courses: " + student.getWaitlistedCourses().getNoOfCourses());
        printNicely("Total No. of AUs: " + student.getWaitlistedCourses().getTotalNoOfAUs());
        showCourseList(student.getWaitlistedCourses());
        //printNicely(student.getWaitlistedCourses().toString());
    }

    public void showCourseRegistered(Course course) {
        printNicely("Following course registered:");
        showCourse(course);
    }

    public void showDroppedCourse(Course course, String type) {
        printNicely("Following " + type + " course dropped:");
        showCourse(course);
    }

    public void showCourseWaitlisted(Course course) {
        printNicely("Following course waitlisted!");
        showCourse(course);
    }

    public void showIndexNoChanged(Course desiredCourse, Course currentCourse) {
        //printNicely(String.format("%-10s %-15s %-20s", "Old Index No.", "Vacancies", "Waitlist Size"));
        printNicely("Successfully changed!");
        printNicely("Old course:");
        showCourse(currentCourse);
        printNicely("New course:");
        showCourse(desiredCourse);
        /*printNicely("Index No changed from: " + currentCourse.getIndexNumber() + " to "
                + desiredCourse.getIndexNumber());
        showCourse(desiredCourse);*/
    }

    public void showIndexSwop(Course currentCourse, Course peerCourse, Student student, Student peer) {
        printNicely("Successfully changed!");
        printNicely("Current course of " + student.getMatricNo() + ":");
        showCourse(peerCourse);
        printNicely();
        printNicely("Current course of " + peer.getMatricNo() + ":");
        showCourse(currentCourse);
        /*printNicely(student.getMatricNo() + " - Index No changed from: " + currentCourse.getIndexNumber() + " to "
                + peerCourse.getIndexNumber());
        printNicely(peer.getMatricNo() + " - Index No changed from: " + peerCourse.getIndexNumber() + " to "
                + currentCourse.getIndexNumber());*/
    }

    public void showCourseVacancy(CourseList courses, String courseCode) {
        printNicely(String.format(Course.VACANCY_FORMAT, "Index", "Vacancies", "Waitlist Size"));
        courses.getCourses().stream().filter((course) -> course.getCourseCode().equals(courseCode))
                .map(Course::getVacancyString).forEach(this::printNicely);
    }
}
