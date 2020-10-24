package mystars.commands.student;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

/**
 * Adds course for student.
 */
public class AddCourseCommand extends StudentCommand {

    public static final String COMMAND_WORD = "1";

    /**
     * Prompts user for course to add and saves to file.
     *
     * @param users     UserList object.
     * @param ui Ui object.
     * @param storage   Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(UserList users, StudentUi ui, Storage storage) throws MyStarsException {
        ui.showAddCourse();
    }
}
