package mystars.commands;

import mystars.data.UserList;
import mystars.data.user.User;
import mystars.data.user.UserType;
import mystars.storage.Storage;
import mystars.ui.StudentUi;
import mystars.ui.Ui;

public class AddCourseCommand extends Command {

    public static final String COMMAND_WORD = "1";

    public AddCourseCommand() {
        this.setLoginStatus(true);
    }

    /**
     * Add course to student.
     *
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(UserList users, Ui ui, Storage storage) {
        assert ui instanceof StudentUi;

        User user = this.getUser();

        if (users.getUserType(user) == UserType.ADMIN) {
            return;
        }

        //list courses and wait for user's choice
        ((StudentUi) ui).showAddCourse();
        //parse user's choice

        //try to add into Student courses; may fail if there is clash

    }
}
