package mystars.commands.student;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.ui.StudentUi;

/**
 * Checks vacancy of course's index.
 */
public class CheckCourseVacancyCommand extends StudentCommand {

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
     * @param ui      Ui object.
     * @param courses List of courses.
     */
    public CheckCourseVacancyCommand(StudentUi ui, CourseList courses) {
        super(ui);
        this.courses = courses;
    }

    /**
     * Checks vacancy of a course's index and shows it to user.
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
