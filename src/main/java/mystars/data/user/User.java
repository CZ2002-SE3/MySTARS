package mystars.data.user;

import mystars.data.shared.Gender;

public abstract class User {

    private char[] username;
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

    User(String name, Gender gender, String nationality) {
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
    }

    public User() {

    }

    public char[] getUsername() {
        return username;
    }

    public void setUsername(char[] username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

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

    public abstract void copyDetails(User user);

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Gender getGender() {
        return gender;
    }

    void setGender(Gender gender) {
        this.gender = gender;
    }

    String getNationality() {
        return nationality;
    }

    void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
