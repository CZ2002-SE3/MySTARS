package mystars.commands.admin;

import mystars.commands.Command;
import mystars.ui.AdminUi;

/**
 * Parent admin command class.
 */
public abstract class AdminCommand extends Command {

    final AdminUi ui;

    AdminCommand(AdminUi ui) {
        this.ui = ui;
    }
}
