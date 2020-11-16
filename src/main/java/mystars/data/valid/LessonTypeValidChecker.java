package mystars.data.valid;

import mystars.data.course.lesson.LessonType;

import java.util.Arrays;

/**
 * Lesson type validity checker.
 */
public class LessonTypeValidChecker implements ValidChecker {

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return Arrays.stream(LessonType.values()).map(LessonType::name).anyMatch(line::equalsIgnoreCase);
    }
}
