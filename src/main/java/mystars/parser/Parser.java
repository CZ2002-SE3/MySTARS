package mystars.parser;

import mystars.commands.Command;
import mystars.commands.admin.AddStudentCommand;
import mystars.commands.admin.AddUpdateCourseCommand;
import mystars.commands.admin.CheckVacancyCommand;
import mystars.commands.admin.EditStudentAccessCommand;
import mystars.commands.admin.PrintListByCourseCommand;
import mystars.commands.admin.PrintListByIndexCommand;
import mystars.commands.shared.LogoutCommand;
import mystars.commands.student.AddCourseCommand;
import mystars.commands.student.ChangeIndexNoCommand;
import mystars.commands.student.CheckCourseVacancyCommand;
import mystars.commands.student.DropCourseCommand;
import mystars.commands.student.PrintCourseRegCommand;
import mystars.commands.student.SwopIndexCommand;
import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.course.lesson.Lesson;
import mystars.data.course.lesson.LessonList;
import mystars.data.course.lesson.LessonType;
import mystars.data.course.lesson.Week;
import mystars.data.exception.MyStarsException;
import mystars.data.shared.AccessDateTime;
import mystars.data.shared.Gender;
import mystars.data.shared.Option;
import mystars.data.user.Admin;
import mystars.data.user.Student;
import mystars.data.user.User;
import mystars.data.user.UserList;
import mystars.storage.Storage;
import mystars.ui.AdminUi;
import mystars.ui.StudentUi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Parses user input and file.
 */
public class Parser {

    /**
     * Escaped line separator.
     */
    public static final String ESCAPED_LINE_SEPARATOR = "\\|";

    /**
     * Line separator.
     */
    public static final String LINE_SEPARATOR = ESCAPED_LINE_SEPARATOR.replace("\\", "");

    /**
     * Tilde separator.
     */
    public static final String TILDE_SEPARATOR = "~";

    /**
     * Escaped asterisk separator.
     */
    public static final String ESCAPED_ASTERISK_SEPARATOR = "\\*";

    /**
     * Asterisk separator.
     */
    public static final String ASTERISK_SEPARATOR = ESCAPED_ASTERISK_SEPARATOR.replace("\\", "");

    /**
     * Invalid user type error message.
     */
    private static final String INVALID_TYPE_ERROR = "Invalid user type: ";

    /**
     * Invalid vacancy error message.
     */
    private static final String INVALID_VACANCY_ERROR = "Vacancy is invalid!";

    /**
     * Invalid AUs error message.
     */
    private static final String INVALID_AU_ERROR = "Number of AUs is invalid!";

    /**
     * Lesson clash error message.
     */
    private static final String LESSON_CLASH_ERROR = "Lessons clash!";

    /**
     * Date/time parse error message.
     */
    private static final String DATETIME_PARSE_ERROR = "I am unable to parse date/time.";

    /**
     * Student string.
     */
    private static final String STUDENT = "STUDENT";

    /**
     * Admin string.
     */
    private static final String ADMIN = "ADMIN";

    /**
     * Parses admin input, and returns corresponding command.
     *
     * @param fullCommand    String of admin input to parse.
     * @param users          List of users.
     * @param ui             Ui object.
     * @param courses        List of courses.
     * @param storage        Storage handler.
     * @param accessDateTime Student's access date/time.
     * @return Command to execute.
     * @throws MyStarsException If command is invalid.
     */
    public Command parseAdminInput(String fullCommand, UserList users, AdminUi ui, CourseList courses, Storage storage,
                                   AccessDateTime accessDateTime) throws MyStarsException {
        Command command;
        switch (fullCommand.trim()) {
        case EditStudentAccessCommand.COMMAND_WORD:
            command = new EditStudentAccessCommand(ui, storage, accessDateTime);
            break;
        case AddStudentCommand.COMMAND_WORD:
            command = new AddStudentCommand(ui, storage, users);
            break;
        case AddUpdateCourseCommand.COMMAND_WORD:
            command = new AddUpdateCourseCommand(ui, storage, courses);
            break;
        case CheckVacancyCommand.COMMAND_WORD:
            command = new CheckVacancyCommand(ui, courses);
            break;
        case PrintListByIndexCommand.COMMAND_WORD:
            command = new PrintListByIndexCommand(ui, courses, users);
            break;
        case PrintListByCourseCommand.COMMAND_WORD:
            command = new PrintListByCourseCommand(ui, courses, users);
            break;
        case LogoutCommand.COMMAND_WORD:
            command = new LogoutCommand(ui);
            break;
        default:
            throw new MyStarsException(Command.COMMAND_ERROR);
        }

        return command;
    }

