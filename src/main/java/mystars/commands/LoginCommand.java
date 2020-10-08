package mystars.commands;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.Ui;

public class LoginCommand extends Command {

    public boolean isLogin;

    public static final String MESSAGE = "Please enter a username and password:";
    public static final String USERNAME_MESSAGE = "Enter Username: ";
    public static final String PASSWORD_MESSAGE = "Enter Password: ";
    public static final String ERROR_MESSAGE = "Invalid username/password!";

    /**
     * Initialises login status.
     */
    public LoginCommand() {
        isLogin = false;
    }

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
        //ui.showLogin();
        String[] usernameAndPassword = ui.readUsernameAndPassword();
        isLogin = users.isLoginValid(usernameAndPassword);
        if (!isLogin) {
            ui.showError(ERROR_MESSAGE);
        }
    }
}
