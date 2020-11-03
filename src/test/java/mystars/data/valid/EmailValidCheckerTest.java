package mystars.data.valid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidCheckerTest {

    @Test
    void check_invalidEmail_returnsFalse() {
        assertFalse(new EmailValidChecker().check("a@a."));
    }

    @Test
    void check_validEmail_returnsTrue() {
        assertTrue(new EmailValidChecker().check("2@2.com"));
    }
}
