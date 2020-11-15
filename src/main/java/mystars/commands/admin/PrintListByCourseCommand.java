package mystars.commands.admin;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.ui.AdminUi;

public class PrintListByCourseCommand extends AdminCommand {

    public static final String COMMAND_WORD = "6";

    private final CourseList courses;
    private final UserList users;

    public PrintListByCourseCommand(AdminUi ui, CourseList courses, UserList users) {
        super(ui);
        this.courses = courses;
        this.users = users;
    }

    /**
     * Executes command.
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
