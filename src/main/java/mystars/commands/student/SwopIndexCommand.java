package mystars.commands.student;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.StudentUi;

/**
 * Swop index no for student.
 */
public class SwopIndexCommand extends StudentCommand {

    public static final String COMMAND_WORD = "6";

    /**
     * Executes command.
     *
     * @param courseList CourseList object.
     * @param users      UserList object.
     * @param ui         Ui object.
     * @param storage    Storage object.
     * @throws MyStarsException If there is issue executing command.
     */
    @Override
    public void execute(CourseList courseList, UserList users, StudentUi ui, Storage storage) throws MyStarsException {
        Student student = (Student) getUser();
        String originalIndexNumber = ui.getOriginalIndexNumber();
        if (!courseList.isIndexNoInList(originalIndexNumber)) {
            throw new MyStarsException("No such course.");
        }
        Course currentCourse = courseList.getCourseByIndex(originalIndexNumber);

        // Get peer's info
        char[][] usernameAndPassword = ui.readUsernameAndPassword();
        Student peer;
        if (users.getUser(usernameAndPassword) instanceof Student) {
            peer = (Student) users.getUser(usernameAndPassword);
        } else {
            throw new MyStarsException("User not valid!");
        }
        String peerIndexNumber = ui.getPeerIndexNumber();
        if (!courseList.isIndexNoInList(peerIndexNumber)) {
            throw new MyStarsException("No such course.");
        }
        Course peerCourse = courseList.getCourseByIndex(peerIndexNumber);

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
                // undo dropping of course
                student.addCourseToRegistered(currentCourse);
                currentCourse.addRegisteredStudent(student);
                peer.addCourseToRegistered(peerCourse);
                peerCourse.addRegisteredStudent(peer);
                ui.showError(e.getMessage());
            }
        } else {
            throw new MyStarsException("These indexes are not from the same course!");
        }

        ui.showIndexSwop(currentCourse, peerCourse, student, peer);

        storage.saveCourses(courseList);
    }
}
