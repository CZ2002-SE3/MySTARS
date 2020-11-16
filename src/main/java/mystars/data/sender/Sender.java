package mystars.data.sender;

import mystars.data.exception.MyStarsException;

/**
 * Interface for senders.
 */
public interface Sender {

    /**
     * Sent message.
     */
    String SEND_MESSAGE = "Added waitlisted student to course and sent notification to student.";

    /**
     * Fail to send error message.
     */
    String SEND_ERROR = "Failed to send notification!";

    /**
     * Sends notification to student.
     *
     * @param courseCode  Course that is successfully added.
     * @param indexNumber Index of course that is successfully added.
     * @param name        Name of student.
     * @throws MyStarsException If there is issue sending notification.
     */
    void send(String courseCode, String indexNumber, String name) throws MyStarsException;
}
