package mystars.commands;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.Ui;

public class LoginCommand extends Command {

    public static final String USERNAME_MESSAGE = "Enter Username: ";
    public static final String PASSWORD_MESSAGE = "Enter Password: ";
    public static final String ERROR_MESSAGE = "Invalid username/password!";
    public static final String WARNING_MESSAGE = "Please use console to mask password.";

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
        char[][] usernameAndPassword = ui.readUsernameAndPassword(USERNAME_MESSAGE, PASSWORD_MESSAGE);
        setLoginStatus(users.isLoginValid(usernameAndPassword));
        if (!isLogin()) {
            throw new MyStarsException(ERROR_MESSAGE);
        }
    }
}
