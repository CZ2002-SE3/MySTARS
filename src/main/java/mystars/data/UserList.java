package mystars.data;

import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;
import mystars.data.user.UserType;
import mystars.login.PasswordHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class UserList {

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
     * Returns size of users.
     *
     * @return Length of users.
     */
    public int getSize() {
        return users.size();
    }

    public boolean isLoginValid(char[][] usernameAndPassword) throws MyStarsException {

        //TODO: Check if username and password is valid.
        for (User user : users) {
            if (Arrays.equals(user.getUsername(), usernameAndPassword[0])) {
                return new PasswordHandler().validatePassword(usernameAndPassword[1], user.getPassword());
            }
        }
        return false;
    }

    public User getUser(char[][] usernameAndPassword) throws MyStarsException {

        //TODO: Check what kind of user.
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

    public UserType getUserType(User user) {
        if (user instanceof Admin) {
            return UserType.ADMIN;
        } else if (user instanceof Student) {
            return UserType.STUDENT;
        }
        return null;
    }

    public void addDetails(ArrayList<User> students, ArrayList<User> admins) {
        ArrayList<User> userDetails = new ArrayList<>(students);
        userDetails.addAll(admins);
        for (User user : users) {
            for (User userDetail : userDetails) {
                if (String.valueOf(user.getUsername()).equals(String.valueOf(userDetail.getUsername()))) {
                    user = userDetail.copyDetails(user);
                    break;
                }
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
