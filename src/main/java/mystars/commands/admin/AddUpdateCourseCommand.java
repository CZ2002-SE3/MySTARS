package mystars.commands.admin;

import mystars.MyStars;
import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.sender.Sender;
import mystars.storage.Storage;
import mystars.ui.AdminUi;
import mystars.ui.Ui;

import java.util.logging.Level;


public class AddUpdateCourseCommand extends AdminCommand {

    public static final String COMMAND_WORD = "3";

    private final Storage storage;
    private final CourseList courses;

    public AddUpdateCourseCommand(Ui ui, Storage storage, CourseList courses) {
        super((AdminUi) ui);
        this.storage = storage;
        this.courses = courses;
    }

    /**
     * Executes command.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        Course course;

        if (courses.isIndexNoInList(indexNumber)) {
            ui.showCourse(courses.getCourseByIndex(indexNumber));

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
