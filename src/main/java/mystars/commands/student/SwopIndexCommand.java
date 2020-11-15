package mystars.commands.student;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

/**
 * Swops index no. for student.
 */
public class SwopIndexCommand extends StudentCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "6";

    /**
     * List of courses.
     */
    private final CourseList courses;

    /**
     * Storage handler.
     */
    private final Storage storage;

    /**
     * List of users.
     */
    private final UserList users;

    /**
     * Initialises command for execution.
     *
     * @param ui      Ui object.
     * @param courses List of courses.
     * @param storage Storage handler.
     * @param users   List of users.
     */
    public SwopIndexCommand(StudentUi ui, CourseList courses, Storage storage, UserList users) {
        super(ui);
        this.courses = courses;
        this.storage = storage;
        this.users = users;
    }

    /**
     * Gets original and peer's index numbers, along with peer's login details.
     * If indexes can be swapped, they will be swapped.
     *
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute() throws MyStarsException {
        Student student = (Student) getUser();

        String originalIndexNumber = ui.getIndexNumber(ORIGINAL);
        courses.checkIndexNoInList(originalIndexNumber);
        Course currentCourse = courses.getCourseByIndex(originalIndexNumber);

        String peerIndexNumber = ui.getIndexNumber(PEER);
        courses.checkIndexNoInList(peerIndexNumber);
        Course peerCourse = courses.getCourseByIndex(peerIndexNumber);

        char[][] usernameAndPassword = ui.readUsernameAndPassword();
        if (!(users.getUser(usernameAndPassword) instanceof Student)) {
            throw new MyStarsException(INVALID_USER_ERROR);
        }

        Student peer = (Student) users.getUser(usernameAndPassword);

        if (peer.equals(student)) {
            throw new MyStarsException(SAME_USER_ERROR);
        } else if (!student.getRegisteredCourses().isIndexNoInList(originalIndexNumber)
                || !peer.getRegisteredCourses().isIndexNoInList(peerIndexNumber)) {
            throw new MyStarsException(NOT_REGISTERED_ERROR);
        }

        if (student.getRegisteredCourses().getCourseByIndex(originalIndexNumber)
                .isSameCourseCode(peer.getRegisteredCourses().getCourseByIndex(peerIndexNumber))) {
            // Drop courses
            student.dropRegisteredCourse(currentCourse);
            peer.dropRegisteredCourse(peerCourse);
            currentCourse.dropRegisteredStudent(student);
            peerCourse.dropRegisteredStudent(peer);

            try {
                // Add courses
                student.addCourseToRegistered(peerCourse);
                peer.addCourseToRegistered(currentCourse);
                peerCourse.addRegisteredStudent(student);
                currentCourse.addRegisteredStudent(peer);
            } catch (MyStarsException e) {
                // Undo dropping of course if there is error adding courses
                student.addCourseToRegistered(currentCourse);
                currentCourse.addRegisteredStudent(student);
                peer.addCourseToRegistered(peerCourse);
                peerCourse.addRegisteredStudent(peer);
                ui.showToUser(e.getMessage());
            }
        } else {
            throw new MyStarsException(DIFFERENT_COURSE_ERROR);
        }

        ui.showIndexSwop(currentCourse, peerCourse, student, peer);

        storage.saveCourses(courses);
    }
}
