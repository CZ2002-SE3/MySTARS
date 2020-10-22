package mystars.commands.student;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

/**
 * Drops course for student.
 */
public class DropCourseCommand extends StudentCommand {

    /**
     * Prompts student for course to drop, and saves changes to file.
     *
     * @param users     UserList object.
     * @param studentUi Ui object.
     * @param storage   Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(UserList users, StudentUi studentUi, Storage storage) throws MyStarsException {

    }
}
