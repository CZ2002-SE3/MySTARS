package mystars.storage;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;
import mystars.data.user.UserList;
import mystars.parser.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Storage {

    private static final String READ_ERROR = "I am unable to read file.";
    private static final String DIRECTORY_ERROR = "I am unable to create directory.";
    private static final String WRITE_ERROR = "I am unable to write file.";

    private static final String SETTINGS_FORMAT = "format: start datetime|end datetime";
    private static final String COURSES_FORMAT = "format: course code|school|index number|vacancy|number of AUs|"
            + "Lesson1*Lesson2*... (refer to Lesson.java for format)";
    private static final String REGISTERED_FORMAT = "format: index number|student1 matric no.|student2...";
    private static final String WAITLISTED_FORMAT = REGISTERED_FORMAT;

    private static final String FOLDER = "db";
    private static final String USERS_FILE = "users.txt";
    private static final String STUDENTS_FILE = "students.txt";
    private static final String ADMINS_FILE = "admins.txt";
    private static final String COURSES_FILE = "courses.txt";
    private static final String SETTINGS_FILE = "settings.txt";
    private static final String REGISTERED_FILE = "registered.txt";
    private static final String WAITLISTED_FILE = "waitlist.txt";

    private final Parser parser;

    public Storage(Parser parser) {
        this.parser = parser;
    }

    /**
     * Loads users, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of users.
     * @throws MyStarsException If there is problem reading file.
     */
    public ArrayList<User> loadUsers() throws MyStarsException {
        Path path = Paths.get(FOLDER, USERS_FILE);
        ArrayList<User> users = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                bufferedReader.readLine();

                while (true) {
                    String line = bufferedReader.readLine();
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
    public ArrayList<User> loadStudents() throws MyStarsException {
        Path path = Paths.get(FOLDER, STUDENTS_FILE);
        ArrayList<User> students = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                bufferedReader.readLine();

                while (true) {
                    String line = bufferedReader.readLine();
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
    public ArrayList<User> loadAdmins() throws MyStarsException {
        Path path = Paths.get(FOLDER, ADMINS_FILE);
        ArrayList<User> admins = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                bufferedReader.readLine();

                while (true) {
                    String line = bufferedReader.readLine();
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

    public void loadCourseRegisteredStudents(CourseList courseList, UserList userList) throws MyStarsException {
        Path path = Paths.get(FOLDER, REGISTERED_FILE);

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                bufferedReader.readLine();

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    String courseIndex = parser.readCourseIndex(line);
                    Course course = courseList.getCourseByIndex(courseIndex);
                    ArrayList<Student> students = parser.readStudentList(line, userList);

                    if (course.isVacancy()) {
                        for (Student student : students) {
                            student.addCourseToRegistered(course);
                        }
                        course.addRegisteredStudents(students);

                    } else {
                        throw new MyStarsException("No more vacancy to put student!");
                    }
                }
            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }
    }

    public void loadCourseWaitlistStudents(CourseList courseList, UserList userList) throws MyStarsException {
        Path path = Paths.get(FOLDER, WAITLISTED_FILE);

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                bufferedReader.readLine();

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    String courseIndex = parser.readCourseIndex(line);
                    Course course = courseList.getCourseByIndex(courseIndex);
                    ArrayList<Student> students = parser.readStudentList(line, userList);

                    for (Student student : students) {
                        student.addCourseToWaitlisted(course);
                    }
                    course.addWaitlistedStudents(students);

                    // TODO : rewrite this line
                    if (course.checkWaitlist()) {
                        //ui.showEmailSent();
                        System.out.println("Email sent");
                    }

                    saveCourses(courseList);
                }
            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }
    }

    /**
     * Loads courses, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of courses.
     * @throws MyStarsException If there is problem reading file.
     */
    public ArrayList<Course> loadCourses() throws MyStarsException {
        Path path = Paths.get(FOLDER, COURSES_FILE);
        ArrayList<Course> courses = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                bufferedReader.readLine();

                while (true) {
                    String line = bufferedReader.readLine();
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

    /**
     * Loads access period, stores them into an array and returns the array.
     *
     * @return Access period, null if file does not exist.
     * @throws MyStarsException If there is problem reading file.
     */
    public LocalDateTime[] loadAccessPeriod() throws MyStarsException {
        Path path = Paths.get(FOLDER, SETTINGS_FILE);
        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                bufferedReader.readLine();

                String line = bufferedReader.readLine();

                return parser.readStudentAccessPeriod(line);

            } catch (IOException e) {
                throw new MyStarsException(READ_ERROR);
            }
        }

        return new LocalDateTime[]{LocalDateTime.now(), LocalDateTime.now()};
    }

    public void saveAccessPeriod(LocalDateTime[] accessPeriod) throws MyStarsException {
        String accessPeriodString = SETTINGS_FORMAT + System.lineSeparator() + accessPeriod[0] +
                Parser.LINE_SEPARATOR + accessPeriod[1];
        writeToFile(accessPeriodString, SETTINGS_FILE);
    }

    /**
     * Writes content to file.
     *
     * @param fileContent String content to write.
     * @param file        Filename to write to.
     * @throws MyStarsException If there is problem writing files.
     */
    private void writeToFile(String fileContent, String file) throws MyStarsException {
        Path folderPath = Paths.get(FOLDER);
        if (!Files.exists(folderPath) && !new File(FOLDER).mkdir()) {
            throw new MyStarsException(DIRECTORY_ERROR);
        }

        Path filePath = Paths.get(FOLDER, file);
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath);
            bufferedWriter.write(fileContent + System.lineSeparator());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new MyStarsException(WRITE_ERROR);
        }
    }

    /**
     * Appends content to file.
     *
     * @param fileContent String content to write.
     * @param file        Filename to write to.
     * @throws MyStarsException If there is problem writing files.
     */
    private void appendToFile(String fileContent, String file) throws MyStarsException {
        Path folderPath = Paths.get(FOLDER);
        Path filePath = Paths.get(FOLDER, file);

        if (!(Files.exists(folderPath) && Files.exists(filePath))) {
            writeToFile(fileContent, file);
        } else {
            try {
                BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND);
                bufferedWriter.write(fileContent + System.lineSeparator());
                bufferedWriter.close();
            } catch (IOException e) {
                throw new MyStarsException(WRITE_ERROR);
            }
        }
    }

    public void saveStudent(Student newStudent) throws MyStarsException {
        appendToFile(newStudent.getFormattedString(), STUDENTS_FILE);
        appendToFile(newStudent.getFormattedUserInfo(), USERS_FILE);
    }

    public void saveCourses(CourseList courses) throws MyStarsException {
        String coursesString = courses.getCourses().stream().map(Course::getStorageString)
                .collect(Collectors.joining(System.lineSeparator()));

        String coursesFileContent = COURSES_FORMAT + System.lineSeparator() + coursesString;
        writeToFile(coursesFileContent, COURSES_FILE);

        String registeredString = courses.getCourses().stream().filter(Course::isThereRegisteredStudents)
                .map(Course::getRegisteredFormattedString).collect(Collectors.joining(System.lineSeparator()));

        String registeredFileContent = REGISTERED_FORMAT + System.lineSeparator() + registeredString;
        writeToFile(registeredFileContent, REGISTERED_FILE);

        String waitlistedString = courses.getCourses().stream().filter(Course::isThereWaitlistedStudents)
                .map(Course::getWaitlistedFormattedString).collect(Collectors.joining(System.lineSeparator()));

        String waitlistedFileContent = WAITLISTED_FORMAT + System.lineSeparator() + waitlistedString;
        writeToFile(waitlistedFileContent, WAITLISTED_FILE);
    }
}
