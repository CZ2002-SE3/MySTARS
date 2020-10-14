package mystars.data.user;

public class Student extends User {

    private String name;
    private String matricNo;
    private char gender;
    private String nationality;
    private String username;

    public Student(String name, String matricNo, char gender, String nationality, String username) {
        this.name = name;
        this.matricNo = matricNo;
        this.gender = gender;
        this.nationality = nationality;
        this.username = username;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNo() { return matricNo; }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public char getGender() { return gender; }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getNationality() { return nationality; }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /*public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }*/
}
