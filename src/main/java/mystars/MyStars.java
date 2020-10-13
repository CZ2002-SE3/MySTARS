package mystars;

import mystars.commands.Command;
import mystars.commands.ExitCommand;
import mystars.commands.LoginCommand;
import mystars.commands.LogoutCommand;
import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.parser.Parser;
import mystars.storage.Storage;
import mystars.ui.Ui;

/**
 * My STudent Automated Registration System.
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
                ui.greetUser();
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
