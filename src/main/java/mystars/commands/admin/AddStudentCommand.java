package mystars.commands.admin;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

/**
 * Adds new student.
 */
public class AddStudentCommand extends AdminCommand {

    public static final String COMMAND_WORD = "2";

    /**
     * Adds a new student, and save to file.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, UserList users, AdminUi ui, Storage storage)
            throws MyStarsException {
        Student newStudent = ui.getNewStudentFromUser();
        users.addUser(newStudent);

        storage.saveStudent(newStudent);
        ui.showAddedStudent(newStudent);
    }
}
