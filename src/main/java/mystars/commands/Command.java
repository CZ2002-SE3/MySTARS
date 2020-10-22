package mystars.commands;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.User;
import mystars.storage.Storage;
import mystars.ui.Ui;

import java.time.LocalDateTime;

/**
 * Parent Command class.
 */
public abstract class Command {

    public static final String COMMAND_ERROR = "I don't understand what you have entered.";

    private static boolean isExit;
    private static boolean isLogin;
    private static User user;

    /**
     * Initialises command to execute and set exit status to false.
     */
    public Command() {
        isExit = false;
        isLogin = false;
    }

    /**
     * Executes command.
     *
     *
     * @param accessDateTime Access date/time array.
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(LocalDateTime[] accessDateTime, UserList users, Ui ui, Storage storage) throws MyStarsException;

    /**
     * Returns MySTARS's exit status.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return isExit;
    }

    public boolean isLogin() {
        return isLogin;
    }

    void setExit() {
        isExit = true;
    }

    protected void setLoginStatus(boolean isLogin) {
        Command.isLogin = isLogin;
    }

    public User getUser() {
        return user;
    }

    void setUser(User u) {
        Command.user = u;
    }
}
