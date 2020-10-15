package mystars.data.user;

public class Student extends User {

    private String name;
    private String matricNo;
    private char gender;
    private String nationality;

    public Student(String name, String matricNo, char gender, String nationality, String username) {
        this.name = name;
        this.matricNo = matricNo;
        this.gender = gender;
        this.nationality = nationality;
        super.setUsername(username.toCharArray());
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
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

}
