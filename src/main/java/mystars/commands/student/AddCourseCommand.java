package mystars.commands.student;

import mystars.commands.Command;
import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

import java.time.LocalDateTime;

public class AddCourseCommand extends StudentCommand {

    public static final String COMMAND_WORD = "1";

    /**
     * Executes command.
     *
     * @param users     UserList object.
     * @param studentUi Ui object.
     * @param storage   Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(UserList users, StudentUi studentUi, Storage storage) throws MyStarsException {
        studentUi.showAddCourse();
    }
}
