package mystars.commands;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;
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
        setUser(users.getUser(usernameAndPassword));
        if (!isLogin()) {
            throw new MyStarsException(ERROR_MESSAGE);
        }
        loadUserInfo(getUser(), users);
        ui.showLine();
    }

    public void loadUserInfo(User u, UserList users) {
        for (User user: users.getUsers()) {
            if (u.getUsername().equals(user.getUsername())) {
                u = user;
                break;
            }
        }
    }
}
