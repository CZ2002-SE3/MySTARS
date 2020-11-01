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
        Course course;

        //TODO : ask user to confirm if they wanna update course if course alr exist

        if (courseList.isIndexNoInList(indexNumber)) {
            ui.showCourse(courseList.getCourseByIndex(indexNumber));
            course = ui.updateCourseDetails(indexNumber, courseList.getCourseByIndex(indexNumber));
        } else {
            course = ui.getCourseDetails(indexNumber);
        }

        Course modifiedCourse = courseList.updateCourse(course);
        modifiedCourse.checkWaitlist();


        storage.saveCourses(courseList);
        ui.showCourseList(courseList);
    }

}
