package mystars.ui;

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
    public void showExit(String message) {
        printNicely(message);
    }

    /**
     * Prints string with nice format.
     *
     * @param string String to print.
     */
    protected void printNicely(String string) {
        System.out.println(string);
    }

    public char[][] readUsernameAndPassword(String usernameMessage, String passwordMessage) {
        char[] username = readUsername(usernameMessage);
        printNicely("");
        char[] password = readPassword(passwordMessage);
        return new char[][]{username, password};
    }

    private char[] readUsername(String message) {
        printNicely(message);
        return in.nextLine().toCharArray();
    }

    private char[] readPassword(String message) {
        printNicely(message);

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
        // Change this to admin menu
        printNicely("1. Register New Course");
        printNicely("2. Register New Student");
        printNicely("3. Check/Print Courses Registered");
        printNicely("4. Check Vacancies Available");
        printNicely("5. Change Index Number of Course");
        printNicely("6. Swop Index Number with Another Student");
        printNicely("7. Logout");
        printNicely("Please select an item:");
    }

    public void showLogout() {
        printNicely("You have successfully logged out!");
    }

    public String askExit() {
        printNicely("Do you want to exit? Press Y to exit, anything else to continue to login.");
        return in.nextLine();
    }

    public void greetUser() {
        printNicely("Hello user!");
    }

    public void showAddCourse() {
        printNicely("adding...");
    }
}
