package mystars.data.sender;

import mystars.data.exception.MyStarsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailSenderTest {

    @Test
    void send_invalidEmail_expectException() {
        assertThrows(MyStarsException.class, () -> new EmailSender("invalidEmail")
                .send("CZ2002", "10001", "Tan"));
    }

    @Test
    void send_validEmail_expectNoException() {
        assertDoesNotThrow(() -> new EmailSender("cz2002.se3.group1@gmail.com")
                .send("CZ2002", "10001", "Tan"));
    }
}
