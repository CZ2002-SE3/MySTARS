package mystars.commands.admin;

import mystars.commands.shared.SharedCommand;
import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.mail.SendMailTLS;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

import static mystars.commands.shared.SharedCommand.checkWaitlist;

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
    public void execute(LocalDateTime[] accessDateTime, CourseList courseList, UserList users, AdminUi ui, Storage storage)
            throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        Course course;

        // TODO: Check vacancy before updating course
        if (courseList.isIndexNoInList(indexNumber)) {
            ui.showCourse(courseList.getCourseByIndex(indexNumber));
            course = ui.updateCourseDetails(indexNumber, courseList.getCourseByIndex(indexNumber));
        } else {
            course = ui.getCourseDetails(indexNumber);
        }

        Course modifiedCourse = courseList.updateCourse(course);
        checkWaitlist(modifiedCourse);


        storage.saveCourses(courseList);
        ui.showCourseList(courseList);
    }

}
