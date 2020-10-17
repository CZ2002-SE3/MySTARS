package mystars.data.user;

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
    public Admin copyDetails(User user) {
        ((Admin) user).setName(this.name);
        ((Admin) user).setStaffId(this.staffId);
        ((Admin) user).setGender(this.gender);
        ((Admin) user).setNationality(this.nationality);
        return (Admin) user;
    }
}
