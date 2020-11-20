package mystars.data.valid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseCodeValidCheckerTest {

    @Test
    void isValid_invalidCourseCode_returnsFalse() {
        assertFalse(new CourseCodeValidChecker().isValid("CZ2002A"));
    }

    @Test
    void isValid_validCourseCode_returnsTrue() {
        assertTrue(new CourseCodeValidChecker().isValid("CZ2002"));
    }
}