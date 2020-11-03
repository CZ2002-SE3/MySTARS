package mystars.commands.student;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

/**
 * Change index no for student.
 */
public class ChangeIndexNoCommand extends StudentCommand {

    public static final String COMMAND_WORD = "5";
    private static final String DIFFERENT_COURSE_ERROR = "These indexes are not from the same course.";
    private static final String SAME_INDEX_ERROR = "Unable to change to the same index.";

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
        Student student = (Student) getUser();

        String originalIndexNumber = ui.getOriginalIndexNumber();
        courseList.checkIndexNoInList(originalIndexNumber);

        Course currentCourse = courseList.getCourseByIndex(originalIndexNumber);

        String desiredIndexNumber = ui.getDesiredIndexNumber();
        courseList.checkIndexNoInList(desiredIndexNumber);
        if (originalIndexNumber.equals(desiredIndexNumber)) {
            throw new MyStarsException(SAME_INDEX_ERROR);
        }

        Course desiredCourse = courseList.getCourseByIndex(desiredIndexNumber);

        if (!desiredCourse.isSameCourseCode(currentCourse)) {
            throw new MyStarsException(DIFFERENT_COURSE_ERROR);
        }

        if (desiredCourse.isVacancy()) {
            student.dropRegisteredCourse(currentCourse);
            currentCourse.dropRegisteredStudent(student);
            student.addCourseToRegistered(desiredCourse);
            desiredCourse.addRegisteredStudent(student);
        }

        ui.showIndexNoChanged(desiredCourse, currentCourse);

        storage.saveCourses(courseList);
    }
}
