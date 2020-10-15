package mystars.ui;

public class AdminUi extends Ui {

    @Override
    public void showMenu() {
        printNicely("1. Edit student access period");
        printNicely("2. Add a student (name, matric number, gender, nationality, etc)");
        printNicely("3. Add/Update a course (course code, school, its index numbers and vacancy)");
        printNicely("4. Check available slot for an index number (vacancy in a class)");
        printNicely("5. Print student list by index number");
        printNicely("6. Print student list by course (all students registered for the selected course).\n"
                + "[ print only student’s name, gender and nationality ]");
        printNicely("7. Logout");
        printNicely("Please select an item:");
    }

    @Override
    public void greetUser() {
        printNicely("Hello admin!");
    }
}
