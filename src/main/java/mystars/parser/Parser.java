package mystars.parser;

import mystars.commands.AddCourseCommand;
import mystars.commands.Command;
import mystars.commands.ExitCommand;
import mystars.commands.LogoutCommand;
import mystars.data.course.Course;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;

/**
 * Parses user input and file.
 */
public class Parser {

    public static final String SEPARATOR = "\\|"; //used to separate each attribute of an object

    /**
     * Parses user input, and returns corresponding command.
     *
     * @param fullCommand String of user input to parse.
     * @return Command to execute.
     * @throws MyStarsException If command is invalid.
     */
    public Command parse(String fullCommand) throws MyStarsException {
        Command command;
        switch (fullCommand.trim()) {
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case LogoutCommand.COMMAND_WORD:
            command = new LogoutCommand();
            break;
        default:
            throw new MyStarsException(Command.COMMAND_ERROR);
        }

        return command;
    }

    public Command parseStudent(String fullCommand) throws MyStarsException {
        Command command;
        switch (fullCommand.trim()) {
            case AddCourseCommand.COMMAND_WORD:
                command = new AddCourseCommand();
                break;
            case ExitCommand.COMMAND_WORD:
                command = new ExitCommand();
                break;
            case LogoutCommand.COMMAND_WORD:
                command = new LogoutCommand();
                break;
            default:
                throw new MyStarsException(Command.COMMAND_ERROR);
        }

        return command;
    }

    /**
     * Reads users from file.
     *
     * @param line Line of user to read.
     * @return Users of corresponding line.
     * @throws MyStarsException If user is incomplete or invalid.
     */
    public User readUser(String line) throws MyStarsException {

        //TODO: Read users from file.
        User user = null;
        String[] userSplit = line.split(SEPARATOR);
        String username = userSplit[0].trim();
        String password = userSplit[1].trim();
        String type = userSplit[2].trim();
        /* switch (type) {
        case "student":
            user = new Student();
            break;
        case "admin":
            user = new Admin();
            break;
        default:
            throw new MyStarsException("Invalid user type: " + type);
        }*/


        user.setUsername(username.toCharArray());
        user.setPassword(password.toCharArray());

        return user;
    }

    /**
     * Reads courses from file.
     *
     * @param line Line of course to read.
     * @return Courses of corresponding line.
     * @throws MyStarsException If course vacancy/number of AUs is not an integer.
     */
    public Course readCourse(String line) throws MyStarsException {

        //TODO: Read courses from file.
        String[] courseSplit = line.split(SEPARATOR);
        String courseCode = courseSplit[0].trim();
        String school = courseSplit[1].trim();
        String indexNumber = courseSplit[2].trim();
        int vacancy, numAu;

        try {
            vacancy = Integer.parseInt(courseSplit[3].trim());
            numAu = Integer.parseInt(courseSplit[4].trim());
        } catch (NumberFormatException e) {
            throw new MyStarsException("Vacancy must be an integer.");
        }

        return new Course(courseCode, school, indexNumber, vacancy, numAu);
    }

    /**
     * Reads students from file.
     *
     * @param line Line of student to read.
     * @return Students of corresponding line.
     */
    public Student readStudent(String line) throws MyStarsException {

        //TODO: Read courses from file.
        String[] studentSplit = line.split(SEPARATOR);
        String name = studentSplit[0].trim();
        String matricNo = studentSplit[1].trim();
        char gender = studentSplit[2].trim().charAt(0);
        String nationality = studentSplit[3].trim();
        String username = studentSplit[4].trim();

        return new Student(name, matricNo, gender, nationality, username);
    }

    /**
     * Reads admins from file.
     *
     * @param line Line of admin to read.
     * @return Admins of corresponding line.
     */
    public Admin readAdmin(String line) throws MyStarsException {

        //TODO: Read courses from file.
        String[] adminSplit = line.split(SEPARATOR);
        String name = adminSplit[0].trim();
        String staffId = adminSplit[1].trim();
        char gender = adminSplit[2].trim().charAt(0);
        String nationality = adminSplit[3].trim();
        String username = adminSplit[4].trim();

        return new Admin(name, staffId, gender, nationality, username);
    }

    public boolean isExit(String fullCommand) {
        return fullCommand.trim().equalsIgnoreCase(ExitCommand.COMMAND_WORD);
    }
}
