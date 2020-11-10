package mystars.commands.admin;

import mystars.MyStars;
import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.sender.Sender;
import mystars.data.shared.AccessDateTime;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.util.logging.Level;


public class AddUpdateCourseCommand extends AdminCommand {

    public static final String COMMAND_WORD = "3";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param courseList     CourseList object.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(AccessDateTime accessDateTime, CourseList courseList, UserList users, AdminUi ui,
                        Storage storage) throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        Course course;

        if (courseList.isIndexNoInList(indexNumber)) {
            ui.showCourse(courseList.getCourseByIndex(indexNumber));

            if (!ui.askUpdate()) {
                return;
            }

            course = ui.updateCourseDetails(indexNumber, courseList.getCourseByIndex(indexNumber));
        } else {
            course = ui.getCourseDetails(indexNumber);
        }

        Course modifiedCourse = courseList.updateCourse(course);
        if (modifiedCourse.checkWaitlist()) {
            MyStars.logger.log(Level.INFO, Sender.SEND_MESSAGE);
            ui.showEmailSent();
        }


        storage.saveCourses(courseList);
        ui.showCourseList(courseList);
    }
}
