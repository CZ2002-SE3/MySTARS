package mystars.commands;

import mystars.data.UserList;
import mystars.storage.Storage;
import mystars.ui.Ui;

/**
 * Exits MySTARS.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "Y";
    public static final String MESSAGE = "Bye! See you again soon!";

    /**
     * Says goodbye to user and exits MySTARS.
     *
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(UserList users, Ui ui, Storage storage) {
        setExit();
        ui.showExit(MESSAGE);
    }
}
