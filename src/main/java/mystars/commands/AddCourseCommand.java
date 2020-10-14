package mystars.commands;

import mystars.data.UserList;
import mystars.data.user.Student;
import mystars.data.user.User;
import mystars.parser.Parser;
import mystars.storage.Storage;
import mystars.ui.Ui;

public class AddCourseCommand extends Command {

    public static final String COMMAND_WORD = "1";

    /**
     * Add course to student.
     *
     * @param users   UserList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     */
    public AddCourseCommand(){
        this.setLoginStatus(true);
    }

    @Override
    public void execute(UserList users, Ui ui, Storage storage) {
        User user = this.getUser();
        String type = users.getUserType(user);

        if (type.equals("admin")){
            return;
        }

        //list courses and wait for user's choice
        System.out.println("adding...");
        //parse user's choice

        //try to add into Student courses; may fail if there is clash

    }
}
