package mystars.commands.student;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

/**
 * Adds course for student.
 */
public class AddCourseCommand extends StudentCommand {

    public static final String COMMAND_WORD = "1";

    /**
     * Prompts user for course to add and saves to file.
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
        String indexNumber = ui.getIndexNumber();
        Course course = courses.getCourseByIndex(indexNumber);
        Student student = (Student) getUser();
        student.addCourse(course);
        ui.showCourseAdded(course);
        //TODO : check if course exist
        //TODO : Save course added
    }
}
