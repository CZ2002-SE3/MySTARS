package mystars.parser;

import mystars.commands.Command;
import mystars.commands.ExitCommand;
import mystars.commands.LogoutCommand;
import mystars.data.exception.MyStarsException;
import mystars.data.user.User;

/**
 * Parses user input and file.
 */
public class Parser {

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
        User user = null;
        return user;
    }

    public boolean isExit(String fullCommand) {
        return fullCommand.trim().equalsIgnoreCase(ExitCommand.COMMAND_WORD);
    }
}
