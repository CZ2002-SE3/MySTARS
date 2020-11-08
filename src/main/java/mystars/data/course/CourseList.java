package mystars.data.course;

import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;

import java.util.ArrayList;

public class CourseList {

    private static final String NO_INDEX_ERROR = "No such index.";
    private static final String NO_COURSE_ERROR = "No such course.";

    private final ArrayList<Course> courses;

    public CourseList() {
        courses = new ArrayList<>();
    }

    public CourseList(ArrayList<Course> courses) {
        this.courses = courses;
    }

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
        return courses.stream().mapToInt(Course::getNumOfAUs).sum();
    }

    @Override
    public String toString() {
        StringBuilder coursesString = new StringBuilder();
        for (int i = 1; i <= courses.size(); i++) {
            coursesString.append(System.lineSeparator()).append("#").append(i).append(System.lineSeparator())
                    .append(courses.get(i - 1).toString());
        }
        return String.join(System.lineSeparator(), "Total No. of Courses Registered: " + courses.size(),
                "Total No. of AUs Registered: " + getTotalNoOfAUs(), coursesString.toString());
    }

    public boolean isCourseInList(Course courseToCheck) {
        for (Course course : courses) {
            if (courseToCheck.isSameCourseCode(course) && courseToCheck.isSameIndexNo(course)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIndexNoInList(String indexNumber) {
        return getCourseByIndex(indexNumber) != null;
    }

    public void checkIndexNoInList(String indexNumber) throws MyStarsException {
        if (!isIndexNoInList(indexNumber)) {
            throw new MyStarsException(NO_INDEX_ERROR);
        }
    }

    public void addCourse(Course courseToAdd) {
        courses.add(courseToAdd);
    }

    public void dropCourse(Course courseToDrop) {
        courses.remove(courseToDrop);
    }

    public boolean isCourseInList(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public void checkCourseInList(String courseCode) throws MyStarsException {
        if (!isCourseInList(courseCode)) {
            throw new MyStarsException(NO_COURSE_ERROR);
        }
    }

    public Course getCourseByIndex(String indexNumber) {
        for (Course course : courses) {
            if (course.getIndexNumber().equals(indexNumber)) {
                return course;
            }
        }
        return null;
    }

    public Course updateCourse(Course newCourse) throws MyStarsException {

        for (Course course : courses) {
            if (course.getIndexNumber().equals(newCourse.getIndexNumber())) {
                course.copyCourseDetails(newCourse);
                for (Student student : course.getRegisteredStudents()) {
                    student.modifyCourse(course);
                }
                return course;
            }
        }
        addCourse(newCourse);
        return newCourse;
    }

    public boolean isClash(Course courseToAdd) {
        return courses.stream().anyMatch(courseToAdd::isClash);
    }
}
