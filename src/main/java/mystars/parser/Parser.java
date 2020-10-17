package mystars.parser;

import mystars.commands.AddCourseCommand;
import mystars.commands.Command;
import mystars.commands.ExitCommand;
import mystars.commands.LogoutCommand;
import mystars.data.CourseList;
import mystars.data.course.Course;
import mystars.data.course.Day;
import mystars.data.course.Lesson;
import mystars.data.course.LessonType;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;

import java.util.ArrayList;

/**
 * Parses user input and file.
 */
public class Parser {

    public static final String SEPARATOR = "\\|"; //used to separate each attribute of an object

    /**
     * Parses admin input, and returns corresponding command.
     *
     * @param fullCommand String of admin input to parse.
     * @return Command to execute.
     * @throws MyStarsException If command is invalid.
     */
    public Command parseAdmin(String fullCommand) throws MyStarsException {
        Command command;
        switch (fullCommand.trim()) {
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case LogoutCommand.COMMAND_WORD:
            command = new LogoutCommand();
            break;
        default:
            throw new MyStarsException(Command.COMMAND_ERROR);
        }

        return command;
    }

    public Command parseStudent(String fullCommand) throws MyStarsException {
        Command command;
        switch (fullCommand.trim()) {
        case AddCourseCommand.COMMAND_WORD:
            command = new AddCourseCommand();
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case LogoutCommand.COMMAND_WORD:
            command = new LogoutCommand();
            break;
        default:
            throw new MyStarsException(Command.COMMAND_ERROR);
        }

        return command;
    }

    /**
     * Reads users from file.
     *
     * @param line Line of user to read.
     * @return Users of corresponding line.
     * @throws MyStarsException If user is incomplete or invalid.
     */
    public User readUser(String line) throws MyStarsException {

        //TODO: Read users from file.
        User user;
        String[] userSplit = line.split(SEPARATOR);
        String username = userSplit[0].trim();
        String password = userSplit[1].trim();
        String type = userSplit[2].trim();
        switch (type) {
        case "student":
            user = new Student();
            break;
        case "admin":
            user = new Admin();
            break;
        default:
            throw new MyStarsException("Invalid user type: " + type);
        }


        user.setUsername(username.toCharArray());
        user.setPassword(password.toCharArray());

        return user;
    }

    /**
     * Reads courses from file.
     *
     * @param line Line of course to read.
     * @return Courses of corresponding line.
     * @throws MyStarsException If course vacancy/number of AUs is not an integer.
     */
    public Course readCourse(String line) throws MyStarsException {

        //TODO: Read courses from file.
        String[] courseSplit = line.split(SEPARATOR);
        String courseCode = courseSplit[0].trim();
        String school = courseSplit[1].trim();
        String indexNumber = courseSplit[2].trim();
        int vacancy, numOfAUs;

        try {
            vacancy = Integer.parseInt(courseSplit[3].trim());
            numOfAUs = Integer.parseInt(courseSplit[4].trim());
        } catch (NumberFormatException e) {
            throw new MyStarsException("Vacancy must be an integer.");
        }

        String lessonString = courseSplit[5];
        String[] lessonsString = lessonString.split(",");
        ArrayList<Lesson> lessons = readLessons(lessonsString);

        return new Course(courseCode, school, indexNumber, vacancy, numOfAUs, lessons);
    }

    private ArrayList<Lesson> readLessons(String[] lessonsString) throws MyStarsException {
        ArrayList<Lesson> lessonsToAdd = new ArrayList<>();
        for (String lessonString: lessonsString) {
            String[] lessonDetailsString = lessonString.split(":");

            LessonType lessonType;
            switch (lessonDetailsString[0]) {
            case "LEC":
                lessonType = LessonType.LEC;
                break;
            case "TUT":
                lessonType = LessonType.TUT;
                break;
            case "LAB":
                lessonType = LessonType.LAB;
                break;
            default:
                throw new MyStarsException("Error when parsing lesson type!");
            }

            String venue = lessonDetailsString[1];

            String time = lessonDetailsString[2];

            Day day;
            switch (lessonDetailsString[3]) {
            case "MON":
                day = Day.MON;
                break;
            case "TUE":
                day = Day.TUE;
                break;
            case "WED":
                day = Day.WED;
                break;
            case "THU":
                day = Day.THU;
                break;
            case "FRI":
                day = Day.FRI;
                break;
            default:
                throw new MyStarsException("Error when parsing day!");
            }

            String group = lessonDetailsString[4];

            Lesson lessonToAdd = new Lesson(lessonType, venue, time, day, group);
            lessonsToAdd.add(lessonToAdd);
        }

        return lessonsToAdd;
    }


    /**
     * Reads students from file.
     *
     * @param line Line of student to read.
     * @return Students of corresponding line.
     */
    public Student readStudent(String line, CourseList availableCoursesList) throws MyStarsException {

        String[] studentSplit = line.split(SEPARATOR);
        String name = studentSplit[0].trim();
        String matricNo = studentSplit[1].trim();
        char gender = studentSplit[2].trim().charAt(0);
        String nationality = studentSplit[3].trim();
        String username = studentSplit[4].trim();

        CourseList registeredCourses;
        try {
            String registeredCoursesString = studentSplit[5];
            ArrayList<Course> regCourses = new ArrayList<>();
            regCourses.addAll(loadCourse(registeredCoursesString.split(","), availableCoursesList));
            registeredCourses = new CourseList(regCourses);
        } catch (ArrayIndexOutOfBoundsException e) {
            registeredCourses = new CourseList();
        }

        CourseList waitlistedCourses;
        try {
            String waitlistedCoursesString = studentSplit[6];
            ArrayList<Course> waitCourses = new ArrayList<>();
            waitCourses.addAll(loadCourse(waitlistedCoursesString.split(","), availableCoursesList));
            waitlistedCourses = new CourseList(waitCourses);
        } catch (ArrayIndexOutOfBoundsException e) {
            waitlistedCourses = new CourseList();
        }

        return new Student(name, matricNo, gender, nationality, username, registeredCourses, waitlistedCourses);
    }

    public ArrayList<Course> loadCourse(String[] registeredCourses, CourseList availableCoursesList) throws MyStarsException {
        ArrayList<Course> courseArrayList = new ArrayList<>();
        try {
            for (String registeredCourse: registeredCourses) {
                String[] courseSplit = registeredCourse.split(":");
                String courseCode = courseSplit[0];
                String courseIndex = courseSplit[1];
                for (Course availableCourse: availableCoursesList.getCourses()) {
                    if (courseCode == availableCourse.getCourseCode() && courseIndex == availableCourse.getIndexNumber()) {
                        courseArrayList.add(availableCourse);
                    }
                }
            }
        } catch (Exception e) {
            throw new MyStarsException("Problem loading student's registered/waitlisted courses!");
        }
        return courseArrayList;
    }

    /**
     * Reads admins from file.
     *
     * @param line Line of admin to read.
     * @return Admins of corresponding line.
     */
    public Admin readAdmin(String line) throws MyStarsException {

        //TODO: Read courses from file.
        String[] adminSplit = line.split(SEPARATOR);
        String name = adminSplit[0].trim();
        String staffId = adminSplit[1].trim();
        char gender = adminSplit[2].trim().charAt(0);
        String nationality = adminSplit[3].trim();
        String username = adminSplit[4].trim();

        return new Admin(name, staffId, gender, nationality, username);
    }

    public boolean isExit(String fullCommand) {
        return fullCommand.trim().equalsIgnoreCase(ExitCommand.COMMAND_WORD);
    }
}
