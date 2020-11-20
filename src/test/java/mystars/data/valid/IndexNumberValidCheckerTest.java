package mystars.data.valid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexNumberValidCheckerTest {
    
    @Test
    void isValid_shortIndexNo_returnsFalse() {
        assertFalse(new IndexNumberValidChecker().isValid("1234"));
    }

    @Test
    void isValid_invalidIndexNo_returnsFalse() {
        assertFalse(new IndexNumberValidChecker().isValid("1234A"));
    }

    @Test
    void isValid_validIndexNo_returnsTrue() {
        assertTrue(new IndexNumberValidChecker().isValid("12345"));
    }
}
