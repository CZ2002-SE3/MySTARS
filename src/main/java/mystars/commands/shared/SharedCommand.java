package mystars.commands.shared;

import mystars.commands.Command;
import mystars.ui.Ui;

/**
 * Parent shared command class.
 */
public abstract class SharedCommand extends Command {

    static final String INVALID_LOGIN_ERROR = "Invalid username/password!";

    final Ui ui;

    SharedCommand(Ui ui) {
        this.ui = ui;
    }

    void checkExit(Ui ui) {
        if (ui.askExit()) {
            new ExitCommand(ui).execute();
        }
    }
}
