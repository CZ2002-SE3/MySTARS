package mystars.commands.admin;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.ui.AdminUi;

public class CheckVacancyCommand extends AdminCommand {

    public static final String COMMAND_WORD = "4";

    private final CourseList courses;

    public CheckVacancyCommand(AdminUi ui, CourseList courses) {
        super(ui);
        this.courses = courses;
    }

    /**
     * Executes command.
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
