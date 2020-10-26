package mystars.commands.student;

import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

/**
 * Drops course for student.
 */
public class DropCourseCommand extends StudentCommand {

    /**
     * Prompts student for course to drop, and saves changes to file.
     *
     *
     * @param courses CourseList object.
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(CourseList courses, UserList users, StudentUi ui, Storage storage) throws MyStarsException {

    }
}
