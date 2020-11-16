package mystars.commands.shared;

import mystars.ui.Ui;

/**
 * Exits MySTARS.
 */
public class ExitCommand extends SharedCommand {

    /**
     * Initializes command for execution.
     *
     * @param ui Ui object.
     */
    public ExitCommand(Ui ui) {
        super(ui);
    }

    /**
     * Says goodbye to user and exits MySTARS.
     */
    @Override
    public void execute() {
        setExit();

        ui.showExit();
    }
}
