package mystars.ui;

import mystars.data.course.Course;
import mystars.data.course.CourseList;
import mystars.data.course.LessonList;
import mystars.data.course.lesson.Lesson;
import mystars.data.course.lesson.LessonType;
import mystars.data.course.lesson.Week;
import mystars.data.exception.MyStarsException;
import mystars.data.password.PasswordHandler;
import mystars.data.shared.Gender;
import mystars.data.shared.Option;
import mystars.data.user.Student;
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
import mystars.data.valid.YearOfStudyValidChecker;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class AdminUi extends Ui {

    private static final String MENU = String.join(System.lineSeparator(), "1. Edit student access period",
            "2. Add a student (name, matric number, gender, nationality, etc)",
            "3. Add/Update a course (course code, school, its index numbers and vacancy)",
            "4. Check available slot for an index number (vacancy in a class)", "5. Print student list by index number",
            "6. Print student list by course (all students registered for the selected course).", "7. Logout",
            "Please select an item:");
    private static final String WELCOME_MESSAGE = "Hello Admin!";

    @Override
    public void showMenu() {
        showToUser(MENU);
    }

    @Override
    public void greetUser() {
        printNicely(WELCOME_MESSAGE);
    }

    public void showStartEndDateTime(LocalDateTime[] accessDateTime, String message) {
        printNicely(message);
        printNicely("Start Date/Time\t: "
                + accessDateTime[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        printNicely("End Date/Time\t: "
                + accessDateTime[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public void showAccessPeriod(LocalDateTime[] accessDateTime) {
        showStartEndDateTime(accessDateTime, "Here is the access period currently:");
    }

    public void showNewAccessPeriod(LocalDateTime[] accessDateTime) {
        printNicely();
        showStartEndDateTime(accessDateTime, "Successfully changed! Access period is as follows: ");
    }

    public void showStudentList(UserList users) {
        printNicely();
        printNicely("Here is the student list:");
        users.getUsers().stream().filter(Student.class::isInstance).forEach(user -> printNicely(user.toString()));
    }

    public void showStudentListByIndex(UserList users, String indexNumber) {
        printNicely("Here are the list of students:");
        users.getUsers().stream().filter(Student.class::isInstance)
                .filter((student) -> ((Student) student).getRegisteredCourses().isIndexNoInList(indexNumber))
                .forEach(user -> printNicely(user.toString()));
    }

    public void showStudentListByCourse(UserList users, String courseCode) {
        printNicely("Here are the list of students:");
        users.getUsers().stream().filter(Student.class::isInstance)
                .filter((student) -> ((Student) student).getRegisteredCourses().isCourseInList(courseCode))
                .forEach(user -> printNicely(user.toString()));
    }

    public void showCourseList(CourseList courses) {
        printNicely();
        printNicely("Here is the courses list:");
        printNicely(courses.getCourses().stream().map(Course::toString)
                .collect(Collectors.joining(System.lineSeparator() + System.lineSeparator())));
    }

    public void showAddedStudent(Student newStudent) {
        printNicely();
        printNicely("Student added: " + newStudent.toString());
    }

    public Course updateCourseDetails(String indexNumber, Course course) throws MyStarsException {
        Course newCourse = getCourseDetails(indexNumber);
        course.checkEnoughVacancies(newCourse.getInitialVacancies());
        newCourse.setLessonList(getLessonList());

        return newCourse;
    }

    public Course getCourseDetails(String indexNumber) {
        String courseCode = getUserInput("Enter course code:", new CourseCodeValidChecker());
        String school = getUserInput("Enter school:", new SchoolValidChecker()).toUpperCase();
        int initialVacancies = Integer.parseInt(getUserInput("Enter vacancy:", new NumberValidChecker()));
        int numOfAUs = Integer.parseInt(getUserInput("Enter number of AUs:", new NumberValidChecker()));
        LessonList lessonList = getLessonList();

        return new Course(courseCode, school, indexNumber, initialVacancies, numOfAUs, lessonList);
    }

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

    private Week getWeek() {
        printNicely("Enter Week (ODD, EVEN, BOTH):");
        String line = in.nextLine().trim();
        while (!parser.isValidWeek(line)) {
            printNicely("Enter valid week!");
            printNicely("Enter Week (ODD, EVEN, BOTH):");
            line = in.nextLine().trim();
        }
        return Week.valueOf(line.toUpperCase());
    }

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

    private Lesson getLesson() {
        LessonType lessonType = LessonType.valueOf(
                getUserInput("Enter Lesson type(LEC for Lecture, TUT for Tutorial, LAB for Lab):",
                        new LessonTypeValidChecker()).toUpperCase());
        String venue = getUserInput("Enter venue:", new InputValidChecker()).toUpperCase();
        LocalTime[] times = getStartAndEndTime();
        DayOfWeek day = DayOfWeek.valueOf(getUserInput("Enter day", new DayOfWeekValidChecker()).toUpperCase());
        Week week = getWeek();
        String group = getUserInput("Enter group:", new InputValidChecker()).toUpperCase();
        return new Lesson(lessonType, venue, times[0], times[1], day, week, group);
    }

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

    public boolean askUpdate() {
        return parser.isYes(getUserInput("Do you want to update course? (Y/N)", new OptionValidChecker()));
    }
}
