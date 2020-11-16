package mystars.data.valid;

import mystars.data.course.lesson.Week;

import java.util.Arrays;

/**
 * Week validity checker.
 */
public class WeekValidChecker implements ValidChecker {

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return Arrays.stream(Week.values()).map(Week::name).anyMatch(line::equalsIgnoreCase);
    }
}
