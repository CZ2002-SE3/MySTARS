package mystars.commands.student;

import mystars.commands.Command;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

public abstract class StudentCommand extends Command {

    /**
     * Executes command.
     *
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(UserList users, StudentUi ui, Storage storage) throws MyStarsException;
}
