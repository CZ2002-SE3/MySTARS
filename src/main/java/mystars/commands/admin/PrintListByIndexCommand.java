package mystars.commands.admin;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.ui.AdminUi;

/**
 * Prints list of students in an index.
 */
public class PrintListByIndexCommand extends AdminCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "5";

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
    public PrintListByIndexCommand(AdminUi ui, CourseList courses, UserList users) {
        super(ui);
        this.courses = courses;
        this.users = users;
    }

    /**
     * Asks user for course index and prints list of students in that index.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        courses.checkIndexNoInList(indexNumber);

        ui.showStudentListByIndex(users, indexNumber);
    }
}
