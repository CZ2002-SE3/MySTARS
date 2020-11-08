package mystars.commands.shared;

import mystars.data.shared.AccessDateTime;
import mystars.data.user.UserList;
import mystars.ui.Ui;

public class LogoutCommand extends SharedCommand {

    public static final String COMMAND_WORD = "7";
    private static final String MESSAGE = "You have successfully logged out!";

    /**
     * Executes command.
     *  @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     */
    @Override
    public void execute(AccessDateTime accessDateTime, UserList users, Ui ui) {
        setLoginStatus(false);
        ui.showToUser(MESSAGE);
    }
}
