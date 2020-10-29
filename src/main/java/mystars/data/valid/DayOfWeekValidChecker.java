package mystars.data.valid;

import java.time.DayOfWeek;
import java.util.Arrays;

public class DayOfWeekValidChecker extends ValidChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return Arrays.stream(DayOfWeek.values()).map(DayOfWeek::name).anyMatch(line::equalsIgnoreCase);
    }
}
