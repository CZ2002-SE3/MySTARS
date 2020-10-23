package mystars.commands.admin;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

public class PrintListByIndexCommand extends AdminCommand {

    public static final String COMMAND_WORD = "5";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, UserList users, AdminUi ui, Storage storage) throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        ui.showStudentListByIndex(users, indexNumber);
    }
}
