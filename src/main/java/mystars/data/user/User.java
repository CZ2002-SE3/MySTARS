package mystars.data.user;

import mystars.data.shared.Gender;

/**
 * User class.
 */
public abstract class User {

    /**
     * Username of user.
     */
    private char[] username;

    /**
     * Password of user.
     */
    private char[] password;

    /**
     * Name of user.
     */
    private String name;

    /**
     * Gender of user.
     */
    private Gender gender;

    /**
     * Nationality of user.
     */
    private String nationality;

    /**
     * Initializes User object.
     *
     * @param name        Name of user.
     * @param gender      Gender of user.
     * @param nationality Nationality of user.
     */
    User(String name, Gender gender, String nationality) {
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
    }

    /**
     * Empty constructor.
     */
    public User() {

    }

    /**
     * Returns username of user.
     *
     * @return Username of user.
     */
    public char[] getUsername() {
        return username;
    }

    /**
     * Sets username of user.
     *
     * @param username Username to set.
     */
    public void setUsername(char[] username) {
        this.username = username;
    }

    /**
     * Returns password of user.
     *
     * @return Password of user.
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * Sets password of user.
     *
     * @param password Password to set.
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    /**
     * Checks if users' username are equal.
     *
     * @param obj User to check.
     * @return True if usernames are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        assert obj instanceof User;

        if (((User) obj).getUsername().length != username.length) {
            return false;
        }

        for (int i = 0; i < username.length; i++) {
            if (((User) obj).username[i] != username[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns name of user.
     *
     * @return Name of user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of user.
     *
     * @param name Name to set.
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Returns gender of user.
     *
     * @return Gender of user.
     */
    Gender getGender() {
        return gender;
    }

    /**
     * Sets gender of user.
     *
     * @param gender Gender to set.
     */
    void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Returns nationality of user.
     *
     * @return Nationality of user.
     */
    String getNationality() {
        return nationality;
    }

    /**
     * Sets nationality of user.
     *
     * @param nationality Nationality to set.
     */
    void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Copies details to combine login info and user details.
     *
     * @param user User to combine.
     */
    public abstract void copyDetails(User user);
}
