package mystars.data.user;

import mystars.data.CourseList;

public class Student extends User {

    private String name;
    private String matricNo;
    private char gender;
    private String nationality;
    private String courseOfStudy;
    private int yearOfStudy;
    private CourseList registeredCourses;
    private CourseList waitlistedCourses;

    public Student(String name, String matricNo, char gender, String nationality, String username, String courseOfStudy, int yearOfStudy, CourseList registeredCourses, CourseList waitlistedCourses) {
        this.name = name;
        this.matricNo = matricNo;
        this.gender = gender;
        this.nationality = nationality;
        this.courseOfStudy = courseOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.registeredCourses = registeredCourses;
        this.waitlistedCourses = waitlistedCourses;
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

    public CourseList getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(CourseList registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public CourseList getWaitlistedCourses() {
        return waitlistedCourses;
    }

    public void setWaitlistedCourses(CourseList waitlistedCourses) {
        this.waitlistedCourses = waitlistedCourses;
    }

    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public Student copyDetails(User user) {
        ((Student) user).setName(this.name);
        ((Student) user).setMatricNo(this.matricNo);
        ((Student) user).setGender(this.gender);
        ((Student) user).setNationality(this.nationality);
        ((Student) user).setCourseOfStudy(this.courseOfStudy);
        ((Student) user).setYearOfStudy(this.yearOfStudy);
        ((Student) user).setRegisteredCourses(this.registeredCourses);
        ((Student) user).setWaitlistedCourses(this.waitlistedCourses);
        return (Student) user;
    }
}
