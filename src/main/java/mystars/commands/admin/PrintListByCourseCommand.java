package mystars.commands.admin;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.ui.AdminUi;

/**
 * Prints list of students in a course.
 */
public class PrintListByCourseCommand extends AdminCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "6";

    /**
     * List of courses.
     */
    private final CourseList courses;

    /**
     * List of users.
     */
    private final UserList users;

    /**
     * Initialises command for execution.
     *
     * @param ui      Ui object.
     * @param courses List of courses.
     * @param users   List of users.
     */
    public PrintListByCourseCommand(AdminUi ui, CourseList courses, UserList users) {
        super(ui);
        this.courses = courses;
        this.users = users;
    }

    /**
     * Asks user for course code and prints list of students in that course.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        String courseCode = ui.getCourseCode();
        courses.checkCourseInList(courseCode);

        ui.showStudentListByCourse(users, courseCode);
    }
}
