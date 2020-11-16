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

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "2";

    /**
     * Storage handler.
     */
    private final Storage storage;

    /**
     * List of users.
     */
    private final UserList users;

    /**
     * Initializes command for execution.
     *
     * @param ui      Ui object.
     * @param storage Storage handler.
     * @param users   List of users.
     */
    public AddStudentCommand(AdminUi ui, Storage storage, UserList users) {
        super(ui);
        this.storage = storage;
        this.users = users;
    }

    /**
     * Adds a new student, displays that student and the list of students, and saves updated list to file.
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
