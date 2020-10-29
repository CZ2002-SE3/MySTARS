package mystars.valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class isValidDateTime extends isValid {
    private static final String SPACE = " ";
    private static final String DATE_TIME_SEPARATOR = "T";

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        try {
            LocalDateTime.parse(line.replace(SPACE, DATE_TIME_SEPARATOR));
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
    }
}
