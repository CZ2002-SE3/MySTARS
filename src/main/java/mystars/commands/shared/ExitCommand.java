package mystars.commands.shared;

import mystars.ui.Ui;

/**
 * Exits MySTARS.
 */
public class ExitCommand extends SharedCommand {

    private static final String MESSAGE = "Bye! See you again soon!";

    public ExitCommand(Ui ui) {
        super(ui);
    }

    /**
     * Says goodbye to user and exits MySTARS.
     */
    @Override
    public void execute() {
        setExit();
        ui.showLine();
        ui.showToUser(MESSAGE);
    }
}
