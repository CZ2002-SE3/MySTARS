package mystars.commands.admin;

import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

/**
 * Adds new student.
 */
public class AddStudentCommand extends AdminCommand {

    public static final String COMMAND_WORD = "2";

    private final Storage storage;
    private final UserList users;

    public AddStudentCommand(AdminUi ui, Storage storage, UserList users) {
        super(ui);
        this.storage = storage;
        this.users = users;
    }

    /**
     * Adds a new student, and save to file.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        Student newStudent = ui.getNewStudentFromUser(users);
        users.addStudent(newStudent);

        storage.saveStudent(newStudent);
        ui.showAddedStudent(newStudent);
        ui.showStudentList(users);
    }
}
