package mystars.commands.admin;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.mail.SendMailTLS;
import mystars.data.user.Student;
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

        // TODO: Check vacancy before updating course
        if (courseList.isIndexNoInList(indexNumber)) {
            ui.showCourse(courseList.getCourseByIndex(indexNumber));
            course = ui.updateCourseDetails(indexNumber, courseList.getCourseByIndex(indexNumber));
        } else {
            course = ui.getCourseDetails(indexNumber);
        }

        checkWaitlist(course);
        courseList.updateCourse(course);


        storage.saveCourses(courseList);
        ui.showCourseList(courseList);
    }

    private void checkWaitlist(Course course) {
        if (course.isThereWaitlistedStudents() && (course.isVacancy())) {
            Student studentToNotify;
            int i = 0;
            while (course.isThereWaitlistedStudents()) {
                studentToNotify = course.getWaitlistedStudents().get(i);
                try {
                    studentToNotify.addCourseToRegistered(course);
                    studentToNotify.dropWaitlistedCourse(course);
                } catch (MyStarsException e) {
                    i++;
                    continue;
                }
                course.addRegisteredStudent(studentToNotify);
                course.dropWaitlistedStudent(studentToNotify);

                sendEmailToStudent(course, studentToNotify);
                break;
            }
        }
    }

    private void sendEmailToStudent(Course course, Student studentToNotify) {
        SendMailTLS.sendMail(studentToNotify.getEmail(), SendMailTLS.getEmailContent(course.getCourseCode()
        , course.getIndexNumber(), studentToNotify.getName()));
    }
}
