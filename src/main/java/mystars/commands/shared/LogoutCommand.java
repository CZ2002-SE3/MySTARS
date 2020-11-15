package mystars.commands.shared;

import mystars.ui.Ui;

public class LogoutCommand extends SharedCommand {

    public static final String COMMAND_WORD = "7";
    private static final String MESSAGE = "You have successfully logged out!";

    public LogoutCommand(Ui ui) {
        super(ui);
    }

    /**
     * Executes command.
     */
    @Override
    public void execute() {
        setLoginStatus(false);
        ui.showToUser(MESSAGE);

        checkExit(ui);
    }
}
