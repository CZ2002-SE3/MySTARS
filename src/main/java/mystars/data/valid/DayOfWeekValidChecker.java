package mystars.data.valid;

import java.time.DayOfWeek;
import java.util.Arrays;

/**
 * Day of week validity checker.
 */
public class DayOfWeekValidChecker implements ValidChecker {

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return Arrays.stream(DayOfWeek.values()).map(DayOfWeek::name).anyMatch(line::equalsIgnoreCase);
    }
}
