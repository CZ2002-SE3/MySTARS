package mystars.ui;

import mystars.data.CourseList;
import mystars.data.UserList;
import mystars.data.course.Course;
import mystars.data.course.Lesson;
import mystars.data.user.Student;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        String line = in.nextLine();
        while (!parser.isValidDateTime(line)) {
            printNicely("Enter valid date/time!");
            printNicely("Enter new start date & time in this format: yyyy-MM-dd HH:mm");
            line = in.nextLine();
        }
        LocalDateTime startDateTime = LocalDateTime.parse(line.replace(" ", "T"));

        printNicely("Enter new end date & time in this format: yyyy-MM-dd HH:mm");
        line = in.nextLine();
        while (!parser.isValidDateTime(line)) {
            printNicely("Enter valid date/time!");
            printNicely("Enter end start date & time in this format: yyyy-MM-dd HH:mm");
            line = in.nextLine();
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
            line = in.nextLine();
        }

        return Integer.parseInt(line);
    }

    private String getStudentCourseOfStudy() {
        printNicely("Enter course of study:");

        String line = in.nextLine().trim();
        while (!parser.isValidInput(line)) {
            printNicely("Enter valid course of study!");
            printNicely("Enter course of study:");
            line = in.nextLine();
        }

        return line.toUpperCase();
    }

    private String getStudentNationality() {
        printNicely("Enter nationality:");

        String line = in.nextLine().trim();
        while (!parser.isValidInput(line)) {
            printNicely("Enter valid nationality!");
            printNicely("Enter nationality:");
            line = in.nextLine();
        }

        return line;
    }

    private char getStudentGender() {
        printNicely("Enter gender (M/F):");

        String line = in.nextLine().trim();
        while (!parser.isValidGender(line)) {
            printNicely("Enter valid gender!");
            printNicely("Enter gender (M/F):");
            line = in.nextLine();
        }
        return line.charAt(0);
    }

    private String getStudentMatricNo() {
        printNicely("Enter matric number:");

        String line = in.nextLine().trim();
        while (!parser.isValidMatricNo(line)) {
            printNicely("Enter valid matric number!");
            printNicely("Enter matric number:");
            line = in.nextLine();
        }

        return line.toUpperCase();
    }

    private String getStudentName() {
        printNicely("Enter student name:");

        String line = in.nextLine().trim();
        while (!parser.isValidInput(line)) {
            printNicely("Enter valid name!");
            printNicely("Enter student name:");
            line = in.nextLine();
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
            line = in.nextLine();
        }

        return line;
    }

    public String getCourseCode() {
        printNicely("Enter course code:");

        String line = in.nextLine().trim();
        while (!parser.isValidCourseCode(line)) {
            printNicely("Enter valid course code!");
            printNicely("Enter course code:");
            line = in.nextLine();
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
        ArrayList<Lesson> lessons = getLessons();

        return new Course(courseCode, school, indexNumber, vacancy, numOfAUs, null);
    }

    private ArrayList<Lesson> getLessons() {
        // TODO: Add lessons
        ArrayList<Lesson> lessons = new ArrayList<>();
        printNicely("Enter Lesson type:");
        printNicely("1.Lecture");
        //while ()
        return new ArrayList<>();
    }

    private int getNumOfAUs() {
        printNicely("Enter number of AUs:");

        String line = in.nextLine().trim();
        while (!parser.isValidNumber(line)) {
            printNicely("Enter valid number!");
            printNicely("Enter number of AUs:");
            line = in.nextLine();
        }

        return Integer.parseInt(line);
    }

    private int getVacancy() {
        printNicely("Enter vacancy:");

        String line = in.nextLine().trim();
        while (!parser.isValidNumber(line)) {
            printNicely("Enter valid number!");
            printNicely("Enter vacancy:");
            line = in.nextLine();
        }

        return Integer.parseInt(line);
    }

    private String getSchool() {
        printNicely("Enter school:");

        String line = in.nextLine().trim();
        while (!parser.isValidSchool(line)) {
            printNicely("Enter valid school!");
            printNicely("Enter school:");
            line = in.nextLine();
        }

        return line.toUpperCase();
    }

    public void showAddedStudent(Student newStudent) {
        printNicely("");
        printNicely("Student added: " + newStudent.toString());
    }
}
