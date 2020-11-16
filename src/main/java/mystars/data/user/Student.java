package mystars.data.user;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.shared.Gender;
import mystars.parser.Parser;

public class Student extends User {

    public static final String FORMAT = "%-30s %-15s %-10s %-15s";
    public static final int MAX_AU_ALLOWED = 21;
    private static final String EXCEED_AU_ERROR = "Exceed maximum AU allowed!";
    private static final String TIMING_CLASH_ERROR = "Timings clash!";
    private static final String COURSE_IN_REGISTERED_ERROR = "Course already present in registered courses.";
    private static final String COURSE_IN_WAITLISTED_ERROR = "Course already present in waitlisted courses.";
    private static final String COURSE_NOT_IN_REGISTERED_ERROR = "Course does not exist in registered courses.";
    private static final String COURSE_NOT_IN_WAITLISTED_ERROR = "Course does not exist in waitlisted courses.";
    private static final String REGISTERED_CLASH_ERROR = "Course clashes with registered courses.";
    private static final String WAITLISTED_CLASH_ERROR = "Course clashes with waitlisted courses.";
    private String matricNo;
    private String courseOfStudy;
    private int yearOfStudy;
    private CourseList registeredCourses;
    private CourseList waitlistedCourses;
    private String email;

    public Student(String name, String matricNo, Gender gender, String nationality, String username,
                   String courseOfStudy, int yearOfStudy, String email) {
        super(name, gender, nationality);
        this.matricNo = matricNo;
        this.courseOfStudy = courseOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.registeredCourses = new CourseList();
        this.waitlistedCourses = new CourseList();
        this.email = email;
        super.setUsername(username.toCharArray());
    }

    public Student(String name, String matricNo, Gender gender, String nationality, String courseOfStudy,
                   int yearOfStudy, String email, char[] username, char[] password) {
        super(name, gender, nationality);
        this.matricNo = matricNo;
        this.courseOfStudy = courseOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.registeredCourses = new CourseList();
        this.waitlistedCourses = new CourseList();
        this.email = email;
        super.setUsername(username);
        super.setPassword(password);
    }

    public Student() {

    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCourseInRegistered(Course courseToAdd) {
        return registeredCourses.isCourseInList(courseToAdd);
    }

    public boolean isCourseInWaitlisted(Course courseToAdd) {
        return waitlistedCourses.isCourseInList(courseToAdd);
    }

    public String getFormattedUserInfo() {
        return String.join(Parser.LINE_SEPARATOR, String.valueOf(getUsername()),
                String.valueOf(getPassword()), "student");
    }

    public String getFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, getName(), matricNo, String.valueOf(getGender()),
                getNationality(), String.valueOf(getUsername()), courseOfStudy + Parser.TILDE_SEPARATOR + yearOfStudy,
                email);
    }

    @Override
    public String toString() {
        return String.format(FORMAT, getName(), matricNo, getGender(), getNationality());
    }

    public void addCourseToRegistered(Course courseToAdd) throws MyStarsException {
        if (courseToAdd.getNumOfAUs() + registeredCourses.getTotalNoOfAUs() > MAX_AU_ALLOWED) {
            throw new MyStarsException(EXCEED_AU_ERROR);
        }
        checkCoursesInList(courseToAdd);
        registeredCourses.addCourse(courseToAdd);
    }

    public void addCourseToWaitlisted(Course courseToAdd) throws MyStarsException {
        checkCoursesInList(courseToAdd);
        waitlistedCourses.addCourse(courseToAdd);
    }

    public void dropRegisteredCourse(Course courseToDrop) throws MyStarsException {
        if (registeredCourses.isCourseInList(courseToDrop)) {
            registeredCourses.dropCourse(courseToDrop);
        } else {
            throw new MyStarsException(COURSE_NOT_IN_REGISTERED_ERROR);
        }
    }

    public void dropWaitlistedCourse(Course courseToDrop) throws MyStarsException {
        if (waitlistedCourses.isCourseInList(courseToDrop)) {
            waitlistedCourses.dropCourse(courseToDrop);
        } else {
            throw new MyStarsException(COURSE_NOT_IN_WAITLISTED_ERROR);
        }
    }

    @Override
    public void copyDetails(User user) {
        setName(((Student) user).getName());
        setMatricNo(((Student) user).getMatricNo());
        setGender(((Student) user).getGender());
        setNationality(((Student) user).getNationality());
        setCourseOfStudy(((Student) user).getCourseOfStudy());
        setYearOfStudy(((Student) user).getYearOfStudy());
        setEmail(((Student) user).getEmail());
        setRegisteredCourses(((Student) user).getRegisteredCourses());
        setWaitlistedCourses(((Student) user).getWaitlistedCourses());
    }

    public void checkCoursesInList(Course courseToAdd) throws MyStarsException {
        if (isCourseInRegistered(courseToAdd)) {
            throw new MyStarsException(COURSE_IN_REGISTERED_ERROR);
        }
        if (isCourseInWaitlisted(courseToAdd)) {
            throw new MyStarsException(COURSE_IN_WAITLISTED_ERROR);
        }
        if (registeredCourses.isClash(courseToAdd)) {
            throw new MyStarsException(REGISTERED_CLASH_ERROR);
        }
        if (waitlistedCourses.isClash(courseToAdd)) {
            throw new MyStarsException(WAITLISTED_CLASH_ERROR);
        }
    }

    public void modifyRegisteredCourse(Course courseToModify) throws MyStarsException {
        for (int i = 0; i < registeredCourses.getCourses().size(); i++) {
            if (registeredCourses.getCourses().get(i).equals(courseToModify)) {
                Course removedCourse = registeredCourses.getCourses().remove(i);
                if (courseToModify.getNumOfAUs() + registeredCourses.getTotalNoOfAUs() > MAX_AU_ALLOWED) {
                    registeredCourses.getCourses().add(removedCourse);
                    throw new MyStarsException(EXCEED_AU_ERROR);
                }
                for (Course registered : registeredCourses.getCourses()) {
                    if (registered.isClash(courseToModify)) {
                        registeredCourses.getCourses().add(removedCourse);
                        throw new MyStarsException(TIMING_CLASH_ERROR);
                    }
                }
                registeredCourses.getCourses().add(courseToModify);
                break;
            }
        }
    }

    public void modifyWaitlistedCourse(Course courseToModify) {
        for (int i = 0; i < waitlistedCourses.getCourses().size(); i++) {
            if (waitlistedCourses.getCourses().get(i).equals(courseToModify)) {
                waitlistedCourses.getCourses().set(i, courseToModify);
                break;
            }
        }
    }
}
