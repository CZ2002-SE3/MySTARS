package mystars.commands.shared;

import mystars.data.exception.MyStarsException;
import mystars.data.shared.AccessDateTime;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.ui.Ui;

public class LoginCommand extends SharedCommand {

    private static final String MESSAGE = "MyStars is closed for students...";

    private final UserList users;
    private final AccessDateTime accessDateTime;

    public LoginCommand(Ui ui, UserList users, AccessDateTime accessDateTime) {
        super(ui);
        this.users = users;
        this.accessDateTime = accessDateTime;
    }

    /**
     * Executes command.
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
            ui.showLine();
            ui.showToUser(MESSAGE);
            checkExit(ui);
            setLoginStatus(false);
        }
    }
}
