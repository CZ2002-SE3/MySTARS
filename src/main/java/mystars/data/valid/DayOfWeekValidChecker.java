package mystars.data.valid;

import java.time.DayOfWeek;
import java.util.Arrays;

public class DayOfWeekValidChecker implements ValidChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return Arrays.stream(DayOfWeek.values()).map(DayOfWeek::name).anyMatch(line::equalsIgnoreCase);
    }
}
