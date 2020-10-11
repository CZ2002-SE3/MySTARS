package mystars.ui;

import mystars.commands.ExitCommand;
import mystars.commands.LoginCommand;
import mystars.storage.Storage;

import java.util.Scanner;

public class Ui {

    private static final String DOTTED_LINE = "============================================================";

    private final Scanner in = new Scanner(System.in);

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        showError(Storage.LOAD_ERROR);
    }

    /**
     * Prints error message.
     *
     * @param message Error message to print.
     */
    public void showError(String message) {
        printNicely(message);
    }

    /**
     * Prints welcome message.
     */
    public void showLogin() {
        showLine();
        printNicely(LoginCommand.MESSAGE);
        showLine();
        printNicely("");
    }

    /**
     * Prints dotted line.
     */
    public void showLine() {
        printNicely(DOTTED_LINE);
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        printNicely("");
        return in.nextLine();
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        printNicely(ExitCommand.MESSAGE);
    }

    /**
     * Prints string with nice format.
     *
     * @param string String to print.
     */
    private void printNicely(String string) {
        System.out.println(string);
    }

    public char[][] readUsernameAndPassword() {
        printNicely("");
        showLine();
        char[] username = readUsername();
        printNicely("");
        char[] password = readPassword();
        printNicely("");
        return new char[][]{username, password};
    }

    private char[] readUsername() {
        printNicely(LoginCommand.USERNAME_MESSAGE);
        return in.nextLine().toCharArray();
    }

    private char[] readPassword() {
        printNicely(LoginCommand.PASSWORD_MESSAGE);

        if (System.console() == null) {
            printNicely("Please use console, or else password is not masked.");
            return in.nextLine().toCharArray();
        }

        return System.console().readPassword();
    }
}
