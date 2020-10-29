package mystars.valid;

import mystars.data.course.lesson.LessonType;

import java.util.Arrays;

public class isValidLessonType extends isValid {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return Arrays.stream(LessonType.values()).map(LessonType::name).anyMatch(line::equalsIgnoreCase);
    }
}
