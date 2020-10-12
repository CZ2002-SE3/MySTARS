package mystars.commands;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.Ui;

public class LogoutCommand extends Command {

    public static final String COMMAND_WORD = "7";

    /**
     * Executes command.
     *
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(UserList users, Ui ui, Storage storage) throws MyStarsException {
        setLoginStatus(false);
        ui.showLogout();
    }
}
