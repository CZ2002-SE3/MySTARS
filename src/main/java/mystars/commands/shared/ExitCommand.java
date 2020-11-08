package mystars.commands.shared;

import mystars.data.shared.AccessDateTime;
import mystars.data.user.UserList;
import mystars.ui.Ui;

/**
 * Exits MySTARS.
 */
public class ExitCommand extends SharedCommand {

    private static final String MESSAGE = "Bye! See you again soon!";

    /**
     * Says goodbye to user and exits MySTARS.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     */
    @Override
    public void execute(AccessDateTime accessDateTime, UserList users, Ui ui) {
        setExit();
        ui.showLine();
        ui.showToUser(MESSAGE);
    }
}
