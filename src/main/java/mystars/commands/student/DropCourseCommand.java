package mystars.commands.student;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

/**
 * Drops course for student.
 */
public class DropCourseCommand extends StudentCommand {

    public static final String COMMAND_WORD = "2";

    /**
     * Prompts student for course to drop, and saves changes to file.
     *
     *
     * @param courseList CourseList object.
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(CourseList courseList, UserList users, StudentUi ui, Storage storage) throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        if (!courseList.isIndexNoInList(indexNumber)) {
            throw new MyStarsException("No such course.");
        }
        Course course = courseList.getCourseByIndex(indexNumber);
        Student student = (Student) getUser();

        student.dropCourse(course);
        course.dropRegisteredStudent(student);
        ui.showCourseDropped(course);


        storage.saveCourses(courseList);
    }
}
