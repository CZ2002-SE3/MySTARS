package mystars.ui;

import mystars.common.Option;
import mystars.data.CourseList;
import mystars.data.LessonList;
import mystars.data.UserList;
import mystars.data.course.Course;
import mystars.data.course.Lesson;
import mystars.data.course.LessonType;
import mystars.data.course.Week;
import mystars.data.user.Student;

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

    public LocalDateTime[] getNewAccessPeriod() {
        printNicely("");
        printNicely("Enter new start date & time in this format: yyyy-MM-dd HH:mm");
        String line = in.nextLine().trim();
        while (!parser.isValidDateTime(line)) {
            printNicely("Enter valid date/time!");
            printNicely("Enter new start date & time in this format: yyyy-MM-dd HH:mm");
            line = in.nextLine().trim();
        }
        LocalDateTime startDateTime = LocalDateTime.parse(line.replace(" ", "T"));

        printNicely("Enter new end date & time in this format: yyyy-MM-dd HH:mm");
        line = in.nextLine().trim();
        while (!parser.isValidDateTime(line)) {
            printNicely("Enter valid date/time!");
            printNicely("Enter end start date & time in this format: yyyy-MM-dd HH:mm");
            line = in.nextLine().trim();
        }
        LocalDateTime endDateTime = LocalDateTime.parse(line.replace(" ", "T"));

        return new LocalDateTime[]{startDateTime, endDateTime};
    }

    public void showNewAccessPeriod(LocalDateTime[] accessDateTime) {
        printNicely("");
        printNicely("Successfully changed! Access period is as follows: ");
        printNicely(accessDateTime[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        printNicely(accessDateTime[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        printNicely("Please restart program for change to take effect.");
    }

    public Student getNewStudentFromUser() {
        String name = getStudentName();
        String matricNo = getStudentMatricNo();
        char gender = getStudentGender();
        String nationality = getStudentNationality();
        String courseOfStudy = getStudentCourseOfStudy();
        int yearOfStudy = getStudentYearOfStudy();
        char[][] usernameAndPassword = readUsernameAndPassword();
        return new Student(name, matricNo, gender, nationality, courseOfStudy, yearOfStudy, usernameAndPassword[0]
                , usernameAndPassword[1]);
    }

    private int getStudentYearOfStudy() {
        printNicely("Enter year of study:");

        String line = in.nextLine().trim();
        while (!parser.isValidYearOfStudy(line)) {
            printNicely("Enter valid year of study!");
            printNicely("Enter year of study:");
            line = in.nextLine().trim();
        }

        return Integer.parseInt(line);
    }

    private String getStudentCourseOfStudy() {
        printNicely("Enter course of study:");

        String line = in.nextLine().trim();
        while (!parser.isValidStartEndTime(line)) {
            printNicely("Enter valid course of study!");
            printNicely("Enter course of study:");
            line = in.nextLine().trim();
        }

        return line.toUpperCase();
    }

    private String getStudentNationality() {
        printNicely("Enter nationality:");

        String line = in.nextLine().trim();
        while (!parser.isValidStartEndTime(line)) {
            printNicely("Enter valid nationality!");
            printNicely("Enter nationality:");
            line = in.nextLine().trim();
        }

        return line;
    }

    private char getStudentGender() {
        printNicely("Enter gender (M/F):");

        String line = in.nextLine().trim();
        while (!parser.isValidGender(line)) {
            printNicely("Enter valid gender!");
            printNicely("Enter gender (M/F):");
            line = in.nextLine().trim();
        }
        return line.charAt(0);
    }

    private String getStudentMatricNo() {
        printNicely("Enter matric number:");

        String line = in.nextLine().trim();
        while (!parser.isValidMatricNo(line)) {
            printNicely("Enter valid matric number!");
            printNicely("Enter matric number:");
            line = in.nextLine().trim();
        }

        return line.toUpperCase();
    }

    private String getStudentName() {
        printNicely("Enter student name:");

        String line = in.nextLine().trim();
        while (!parser.isValidStartEndTime(line)) {
            printNicely("Enter valid name!");
            printNicely("Enter student name:");
            line = in.nextLine().trim();
        }

        return line;
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

    public String getIndexNumber() {
        printNicely("Enter index number:");

        String line = in.nextLine().trim();
        while (!parser.isValidIndexNumber(line)) {
            printNicely("Enter valid index number!");
            printNicely("Enter index number:");
            line = in.nextLine().trim();
        }

        return line;
    }

    public String getCourseCode() {
        printNicely("Enter course code:");

        String line = in.nextLine().trim();
        while (!parser.isValidCourseCode(line)) {
            printNicely("Enter valid course code!");
            printNicely("Enter course code:");
            line = in.nextLine().trim();
        }

        return line.toUpperCase();
    }

    public void showVacancy(CourseList courses, String indexNumber) {
        for (Course course : courses.getCourses()) {
            if (course.getIndexNumber().equals(indexNumber)) {
                printNicely("The number of vacancy is " + course.getVacancy() + ".");
                return;
            }
        }
        printNicely("Index not found!");
    }

    public Course getCourseDetails(String indexNumber) {
        String courseCode = getCourseCode();
        String school = getSchool();
        int vacancy = getVacancy();
        int numOfAUs = getNumOfAUs();
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
            option = askOption("Add more lesson?");
        }

        return lessonList;
    }

    private Lesson getLesson() {
        LessonType lessonType = getLessonType();
        String venue = getVenue();
        LocalTime[] times = getStartAndEndTime();
        DayOfWeek day = getDayOfWeek();
        Week week = getWeek();
        String group = getGroup();
        return new Lesson(lessonType, venue, times[0], times[1], day, week, group);
    }

    private LocalTime[] getStartAndEndTime() {
        LocalTime startTime = getTime("start");
        LocalTime endTime = getTime("end");
        while (!parser.isValidStartEndTime(startTime, endTime)) {
            printNicely("End time is before start time!");
            startTime = getTime("start");
            endTime = getTime("end");
        }
        return new LocalTime[]{startTime, endTime};
    }

    private String getGroup() {
        printNicely("Enter group:");

        String line = in.nextLine().trim();
        while (!parser.isValidStartEndTime(line)) {
            printNicely("Enter valid group!");
            printNicely("Enter group:");
            line = in.nextLine().trim();
        }

        return line.toUpperCase();
    }

    private DayOfWeek getDayOfWeek() {
        printNicely("Enter day:");

        String line = in.nextLine().trim();
        while (!parser.isValidDayOfWeek(line)) {
            printNicely("Enter valid day!");
            printNicely("Enter day:");
            line = in.nextLine().trim();
        }

        return DayOfWeek.valueOf(line.toUpperCase());
    }

    private LocalTime getTime(String type) {
        printNicely("Enter " + type + " time (HH:mm)");

        String line = in.nextLine().trim();
        while (!parser.isValidTime(line)) {
            printNicely("Enter valid " + type + " time!");
            printNicely("Enter " + type + " time (HH:mm):");
            line = in.nextLine().trim();
        }

        return LocalTime.parse(line);
    }

    private String getVenue() {
        printNicely("Enter venue:");

        String line = in.nextLine().trim();
        while (!parser.isValidStartEndTime(line)) {
            printNicely("Enter valid venue!");
            printNicely("Enter venue:");
            line = in.nextLine().trim();
        }

        return line.toUpperCase();
    }

    private LessonType getLessonType() {
        printNicely("Enter Lesson type(LEC for Lecture, TUT for Tutorial, LAB for Lab):");
        String line = in.nextLine().trim();
        while (!parser.isValidLessonType(line)) {
            printNicely("Enter valid lesson type!");
            printNicely("Enter Lesson type(LEC for Lecture, TUT for Tutorial, LAB for Lab):");
            line = in.nextLine().trim();
        }
        return LessonType.valueOf(line.toUpperCase());
    }

    private int getNumOfAUs() {
        printNicely("Enter number of AUs:");

        String line = in.nextLine().trim();
        while (!parser.isValidNumber(line)) {
            printNicely("Enter valid number!");
            printNicely("Enter number of AUs:");
            line = in.nextLine().trim();
        }

        return Integer.parseInt(line);
    }

    private int getVacancy() {
        printNicely("Enter vacancy:");

        String line = in.nextLine().trim();
        while (!parser.isValidNumber(line)) {
            printNicely("Enter valid number!");
            printNicely("Enter vacancy:");
            line = in.nextLine().trim();
        }

        return Integer.parseInt(line);
    }

    private String getSchool() {
        printNicely("Enter school:");

        String line = in.nextLine().trim();
        while (!parser.isValidSchool(line)) {
            printNicely("Enter valid school!");
            printNicely("Enter school:");
            line = in.nextLine().trim();
        }

        return line.toUpperCase();
    }

    public void showAddedStudent(Student newStudent) {
        printNicely("");
        printNicely("Student added: " + newStudent.toString());
    }

    public void showCourseList(CourseList courses) {
        courses.getCourses().forEach(course -> printNicely(course.toString()));
    }
}
