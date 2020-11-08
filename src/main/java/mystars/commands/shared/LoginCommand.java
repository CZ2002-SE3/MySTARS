package mystars.commands.shared;

import mystars.data.exception.MyStarsException;
import mystars.data.shared.AccessDateTime;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.ui.Ui;

import java.time.LocalDateTime;

public class LoginCommand extends SharedCommand {

    private static final String MESSAGE = "MyStars is closed for students...";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(AccessDateTime accessDateTime, UserList users, Ui ui)
            throws MyStarsException {
        char[][] usernameAndPassword = ui.readUsernameAndPassword();
        setLoginStatus(users.isLoginValid(usernameAndPassword));
        setUser(users.getUser(usernameAndPassword));

        if (users.getUser(usernameAndPassword) instanceof Student) {
            checkAccessPeriod(accessDateTime.getAccessDateTime(), ui);
        }
        if (!isLogin()) {
            ui.showLine();
            throw new MyStarsException(INVALID_LOGIN_ERROR);
        }
        ui.showLine();
    }

    private void checkAccessPeriod(LocalDateTime[] accessDateTime, Ui ui) {
        if (accessDateTime[0].isAfter(LocalDateTime.now()) || accessDateTime[1].isBefore(LocalDateTime.now())) {
            ui.showToUser(MESSAGE);
            ui.showLine();
            System.exit(1);
        }
    }
}
