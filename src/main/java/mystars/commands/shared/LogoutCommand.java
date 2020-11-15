package mystars.commands.shared;

import mystars.ui.Ui;

/**
 * Logs out user.
 */
public class LogoutCommand extends SharedCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "7";

    /**
     * Initialises command for execution.
     *
     * @param ui Ui object.
     */
    public LogoutCommand(Ui ui) {
        super(ui);
    }

    /**
     * Logs out user and asks to exit.
     */
    @Override
    public void execute() {
        setLoginStatus(false);
        ui.showLogout();

        checkExit();
    }
}
