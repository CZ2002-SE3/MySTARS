package mystars.ui;

import mystars.commands.Command;
import mystars.commands.ExitCommand;
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
        printWithFormat(message);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showLine();
        printWithFormat(Command.MESSAGE);
        showLine();
    }

    /**
     * Prints dotted line.
     */
    public void showLine() {
        printWithFormat(DOTTED_LINE);
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        printWithFormat("");
        return in.nextLine();
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        printWithFormat(ExitCommand.MESSAGE);
    }

    /**
     * Prints string with nice format.
     *
     * @param string String to print.
     */
    private void printWithFormat(String string) {
        System.out.println("\t" + string);
    }
}
