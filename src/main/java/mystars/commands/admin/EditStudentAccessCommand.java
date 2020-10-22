package mystars.commands.admin;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

public class EditStudentAccessCommand extends AdminCommand {

    public static final String COMMAND_WORD = "1";

    /**
     * Executes command.
     *
     * @param accessDateTime Access date/time array.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, UserList users, AdminUi ui, Storage storage) throws MyStarsException {

    }
}
