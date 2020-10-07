package mystars.commands;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.Ui;

/**
 * Parent Command class.
 */
public abstract class Command {

    public static final String MESSAGE = "Enter bye";
    public static final String COMMAND_ERROR = "I don't understand what you have entered.";

    boolean isExit;

    /**
     * Initialises command to execute and set exit status to false.
     */
    public Command() {
        isExit = false;
    }

    /**
     * Executes command.
     *
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(UserList users, Ui ui, Storage storage) throws MyStarsException;

    /**
     * Returns MySTARS's exit status.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return isExit;
    }
}
