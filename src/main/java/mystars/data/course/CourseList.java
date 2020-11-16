package mystars.data.course;

import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;

import java.util.ArrayList;

/**
 * List of courses, along with helpful functions.
 */
public class CourseList {

    /**
     * Index not found error message.
     */
    private static final String NO_INDEX_ERROR = "No such index.";

    /**
     * Course not found error message.
     */
    private static final String NO_COURSE_ERROR = "No such course.";

    /**
     * List of courses.
     */
    private final ArrayList<Course> courses;

    /**
     * Initialise CourseList object.
     */
    public CourseList() {
        courses = new ArrayList<>();
    }

    /**
     * Initialise CourseList object with courses.
     *
     * @param courses List of courses.
     */
    public CourseList(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns list of courses.
     *
     * @return List of courses.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Calculates the total number of AUs for a list of courses.
     * Used for CourseList defined in Student objects to check for total AUs restrictions.
     *
     * @return Total number of AUs of a list of courses.
     */
    public int getTotalNoOfAUs() {
        return getCourses().stream().mapToInt(Course::getNumOfAUs).sum();
    }

    /**
     * Returns course of given index.
     *
     * @param indexNumber Index of course.
     * @return Course with that index.
     */
    public Course getCourseByIndex(String indexNumber) {
        for (Course course : getCourses()) {
            if (course.getIndexNumber().equals(indexNumber)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Returns if course is in list.
     *
     * @param courseToCheck Course to check.
     * @return True if course is in list, false otherwise.
     */
    public boolean isCourseInList(Course courseToCheck) {
        for (Course course : getCourses()) {
            if (courseToCheck.isSameCourseCode(course) && courseToCheck.isSameIndexNo(course)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if course is in list, given index.
     *
     * @param indexNumber Index number to check.
     * @return True if index is in list, false otherwise.
     */
    public boolean isIndexNoInList(String indexNumber) {
        return getCourseByIndex(indexNumber) != null;
    }

    /**
     * Returns if course in in list, given course code.
     *
     * @param courseCode Course code to check.
     * @return True if course code is in list, false otherwise.
     */
    public boolean isCourseInList(String courseCode) {
        for (Course course : getCourses()) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if course clashes with other courses.
     *
     * @param courseToAdd Course to check.
     * @return True if course clashes, false otherwise.
     */
    public boolean isClash(Course courseToAdd) {
        return getCourses().stream().anyMatch(courseToAdd::isClash);
    }

    /**
     * Adds course to list of courses.
     *
     * @param courseToAdd Course to add.
     */
    public void addCourse(Course courseToAdd) {
        getCourses().add(courseToAdd);
    }

    /**
     * Drops course from list of courses.
     *
     * @param courseToDrop Course to drop.
     */
    public void dropCourse(Course courseToDrop) {
        getCourses().remove(courseToDrop);
    }

    /**
     * Updates/Adds course in list of courses, as well as on student's list of courses.
     *
     * @param newCourse Course to update.
     * @return Course added.
     * @throws MyStarsException If course cannot be updated or added.
     */
    public Course updateCourse(Course newCourse) throws MyStarsException {

        for (Course course : getCourses()) {
            if (course.getIndexNumber().equals(newCourse.getIndexNumber())) {
                course.copyCourseDetails(newCourse);
                for (Student student : course.getRegisteredStudents()) {
                    student.modifyRegisteredCourse(course);
                }
                for (Student student : course.getWaitlistedStudents()) {
                    student.modifyWaitlistedCourse(course);
                }
                return course;
            }
        }
        addCourse(newCourse);
        return newCourse;
    }

    /**
     * Checks if index number is in the list.
     *
     * @param indexNumber Index number to check.
     * @throws MyStarsException If index number does not exist.
     */
    public void checkIndexNoInList(String indexNumber) throws MyStarsException {
        if (!isIndexNoInList(indexNumber)) {
            throw new MyStarsException(NO_INDEX_ERROR);
        }
    }

    /**
     * Checks if course is in the list, given course code.
     *
     * @param courseCode Course code to check.
     * @throws MyStarsException If course code does not exist.
     */
    public void checkCourseInList(String courseCode) throws MyStarsException {
        if (!isCourseInList(courseCode)) {
            throw new MyStarsException(NO_COURSE_ERROR);
        }
    }

    /**
     * Returns number of courses.
     *
     * @return Number of courses in the list.
     */
    public int getNoOfCourses() {
        return getCourses().size();
    }
}
