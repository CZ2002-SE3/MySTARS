package mystars.commands.admin;

import mystars.commands.Command;
import mystars.data.CourseList;
import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

/**
 * Parent admin command class.
 */
public abstract class AdminCommand extends Command {

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param courses        CourseList object.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(LocalDateTime[] accessDateTime, CourseList courses, UserList users, AdminUi ui
            , Storage storage) throws MyStarsException;
}
