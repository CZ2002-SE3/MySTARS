package mystars.data.course.lesson;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LessonTest {

    @Test
    void isClash_clash_returnsTrue() {
        assertTrue((new Lesson(LessonType.LEC, "LT2", LocalTime.parse("09:30"), LocalTime.parse("10:30"),
                DayOfWeek.MONDAY, Week.BOTH, "CS2"))
                .isClash(new Lesson(LessonType.LEC, "LT4", LocalTime.parse("10:00"), LocalTime.parse("11:00"),
                        DayOfWeek.MONDAY, Week.BOTH, "CS1")));
    }

    @Test
    void isClash_noClash_returnsFalse() {
        assertFalse((new Lesson(LessonType.LEC, "LT2", LocalTime.parse("09:30"), LocalTime.parse("10:30"),
                DayOfWeek.MONDAY, Week.EVEN, "CS2"))
                .isClash(new Lesson(LessonType.LEC, "LT4", LocalTime.parse("10:30"), LocalTime.parse("11:30"),
                        DayOfWeek.MONDAY, Week.EVEN, "CS1")));
    }

    @Test
    void isClash_sameTimeDifferentWeek_returnsFalse() {
        assertFalse((new Lesson(LessonType.LEC, "LT2", LocalTime.parse("09:30"), LocalTime.parse("10:30"),
                DayOfWeek.MONDAY, Week.EVEN, "CS2"))
                .isClash(new Lesson(LessonType.LEC, "LT4", LocalTime.parse("09:30"), LocalTime.parse("10:30"),
                        DayOfWeek.MONDAY, Week.ODD, "CS1")));
    }

    @Test
    void isClash_sameTimeDifferentDay_returnsFalse() {
        assertFalse((new Lesson(LessonType.LEC, "LT2", LocalTime.parse("09:30"), LocalTime.parse("10:30"),
                DayOfWeek.MONDAY, Week.BOTH, "CS2"))
                .isClash(new Lesson(LessonType.LEC, "LT4", LocalTime.parse("09:30"), LocalTime.parse("10:30"),
                        DayOfWeek.THURSDAY, Week.BOTH, "CS1")));
    }
}
