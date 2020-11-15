package mystars.commands.student;

import mystars.MyStars;
import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.sender.Sender;
import mystars.data.user.Student;
import mystars.storage.Storage;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

import java.util.logging.Level;


/**
 * Drops course for student.
 */
public class DropCourseCommand extends StudentCommand {

    public static final String COMMAND_WORD = "2";

    private final Storage storage;

    public DropCourseCommand(Ui ui, CourseList courses, Storage storage) {
        super((StudentUi) ui, courses);
        this.storage = storage;
    }

    /**
     * Prompts student for course to drop, and saves changes to file.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        String indexNumber = ui.getIndexNumber();
        courses.isIndexNoInList(indexNumber);

        Course course = courses.getCourseByIndex(indexNumber);
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
            MyStars.logger.log(Level.INFO, Sender.SEND_MESSAGE);
            ui.showEmailSent();
        }

        storage.saveCourses(courses);
    }
}
