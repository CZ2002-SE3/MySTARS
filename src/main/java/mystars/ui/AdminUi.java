package mystars.ui;

import mystars.data.course.Course;
import mystars.data.course.lesson.Lesson;
import mystars.data.course.lesson.LessonList;
import mystars.data.course.lesson.LessonType;
import mystars.data.course.lesson.Week;
import mystars.data.exception.MyStarsException;
import mystars.data.password.PasswordHandler;
import mystars.data.shared.Gender;
import mystars.data.shared.Option;
import mystars.data.user.Student;
import mystars.data.user.User;
import mystars.data.user.UserList;
import mystars.data.valid.CourseCodeValidChecker;
import mystars.data.valid.DateTimeValidChecker;
import mystars.data.valid.DayOfWeekValidChecker;
import mystars.data.valid.EmailValidChecker;
import mystars.data.valid.GenderValidChecker;
import mystars.data.valid.InputValidChecker;
import mystars.data.valid.LessonTypeValidChecker;
import mystars.data.valid.MatricNoValidChecker;
import mystars.data.valid.NumberValidChecker;
import mystars.data.valid.OptionValidChecker;
import mystars.data.valid.SchoolValidChecker;
import mystars.data.valid.TimeValidChecker;
import mystars.data.valid.WeekValidChecker;
import mystars.data.valid.YearOfStudyValidChecker;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Admin user interface.
 */
public class AdminUi extends Ui {

    /**
     * Student table header.
     */
    private static final String STUDENT_HEADER = String.format(Student.FORMAT, "Name", "Gender", "Nationality");

    /**
     * Menu.
     */
    private static final String MENU = String.join(System.lineSeparator(), "1. Edit student access period",
            "2. Add a student", "3. Add/Update a course", "4. Check available slot for an index number",
            "5. Print student list by index number", "6. Print student list by course", "7. Logout",
            "Please select an item:");

    /**
     * Welcome message.
     */
    private static final String WELCOME_MESSAGE = "Hello Admin!";

    /**
     * Prints menu.
     */
    @Override
    public void showMenu() {
        showToUser(MENU);
    }

    /**
     * Greets user.
     */
    @Override
    public void greetUser() {
        printNicely(WELCOME_MESSAGE);
    }

