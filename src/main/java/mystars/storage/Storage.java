package mystars.storage;

import mystars.data.exception.MyStarsException;
import mystars.data.user.User;
import mystars.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    public static final String LOAD_ERROR = "I am unable to load file.";
    private static final String READ_ERROR = "I am unable to read file.";

    private final String folder;
    private final String file;

    /**
     * Initializes path of folder and file.
     *
     * @param filePath Full file path.
     */
    public Storage(String filePath) {
        int index = filePath.lastIndexOf("/");
        folder = filePath.substring(0, index);
        file = filePath.substring(index + 1);
    }

    /**
     * Loads users, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of users.
     * @throws MyStarsException If there is problem reading file.
     */
    public ArrayList<User> load() throws MyStarsException {
        Path path = Paths.get(folder, file);
        ArrayList<User> users = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    User user = Parser.readUser(line);
                    users.add(user);
                }
            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }

        return users;
    }

}
