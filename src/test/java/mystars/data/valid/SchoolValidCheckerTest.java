package mystars.data.valid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchoolValidCheckerTest {

    @Test
    void isValid_invalidSchool_returnsFalse() {
        assertFalse(new SchoolValidChecker().isValid("SC1E"));
    }

    @Test
    void isValid_validSchool_returnsTrue() {
        assertTrue(new SchoolValidChecker().isValid("SCSE"));
    }
}
