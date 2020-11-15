package mystars.commands.student;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

public class CheckCourseVacancyCommand extends StudentCommand {

    public static final String COMMAND_WORD = "4";

    public CheckCourseVacancyCommand(Ui ui, CourseList courses) {
        super((StudentUi) ui, courses);
    }

    /**
     * Executes command.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        String courseCode = ui.getCourseCode();
        courses.checkCourseInList(courseCode);

        ui.showCourseVacancy(courses, courseCode);
    }
}
