package mystars.commands.shared;

import mystars.commands.Command;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.Ui;

import java.time.LocalDateTime;

/**
 * Parent shared command class.
 */
public abstract class SharedCommand extends Command {

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(LocalDateTime[] accessDateTime, UserList users, Ui ui, Storage storage)
            throws MyStarsException;
}
