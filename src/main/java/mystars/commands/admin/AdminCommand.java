package mystars.commands.admin;

import mystars.commands.Command;
import mystars.ui.AdminUi;

/**
 * Parent admin command class.
 */
abstract class AdminCommand extends Command {

    /**
     * Different AUs error message.
     */
    static final String DIFFERENT_AU_ERROR
            = "Existing course code have different number of AUs from given number of AUs!";

    /**
     * Ui object.
     */
    final AdminUi ui;

    /**
     * Initializes Ui object for subclasses.
     *
     * @param ui Ui object.
     */
    AdminCommand(AdminUi ui) {
        this.ui = ui;
    }
}
