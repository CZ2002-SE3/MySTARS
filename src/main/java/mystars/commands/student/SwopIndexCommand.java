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

        String originalIndexNumber = ui.getIndexNumber(ORIGINAL);
        courseList.checkIndexNoInList(originalIndexNumber);
        Course currentCourse = courseList.getCourseByIndex(originalIndexNumber);

        String peerIndexNumber = ui.getIndexNumber(PEER);
        courseList.checkIndexNoInList(peerIndexNumber);
        Course peerCourse = courseList.getCourseByIndex(peerIndexNumber);

        char[][] usernameAndPassword = ui.readUsernameAndPassword();
        if (!(users.getUser(usernameAndPassword) instanceof Student)) {
            throw new MyStarsException(INVALID_USER_ERROR);
        }

        Student peer = (Student) users.getUser(usernameAndPassword);

        if (peer.equals(student)) {
            throw new MyStarsException(SAME_USER_ERROR);
        } else if (!student.getRegisteredCourses().isIndexNoInList(originalIndexNumber) ||
                !peer.getRegisteredCourses().isIndexNoInList(peerIndexNumber)) {
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

        storage.saveCourses(courseList);
    }
}
