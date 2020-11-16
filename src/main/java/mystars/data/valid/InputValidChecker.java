package mystars.data.valid;

import mystars.parser.Parser;

/**
 * Input valid checker.
 */
public class InputValidChecker implements ValidChecker {

    /**
     * Empty string.
     */
    private static final String EMPTY_STRING = "";

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return !line.contains(Parser.LINE_SEPARATOR) && !line.contains(Parser.ASTERISK_SEPARATOR)
                && !line.contains(Parser.TILDE_SEPARATOR) && !line.equals(EMPTY_STRING);
    }
}
