package mystars.data.mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailSenderTest {

    @Test
    void sendMail_invalidEmail_expectException() {
        assertThrows(RuntimeException.class, () -> new EmailSender().sendMail("invalidEmail", ""));
    }

    @Test
    void sendMail_validEmail_expectNoException() {
        assertDoesNotThrow(() -> new EmailSender().sendMail("cz2002.se3.group1@gmail.com", ":)"));
    }
}
