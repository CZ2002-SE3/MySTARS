package mystars.commands.admin;

import mystars.commands.Command;
import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.Ui;

import java.time.LocalDateTime;

public class EditStudentAccessCommand extends Command {
    /**
     * Executes command.
     *
     *
     * @param accessDateTime
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, UserList users, Ui ui, Storage storage) throws MyStarsException {

    }
}
