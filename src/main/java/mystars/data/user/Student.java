package mystars.data.user;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.password.PasswordHandler;
import mystars.data.shared.Gender;
import mystars.parser.Parser;

public class Student extends User {

    public static final int MAX_AU_ALLOWED = 21;

    private String name;
    private String matricNo;
    private Gender gender;
    private String nationality;
    private String courseOfStudy;
    private int yearOfStudy;
    private CourseList registeredCourses;
    private CourseList waitlistedCourses;

    public Student(String name, String matricNo, Gender gender, String nationality, String username, String courseOfStudy
            , int yearOfStudy) {
        this.name = name;
        this.matricNo = matricNo;
        this.gender = gender;
        this.nationality = nationality;
        this.courseOfStudy = courseOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.registeredCourses = new CourseList();
        this.waitlistedCourses = new CourseList();
        super.setUsername(username.toCharArray());
    }

    public Student(String name, String matricNo, Gender gender, String nationality, String courseOfStudy, int yearOfStudy
            , char[] username, char[] password) {
        this.name = name;
        this.matricNo = matricNo;
        this.gender = gender;
        this.nationality = nationality;
        this.courseOfStudy = courseOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.registeredCourses = new CourseList();
        this.waitlistedCourses = new CourseList();
        super.setUsername(username);
        super.setPassword(password);
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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
    public void copyDetails(User user) {
        setName(((Student) user).getName());
        setMatricNo(((Student) user).getMatricNo());
        setGender(((Student) user).getGender());
        setNationality(((Student) user).getNationality());
        setCourseOfStudy(((Student) user).getCourseOfStudy());
        setYearOfStudy(((Student) user).getYearOfStudy());
        setRegisteredCourses(((Student) user).getRegisteredCourses());
        setWaitlistedCourses(((Student) user).getWaitlistedCourses());
    }

    @Override
    public String getFormattedUserInfo() throws MyStarsException {
        return String.join(Parser.LINE_SEPARATOR, String.valueOf(getUsername())
                , new PasswordHandler().generatePBKDF2String(getPassword()), "student");
    }

    public void addCourseToRegistered(Course courseToAdd) throws MyStarsException {
        checkCoursesInList(courseToAdd);
        registeredCourses.addCourse(courseToAdd);
    }

    public void addCourseToWaitlisted(Course courseToAdd) throws MyStarsException {
        checkCoursesInList(courseToAdd);
        waitlistedCourses.addCourse(courseToAdd);
    }

    public void checkCoursesInList(Course courseToAdd) throws MyStarsException {
        if (registeredCourses.isCourseInList(courseToAdd)) {
            throw new MyStarsException("Course already present in registered courses.");
        }
        if (waitlistedCourses.isCourseInList(courseToAdd)) {
            throw new MyStarsException("Course already present in waitlisted courses.");
        }
        if (registeredCourses.isClash(courseToAdd)) {
            throw new MyStarsException("Course clashes with registered courses.");
        }
        if (waitlistedCourses.isClash(courseToAdd)) {
            throw new MyStarsException("Course clashes with waitlisted courses.");
        }
    }

    public void dropCourse(Course courseToDrop) throws MyStarsException {
        checkCourseRegistered(courseToDrop);
        registeredCourses.dropCourse(courseToDrop);
    }

    public void checkCourseRegistered(Course courseToDrop) throws MyStarsException {
        if (!registeredCourses.isCourseInList(courseToDrop)) {
            throw new MyStarsException("Course does not exist in registered courses.");
        }
    }

    public String getFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, name, matricNo, String.valueOf(gender), nationality
                , String.valueOf(getUsername()), courseOfStudy + Parser.TILDE_SEPARATOR + yearOfStudy);
    }

    @Override
    public String toString() {
        return String.join(", ", name, matricNo, String.valueOf(gender), nationality);
    }

    public void modifyCourse(Course courseToModify) {
        //TODO: check clash & and if clash drop course?
        //TODO: check if exceed AU count
        for (int i = 0; i < registeredCourses.getCourses().size(); i++) {
            if (registeredCourses.getCourses().get(i).equals(courseToModify)) {
                registeredCourses.getCourses().set(i, courseToModify);
                break;
            }
        }
    }
}
