package mystars.commands;

import mystars.data.user.User;

/**
 * Parent command class.
 */
public abstract class Command {
    public static final String COMMAND_ERROR = "I don't understand what you have entered.";

    private static boolean isExit = false;
    private static boolean isLogin = false;
    private static User user;

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

    void setUser(User user) {
        Command.user = user;
    }
}
