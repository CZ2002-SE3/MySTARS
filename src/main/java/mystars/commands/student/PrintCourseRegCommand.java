package mystars.commands.student;

import mystars.data.user.Student;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

public class PrintCourseRegCommand extends StudentCommand {

    public static final String COMMAND_WORD = "3";

    public PrintCourseRegCommand(Ui ui) {
        super((StudentUi) ui, null);
    }

    /**
     * Executes command.
     */
    @Override
    public void execute() {
        Student student = (Student) this.getUser();
        ui.showAllCourses(student);
    }
}
