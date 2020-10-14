package mystars.storage;

import mystars.data.course.Course;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;
import mystars.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    public static final String LOAD_ERROR = "I am unable to load file.";
    private static final String READ_ERROR = "I am unable to read file.";

    private static final String FOLDER = "db";
    private static final String USERS_FILE = "users.txt";
    private static final String STUDENTS_FILE = "students.txt";
    private static final String ADMINS_FILE = "admins.txt";
    private static final String COURSES_FILE = "courses.txt";

    /**
     * Loads users, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of users.
     * @throws MyStarsException If there is problem reading file.
     */
    public ArrayList<User> loadUsers(Parser parser) throws MyStarsException {
        Path path = Paths.get(FOLDER, USERS_FILE);
        ArrayList<User> users = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                String line = bufferedReader.readLine();

                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    User user = parser.readUser(line);
                    users.add(user);
                }
            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }

        return users;
    }

    /**
     * Loads students, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of students.
     * @throws MyStarsException If there is problem reading file.
     */
    public ArrayList<Student> loadStudents(Parser parser) throws MyStarsException {
        Path path = Paths.get(FOLDER, STUDENTS_FILE);
        ArrayList<Student> students = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                String line = bufferedReader.readLine();

                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    Student student = parser.readStudent(line);
                    students.add(student);
                }
            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }

        return students;
    }

    /**
     * Loads admins, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of admins.
     * @throws MyStarsException If there is problem reading file.
     */
    public ArrayList<Admin> loadAdmins(Parser parser) throws MyStarsException {
        Path path = Paths.get(FOLDER, ADMINS_FILE);
        ArrayList<Admin> admins = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                String line = bufferedReader.readLine();

                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    Admin admin = parser.readAdmin(line);
                    admins.add(admin);
                }
            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }

        return admins;
    }

    /**
     * Loads courses, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of courses.
     * @throws MyStarsException If there is problem reading file.
     */
    public ArrayList<Course> loadCourses(Parser parser) throws MyStarsException {
        Path path = Paths.get(FOLDER, COURSES_FILE);
        ArrayList<Course> courses = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                String line = bufferedReader.readLine();

                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    Course course = parser.readCourse(line);
                    courses.add(course);
                }
            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }

        return courses;
    }

}
