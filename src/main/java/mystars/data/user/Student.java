package mystars.data.user;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.shared.Gender;
import mystars.parser.Parser;

/**
 * Student class.
 */
public class Student extends User {

    /**
     * Table format of student.
     */
    public static final String FORMAT = "%-30s %-15s %-10s %-15s";

    /**
     * Maximum number of AUs allowed.
     */
    public static final int MAX_AU_ALLOWED = 21;


    /**
     * Exceed number of AUs error message.
     */
    private static final String EXCEED_AU_ERROR = "Exceed maximum AU allowed!";

    /**
     * Timing clash error message.
     */
    private static final String TIMING_CLASH_ERROR = "Timings clash!";

    /**
     * Course already in registered error message.
     */
    private static final String COURSE_IN_REGISTERED_ERROR = "Course already present in registered courses.";

    /**
     * Course already in waitlisted error message.
     */
    private static final String COURSE_IN_WAITLISTED_ERROR = "Course already present in waitlisted courses.";

    /**
     * Course not in registered error message.
     */
    private static final String COURSE_NOT_IN_REGISTERED_ERROR = "Course does not exist in registered courses.";

    /**
     * Course not in waitlisted error message.
     */
    private static final String COURSE_NOT_IN_WAITLISTED_ERROR = "Course does not exist in waitlisted courses.";

    /**
     * Course clashes with registered courses error message.
     */
    private static final String REGISTERED_CLASH_ERROR = "Course clashes with registered courses.";

    /**
     * Course clashes with waitlisted courses error message.
     */
    private static final String WAITLISTED_CLASH_ERROR = "Course clashes with waitlisted courses.";

    /**
     * Matriculation number of student.
     */
    private String matricNo;

    /**
     * Course of study.
     */
    private String courseOfStudy;

    /**
     * Year of study.
     */
    private int yearOfStudy;

    /**
     * List of registered courses.
     */
    private CourseList registeredCourses;

    /**
     * List of waitlisted courses.
     */
    private CourseList waitlistedCourses;

    /**
     * Email address.
     */
    private String email;

    /**
     * Initializes Student object.
     *
     * @param name          Name of student.
     * @param matricNo      Matriculation number of student.
     * @param gender        Gender of student.
     * @param nationality   Nationality of student.
     * @param username      Username of student.
     * @param courseOfStudy Course of study.
     * @param yearOfStudy   Year of study.
     * @param email         Email address of student.
     */
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

    /**
     * Initializes Student object.
     *
     * @param name          Name of student.
     * @param matricNo      Matriculation number of student.
     * @param gender        Gender of student.
     * @param nationality   Nationality of student.
     * @param courseOfStudy Course of study.
     * @param yearOfStudy   Year of study.
     * @param email         Email address of student.
     * @param username      Username of student.
     * @param password      Password of student.
     */
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

    /**
     * Empty constructor.
     */
    public Student() {

    }

    /**
     * Returns matriculation number of student.
     *
     * @return Matriculation number of student.
     */
    public String getMatricNo() {
        return matricNo;
    }

    /**
     * Returns registered courses.
     *
     * @return List of registered courses.
     */
    public CourseList getRegisteredCourses() {
        return registeredCourses;
    }

    /**
     * Returns waitlisted courses.
     *
     * @return List of waitlisted courses.
     */
    public CourseList getWaitlistedCourses() {
        return waitlistedCourses;
    }

    /**
     * Returns course of study.
     *
     * @return Course of study.
     */
    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    /**
     * Returns year of study.
     *
     * @return Year of study.
     */
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    /**
     * Returns email address.
     *
     * @return Email address of student.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns if course is in list of registered courses.
     *
     * @param course Course to check.
     * @return True is course is in list of registered courses, false otherwise.
     */
    public boolean isCourseInRegistered(Course course) {
        return registeredCourses.isCourseInList(course);
    }

    /**
     * Returns if course is in list of waitlisted courses.
     *
     * @param course Course to check.
     * @return True is course is in list of waitlisted courses, false otherwise.
     */
    public boolean isCourseInWaitlisted(Course course) {
        return waitlistedCourses.isCourseInList(course);
    }

    /**
     * Returns string for storage in users.txt
     *
     * @return Formatted string for storage in users.txt
     */
    public String getFormattedUserInfo() {
        return String.join(Parser.LINE_SEPARATOR, String.valueOf(getUsername()),
                String.valueOf(getPassword()), "student");
    }

