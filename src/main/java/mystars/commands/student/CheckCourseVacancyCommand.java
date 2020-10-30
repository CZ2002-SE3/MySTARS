package mystars.commands.student;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

public class CheckCourseVacancyCommand extends StudentCommand {

    public static final String COMMAND_WORD = "4";

    /**
     * Executes command.
     *
     * @param courseList CourseList object.
     * @param users      UserList object.
     * @param ui         Ui object.
     * @param storage    Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(CourseList courseList, UserList users, StudentUi ui, Storage storage) throws MyStarsException {
        String courseCode = ui.getCourseCode();
        if (!courseList.isCourseInList(courseCode)) {
            throw new MyStarsException("No such course.");
        }
        ui.showCourseVacancy(courseList, courseCode);
    }
}
