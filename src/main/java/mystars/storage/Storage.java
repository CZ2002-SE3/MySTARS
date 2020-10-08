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

    private static final String FOLDER = "data";
    private static final String USERS_FILE = "users.txt";
    private static final String COURSES_FILE = "courses.txt";
    private static final String STUDENTS_FILE = "students.txt";

    /**
     * Loads users, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of users.
     * @throws MyStarsException If there is problem reading file.
     */
    public ArrayList<User> loadUsers(Parser parser) throws MyStarsException {
        Path path = Paths.get(FOLDER, USERS_FILE);
        ArrayList<User> users = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    User user = parser.readUser(line);
                    users.add(user);
                }
            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }

        return users;
    }

}
