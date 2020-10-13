package mystars.data;

import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;

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

    public boolean isLoginValid(char[][] usernameAndPassword) {

        //TODO: Check if username and password is valid.
        return String.valueOf(usernameAndPassword[1]).equals("password");
    }

    public String getUserType(char[][] usernameAndPassword) {

        //TODO: Check what kind of user.
        for(User user: users) {
            if (Arrays.equals(user.getUsername(), usernameAndPassword[0])) {
                if (Arrays.equals(user.getPassword(), usernameAndPassword[1])) {
                    if (user instanceof Student) {
                        return "student";
                    } else if (user instanceof Admin) {
                        return "admin";
                    }
                }
                else {
                    break;
                }
            }
        }
        return null;
    }
}
