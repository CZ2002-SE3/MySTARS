package mystars.ui;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.user.Student;
import mystars.parser.Parser;

public class StudentUi extends Ui {

    public StudentUi(Parser parser) {
        super(parser);
    }

    public StudentUi() {

    }

    @Override
    public void showMenu() {
        printNicely("1. Add Course");
        printNicely("2. Drop Course");
        printNicely("3. Check/Print Courses Registered");
        printNicely("4. Check Vacancies Available");
        printNicely("5. Change Index Number of Course");
        printNicely("6. Swop Index Number with Another Student");
        printNicely("7. Logout");
        printNicely("Please select an item:");
    }

    @Override
    public void greetUser() {
        printNicely("Hello student!");
    }

    public void printRegCourses(Student student) {
        printNicely("Here are your registered courses:");
        printNicely(student.getRegisteredCourses().toString());
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

    public void showCourseVacancy(CourseList courseList, String courseCode) {
        courseList.getCourses().stream().filter((course) -> course.getCourseCode().equals(courseCode)).map(Course::getVacancyString).forEach(this::printNicely);
    }
}
