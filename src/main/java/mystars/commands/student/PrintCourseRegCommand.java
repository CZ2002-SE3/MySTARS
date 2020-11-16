package mystars.commands.student;

import mystars.data.user.Student;
import mystars.ui.StudentUi;

/**
 * Prints student's registered courses.
 */
public class PrintCourseRegCommand extends StudentCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "3";

    /**
     * Initializes command for execution.
     *
     * @param ui Ui object.
     */
    public PrintCourseRegCommand(StudentUi ui) {
        super(ui);
    }

    /**
     * Prints logged in student's registered courses.
     */
    @Override
    public void execute() {
        Student student = (Student) this.getUser();
        ui.showAllCourses(student);
    }
}
