package mystars.commands.admin;

import mystars.data.exception.MyStarsException;
import mystars.data.shared.AccessDateTime;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

/**
 * Edits student access period.
 */
public class EditStudentAccessCommand extends AdminCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "1";

    /**
     * Storage handler.
     */
    private final Storage storage;

    /**
     * Student's access date/time.
     */
    private final AccessDateTime accessDateTime;

    /**
     * Initialises command for execution.
     *
     * @param ui             Ui object.
     * @param storage        Storage handler.
     * @param accessDateTime Student's access date/time.
     */
    public EditStudentAccessCommand(AdminUi ui, Storage storage, AccessDateTime accessDateTime) {
        super(ui);
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
