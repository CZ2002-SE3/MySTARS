package mystars.data.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private static final String[] AUTH = {"mail.smtp.auth", "true"};
    private static final String[] STARTTLS = {"mail.smtp.starttls.enable", "true"};
    private static final String[] HOST = {"mail.smtp.host", "smtp.gmail.com"};
    private static final String[] PORT = {"mail.smtp.port", "587"};
    private static final String[] USERNAME_AND_PASSWORD = {"cz2002.se3.group1@gmail.com", "ilovestars"};

    private static final String SUBJECT = "New Course Registered Notification";

    public void sendMail(String targetEmailAddress, String emailContent) {

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
            throw new RuntimeException(e);
        }
    }

    public void sendMail(String email, String courseCode, String indexNumber, String name) {
        sendMail(email, String.join(System.lineSeparator(), "Dear " + name + ",", System.lineSeparator()
                + "We are pleased to inform you that there is an available slot in " + courseCode + ", of index number "
                + indexNumber + " and you have been successfully registered for the course.", System.lineSeparator()
                + "Regards,", "STARS Administrators"));
    }
}
