package mystars.commands.shared;

import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.ui.Ui;

import java.time.LocalDateTime;

public class LoginCommand extends SharedCommand {

    public static final String USERNAME_MESSAGE = "Enter Username: ";
    public static final String PASSWORD_MESSAGE = "Enter Password: ";
    public static final String ERROR_MESSAGE = "Invalid username/password!";
    public static final String WARNING_MESSAGE = "Please use console to mask password.";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, UserList users, Ui ui)
            throws MyStarsException {
        char[][] usernameAndPassword = ui.readUsernameAndPassword();
        setLoginStatus(users.isLoginValid(usernameAndPassword));
        setUser(users.getUser(usernameAndPassword));

        if (users.getUser(usernameAndPassword) instanceof Student) {
            checkAccessPeriod(accessDateTime, ui);
        }
        if (!isLogin()) {
            ui.showLine();
            throw new MyStarsException(ERROR_MESSAGE);
        }
        ui.showLine();
    }

    private void checkAccessPeriod(LocalDateTime[] accessDateTime, Ui ui) {
        if (accessDateTime[0].isAfter(LocalDateTime.now()) || accessDateTime[1].isBefore(LocalDateTime.now())) {
            ui.showClosedMessage();
            ui.showLine();
            System.exit(1);
        }
    }
}
