package mystars.data.valid;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TimeValidChecker extends ValidChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        try {
            LocalTime.parse(line);
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
    }
}
