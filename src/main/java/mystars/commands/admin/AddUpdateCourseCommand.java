package mystars.commands.admin;

import mystars.MyStars;
import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.sender.Sender;
import mystars.storage.Storage;
import mystars.ui.AdminUi;

import java.util.logging.Level;


/**
 * Adds or updates course.
 */
public class AddUpdateCourseCommand extends AdminCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "3";

    /**
     * Storage handler.
     */
    private final Storage storage;

    /**
     * List of courses.
     */
    private final CourseList courses;

    /**
     * @param ui      Ui object.
     * @param storage Storage handler.
     * @param courses List of courses.
     */
    public AddUpdateCourseCommand(AdminUi ui, Storage storage, CourseList courses) {
        super(ui);
        this.storage = storage;
        this.courses = courses;
    }

    /**
     * Checks if course index entered exists.
     * If so, asks user to confirm updating course and prompts accordingly.
     * Else, prompts user to enter new course details.
     * Then, saves course to file.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        Course course;

        if (courses.isIndexNoInList(indexNumber)) {
            ui.showExistingCourse(courses.getCourseByIndex(indexNumber));

            if (!ui.askUpdate()) {
                return;
            }

            course = ui.updateCourseDetails(indexNumber, courses.getCourseByIndex(indexNumber));
        } else {
            course = ui.getCourseDetails(indexNumber);
        }

        Course modifiedCourse = courses.updateCourse(course);
        if (modifiedCourse.checkWaitlist()) {
            MyStars.logger.log(Level.INFO, Sender.SEND_MESSAGE);
            ui.showEmailSent();
        }


        storage.saveCourses(courses);
        ui.showCourseList(courses);
    }
}
