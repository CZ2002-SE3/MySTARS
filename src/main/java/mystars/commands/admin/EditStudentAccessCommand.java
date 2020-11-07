package mystars.commands.admin;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

/**
 * Edits student access period.
 */
public class EditStudentAccessCommand extends AdminCommand {

    public static final String COMMAND_WORD = "1";

    /**
     * Prompts admin for new access period and saves to file.
     *
     * @param accessDateTime Access period.
     * @param courseList     CourseList object.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, CourseList courseList, UserList users, AdminUi ui,
                        Storage storage) throws MyStarsException {
        ui.showAccessPeriod(accessDateTime);
        accessDateTime = ui.getNewAccessPeriod();

        storage.saveAccessPeriod(accessDateTime);
        ui.showNewAccessPeriod(accessDateTime);
    }
}
