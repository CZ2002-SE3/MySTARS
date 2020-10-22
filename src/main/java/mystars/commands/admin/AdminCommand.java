package mystars.commands.admin;

import mystars.commands.Command;
import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.AdminUi;
import mystars.ui.Ui;

import java.time.LocalDateTime;

public abstract class AdminCommand extends Command {
    /**
     * Executes command.
     *
     * @param accessDateTime Access date/time array.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(LocalDateTime[] accessDateTime, UserList users, AdminUi ui, Storage storage) throws MyStarsException;
}
