package mystars;

import mystars.commands.Command;
import mystars.commands.LoginCommand;
import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.parser.Parser;
import mystars.storage.Storage;
import mystars.ui.Ui;

/**
 * My STudent Automated Registration System
 */
public class MyStars {

    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private UserList users;

    /**
     * Initialises MySTARS.
     */
    public MyStars() {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage();
        try {
            users = new UserList(storage.loadUsers(parser));
        } catch (MyStarsException e) {
            ui.showLoadingError();
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

        Command command = new LoginCommand();
        while (!command.isLogin()) {
            try {
                command.execute(users, ui, storage);
            } catch (MyStarsException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showWelcome();

        while (!command.isExit()) {
            try {
                ui.showMenu();
                String fullCommand = ui.readCommand();
                ui.showLine();
                command = parser.parse(fullCommand);
                command.execute(users, ui, storage);
            } catch (MyStarsException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
