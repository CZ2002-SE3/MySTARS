package mystars.data.valid;

import mystars.parser.Parser;

public class InputValidChecker extends ValidChecker {
    private static final String EMPTY_STRING = "";

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return !line.contains(Parser.LINE_SEPARATOR) && !line.contains(Parser.ASTERISK_SEPERATOR)
                && !line.contains(Parser.TILDE_SEPARATOR) && !line.equals(EMPTY_STRING);
    }
}
