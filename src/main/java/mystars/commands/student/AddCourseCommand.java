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

        //student.addCourse(course);

        if (course.isVacancy()) {
            student.addCourseToRegistered(course);
            course.addRegisteredStudent(student);
            ui.showCourseRegistered(course);
        } else {
            student.addCourseToWaitlisted(course);
            course.addWaitlistedStudent(student);
            ui.showCourseWaitlisted(course);
        }

        storage.saveCourses(courseList);
        //TODO : Save course added
    }
}
