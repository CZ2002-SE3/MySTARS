package mystars.parser;

import mystars.commands.Command;
import mystars.commands.LogoutCommand;
import mystars.commands.admin.AddStudentCommand;
import mystars.commands.admin.AddUpdateCourseCommand;
import mystars.commands.admin.CheckVacancyCommand;
import mystars.commands.admin.EditStudentAccessCommand;
import mystars.commands.admin.PrintListByCourseCommand;
import mystars.commands.admin.PrintListByIndexCommand;
import mystars.commands.student.AddCourseCommand;
import mystars.commands.student.PrintCourseRegCommand;
import mystars.common.Option;
import mystars.data.CourseList;
import mystars.data.LessonList;
import mystars.data.course.Course;
import mystars.data.course.Lesson;
import mystars.data.course.LessonType;
import mystars.data.course.Week;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parses user input and file.
 */
public class Parser {

    // Used to separate each attribute of an object
    public static final String ESCAPED_LINE_SEPARATOR = "\\|";
    public static final String LINE_SEPARATOR = ESCAPED_LINE_SEPARATOR.replace("\\", "");
    public static final String TILDE_SEPARATOR = "~";
    public static final String ESCAPED_ASTERISK_SEPERATOR = "\\*";
    public static final String ASTERISK_SEPERATOR = ESCAPED_ASTERISK_SEPERATOR.replace("\\", "");

    private static final int MATRIC_NO_LENGTH = 9;
    private static final int COURSE_CODE_LENGTH = 6;
    private static final int INDEX_NO_LENGTH = 5;
    private static final int MAX_SCHOOL_LENGTH = 4;
    private static final int MIN_SCHOOL_LENGTH = 3;

    /**
     * Parses admin input, and returns corresponding command.
     *
     * @param fullCommand String of admin input to parse.
     * @return Command to execute.
     * @throws MyStarsException If command is invalid.
     */
    public Command parseAdminInput(String fullCommand) throws MyStarsException {
        Command command;
        switch (fullCommand.trim()) {
        case EditStudentAccessCommand.COMMAND_WORD:
            command = new EditStudentAccessCommand();
            break;
        case AddStudentCommand.COMMAND_WORD:
            command = new AddStudentCommand();
            break;
        case AddUpdateCourseCommand.COMMAND_WORD:
            command = new AddUpdateCourseCommand();
            break;
        case CheckVacancyCommand.COMMAND_WORD:
            command = new CheckVacancyCommand();
            break;
        case PrintListByIndexCommand.COMMAND_WORD:
            command = new PrintListByIndexCommand();
            break;
        case PrintListByCourseCommand.COMMAND_WORD:
            command = new PrintListByCourseCommand();
            break;
        case LogoutCommand.COMMAND_WORD:
            command = new LogoutCommand();
            break;
        default:
            throw new MyStarsException(Command.COMMAND_ERROR);
        }

        return command;
    }

    public Command parseStudentInput(String fullCommand) throws MyStarsException {
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

        User user;
        String[] userSplit = line.split(ESCAPED_LINE_SEPARATOR);
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

        String[] courseSplit = line.split(ESCAPED_LINE_SEPARATOR);
        String courseCode = courseSplit[0].trim();
        String school = courseSplit[1].trim();
        String indexNumber = courseSplit[2].trim();
        String vacancyString = courseSplit[3].trim();
        String numOfAUsString = courseSplit[4].trim();
        String lessonString = courseSplit[5].trim();

        int vacancy;
        int numOfAUs;
        if (isValidNumber(vacancyString)) {
            vacancy = Integer.parseInt(vacancyString);
        } else {
            throw new MyStarsException("Vacancy is not valid!");
        }

        if (isValidNumber(numOfAUsString)) {
            numOfAUs = Integer.parseInt(numOfAUsString);
        } else {
            throw new MyStarsException("Number of AUs is not valid!");
        }

        String[] lessonsString = lessonString.split(ESCAPED_ASTERISK_SEPERATOR);
        LessonList lessonList = new LessonList(readLessons(lessonsString));

        return new Course(courseCode, school, indexNumber, vacancy, numOfAUs, lessonList);
    }

    public boolean isValidWeek(String line) {
        return Arrays.stream(Week.values()).map(Week::name).anyMatch(line::equalsIgnoreCase);
    }

