package mystars.commands.admin;

import mystars.data.CourseList;
import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

public class CheckVacancyCommand extends AdminCommand {

    public static final String COMMAND_WORD = "4";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param courses        CourseList object.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, CourseList courses, UserList users, AdminUi ui, Storage storage)
            throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        ui.showVacancy(courses, indexNumber);
    }
}
