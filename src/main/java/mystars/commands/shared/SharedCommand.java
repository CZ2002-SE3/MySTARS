package mystars.commands.shared;

import mystars.commands.Command;
import mystars.ui.Ui;

/**
 * Parent shared command class.
 */
public abstract class SharedCommand extends Command {

    /**
     * Invalid login error message.
     */
    static final String INVALID_LOGIN_ERROR = "Invalid username/password!";

    /**
     * Ui object.
     */
    final Ui ui;

    /**
     * Initialises ui object for subclasses.
     *
     * @param ui Ui object.
     */
    SharedCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Checks if exit is called.
     */
    void checkExit() {
        if (ui.askExit()) {
            new ExitCommand(ui).execute();
        }
    }
}
