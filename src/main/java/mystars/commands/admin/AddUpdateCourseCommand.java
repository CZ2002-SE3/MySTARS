package mystars.commands.admin;

import mystars.data.CourseList;
import mystars.data.UserList;
import mystars.data.course.Course;
import mystars.data.exception.MyStarsException;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.time.LocalDateTime;

public class AddUpdateCourseCommand extends AdminCommand {

    public static final String COMMAND_WORD = "3";

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param courses        CourseList object.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @param storage        Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(LocalDateTime[] accessDateTime, CourseList courses, UserList users, AdminUi ui, Storage storage)
            throws MyStarsException {
        String indexNumber = ui.getIndexNumber();

        if (courses.isIndexNoInList(indexNumber)) {
            ui.showCourse(courses.getCourseByIndex(indexNumber));
        }
        Course course = ui.getCourseDetails(indexNumber);
        courses.updateCourse(course);
        //TODO: save courses
        //storage.saveCourses(courses);
    }
}
