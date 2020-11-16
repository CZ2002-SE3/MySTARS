package mystars.data.valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Date/Time validity checker.
 */
public class DateTimeValidChecker implements ValidChecker {

    /**
     * Space string.
     */
    private static final String SPACE = " ";

    /**
     * Date time separator string.
     */
    private static final String DATE_TIME_SEPARATOR = "T";

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        try {
            LocalDateTime.parse(line.replace(SPACE, DATE_TIME_SEPARATOR));
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
    }
}
