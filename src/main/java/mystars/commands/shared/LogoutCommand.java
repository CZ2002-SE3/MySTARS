package mystars.commands.shared;

import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.Ui;

import java.time.LocalDateTime;

public class LogoutCommand extends SharedCommand {

    public static final String COMMAND_WORD = "7";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, UserList users, Ui ui)
            throws MyStarsException {
        setLoginStatus(false);
        ui.showLogout();
    }
}
