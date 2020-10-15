package mystars;

import mystars.commands.Command;
import mystars.commands.ExitCommand;
import mystars.commands.LoginCommand;
import mystars.commands.LogoutCommand;
import mystars.data.CourseList;
import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.UserType;
import mystars.parser.Parser;
import mystars.storage.Storage;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * My STudent Automated Registration System.
 */
public class MyStars {

    private final Parser parser;
    private final Storage storage;
    private Ui ui;
    private UserList users;
    private CourseList courses;
    private UserType userType;
    private static final Logger logger = Logger.getLogger(MyStars.class.getName());
    /**
     * Initialises MySTARS.
     */
    public MyStars() {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage();
        try {
            users = new UserList(storage.loadUsers(parser));
            users.addDetails(storage.loadStudents(parser), storage.loadAdmins(parser));
            courses = new CourseList(storage.loadCourses(parser));

        } catch (MyStarsException e) {
            ui.showError(e.getMessage());
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
                    command.execute(users, ui, storage);
                }
                if (users.getUserType(command.getUser()) == UserType.STUDENT) {
                    logger.log(Level.INFO, "change ui to student");
                    ui = new StudentUi();
                    userType = UserType.STUDENT;
                }
                ui.greetUser();
                ui.showMenu();
                String fullCommand = ui.readCommand();
                ui.showLine();
                if (userType == UserType.STUDENT){
                    command = parser.parseStudent(fullCommand);
                }
                else{command = parser.parse(fullCommand);}
                command.execute(users, ui, storage);
            } catch (MyStarsException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

            if (command instanceof LogoutCommand) {
                String fullCommand = ui.askExit();
                if (parser.isExit(fullCommand)) {
                    ui.showLine();
                    new ExitCommand().execute(users, ui, storage);
                }
                ui.showLine();
            }
        }
    }
}
