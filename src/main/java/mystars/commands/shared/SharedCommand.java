package mystars.commands.shared;

import mystars.commands.Command;
import mystars.data.exception.MyStarsException;
import mystars.data.shared.AccessDateTime;
import mystars.data.user.UserList;
import mystars.ui.Ui;

/**
 * Parent shared command class.
 */
public abstract class SharedCommand extends Command {

    static final String INVALID_LOGIN_ERROR = "Invalid username/password!";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(AccessDateTime accessDateTime, UserList users, Ui ui) throws MyStarsException;

}
