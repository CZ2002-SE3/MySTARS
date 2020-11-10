package mystars.ui;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.valid.CourseCodeValidChecker;
import mystars.data.valid.IndexNumberValidChecker;
import mystars.data.valid.OptionValidChecker;
import mystars.data.valid.ValidChecker;
import mystars.parser.Parser;

import java.util.Arrays;
import java.util.Scanner;

public abstract class Ui {

    static final Scanner in = new Scanner(System.in);
    private static final String DOTTED_LINE = "------------------------------------------------------------";
    private static final String LOGO = String.join(System.lineSeparator(),
            " _______         _______________________ _______ _______ ",
            "(       )\\     /(  ____ \\__   __(  ___  |  ____ |  ____ \\",
            "| () () ( \\   / ) (    \\/  ) (  | (   ) | (    )| (    \\/",
            "| || || |\\ (_) /| (_____   | |  | (___) | (____)| (_____ ",
            "| |(_)| | \\   / (_____  )  | |  |  ___  |     __|_____  )",
            "| |   | |  ) (        ) |  | |  | (   ) | (\\ (        ) |",
            "| )   ( |  | |  /\\____) |  | |  | )   ( | ) \\ \\_/\\____) |",
            "|/     \\|  \\_/  \\_______)  )_(  |/     \\|/   \\__|_______)",
            "                                                         ", "Welcome!");


    static Parser parser;

    public Ui() {

    }

    public Ui(Parser parser) {
        Ui.parser = parser;
    }

    /**
     * Prints dotted line.
     */
    public void showLine() {
        printNicely();
        printNicely(DOTTED_LINE);
        printNicely();
    }

    public void showToUser(String message) {
        Arrays.stream(message.split(System.lineSeparator())).forEach(this::printNicely);
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        greetUser();
        showMenu();
        String fullCommand = in.nextLine();
        showLine();
        return fullCommand;
    }

    /**
     * Prints string with nice format.
     *
     * @param string String to print.
     */
    protected void printNicely(String string) {
        System.out.println(string);
    }

    protected void printNicely() {
        printNicely("");
    }

    public char[][] readUsernameAndPassword() {
        char[] username = readUsername();
        char[] password = readPassword();
        return new char[][]{username, password};
    }

    private char[] readUsername() {
        printNicely("Enter Username: ");
        return in.nextLine().replaceAll(Parser.ESCAPED_LINE_SEPARATOR, "").toUpperCase().toCharArray();
    }

    private char[] readPassword() {
        printNicely("Enter Password: ");

        if (System.console() == null) {
            return in.nextLine().toCharArray();
        }

        return System.console().readPassword();
    }

    public boolean askExit() {
        return parser.isYes(getUserInput("Do you want to exit? (Y/N)", new OptionValidChecker()));
    }

    public void showWelcome() {
        showToUser(LOGO);
    }

    public void showCourse(Course course) {
        printNicely(course.toString());
    }

    public void showVacancy(CourseList courses, String indexNumber) {
        for (Course course : courses.getCourses()) {
            if (course.getIndexNumber().equals(indexNumber)) {
                printNicely("The number of vacancy is " + course.getVacancies() + ".");
                return;
            }
        }
        printNicely("Index not found!");
    }

    public void showEmailSent() {
        showLine();
        printNicely("Added waitlisted student to course and sent email.");
    }

    String getUserInput(String message, ValidChecker validChecker) {
        printNicely(message);
        String line = in.nextLine().trim();
        while (!validChecker.isValid(line)) {
            printNicely("Invalid input!");
            printNicely(message);
            line = in.nextLine().trim();
        }
        return line;
    }

    public String getIndexNumber() {
        return getIndexNumber("");
    }

    public String getIndexNumber(String description) {
        return getUserInput("Enter " + description + "index number:", new IndexNumberValidChecker());
    }

    public String getCourseCode() {
        return getUserInput("Enter course code:", new CourseCodeValidChecker()).toUpperCase();
    }

    public abstract void showMenu();

    public abstract void greetUser();
}
