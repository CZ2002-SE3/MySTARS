package mystars.commands.admin;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

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

        // TODO: Check vacancy before updating course
        if (courseList.isIndexNoInList(indexNumber)) {
            ui.showCourse(courseList.getCourseByIndex(indexNumber));
        }
        Course course = ui.getCourseDetails(indexNumber);
        courseList.updateCourse(course);


        storage.saveCourses(courseList);
        ui.showCourseList(courseList);
    }
}