    /**
     * Prints start date/time and end date/time.
     *
     * @param accessDateTime Start date/time and end date/time.
     * @param message        Message to print.
     */
    public void showStartEndDateTime(LocalDateTime[] accessDateTime, String message) {
        printNicely(message);
        printNicely("Start Date/Time\t: "
                + accessDateTime[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        printNicely("End Date/Time\t: "
                + accessDateTime[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     * Prints current access period.
     *
     * @param accessDateTime Access period.
     */
    public void showAccessPeriod(LocalDateTime[] accessDateTime) {
        showStartEndDateTime(accessDateTime, "Here is the access period currently:");
    }

    /**
     * Prints new access period.
     *
     * @param accessDateTime Access period.
     */
    public void showNewAccessPeriod(LocalDateTime[] accessDateTime) {
        printNicely();
        showStartEndDateTime(accessDateTime, "Successfully changed! Access period is as follows:");
    }

    /**
     * Prints student list.
     *
     * @param users List of users.
     */
    public void showStudentList(UserList users) {
        printNicely();
        printNicely("Here is the list of students:");
        printNicely(STUDENT_HEADER);
        for (User user : users.getUsers()) {
            if (user instanceof Student) {
                printNicely(user.toString());
            }
        }
    }

    /**
     * Prints student list of an index.
     *
     * @param users       List of users.
     * @param indexNumber Index number to filter.
     */
    public void showStudentListByIndex(UserList users, String indexNumber) {
        printNicely();
        printNicely("Here is the list of students of index " + indexNumber + ":");
        printNicely(STUDENT_HEADER);
        for (User user : users.getUsers()) {
            if (user instanceof Student && ((Student) user).getRegisteredCourses().isIndexNoInList(indexNumber)) {
                printNicely(user.toString());
            }
        }
    }

    /**
     * Prints student list of a course code.
     *
     * @param users      List of users.
     * @param courseCode Course code to filter.
     */
    public void showStudentListByCourse(UserList users, String courseCode) {
        printNicely();
        printNicely("Here is the list of students of course " + courseCode + ":");
        printNicely(STUDENT_HEADER);
        for (User user : users.getUsers()) {
            if (user instanceof Student && ((Student) user).getRegisteredCourses().isCourseInList(courseCode)) {
                printNicely(user.toString());
            }
        }
    }

    /**
     * Prints added student.
     *
     * @param newStudent Added student,
     */
    public void showAddedStudent(Student newStudent) {
        printNicely();
        printNicely("Student added:");
        printNicely(STUDENT_HEADER);
        printNicely(newStudent.toString());
    }

    /**
     * Updates course details with input from user.
     *
     * @param indexNumber Index number of course.
     * @param course      Course to update.
     * @return Updated course.
     * @throws MyStarsException If there are not enough vacancies.
     */
    public Course updateCourseDetails(String indexNumber, Course course) throws MyStarsException {
        Course newCourse = getCourseDetails(indexNumber);
        course.checkEnoughVacancies(newCourse.getInitialVacancies());
        newCourse.setLessonList(getLessonList());

        return newCourse;
    }

    /**
     * Gets course details with input from user.
     *
     * @param indexNumber Index number of course.
     * @return New course.
     */
    public Course getCourseDetails(String indexNumber) {
        String courseCode = getUserInput("Enter course code:", new CourseCodeValidChecker()).toUpperCase();
        String school = getUserInput("Enter school:", new SchoolValidChecker()).toUpperCase();
        int initialVacancies = Integer.parseInt(getUserInput("Enter vacancy:", new NumberValidChecker()));
        int numOfAUs = Integer.parseInt(getUserInput("Enter number of AUs:", new NumberValidChecker()));
        LessonList lessonList = getLessonList();

        return new Course(courseCode, school, indexNumber, initialVacancies, numOfAUs, lessonList);
    }

    /**
     * Gets new access period from user.
     *
     * @return New access period.
     */
    public LocalDateTime[] getNewAccessPeriod() {
        String startDateTimeString = getUserInput("Enter new start date & time (yyyy-MM-dd HH:mm):",
                new DateTimeValidChecker());
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString.replace(" ", "T"));

        String endDateTimeString = getUserInput("Enter new end date & time (yyyy-MM-dd HH:mm):",
                new DateTimeValidChecker());
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString.replace(" ", "T"));

        while (!parser.isValidStartEndDateTime(startDateTime, endDateTime)) {
            printNicely("End date & time is before start date & time!");
            startDateTimeString = getUserInput("Enter new start date & time (yyyy-MM-dd HH:mm):",
                    new DateTimeValidChecker());
            startDateTime = LocalDateTime.parse(startDateTimeString.replace(" ", "T"));
            endDateTimeString = getUserInput("Enter new end date & time (yyyy-MM-dd HH:mm):",
                    new DateTimeValidChecker());
            endDateTime = LocalDateTime.parse(endDateTimeString.replace(" ", "T"));
        }

        return new LocalDateTime[]{startDateTime, endDateTime};
    }

    /**
     * Gets new student from user.
     *
     * @param users List of users.
     * @return New student.
     * @throws MyStarsException If there is issue adding student.
     */
    public Student getNewStudentFromUser(UserList users) throws MyStarsException {
        String name = getUserInput("Enter student name:", new InputValidChecker());
        String matricNo = getUserInput("Enter matric number:", new MatricNoValidChecker()).toUpperCase();
        users.checkDuplicateMatricNo(matricNo);
        Gender gender = Gender.valueOf(getUserInput("Enter gender (M/F):",
                new GenderValidChecker()).toUpperCase());
        String nationality = getUserInput("Enter nationality:", new InputValidChecker());
        String courseOfStudy = getUserInput("Enter course of study:", new InputValidChecker()).toUpperCase();
        int yearOfStudy = Integer.parseInt(getUserInput("Enter year of study:", new YearOfStudyValidChecker()));
        String email = getUserInput("Enter email:", new EmailValidChecker());
        char[][] usernameAndPassword = readUsernameAndPassword();
        users.checkDuplicateUsername(usernameAndPassword[0]);
        usernameAndPassword[1] = new PasswordHandler().generatePBKDF2String(usernameAndPassword[1]).toCharArray();
        return new Student(name, matricNo, gender, nationality, courseOfStudy, yearOfStudy, email,
                usernameAndPassword[0], usernameAndPassword[1]);
    }

    /**
     * Gets list of lessons from user.
     *
     * @return List of lessons.
     */
    private LessonList getLessonList() {
        LessonList lessonList = new LessonList();
        String option = Option.Y.name();
        while (parser.isYes(option)) {
            Lesson lesson = getLesson();
            if (lessonList.isClash(lesson)) {
                printNicely("Not added as timing clash!");
            } else {
                lessonList.addLesson(lesson);
            }
            option = getUserInput("Add more lesson? (Y/N)", new OptionValidChecker());
        }

        return lessonList;
    }

    /**
     * Gets lesson from user.
     *
     * @return Lesson.
     */
    private Lesson getLesson() {
        LessonType lessonType = LessonType.valueOf(
                getUserInput("Enter Lesson type (LEC for Lecture, TUT for Tutorial, LAB for Lab):",
                        new LessonTypeValidChecker()).toUpperCase());
        String venue = getUserInput("Enter venue:", new InputValidChecker()).toUpperCase();
        LocalTime[] times = getStartAndEndTime();
        DayOfWeek day = DayOfWeek.valueOf(getUserInput("Enter day", new DayOfWeekValidChecker()).toUpperCase());
        Week week = Week.valueOf(getUserInput("Enter week (ODD, EVEN, BOTH):",
                new WeekValidChecker()).toUpperCase());
        String group = getUserInput("Enter group:", new InputValidChecker()).toUpperCase();
        return new Lesson(lessonType, venue, times[0], times[1], day, week, group);
    }

    /**
     * Gets start and end time from user.
     *
     * @return Stars and end time.
     */
    private LocalTime[] getStartAndEndTime() {
        LocalTime startTime = LocalTime.parse(getUserInput("Enter start time (HH:mm)", new TimeValidChecker()));
        LocalTime endTime = LocalTime.parse(getUserInput("Enter end time (HH:mm)", new TimeValidChecker()));
        while (!parser.isValidStartEndTime(startTime, endTime)) {
            printNicely("End time is before start time!");
            startTime = LocalTime.parse(getUserInput("Enter start time (HH:mm)", new TimeValidChecker()));
            endTime = LocalTime.parse(getUserInput("Enter end time (HH:mm)", new TimeValidChecker()));
        }
        return new LocalTime[]{startTime, endTime};
    }

    /**
     * Asks user whether to update course.
     *
     * @return True if users want to update course, false otherwise.
     */
    public boolean askUpdate() {
        return parser.isYes(getUserInput("Do you want to update course? (Y/N)", new OptionValidChecker()));
    }

    /**
     * Prints existing course.
     *
     * @param course Course to show.
     */
    public void showExistingCourse(Course course) {
        printNicely();
        printNicely("Here is the course to be updated:");
        showCourse(course);
    }
}
