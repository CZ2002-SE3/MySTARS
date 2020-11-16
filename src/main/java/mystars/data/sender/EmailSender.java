package mystars.data.sender;

import mystars.data.exception.MyStarsException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Sender for email.
 */
public class EmailSender implements Sender {

    /**
     * Authentication setting.
     */
    private static final String[] AUTH = {"mail.smtp.auth", "true"};

    /**
     * STARTTLS setting.
     */
    private static final String[] STARTTLS = {"mail.smtp.starttls.enable", "true"};


    /**
     * Host setting.
     */
    private static final String[] HOST = {"mail.smtp.host", "smtp.gmail.com"};

    /**
     * Port setting.
     */
    private static final String[] PORT = {"mail.smtp.port", "587"};


    /**
     * Username and password of sender.
     */
    private static final String[] USERNAME_AND_PASSWORD = {"cz2002.se3.group1@gmail.com", "ilovestars"};

    /**
     * Subject title.
     */
    private static final String SUBJECT = "New Course Registered Notification";

    /**
     * Email address to send.
     */
    private final String targetEmailAddress;

    /**
     * Initialize Sender with email.
     *
     * @param targetEmailAddress email address to send to.
     */
    public EmailSender(String targetEmailAddress) {
        this.targetEmailAddress = targetEmailAddress;
    }

    /**
     * Sends email.
     *
     * @param emailContent Email body.
     * @throws MyStarsException If there is issue sending email.
     */
    private void send(String emailContent) throws MyStarsException {

        Properties props = new Properties();
        props.put(AUTH[0], AUTH[1]);
        props.put(STARTTLS[0], STARTTLS[1]);
        props.put(HOST[0], HOST[1]);
        props.put(PORT[0], PORT[1]);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME_AND_PASSWORD[0], USERNAME_AND_PASSWORD[1]);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetEmailAddress));
            message.setSubject(SUBJECT);
            message.setText(emailContent);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new MyStarsException(SEND_ERROR);
        }
    }

    /**
     * Sends email with given details.
     *
     * @param courseCode  Course code that was successfully registered.
     * @param indexNumber Index number of successfully registered course.
     * @param name        Name of student.
     * @throws MyStarsException If there is issue sending notification.
     */
    @Override
    public void send(String courseCode, String indexNumber, String name) throws MyStarsException {
        send(String.join(System.lineSeparator(), "Dear " + name + ",", System.lineSeparator()
                + "We are pleased to inform you that there is an available slot in " + courseCode + ", of index number "
                + indexNumber + " and you have been successfully registered for the course.", System.lineSeparator()
                + "Regards,", "STARS Administrators"));
    }
}
