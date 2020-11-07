package mystars.data.user;

import mystars.data.exception.MyStarsException;
import mystars.data.password.PasswordHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class UserList {

    private static final String DUPLICATE_MATRIC_ERROR = "Duplicate matric number!";
    private static final String DUPLICATE_USERNAME_ERROR = "Duplicate username!";

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

    public boolean isLoginValid(char[][] usernameAndPassword) throws MyStarsException {

        for (User user : users) {
            if (Arrays.equals(user.getUsername(), usernameAndPassword[0])) {
                return new PasswordHandler().validatePassword(usernameAndPassword[1], user.getPassword());
            }
        }
        return false;
    }

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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addStudent(Student student) {
        users.add(student);
    }

    public void checkDuplicateMatricNo(String line) throws MyStarsException {
        if (isDuplicateMatricNo(line)) {
            throw new MyStarsException(DUPLICATE_MATRIC_ERROR);
        }
    }

    public void checkDuplicateUsername(char[] line) throws MyStarsException {
        if (isDuplicateUsername(line)) {
            throw new MyStarsException(DUPLICATE_USERNAME_ERROR);
        }
    }

    public boolean isDuplicateMatricNo(String line) {
        for (User user : users) {
            if (user instanceof Student && ((Student) user).getMatricNo().equals(line)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDuplicateUsername(char[] line) {
        for (User user : users) {
            if (Arrays.equals(user.getUsername(), line)) {
                return true;
            }
        }
        return false;
    }
}
