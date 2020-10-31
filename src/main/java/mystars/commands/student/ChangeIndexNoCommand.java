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

        String originalIndexNumber = ui.getOriginalIndexNumber();
        if (!courseList.isIndexNoInList(originalIndexNumber)) {
            throw new MyStarsException("No such course.");
        }
        Course currentCourse = courseList.getCourseByIndex(originalIndexNumber);


        String desiredIndexNumber = ui.getDesiredIndexNumber();
        if (!courseList.isIndexNoInList(desiredIndexNumber)) {
            throw new MyStarsException("No such course.");
        }
        Course desiredCourse = courseList.getCourseByIndex(desiredIndexNumber);
        if (!desiredCourse.isSameCourseCode(currentCourse)) {
            throw new MyStarsException("These indexes are not from the same course!");
        }

        if (originalIndexNumber.equals(desiredIndexNumber)) {
            throw new MyStarsException("You cannot change to the same index!");
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
