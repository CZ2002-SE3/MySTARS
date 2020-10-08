package mystars.data;

import mystars.data.user.User;

import java.util.ArrayList;

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

    public boolean isLoginValid(String[] usernameAndPassword) {

        //TODO: Check if username and password is valid.
        return usernameAndPassword[1].equals("password");
    }
}
