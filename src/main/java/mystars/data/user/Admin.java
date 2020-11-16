package mystars.data.user;

import mystars.data.shared.Gender;

/**
 * Admin class.
 */
public class Admin extends User {

    /**
     * Staff ID.
     */
    private String staffId;

    /**
     * Initializes Admin object.
     *
     * @param name Name of admin.
     * @param staffId Staff ID.
     * @param gender Gender of admin.
     * @param nationality Nationality of admin.
     * @param username Username of admin.
     */
    public Admin(String name, String staffId, Gender gender, String nationality, String username) {
        super(name, gender, nationality);
        this.staffId = staffId;
        super.setUsername(username.toCharArray());
    }

    /**
     * Empty constructor.
     */
    public Admin() {

    }

    /**
     * Copys details to combine login info and admin details.
     *
     * @param user Admin user to combine.
     */
    @Override
    public void copyDetails(User user) {
        setName(user.getName());
        this.staffId = ((Admin) user).staffId;
        setGender(user.getGender());
        setNationality(user.getNationality());
    }
}
