package mystars;

import mystars.commands.Command;
import mystars.commands.shared.LoginCommand;
import mystars.commands.shared.LogoutCommand;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.shared.AccessDateTime;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.UserList;
import mystars.parser.Parser;
import mystars.storage.Storage;
import mystars.ui.AdminUi;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * My STudent Automated Registration System.
 */
public class MyStars {

    /**
     * Logger object.
     */
    public static Logger logger;

    /**
     * Parser object.
     */
    private final Parser parser;

    /**
     * Storage handler.
     */
    private final Storage storage;

    /**
     * User interface.
     */
    private Ui ui;

    /**
     * List of users.
     */
    private UserList users;

    /**
     * List of courses.
     */
    private CourseList courses;

    /**
     * Access date/time.
     */
    private AccessDateTime accessDateTime;

    /**
     * Initializes MySTARS.
     */
    public MyStars() {
        logger = Logger.getLogger(MyStars.class.getName());
        logger.setLevel(Level.WARNING);
        parser = new Parser();
        ui = new StudentUi(parser);
        storage = new Storage(parser);
        try {
            courses = new CourseList(storage.loadCourses());
            users = new UserList(storage.loadUsers());
            users.addDetails(storage.loadStudents(), storage.loadAdmins());
            accessDateTime = new AccessDateTime(storage.loadAccessPeriod());
            storage.loadCourseRegisteredStudents(courses, users);
            storage.loadCourseWaitlistStudents(courses, users);

        } catch (MyStarsException e) {
            ui.showToUser(e.getMessage());
            users = new UserList();
        }
    }

    /**
     * Starts MySTARS.
     *
     * @param args Command line argument.
     */
    public static void main(String[] args) {
        new MyStars().run();
    }

    /**
     * Runs MySTARS.
     */
    public void run() {
        ui.showLine();
        ui.showWelcome();
        Command command = new LogoutCommand(ui);
        while (!command.isExit()) {
            try {
                if (!command.isLogin()) {
                    command = new LoginCommand(ui, users, accessDateTime);
                } else if (command.getUser() instanceof Student) {
                    ui = new StudentUi();
                    command = parser.parseStudentInput(ui.readCommand(), users, (StudentUi) ui, courses, storage);
                } else if (command.getUser() instanceof Admin) {
                    ui = new AdminUi();
                    command = parser.parseAdminInput(ui.readCommand(), users, (AdminUi) ui, courses, storage,
                            accessDateTime);
                }
                command.execute();
            } catch (MyStarsException e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