    /**
     * Returns string for storage in students.txt
     *
     * @return Formatted string for storage in students.txt
     */
    public String getFormattedString() {
        return String.join(Parser.LINE_SEPARATOR, getName(), matricNo, String.valueOf(getGender()),
                getNationality(), String.valueOf(getUsername()), courseOfStudy + Parser.TILDE_SEPARATOR + yearOfStudy,
                email);
    }

    /**
     * Returns string in table format.
     *
     * @return String in table format.
     */
    @Override
    public String toString() {
        return String.format(FORMAT, getName(), matricNo, getGender(), getNationality());
    }

    /**
     * Adds course to list of registered courses.
     *
     * @param courseToAdd Course to add.
     * @throws MyStarsException If there is issue adding courses.
     */
    public void addCourseToRegistered(Course courseToAdd) throws MyStarsException {
        if (courseToAdd.getNumOfAUs() + registeredCourses.getTotalNoOfAUs() > MAX_AU_ALLOWED) {
            throw new MyStarsException(EXCEED_AU_ERROR);
        }
        checkCoursesInList(courseToAdd);
        registeredCourses.addCourse(courseToAdd);
    }

    /**
     * Adds course to list of waitlisted courses.
     *
     * @param courseToAdd Course to add.
     * @throws MyStarsException If there is issue adding courses.
     */
    public void addCourseToWaitlisted(Course courseToAdd) throws MyStarsException {
        checkCoursesInList(courseToAdd);
        waitlistedCourses.addCourse(courseToAdd);
    }

    /**
     * Drops course from list of registered courses.
     *
     * @param courseToDrop Course to drop.
     * @throws MyStarsException If there is issue dropping courses.
     */
    public void dropRegisteredCourse(Course courseToDrop) throws MyStarsException {
        if (registeredCourses.isCourseInList(courseToDrop)) {
            registeredCourses.dropCourse(courseToDrop);
        } else {
            throw new MyStarsException(COURSE_NOT_IN_REGISTERED_ERROR);
        }
    }

    /**
     * Drops course from list of waitlisted courses.
     *
     * @param courseToDrop Course to drop.
     * @throws MyStarsException If there is issue dropping courses.
     */
    public void dropWaitlistedCourse(Course courseToDrop) throws MyStarsException {
        if (waitlistedCourses.isCourseInList(courseToDrop)) {
            waitlistedCourses.dropCourse(courseToDrop);
        } else {
            throw new MyStarsException(COURSE_NOT_IN_WAITLISTED_ERROR);
        }
    }

    /**
     * Copies details to combine login info and student details.
     *
     * @param user Student user to combine.
     */
    @Override
    public void copyDetails(User user) {
        setName(user.getName());
        matricNo = ((Student) user).getMatricNo();
        setGender(user.getGender());
        setNationality(user.getNationality());
        courseOfStudy = ((Student) user).getCourseOfStudy();
        yearOfStudy = ((Student) user).getYearOfStudy();
        email = ((Student) user).getEmail();
        registeredCourses = ((Student) user).getRegisteredCourses();
        waitlistedCourses = ((Student) user).getWaitlistedCourses();
    }

    /**
     * Checks courses in list, and whether that is an issue.
     *
     * @param courseToAdd Course to add.
     * @throws MyStarsException If there is issue adding course.
     */
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

    /**
     * Modifies registered courses.
     *
     * @param course Course to overwrite with.
     * @throws MyStarsException If there is problem replacing course.
     */
    public void modifyRegisteredCourse(Course course) throws MyStarsException {
        for (int i = 0; i < registeredCourses.getCourses().size(); i++) {
            if (registeredCourses.getCourses().get(i).equals(course)) {
                Course removedCourse = registeredCourses.getCourses().remove(i);
                if (course.getNumOfAUs() + registeredCourses.getTotalNoOfAUs() > MAX_AU_ALLOWED) {
                    registeredCourses.getCourses().add(removedCourse);
                    throw new MyStarsException(EXCEED_AU_ERROR);
                }
                for (Course registered : registeredCourses.getCourses()) {
                    if (registered.isClash(course)) {
                        registeredCourses.getCourses().add(removedCourse);
                        throw new MyStarsException(TIMING_CLASH_ERROR);
                    }
                }
                registeredCourses.getCourses().add(course);
                break;
            }
        }
    }

    /**
     * Modifies waitlisted courses.
     *
     * @param course Course to overwrite with.
     */
    public void modifyWaitlistedCourse(Course course) {
        for (int i = 0; i < waitlistedCourses.getCourses().size(); i++) {
            if (waitlistedCourses.getCourses().get(i).equals(course)) {
                waitlistedCourses.getCourses().set(i, course);
                break;
            }
        }
    }
}
