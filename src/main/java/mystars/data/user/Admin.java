package mystars.data.user;

import mystars.data.exception.MyStarsException;
import mystars.data.password.PasswordHandler;
import mystars.parser.Parser;

public class Admin extends User {

    private String name;
    private String staffId;
    private char gender;
    private String nationality;

    public Admin(String name, String staffId, char gender, String nationality, String username) {
        this.name = name;
        this.staffId = staffId;
        this.gender = gender;
        this.nationality = nationality;
        super.setUsername(username.toCharArray());
    }

    public Admin() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public void copyDetails(User user) {
        setName(((Admin) user).getName());
        setStaffId(((Admin) user).getStaffId());
        setGender(((Admin) user).getGender());
        setNationality(((Admin) user).getNationality());
    }

    @Override
    public String getFormattedUserInfo() throws MyStarsException {
        return String.join(Parser.LINE_SEPARATOR, String.valueOf(getUsername())
                , new PasswordHandler().generatePBKDF2String(getPassword()), "admin");
    }
}
