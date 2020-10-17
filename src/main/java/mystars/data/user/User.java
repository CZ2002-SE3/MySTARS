package mystars.data.user;

public abstract class User {

    private char[] username;
    private char[] password;

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

        for (int i = 0; i < password.length; i++) {
            if (((User) obj).username[i] == username[i]) {
                return false;
            }
        }

        return true;
    }

    public abstract User copyDetails(User user);
}
