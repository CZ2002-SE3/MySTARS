package mystars.commands;

import mystars.data.UserList;
import mystars.data.exception.MyStarsException;
import mystars.data.user.Student;
import mystars.storage.Storage;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

public class PrintCourseRegCommand extends Command {

    public static final String COMMAND_WORD = "3";

    public PrintCourseRegCommand() {
        this.setLoginStatus(true);
    }

    @Override
    public void execute(UserList users, Ui ui, Storage storage) throws MyStarsException {
        assert ui instanceof StudentUi;

        Student student = (Student) this.getUser();

        ((StudentUi) ui).printRegCourses(student);

    }
}
