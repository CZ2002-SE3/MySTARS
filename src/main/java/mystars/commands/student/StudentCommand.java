package mystars.commands.student;

import mystars.commands.Command;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

public abstract class StudentCommand extends Command {

    static final String DIFFERENT_COURSE_ERROR = "These indexes are not from the same course.";
    static final String INVALID_USER_ERROR = "User not valid!";
    static final String SAME_USER_ERROR = "You cannot swop index with yourself!";
    static final String NOT_REGISTERED_ERROR = "Either of you are not registered for the specified index!";
    static final String SAME_INDEX_ERROR = "Unable to change to the same index.";
    static final String NO_VACANCY_ERROR = "No vacancies to change to.";
    static final String COURSE_NOT_FOUND_ERROR = "No such course found.";

    /**
     * Executes command.
     *
     * @param courseList CourseList object.
     * @param users      UserList object.
     * @param ui         Ui object.
     * @param storage    Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(CourseList courseList, UserList users, StudentUi ui, Storage storage) throws MyStarsException;
}
