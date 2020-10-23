package mystars.ui;

import mystars.data.user.Student;

import java.time.LocalDateTime;
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
        return new Student(name, matricNo, gender, nationality, courseOfStudy, yearOfStudy);
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

    public void showAddedStudent(Student newStudent) {
        printNicely("Student Added: " + newStudent.toString());
    }
}