    private ArrayList<Lesson> readLessons(String[] lessonsString) {
        ArrayList<Lesson> lessonsToAdd = new ArrayList<>();
        for (String lessonString : lessonsString) {
            String[] lessonDetailsString = lessonString.split(TILDE_SEPARATOR);

            LessonType lessonType = LessonType.valueOf(lessonDetailsString[0].trim());

            String venue = lessonDetailsString[1];
            LocalTime startTime = LocalTime.parse(lessonDetailsString[2]);
            LocalTime endTime = LocalTime.parse(lessonDetailsString[3]);

            DayOfWeek day = DayOfWeek.valueOf(lessonDetailsString[4].trim());

            Week week = Week.valueOf(lessonDetailsString[5].trim());

            String group = lessonDetailsString[6];

            Lesson lessonToAdd = new Lesson(lessonType, venue, startTime, endTime, day, week, group);
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

        String[] studentSplit = line.split(ESCAPED_LINE_SEPARATOR);
        String name = studentSplit[0].trim();
        String matricNo = studentSplit[1].trim();
        char gender = studentSplit[2].trim().charAt(0);
        String nationality = studentSplit[3].trim();
        String username = studentSplit[4].trim();

        String[] courseAndYear = studentSplit[5].split(TILDE_SEPARATOR);
        String courseOfStudy = courseAndYear[0];
        int yearOfStudy = Integer.parseInt(courseAndYear[1]);

        CourseList registeredCourses;
        try {
            String registeredCoursesString = studentSplit[6];
            ArrayList<Course> regCourses = new ArrayList<>(loadCourse(registeredCoursesString
                    .split(ESCAPED_ASTERISK_SEPERATOR), availableCoursesList));
            registeredCourses = new CourseList(regCourses);
        } catch (ArrayIndexOutOfBoundsException e) {
            registeredCourses = new CourseList();
        }

        CourseList waitlistedCourses;
        try {
            String waitlistedCoursesString = studentSplit[7];
            ArrayList<Course> waitCourses = new ArrayList<>(loadCourse(waitlistedCoursesString
                    .split(ESCAPED_ASTERISK_SEPERATOR), availableCoursesList));
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
                String[] courseSplit = course.split(TILDE_SEPARATOR);
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
    public Admin readAdmin(String line) {

        String[] adminSplit = line.split(ESCAPED_LINE_SEPARATOR);
        String name = adminSplit[0].trim();
        String staffId = adminSplit[1].trim();
        char gender = adminSplit[2].trim().charAt(0);
        String nationality = adminSplit[3].trim();
        String username = adminSplit[4].trim();

        return new Admin(name, staffId, gender, nationality, username);
    }

    /**
     * Reads student's access period.
     *
     * @param line Line of DateTime to read.
     * @return Access period.
     */
    public LocalDateTime[] readStudentAccessPeriod(String line) throws MyStarsException {
        try {
            String[] dateTime = line.split(ESCAPED_LINE_SEPARATOR);
            LocalDateTime start = LocalDateTime.parse(dateTime[0].trim());
            LocalDateTime end = LocalDateTime.parse(dateTime[1].trim());
            return new LocalDateTime[]{start, end};
        } catch (DateTimeParseException dateTimeParseException) {
            throw new MyStarsException("I am unable to parse date/time.");
        }
    }

    public boolean isValidStartEndTime(String line) {
        return !line.contains(LINE_SEPARATOR) && !line.equals("");
    }

    public boolean isValidGender(String line) {
        return line.equalsIgnoreCase("M") || line.equalsIgnoreCase("F");
    }

    public boolean isValidMatricNo(String line) {
        return isValidStartEndTime(line) && line.length() == MATRIC_NO_LENGTH
                && line.substring(1, MATRIC_NO_LENGTH - 1).chars().allMatch(Character::isDigit)
                && Character.isLetter(line.charAt(0)) && Character.isLetter(line.charAt(MATRIC_NO_LENGTH - 1));
    }

    public boolean isValidYearOfStudy(String line) {
        return line.length() == 1 && Character.isDigit(line.charAt(0))
                && Integer.parseInt(line) >= 1 && Integer.parseInt(line) <= 5;
    }

    public boolean isValidDateTime(String line) {
        try {
            LocalDateTime.parse(line.replace(" ", "T"));
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
    }

    public boolean isValidIndexNumber(String line) {
        return line.length() == INDEX_NO_LENGTH && line.chars().allMatch(Character::isDigit);
    }

    public boolean isValidCourseCode(String line) {
        return line.length() == COURSE_CODE_LENGTH && line.substring(0, 1).chars().allMatch(Character::isLetter)
                && line.substring(2, COURSE_CODE_LENGTH - 1).chars().allMatch(Character::isDigit);
    }

    public boolean isValidSchool(String line) {
        return line.length() >= MIN_SCHOOL_LENGTH && line.length() <= MAX_SCHOOL_LENGTH
                && line.chars().allMatch(Character::isLetter);
    }

    public boolean isValidNumber(String line) {
        return line.chars().allMatch(Character::isDigit) && Integer.parseInt(line) >= 0;
    }

    public boolean isValidLessonType(String line) {
        return Arrays.stream(LessonType.values()).map(LessonType::name).anyMatch(line::equalsIgnoreCase);
    }

    public boolean isValidTime(String line) {
        try {
            LocalTime.parse(line);
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
    }

    public boolean isValidDayOfWeek(String line) {
        return Arrays.stream(DayOfWeek.values()).map(DayOfWeek::name).anyMatch(line::equalsIgnoreCase);
    }

    public boolean isValidOption(String line) {
        return Arrays.stream(Option.values()).map(Option::name).anyMatch(line::equalsIgnoreCase);
    }

    public boolean isYes(String line) {
        return line.trim().equalsIgnoreCase(Option.Y.name());
    }

    public boolean isValidStartEndTime(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }
}
