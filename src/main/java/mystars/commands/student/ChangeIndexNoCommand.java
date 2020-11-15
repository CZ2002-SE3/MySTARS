package mystars.commands.student;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.storage.Storage;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

/**
 * Change index no for student.
 */
public class ChangeIndexNoCommand extends StudentCommand {

    public static final String COMMAND_WORD = "5";

    private final Storage storage;

    public ChangeIndexNoCommand(Ui ui, CourseList courses, Storage storage) {
        super((StudentUi) ui, courses);
        this.storage = storage;
    }

    /**
     * Executes command.
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

        ui.showIndexNoChanged(desiredCourse, currentCourse);

        storage.saveCourses(courses);
    }
}
