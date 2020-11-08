package mystars;

import mystars.commands.Command;
import mystars.commands.admin.AdminCommand;
import mystars.commands.shared.ExitCommand;
import mystars.commands.shared.LoginCommand;
import mystars.commands.shared.LogoutCommand;
import mystars.commands.student.StudentCommand;
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

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * My STudent Automated Registration System.
 */
public class MyStars {

    public static final Logger logger = Logger.getLogger(MyStars.class.getName());
    private final Parser parser;
    private final Storage storage;
    private Ui ui;
    private UserList users;
    private CourseList courses;
    private AccessDateTime accessDateTime;

    /**
     * Initialises MySTARS.
     */
    public MyStars() {
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
        Command command = new LogoutCommand();
        while (!command.isExit()) {
            try {
                while (!command.isLogin()) {
                    command = new LoginCommand();
                    ((LoginCommand) command).execute(accessDateTime, users, ui);
                }

                if (command.getUser() instanceof Student) {
                    ui = new StudentUi();
                    command = parser.parseStudentInput(ui.readCommand());
                } else if (command.getUser() instanceof Admin) {
                    ui = new AdminUi();
                    command = parser.parseAdminInput(ui.readCommand());
                }
                execute(command);

            } catch (MyStarsException e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.showLine();
            }

            if (command instanceof LogoutCommand) {
                String fullCommand = ui.askExit();
                if (parser.isYes(fullCommand)) {
                    ui.showLine();
                    new ExitCommand().execute(accessDateTime, users, ui);
                }
                ui.showLine();
            }
        }
    }

    private void execute(Command command) throws MyStarsException {
        if (command instanceof StudentCommand) {
            ((StudentCommand) command).execute(courses, users, (StudentUi) ui, storage);
        } else if (command instanceof AdminCommand) {
            ((AdminCommand) command).execute(accessDateTime, courses, users, (AdminUi) ui, storage);
        } else if (command instanceof LogoutCommand) {
            ((LogoutCommand) command).execute(accessDateTime, users, ui);
        }
    }
}
