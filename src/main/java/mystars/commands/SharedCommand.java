package mystars.commands;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.User;
import mystars.storage.Storage;
import mystars.ui.Ui;

import java.time.LocalDateTime;

/**
 * Parent Command class.
 */
public abstract class SharedCommand extends Command {

    /**
     * Executes command.
     *
     *
     * @param accessDateTime Access date/time array.
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(LocalDateTime[] accessDateTime, UserList users, Ui ui, Storage storage) throws MyStarsException;
}
