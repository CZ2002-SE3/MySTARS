package mystars.ui;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.valid.CourseCodeValidChecker;
import mystars.data.valid.IndexNumberValidChecker;
import mystars.data.valid.OptionValidChecker;
import mystars.data.valid.ValidChecker;
import mystars.parser.Parser;

import java.util.Scanner;

/**
 * User interface class.
 */
public abstract class Ui {

    /**
     * Scanner for user input.
     */
    static final Scanner IN = new Scanner(System.in);

    /**
     * Dotted line string.
     */
    private static final String DOTTED_LINE = "------------------------------------------------------------";

    /**
     * MySTARS logo.
     */
    private static final String LOGO = String.join(System.lineSeparator(),
            " _______         _______________________ _______ _______ ",
            "(       )\\     /(  ____ \\__   __(  ___  |  ____ |  ____ \\",
            "| () () ( \\   / ) (    \\/  ) (  | (   ) | (    )| (    \\/",
            "| || || |\\ (_) /| (_____   | |  | (___) | (____)| (_____ ",
            "| |(_)| | \\   / (_____  )  | |  |  ___  |     __|_____  )",
            "| |   | |  ) (        ) |  | |  | (   ) | (\\ (        ) |",
            "| )   ( |  | |  /\\____) |  | |  | )   ( | ) \\ \\_/\\____) |",
            "|/     \\|  \\_/  \\_______)  )_(  |/     \\|/   \\__|_______)",
            "                                                         ");

    /**
     * Welcome message.
     */
    private static final String WELCOME_MESSAGE = "Welcome!";

    /**
     * Course table header.
     */
    private static final String COURSE_HEADER = String.format(Course.FORMAT, "Course Code", "School", "Index No.",
            "AU");

    /**
     * Parser to parse user input.
     */
    static Parser parser;

    /**
     * Initializes Ui with parser.
     *
     * @param parser Parser object.
     */
    Ui(Parser parser) {
        Ui.parser = parser;
    }

    /**
     * Empty constructor.
     */
    Ui() {

    }

    /**
     * Prints dotted line.
     */
    public void showLine() {
        printNicely();
        printNicely(DOTTED_LINE);
        printNicely();
    }

    /**
     * Shows message to user.
     *
     * @param message Message to show.
     */
    public void showToUser(String message) {
        for (String line : message.split(System.lineSeparator())) {
            printNicely(line);
        }
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        showLine();
        showToUser("Bye! See you again soon!");
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        greetUser();
        showMenu();
        String fullCommand = IN.nextLine();
        showLine();
        return fullCommand;
    }

    /**
     * Prints string with nice format.
     *
     * @param string String to print.
     */
    void printNicely(String string) {
        System.out.println(string);
    }

    /**
     * Prints new line with nice format.
     */
    void printNicely() {
        printNicely("");
    }

    /**
     * Returns username from user.
     *
     * @return Username from user.
     */
    private char[] readUsername() {
        printNicely("Enter Username:");
        return IN.nextLine().replaceAll(Parser.ESCAPED_LINE_SEPARATOR, "").toUpperCase().toCharArray();
    }

    /**
     * Returns password from user.
     *
     * @return Password from user.
     */
    private char[] readPassword() {
        printNicely("Enter Password:");

        if (System.console() == null) {
            return IN.nextLine().toCharArray();
        }

        return System.console().readPassword();
    }

    /**
     * Returns username and password from user.
     *
     * @return Username and password.
     */
    public char[][] readUsernameAndPassword() {
        char[] username = readUsername();
        char[] password = readPassword();
        return new char[][]{username, password};
    }

    /**
     * Asks user for exit and return.
     *
     * @return True if user want to exit, false otherwise.
     */
    public boolean askExit() {
        return parser.isYes(getUserInput("Do you want to exit? (Y/N)", new OptionValidChecker()));
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showToUser(LOGO);
        showToUser(WELCOME_MESSAGE);
    }

    /**
     * Prints course.
     *
     * @param course Course to print.
     */
    public void showCourse(Course course) {
        printNicely(COURSE_HEADER);
        printNicely(course.toString());
    }

    /**
     * Prints list of courses.
     *
     * @param courses Courses to print.
     */
    public void showCourseList(CourseList courses) {
        printNicely();
        printNicely("Here is the courses list:");
        printNicely(COURSE_HEADER);
        for (Course course : courses.getCourses()) {
            printNicely(course.toString());
        }
    }

    /**
     * Returns course vacancies.
     *
     * @param courses     List of courses.
     * @param indexNumber Index number of course to show vacancy.
     */
    public void showVacancy(CourseList courses, String indexNumber) {
        for (Course course : courses.getCourses()) {
            if (course.getIndexNumber().equals(indexNumber)) {
                printNicely("The number of vacancy is " + course.getVacancies() + ".");
                return;
            }
        }
        printNicely("Index not found!");
    }

    /**
     * Show email sent message.
     */
    public void showEmailSent() {
        showLine();
        printNicely("Added waitlisted student to course and sent email.");
    }

    /**
     * Prints closed message.
     */
    public void showClosed() {
        showLine();
        showToUser("MyStars is closed for students...");
    }

    /**
     * Prints logout message.
     */
    public void showLogout() {
        showToUser("You have successfully logged out!");
    }

    /**
     * Returns user input.
     *
     * @param message      Message to show.
     * @param validChecker Validity checker.
     * @return Input from user.
     */
    String getUserInput(String message, ValidChecker validChecker) {
        printNicely(message);
        String line = IN.nextLine().trim();
        while (!validChecker.isValid(line)) {
            printNicely("Invalid input!");
            printNicely(message);
            line = IN.nextLine().trim();
        }
        return line;
    }

    /**
     * Gets index number from user.
     *
     * @return Index number.
     */
    public String getIndexNumber() {
        return getIndexNumber("");
    }

    /**
     * Gets index number from user.
     *
     * @param description Description of index number.
     * @return Index number.
     */
    public String getIndexNumber(String description) {
        return getUserInput("Enter " + description + "index number:", new IndexNumberValidChecker());
    }

    /**
     * Gets course code from user.
     *
     * @return Course code from user.
     */
    public String getCourseCode() {
        return getUserInput("Enter course code:", new CourseCodeValidChecker()).toUpperCase();
    }

    /**
     * Prints menu.
     */
    public abstract void showMenu();

    /**
     * Greets user.
     */
    public abstract void greetUser();
}
