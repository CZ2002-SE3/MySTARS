package mystars.commands.admin;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.data.user.UserList;
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
     * @param courseList     CourseList object.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, CourseList courseList, UserList users, AdminUi ui, Storage storage)
            throws MyStarsException {
        Student newStudent = ui.getNewStudentFromUser();
        users.addStudent(newStudent);

        storage.saveStudent(newStudent);
        ui.showAddedStudent(newStudent);
        ui.showStudentList(users);
    }
}
