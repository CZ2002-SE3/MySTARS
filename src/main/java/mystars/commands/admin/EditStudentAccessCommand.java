package mystars.commands.admin;

import mystars.data.exception.MyStarsException;
import mystars.data.shared.AccessDateTime;
import mystars.storage.Storage;
import mystars.ui.AdminUi;
import mystars.ui.Ui;

/**
 * Edits student access period.
 */
public class EditStudentAccessCommand extends AdminCommand {

    public static final String COMMAND_WORD = "1";

    private final Storage storage;
    private final AccessDateTime accessDateTime;

    public EditStudentAccessCommand(Ui ui, Storage storage, AccessDateTime accessDateTime) {
        super((AdminUi) ui);
        this.storage = storage;
        this.accessDateTime = accessDateTime;
    }

    /**
     * Prompts admin for new access period and saves to file.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        ui.showAccessPeriod(accessDateTime.getAccessDateTime());
        accessDateTime.setAccessDateTime(ui.getNewAccessPeriod());

        storage.saveAccessPeriod(accessDateTime.getAccessDateTime());
        ui.showNewAccessPeriod(accessDateTime.getAccessDateTime());
    }
}
