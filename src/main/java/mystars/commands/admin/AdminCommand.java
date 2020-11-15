package mystars.commands.admin;

import mystars.commands.Command;
import mystars.ui.AdminUi;

/**
 * Parent admin command class.
 */
abstract class AdminCommand extends Command {

    /**
     * Ui object.
     */
    final AdminUi ui;

    /**
     * Initialises ui object for subclasses.
     *
     * @param ui Ui object.
     */
    AdminCommand(AdminUi ui) {
        this.ui = ui;
    }
}
