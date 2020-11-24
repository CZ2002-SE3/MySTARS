package mystars.commands.student;

import mystars.MyStars;
import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.sender.Sender;
import mystars.data.user.Student;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

import java.util.logging.Level;

/**
 * Changes index no for student.
 */
public class ChangeIndexNoCommand extends StudentCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "5";

    /**
     * List of courses.
     */
    private final CourseList courses;

    /**
     * Storage handler.
     */
    private final Storage storage;

    /**
     * Initializes command for execution.
     *
     * @param ui      Ui object.
     * @param courses List of courses.
     * @param storage Storage handler.
     */
    public ChangeIndexNoCommand(StudentUi ui, CourseList courses, Storage storage) {
        super(ui);
        this.courses = courses;
        this.storage = storage;
    }

    /**
     * Changes student's course to another index, and saves updated information to storage.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        Student student = (Student) getUser();

        String originalIndexNumber = ui.getIndexNumber(ORIGINAL);
        courses.checkIndexNoInList(originalIndexNumber);

        String desiredIndexNumber = ui.getIndexNumber(DESIRED);
        courses.checkIndexNoInList(desiredIndexNumber);

        Course currentCourse = courses.getCourseByIndex(originalIndexNumber);
        Course desiredCourse = courses.getCourseByIndex(desiredIndexNumber);

        if (originalIndexNumber.equals(desiredIndexNumber)) {
            throw new MyStarsException(SAME_INDEX_ERROR);
        } else if (!desiredCourse.isSameCourseCode(currentCourse)) {
            throw new MyStarsException(DIFFERENT_COURSE_ERROR);
        } else if (!desiredCourse.isVacancy()) {
            throw new MyStarsException(NO_VACANCY_ERROR);
        }

        student.dropRegisteredCourse(currentCourse);
        currentCourse.dropRegisteredStudent(student);
        student.addCourseToRegistered(desiredCourse);
        desiredCourse.addRegisteredStudent(student);

        if (currentCourse.checkWaitlist()) {
            MyStars.logger.log(Level.INFO, Sender.SEND_MESSAGE);
            ui.showEmailSent();
        }

        storage.saveCourses(courses);
        ui.showIndexNoChanged(desiredCourse, currentCourse);
    }
}
