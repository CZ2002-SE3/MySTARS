package mystars.ui;

import mystars.commands.ExitCommand;
import mystars.commands.LoginCommand;
import mystars.storage.Storage;

import java.util.Scanner;

public class Ui {

    private static final String DOTTED_LINE = "------------------------------------------------------------";
    private static final String LOGO = " _______         _______________________ _______ _______ \n"
            + "(       )\\     /(  ____ \\__   __(  ___  |  ____ |  ____ \\\n"
            + "| () () ( \\   / ) (    \\/  ) (  | (   ) | (    )| (    \\/\n"
            + "| || || |\\ (_) /| (_____   | |  | (___) | (____)| (_____ \n"
            + "| |(_)| | \\   / (_____  )  | |  |  ___  |     __|_____  )\n"
            + "| |   | |  ) (        ) |  | |  | (   ) | (\\ (        ) |\n"
            + "| )   ( |  | |  /\\____) |  | |  | )   ( | ) \\ \\_/\\____) |\n"
            + "|/     \\|  \\_/  \\_______)  )_(  |/     \\|/   \\__|_______)\n"
            + "                                                         ";

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
     * Prints dotted line.
     */
    public void showLine() {
        printNicely("");
        printNicely(DOTTED_LINE);
        printNicely("");
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
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
        char[] username = readUsername();
        printNicely("");
        char[] password = readPassword();
        return new char[][]{username, password};
    }

    private char[] readUsername() {
        printNicely(LoginCommand.USERNAME_MESSAGE);
        return in.nextLine().toCharArray();
    }

    private char[] readPassword() {
        printNicely(LoginCommand.WARNING_MESSAGE);
        printNicely(LoginCommand.PASSWORD_MESSAGE);

        if (System.console() == null) {
            return in.nextLine().toCharArray();
        }

        return System.console().readPassword();
    }

    public void showWelcome() {
        printNicely(LOGO);
        printNicely("Welcome!");
    }

    public void showMenu() {
        printNicely("1. Add Course");
        printNicely("2. Drop Course");
        printNicely("3. Check/Print Courses Registered");
        printNicely("4. Check Vacancies Available");
        printNicely("5. Change Index Number of Course");
        printNicely("6. Swop Index Number with Another Student");
        printNicely("7. Logout");
        printNicely("Please select an item:");
    }
}
