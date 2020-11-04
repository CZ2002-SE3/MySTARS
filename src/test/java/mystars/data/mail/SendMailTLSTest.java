package mystars.data.mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SendMailTLSTest {

    @Test
    void sendMail_invalidEmail_expectException() {
        assertThrows(RuntimeException.class, () -> new SendMailTLS().sendMail("invalidEmail", ""));
    }

    @Test
    void sendMail_validEmail_expectNoException() {
        assertDoesNotThrow(() -> new SendMailTLS().sendMail("cz2002.se3.group1@gmail.com", ":)"));
    }
}
