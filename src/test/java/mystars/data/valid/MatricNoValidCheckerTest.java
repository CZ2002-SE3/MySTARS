package mystars.data.valid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatricNoValidCheckerTest {

    @Test
    void isValid_invalidMatricNo_returnsFalse() {
        assertFalse(new MatricNoValidChecker().isValid("21900!00E"));
    }

    @Test
    void isValid_shorterMatricNo_returnsFalse() {
        assertFalse(new MatricNoValidChecker().isValid("U190100E"));
    }

    @Test
    void isValid_validMatricNo_returnsTrue() {
        assertTrue(new MatricNoValidChecker().isValid("U1900797E"));
    }
}
