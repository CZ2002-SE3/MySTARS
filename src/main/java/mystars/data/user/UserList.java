package mystars.data.user;

import mystars.data.exception.MyStarsException;
import mystars.data.password.PasswordHandler;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * List of users.
 */
public class UserList {

    /**
     * Duplicate matriculation number error message.
     */
    private static final String DUPLICATE_MATRIC_ERROR = "Duplicate matric number!";

    /**
     * Duplicate username error message.
     */
    private static final String DUPLICATE_USERNAME_ERROR = "Duplicate username!";

    /**
     * List of users.
     */
    private final ArrayList<User> users;

    /**
     * Initializes users ArrayList.
     */
    public UserList() {
        users = new ArrayList<>();
    }

    /**
     * Initializes users ArrayList using parameter.
     *
     * @param users Users list to use.
     */
    public UserList(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Gets user with login credentials.
     *
     * @param usernameAndPassword Username and password.
     * @return User with given login credentials.
     * @throws MyStarsException If there is issue validating password.
     */
    public User getUser(char[][] usernameAndPassword) throws MyStarsException {
        for (User user : users) {
            if (Arrays.equals(user.getUsername(), usernameAndPassword[0])) {
                if (new PasswordHandler().validatePassword(usernameAndPassword[1], user.getPassword())) {
                    return user;
                } else {
                    break;
                }
            }
        }
        return null;
    }

    /**
     * Returns list of users.
     *
     * @return List of users.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Returns if login is valid.
     *
     * @param usernameAndPassword Username and password.
     * @return If login is valid.
     * @throws MyStarsException If there is issue validating password.
     */
    public boolean isLoginValid(char[][] usernameAndPassword) throws MyStarsException {
        for (User user : users) {
            if (Arrays.equals(user.getUsername(), usernameAndPassword[0])) {
                return new PasswordHandler().validatePassword(usernameAndPassword[1], user.getPassword());
            }
        }
        return false;
    }

    /**
     * Returns if matriculation number is a duplicate.
     *
     * @param matricNo matriculation number to check.
     * @return True if it is a duplicate, false otherwise.
     */
    private boolean isDuplicateMatricNo(String matricNo) {
        for (User user : users) {
            if (user instanceof Student && ((Student) user).getMatricNo().equals(matricNo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if username is a duplicate.
     *
     * @param username Username to check.
     * @return True if username is a duplicate, false otherwise.
     */
    private boolean isDuplicateUsername(char[] username) {
        for (User user : users) {
            if (Arrays.equals(user.getUsername(), username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds details of users into original list.
     *
     * @param students Student list.
     * @param admins   Admin list.
     */
    public void addDetails(ArrayList<User> students, ArrayList<User> admins) {
        ArrayList<User> userDetails = new ArrayList<>(students);
        userDetails.addAll(admins);
        for (User user : users) {
            for (User userDetail : userDetails) {
                if (user.equals(userDetail)) {
                    user.copyDetails(userDetail);
                    break;
                }
            }
        }
    }

    /**
     * Adds user to list.
     *
     * @param user User to add.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Checks if matriculation number is duplicated.
     *
     * @param matricNo Matriculation number to check.
     * @throws MyStarsException If it is a duplicate.
     */
    public void checkDuplicateMatricNo(String matricNo) throws MyStarsException {
        if (isDuplicateMatricNo(matricNo)) {
            throw new MyStarsException(DUPLICATE_MATRIC_ERROR);
        }
    }

    /**
     * Checks if username is duplicated.
     *
     * @param username Username to check.
     * @throws MyStarsException If it is a duplicate.
     */
    public void checkDuplicateUsername(char[] username) throws MyStarsException {
        if (isDuplicateUsername(username)) {
            throw new MyStarsException(DUPLICATE_USERNAME_ERROR);
        }
    }
}
