package mystars.ui;

import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        try {
            printNicely("");
            printNicely("Enter new start date & time in this format: yyyy-MM-dd HH:mm");
            LocalDateTime startDateTime = LocalDateTime.parse(in.nextLine().replace(" ", "T"));
            printNicely("Enter new end date & time in this format: yyyy-MM-dd HH:mm");
            LocalDateTime endDateTime = LocalDateTime.parse(in.nextLine().replace(" ", "T"));
            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (DateTimeParseException dateTimeParseException) {
            throw new MyStarsException("Invalid Date/Time!");
        }

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
        return Integer.parseInt(in.nextLine());
    }

    private String getStudentCourseOfStudy() {
        printNicely("Enter course of study:");
        return in.nextLine();
    }

    private String getStudentNationality() {
        printNicely("Enter nationality:");
        return in.nextLine();
    }

    private char getStudentGender() {
        printNicely("Enter gender (M/F):");
        return in.nextLine().charAt(0);
    }

    private String getStudentMatricNo() {
        printNicely("Enter matric number:");
        return in.nextLine();
    }

    private String getStudentName() {
        printNicely("Enter student name:");
        return in.nextLine();
    }

    public void showAddedStudent(Student newStudent) {
        printNicely("Student Added: " + newStudent.toString());
    }
}
