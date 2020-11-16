package mystars.commands;

import mystars.data.exception.MyStarsException;
import mystars.data.user.User;

/**
 * Parent command class.
 */
public abstract class Command {


    /**
     * Command error message.
     */
    public static final String COMMAND_ERROR = "I don't understand what you have entered.";

    /**
     * Exit status.
     */
    private static boolean isExit = false;

    /**
     * Login status.
     */
    private static boolean isLogin = false;

    /**
     * Logged in user.
     */
    private static User user;

    /**
     * Returns MySTARS's exit status.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets MySTARS's exit status to true.
     */
    protected void setExit() {
        isExit = true;
    }

    /**
     * Returns MySTARS's login status.
     *
     * @return Login status.
     */
    public boolean isLogin() {
        return isLogin;
    }

    /**
     * Sets MySTARS's login status.
     *
     * @param isLogin Login status to set.
     */
    protected void setLoginStatus(boolean isLogin) {
        Command.isLogin = isLogin;
    }

    /**
     * Returns logged in user.
     *
     * @return User.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets logged in user.
     *
     * @param user User to set.
     */
    protected void setUser(User user) {
        Command.user = user;
    }

    /**
     * Executes command.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute() throws MyStarsException;
}
