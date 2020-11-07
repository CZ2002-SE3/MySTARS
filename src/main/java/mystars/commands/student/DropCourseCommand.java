package mystars.commands.student;

import mystars.MyStars;
import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.mail.EmailSender;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

import java.util.logging.Level;


/**
 * Drops course for student.
 */
public class DropCourseCommand extends StudentCommand {

    public static final String COMMAND_WORD = "2";

    /**
     * Prompts student for course to drop, and saves changes to file.
     *
     * @param courseList CourseList object.
     * @param users      UserList object.
     * @param ui         Ui object.
     * @param storage    Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(CourseList courseList, UserList users, StudentUi ui, Storage storage) throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        courseList.isIndexNoInList(indexNumber);

        Course course = courseList.getCourseByIndex(indexNumber);
        Student student = (Student) getUser();

        if (student.isCourseInRegistered(course)) {
            student.dropRegisteredCourse(course);
            course.dropRegisteredStudent(student);
            ui.showDroppedCourse(course, REGISTERED);
        } else if (student.isCourseInWaitlisted(course)) {
            student.dropWaitlistedCourse(course);
            course.dropWaitlistedStudent(student);
            ui.showDroppedCourse(course, WAITLISTED);
        } else {
            throw new MyStarsException(COURSE_NOT_FOUND_ERROR);
        }

        if (course.checkWaitlist()) {
            MyStars.logger.log(Level.INFO, EmailSender.SEND_EMAIL_MESSAGE);
            ui.showEmailSent();
        }

        storage.saveCourses(courseList);
    }
}
