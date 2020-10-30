package mystars.data.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailTLS {

    public static void sendMail (String targetEmailAddress, String emailContent) {

        final String username = "cz2002.se3.group1@gmail.com"; // to be added
        final String password = "ilovestars"; // to be added

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(targetEmailAddress)); // to be added an email addr
            message.setSubject("New Course Registered Notification");
            message.setText(emailContent);

            Transport.send(message);

            System.out.println("------------------------------------------------------------");
            System.out.println("Added waitlisted student to course and email sent to inform student.");
            System.out.println("------------------------------------------------------------");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEmailContent (String courseCode, String indexNumber, String name) {
        return "Dear " + name + "," + System.lineSeparator() + System.lineSeparator() +
                "We are pleased to inform you that there is an available slot in " + courseCode + ", of index number " + indexNumber +
                " and you have been successfully registered for the course." + System.lineSeparator() + System.lineSeparator() +
                "Regards, \nSTARS Administrators";
    }

}