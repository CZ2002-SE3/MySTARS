package mystars.commands.student;

import mystars.commands.Command;
import mystars.ui.StudentUi;

/**
 * Parent student command class.
 */
public abstract class StudentCommand extends Command {

    /**
     * Different course error message.
     */
    static final String DIFFERENT_COURSE_ERROR = "These indexes are not from the same course.";

    /**
     * Invalid user error message.
     */
    static final String INVALID_USER_ERROR = "User not valid!";

    /**
     * Same user error message.
     */
    static final String SAME_USER_ERROR = "You cannot swop index with yourself!";

    /**
     * Not registered error message.
     */
    static final String NOT_REGISTERED_ERROR = "Either of you are not registered for the specified index!";

    /**
     * Same index error message.
     */
    static final String SAME_INDEX_ERROR = "Unable to change to the same index.";

    /**
     * No vacancies error message.
     */
    static final String NO_VACANCY_ERROR = "No vacancies to change to.";

    /**
     * Course not found error message.
     */
    static final String COURSE_NOT_FOUND_ERROR = "No such course found.";

    /**
     * Original string.
     */
    static final String ORIGINAL = "original ";

    /**
     * Desired string.
     */
    static final String DESIRED = "desired ";

    /**
     * Peer string.
     */
    static final String PEER = "peer ";

    /**
     * Registered string.
     */
    static final String REGISTERED = "Registered";

    /**
     * Waitlisted string.
     */
    static final String WAITLISTED = "Waitlisted";

    /**
     * Ui object.
     */
    final StudentUi ui;

    /**
     * Initializes Ui object for subclasses.
     *
     * @param ui Ui object.
     */
    StudentCommand(StudentUi ui) {
        this.ui = ui;
    }
}
