package mystars.commands.shared;

import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.ui.Ui;

import java.time.LocalDateTime;

public class LogoutCommand extends SharedCommand {

    public static final String COMMAND_WORD = "7";
    private static final String MESSAGE = "You have successfully logged out!";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, UserList users, Ui ui) {
        setLoginStatus(false);
        ui.showToUser(MESSAGE);
    }
}
