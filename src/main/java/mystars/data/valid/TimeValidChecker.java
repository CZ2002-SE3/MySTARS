package mystars.data.valid;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Time validity checker.
 */
public class TimeValidChecker implements ValidChecker {

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        try {
            LocalTime.parse(line);
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
    }
}
