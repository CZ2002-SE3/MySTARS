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

public class AdminUi extends Ui {

    @Override
    public void showMenu() {
        printNicely("1. Edit student access period");
        printNicely("2. Add a student (name, matric number, gender, nationality, etc)");
        printNicely("3. Add/Update a course (course code, school, its index numbers and vacancy)");
        printNicely("4. Check available slot for an index number (vacancy in a class)");
        printNicely("5. Print student list by index number");
        printNicely("6. Print student list by course (all students registered for the selected course).");
        // "[ print only student’s name, gender and nationality ]"
        printNicely("7. Logout");
        printNicely("Please select an item:");
    }

    @Override
    public void greetUser() {
        printNicely("Hello admin!");
    }

    public LocalDateTime[] getNewAccessPeriod() throws MyStarsException {
        String startDateTimeString = getUserInput("Enter new start date & time in this format: yyyy-MM-dd HH:mm", new DateTimeValidChecker());
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString.replace(" ", "T"));

        String endDateTimeString = getUserInput("Enter new end date & time in this format: yyyy-MM-dd HH:mm", new DateTimeValidChecker());
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString.replace(" ", "T"));

        if (startDateTime.isAfter(endDateTime)) {
            throw new MyStarsException("End date/time cannot be early than start date/time");
        }

        return new LocalDateTime[]{startDateTime, endDateTime};
    }

    public void showNewAccessPeriod(LocalDateTime[] accessDateTime) {
        printNicely("");
        printNicely("Successfully changed! Access period is as follows: ");
        printNicely(accessDateTime[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        printNicely(accessDateTime[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        printNicely("Please restart program for change to take effect.");
    }

    public Student getNewStudentFromUser() throws MyStarsException {
        String name = getUserInput("Enter student name:", new InputValidChecker());
        String matricNo = getUserInput("Enter matric number:", new MatricNoValidChecker()).toUpperCase();
        Gender gender = Gender.valueOf(getUserInput("Enter gender (M/F):", new GenderValidChecker()).toUpperCase());
        String nationality = getUserInput("Enter nationality:", new InputValidChecker());
        String courseOfStudy = getUserInput("Enter course of study:", new InputValidChecker()).toUpperCase();
        int yearOfStudy = Integer.parseInt(getUserInput("Enter year of study:", new YearOfStudyValidChecker()));
        String email = getUserInput("Enter email:", new EmailValidChecker());
        char[][] usernameAndPassword = readUsernameAndPassword();
        usernameAndPassword[1] = new PasswordHandler().generatePBKDF2String(usernameAndPassword[1]).toCharArray();
        return new Student(name, matricNo, gender, nationality, courseOfStudy, yearOfStudy, email, usernameAndPassword[0]
                , usernameAndPassword[1]);
    }

    public void showStudentList(UserList users) {
        printNicely("");
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

    public Course getCourseDetails(String indexNumber) {
        String courseCode = getUserInput("Enter course code:", new CourseCodeValidChecker());
        String school = getUserInput("Enter school:", new SchoolValidChecker()).toUpperCase();
        int vacancy = Integer.parseInt(getUserInput("Enter vacancy:", new NumberValidChecker()));
        int numOfAUs = Integer.parseInt(getUserInput("Enter number of AUs:", new NumberValidChecker()));
        LessonList lessonList = getLessonList();

        return new Course(courseCode, school, indexNumber, vacancy, numOfAUs, lessonList);
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
            if (!lessonList.addLesson(getLesson())) {
                printNicely("Not added as timing clash!");
            }
            option = getUserInput("Add more lesson? (Y/N)", new OptionValidChecker());
        }

        return lessonList;
    }

    private Lesson getLesson() {
        LessonType lessonType = LessonType.valueOf(getUserInput("Enter Lesson type(LEC for Lecture, TUT for Tutorial, LAB for Lab):", new LessonTypeValidChecker()).toUpperCase());
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


    public void showAddedStudent(Student newStudent) {
        printNicely("");
        printNicely("Student added: " + newStudent.toString());
    }

    public void showCourseList(CourseList courses) {
        courses.getCourses().forEach(course -> printNicely(course.toString()));
    }
}
