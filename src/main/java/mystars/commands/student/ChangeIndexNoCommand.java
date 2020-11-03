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

        String originalIndexNumber = ui.getIndexNumber(ORIGINAL);
        courseList.checkIndexNoInList(originalIndexNumber);

        String desiredIndexNumber = ui.getIndexNumber(DESIRED);
        courseList.checkIndexNoInList(desiredIndexNumber);

        Course currentCourse = courseList.getCourseByIndex(originalIndexNumber);
        Course desiredCourse = courseList.getCourseByIndex(desiredIndexNumber);

        if (originalIndexNumber.equals(desiredIndexNumber)) {
            throw new MyStarsException(SAME_INDEX_ERROR);
        } else if (!desiredCourse.isSameCourseCode(currentCourse)) {
            throw new MyStarsException(DIFFERENT_COURSE_ERROR);
        } else if (!desiredCourse.isVacancy()) {
            throw new MyStarsException(NO_VACANCY_ERROR);
        }

        student.dropRegisteredCourse(currentCourse);
        currentCourse.dropRegisteredStudent(student);
        student.addCourseToRegistered(desiredCourse);
        desiredCourse.addRegisteredStudent(student);

        ui.showIndexNoChanged(desiredCourse, currentCourse);

        storage.saveCourses(courseList);
    }
}
