package mystars.commands.shared;

import mystars.commands.Command;
import mystars.data.course.Course;
import mystars.data.exception.MyStarsException;
import mystars.data.mail.SendMailTLS;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.ui.Ui;

import java.time.LocalDateTime;

/**
 * Parent shared command class.
 */
public abstract class SharedCommand extends Command {

    /**
     * Executes command.
     *
     * @param accessDateTime Access period.
     * @param users          UserList object.
     * @param ui             Ui object.
     * @throws MyStarsException If there is issue executing command.
     */
    public abstract void execute(LocalDateTime[] accessDateTime, UserList users, Ui ui)
            throws MyStarsException;

    public static void checkWaitlist(Course course) {
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

    private static void sendEmailToStudent(Course course, Student studentToNotify) {
        SendMailTLS.sendMail(studentToNotify.getEmail(), SendMailTLS.getEmailContent(course.getCourseCode()
                , course.getIndexNumber(), studentToNotify.getName()));
    }
}
