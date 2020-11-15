package mystars.commands.admin;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.ui.AdminUi;

/**
 * Checks vacancy of a course.
 */
public class CheckVacancyCommand extends AdminCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "4";

    /**
     * List of courses.
     */
    private final CourseList courses;

    /**
     * Initialises command for execution.
     *
     * @param ui Ui object.
     * @param courses List of courses.
     */
    public CheckVacancyCommand(AdminUi ui, CourseList courses) {
        super(ui);
        this.courses = courses;
    }

    /**
     * Checks vacancy of a course and shows it to user.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        courses.checkIndexNoInList(indexNumber);
        ui.showVacancy(courses, indexNumber);
    }
}
