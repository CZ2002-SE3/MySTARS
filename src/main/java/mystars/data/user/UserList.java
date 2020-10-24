package mystars.data.user;

import mystars.data.exception.MyStarsException;
import mystars.data.password.PasswordHandler;

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
        for (int i = 0; i < users.size(); i++) {
            for (User userDetail : userDetails) {
                if (users.get(i).equals(userDetail)) {
                    users.get(i).copyDetails(userDetail);
                    break;
                }
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addStudent(Student student) throws MyStarsException {
        if (!isDuplicateStudent(student)) {
            users.add(student);
        } else {
            throw new MyStarsException("Duplicate username/matric number!");
        }
    }

    private boolean isDuplicateStudent(Student student) {
        for (User user : users) {
            if (user.equals(student)
                    || (user instanceof Student && ((Student) user).getMatricNo().equals(student.getMatricNo()))) {
                return true;
            }
        }
        return false;
    }
}
