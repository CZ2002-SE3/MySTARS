package mystars.parser;

import mystars.commands.Command;
import mystars.commands.ExitCommand;
import mystars.commands.LogoutCommand;
import mystars.commands.admin.EditStudentAccessCommand;
import mystars.commands.student.AddCourseCommand;
import mystars.commands.student.PrintCourseRegCommand;
import mystars.data.CourseList;
import mystars.data.course.Course;
import mystars.data.course.Day;
import mystars.data.course.Lesson;
import mystars.data.course.LessonType;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Parses user input and file.
 */
public class Parser {

    // Used to separate each attribute of an object
    public static final String SEPARATOR = "\\|";
    public static final String COLON_SEPARATOR = ":";
    public static final String COMMA_SEPARATOR = ",";

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
        case EditStudentAccessCommand.COMMAND_WORD:
            command = new EditStudentAccessCommand();
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
        case PrintCourseRegCommand.COMMAND_WORD:
            command = new PrintCourseRegCommand();
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
        int vacancy;
        int numOfAUs;

        try {
            vacancy = Integer.parseInt(courseSplit[3].trim());
            numOfAUs = Integer.parseInt(courseSplit[4].trim());
        } catch (NumberFormatException e) {
            throw new MyStarsException("Vacancy must be an integer.");
        }

        String lessonString = courseSplit[5];
        String[] lessonsString = lessonString.split(COMMA_SEPARATOR);
        ArrayList<Lesson> lessons = readLessons(lessonsString);

        return new Course(courseCode, school, indexNumber, vacancy, numOfAUs, lessons);
    }

    private ArrayList<Lesson> readLessons(String[] lessonsString) throws MyStarsException {
        ArrayList<Lesson> lessonsToAdd = new ArrayList<>();
        for (String lessonString : lessonsString) {
            String[] lessonDetailsString = lessonString.split(COLON_SEPARATOR);

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

            String timeString = lessonDetailsString[2];
            LocalTime[] time = parseTime(timeString);
            LocalTime startTime = time[0];
            LocalTime endTime = time[1];

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

            Lesson lessonToAdd = new Lesson(lessonType, venue, startTime, endTime, day, group);
            lessonsToAdd.add(lessonToAdd);
        }

        return lessonsToAdd;
    }

    private LocalTime[] parseTime(String timeString) throws MyStarsException {
        try {
            String startTimeString = timeString.split("-")[0];
            String endTimeString = timeString.split("-")[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            LocalTime[] time = new LocalTime[2];
            time[0] = LocalTime.parse(startTimeString, formatter);
            time[1] = LocalTime.parse(endTimeString, formatter);
            return time;
        } catch (DateTimeParseException e) {
            throw new MyStarsException("Error while parsing time!");
        }
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

        String[] courseAndYear = studentSplit[5].split(COLON_SEPARATOR);
        String courseOfStudy = courseAndYear[0];
        int yearOfStudy = Integer.parseInt(courseAndYear[1]);

        CourseList registeredCourses;
        try {
            String registeredCoursesString = studentSplit[6];
            ArrayList<Course> regCourses = new ArrayList<>(loadCourse(registeredCoursesString.split(COMMA_SEPARATOR)
                    , availableCoursesList));
            registeredCourses = new CourseList(regCourses);
        } catch (ArrayIndexOutOfBoundsException e) {
            registeredCourses = new CourseList();
        }

        CourseList waitlistedCourses;
        try {
            String waitlistedCoursesString = studentSplit[7];
            ArrayList<Course> waitCourses = new ArrayList<>(loadCourse(waitlistedCoursesString.split(COMMA_SEPARATOR)
                    , availableCoursesList));
            waitlistedCourses = new CourseList(waitCourses);
        } catch (ArrayIndexOutOfBoundsException e) {
            waitlistedCourses = new CourseList();
        }

        return new Student(name, matricNo, gender, nationality, username, courseOfStudy, yearOfStudy, registeredCourses
                , waitlistedCourses);
    }

    /**
     * Loads courses taken by student.
     *
     * @param courses              String to be parsed containing information about students' registered courses.
     * @param availableCoursesList Loaded from database of courses.
     * @return Arraylist of course.
     * @throws MyStarsException IF there are problem loading courses.
     */
    public ArrayList<Course> loadCourse(String[] courses, CourseList availableCoursesList) throws MyStarsException {
        ArrayList<Course> courseArrayList = new ArrayList<>();
        try {
            for (String course : courses) {
                String[] courseSplit = course.split(COLON_SEPARATOR);
                String courseCode = courseSplit[0];
                String courseIndex = courseSplit[1];
                for (Course availableCourse : availableCoursesList.getCourses()) {
                    if (courseCode.equals(availableCourse.getCourseCode())
                            && courseIndex.equals(availableCourse.getIndexNumber())) {
                        courseArrayList.add(availableCourse);
                        //update vacancy for the course of a specific index
                        int newVacancy = availableCourse.getVacancy() - 1;
                        if (newVacancy < 0) {
                            throw new MyStarsException("More students registered than vacancies!");
                        }
                        availableCourse.setVacancy(newVacancy);
                        break;
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

    /**
     * Checks if exit command is called.
     *
     * @param fullCommand Command to check.
     * @return True if exit command is called, false otherwise.
     */
    public boolean isExit(String fullCommand) {
        return fullCommand.trim().equalsIgnoreCase(ExitCommand.COMMAND_WORD);
    }

    /**
     * Reads student's access period.
     *
     * @param line Line of DateTime to read.
     * @return Access period.
     */
    public LocalDateTime[] readStudentAccessPeriod(String line) throws MyStarsException {
        try {
            String[] dateTime = line.split(SEPARATOR);
            LocalDateTime start = LocalDateTime.parse(dateTime[0].trim());
            LocalDateTime end = LocalDateTime.parse(dateTime[1].trim());
            return new LocalDateTime[]{start, end};
        } catch (DateTimeParseException dateTimeParseException) {
            throw new MyStarsException("I am unable to parse date/time.");
        }
    }
}
