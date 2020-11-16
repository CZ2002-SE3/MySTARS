package mystars.commands.shared;

import mystars.data.exception.MyStarsException;
import mystars.data.shared.AccessDateTime;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.ui.Ui;

/**
 * Checks login of user.
 */
public class LoginCommand extends SharedCommand {

    /**
     * List of users.
     */
    private final UserList users;

    /**
     * Student's access date/time.
     */
    private final AccessDateTime accessDateTime;

    /**
     * Initializes command for execution.
     *
     * @param ui             Ui object.
     * @param users          List of users.
     * @param accessDateTime Student's access date/time.
     */
    public LoginCommand(Ui ui, UserList users, AccessDateTime accessDateTime) {
        super(ui);
        this.users = users;
        this.accessDateTime = accessDateTime;
    }

    /**
     * Prompts user for login info and checks validity.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute()
            throws MyStarsException {
        char[][] usernameAndPassword = ui.readUsernameAndPassword();
        setLoginStatus(users.isLoginValid(usernameAndPassword));
        setUser(users.getUser(usernameAndPassword));

        if (!isLogin()) {
            ui.showLine();
            throw new MyStarsException(INVALID_LOGIN_ERROR);
        }

        if (users.getUser(usernameAndPassword) instanceof Student && !accessDateTime.isAccessPeriod()) {
            ui.showClosed();

            checkExit();
            setLoginStatus(false);
        }
    }
}
