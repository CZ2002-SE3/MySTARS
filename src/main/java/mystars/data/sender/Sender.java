package mystars.data.sender;

import mystars.data.exception.MyStarsException;

public interface Sender {
    String SEND_MESSAGE = "Added waitlisted student to course and sent notification to student.";
    String SEND_ERROR = "Failed to send notification!";

    void send(String email, String courseCode, String indexNumber, String name) throws MyStarsException;
}