    /**
     * Parses student input, and returns corresponding command.
     *
     * @param fullCommand String of student input to parse.
     * @param users       List of users.
     * @param ui          Ui object.
     * @param courses     List of courses.
     * @param storage     Storage handler.
     * @return Command to execute.
     * @throws MyStarsException If command is invalid.
     */
    public Command parseStudentInput(String fullCommand, UserList users, StudentUi ui, CourseList courses,
                                     Storage storage) throws MyStarsException {
        Command command;
        switch (fullCommand.trim()) {
        case AddCourseCommand.COMMAND_WORD:
            command = new AddCourseCommand(ui, courses, storage);
            break;
        case DropCourseCommand.COMMAND_WORD:
            command = new DropCourseCommand(ui, courses, storage);
            break;
        case PrintCourseRegCommand.COMMAND_WORD:
            command = new PrintCourseRegCommand(ui);
            break;
        case CheckCourseVacancyCommand.COMMAND_WORD:
            command = new CheckCourseVacancyCommand(ui, courses);
            break;
        case ChangeIndexNoCommand.COMMAND_WORD:
            command = new ChangeIndexNoCommand(ui, courses, storage);
            break;
        case SwopIndexCommand.COMMAND_WORD:
            command = new SwopIndexCommand(ui, courses, storage, users);
            break;
        case LogoutCommand.COMMAND_WORD:
            command = new LogoutCommand(ui);
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
        String username = userSplit[0].trim().toUpperCase();
        String password = userSplit[1].trim();
        String type = userSplit[2].trim().toUpperCase();
        switch (type) {
        case STUDENT:
            user = new Student();
            break;
        case ADMIN:
            user = new Admin();
            break;
        default:
            throw new MyStarsException(INVALID_TYPE_ERROR + type);
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
        String courseCode = courseSplit[0].trim().toUpperCase();
        String school = courseSplit[1].trim().toUpperCase();
        String indexNumber = courseSplit[2].trim();
        String vacancyString = courseSplit[3].trim();
        String numOfAUsString = courseSplit[4].trim();
        String lessonString = courseSplit[5].trim();

        int vacancy;
        int numOfAUs;
        try {
            vacancy = Integer.parseInt(vacancyString);
        } catch (NumberFormatException numberFormatException) {
            throw new MyStarsException(INVALID_VACANCY_ERROR);
        }

        try {
            numOfAUs = Integer.parseInt(numOfAUsString);
        } catch (NumberFormatException numberFormatException) {
            throw new MyStarsException(INVALID_AU_ERROR);
        }

        String[] lessonsString = lessonString.split(ESCAPED_ASTERISK_SEPARATOR);
        LessonList lessonList = readLessons(lessonsString);

        return new Course(courseCode, school, indexNumber, vacancy, numOfAUs, lessonList);
    }

    /**
     * Reads list of lessons and returns it.
     *
     * @param lessonsString Lessons string.
     * @return List of lessons.
     * @throws MyStarsException If there is issue reading lessons.
     */
    private LessonList readLessons(String[] lessonsString) throws MyStarsException {
        LessonList lessonList = new LessonList();
        for (String lessonString : lessonsString) {
            String[] lessonDetailsString = lessonString.split(TILDE_SEPARATOR);
            LessonType lessonType = LessonType.valueOf(lessonDetailsString[0].trim().toUpperCase());
            String venue = lessonDetailsString[1].trim().toUpperCase();
            LocalTime startTime = LocalTime.parse(lessonDetailsString[2].trim());
            LocalTime endTime = LocalTime.parse(lessonDetailsString[3].trim());
            DayOfWeek day = DayOfWeek.valueOf(lessonDetailsString[4].trim().toUpperCase());
            Week week = Week.valueOf(lessonDetailsString[5].trim().toUpperCase());
            String group = lessonDetailsString[6].trim().toUpperCase();

            Lesson lessonToAdd = new Lesson(lessonType, venue, startTime, endTime, day, week, group);

            if (lessonList.isClash(lessonToAdd)) {
                throw new MyStarsException(LESSON_CLASH_ERROR);
            }
            lessonList.addLesson(lessonToAdd);
        }

        return lessonList;
    }

    /**
     * Reads students from file.
     *
     * @param line Line of student to read.
     * @return Students of corresponding line.
     */
    public Student readStudent(String line) {

        String[] studentSplit = line.split(ESCAPED_LINE_SEPARATOR);
        String name = studentSplit[0].trim();
        String matricNo = studentSplit[1].trim().toUpperCase();
        Gender gender = Gender.valueOf(studentSplit[2].trim().toUpperCase());
        String nationality = studentSplit[3].trim();
        String username = studentSplit[4].trim().toUpperCase();

        String[] courseAndYear = studentSplit[5].split(TILDE_SEPARATOR);
        String courseOfStudy = courseAndYear[0].trim().toUpperCase();
        int yearOfStudy = Integer.parseInt(courseAndYear[1].trim());

        String email = studentSplit[6].trim();

        return new Student(name, matricNo, gender, nationality, username, courseOfStudy, yearOfStudy, email);
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
        Gender gender = Gender.valueOf(adminSplit[2].trim().toUpperCase());
        String nationality = adminSplit[3].trim();
        String username = adminSplit[4].trim().toUpperCase();

        return new Admin(name, staffId, gender, nationality, username);
    }

    /**
     * Reads student's access period.
     *
     * @param line Line of date/time to read.
     * @return Access period.
     * @throws MyStarsException If there is problem parsing date/time.
     */
    public LocalDateTime[] readStudentAccessPeriod(String line) throws MyStarsException {
        try {
            String[] dateTime = line.split(ESCAPED_LINE_SEPARATOR);
            LocalDateTime start = LocalDateTime.parse(dateTime[0].trim());
            LocalDateTime end = LocalDateTime.parse(dateTime[1].trim());

            return new LocalDateTime[]{start, end};
        } catch (DateTimeParseException dateTimeParseException) {
            throw new MyStarsException(DATETIME_PARSE_ERROR);
        }
    }

    /**
     * Reads and returns students list.
     *
     * @param line  Line to read.
     * @param users Student list.
     * @return List of students.
     */
    public ArrayList<Student> readStudentList(String line, UserList users) {
        String[] matricNos = line.split(ESCAPED_LINE_SEPARATOR);
        ArrayList<Student> registeredStudents = new ArrayList<>();
        for (int i = 1; i < matricNos.length; i++) {
            for (Student student : users.getUsers().stream().filter(Student.class::isInstance).map(Student.class::cast)
                    .collect(Collectors.toList())) {
                if (student.getMatricNo().equalsIgnoreCase(matricNos[i].trim())) {
                    registeredStudents.add(student);
                }
            }
        }
        return registeredStudents;
    }

    /**
     * Reads course index and returns it.
     *
     * @param line Line to read.
     * @return Course index.
     */
    public String readCourseIndex(String line) {
        return line.split(ESCAPED_LINE_SEPARATOR)[0];
    }

    /**
     * Returns if user input is yes.
     *
     * @param option User input.
     * @return True if yes, false otherwise.
     */
    public boolean isYes(String option) {
        return option.trim().equalsIgnoreCase(Option.Y.name());
    }

    /**
     * Returns if start time and end time is valid.
     *
     * @param startTime Start time.
     * @param endTime   End time.
     * @return True if it is valid, false otherwise.
     */
    public boolean isValidStartEndTime(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }

    /**
     * Returns if start date/time and end date/time is valid.
     *
     * @param startDateTime Start date/time.
     * @param endDateTime   End date/time.
     * @return True if it is valid, false otherwise.
     */
    public boolean isValidStartEndDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.isBefore(endDateTime);
    }
}
