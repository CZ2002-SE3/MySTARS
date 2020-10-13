package mystars.parser;

import mystars.commands.Command;
import mystars.commands.ExitCommand;
import mystars.commands.LogoutCommand;
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

    /**
     * Reads users from file.
     *
     * @param line Line of user to read.
     * @return Users of corresponding line.
     * @throws MyStarsException If user is incomplete or invalid.
     */
    public User readUser(String line) throws MyStarsException {

        //TODO: Read users from file.
        User user;
        String[] userSplit = line.split(SEPARATOR);
        String username = userSplit[0].trim();
        String password = userSplit[1].trim();
        String type = userSplit[2].trim();
        switch (type) {
        case "student":
            user = new Student();
            break;
        case "admin":
            user = new Admin();
            break;
        default:
            throw new MyStarsException("Invalid user type: " + type);
        }


        user.setUsername(username.toCharArray());
        user.setPassword(password.toCharArray());

        return user;
    }

    public boolean isExit(String fullCommand) {
        return fullCommand.trim().equalsIgnoreCase(ExitCommand.COMMAND_WORD);
    }
}
