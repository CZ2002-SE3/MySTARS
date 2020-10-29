package mystars.valid;

import mystars.parser.Parser;

public class isValidInput extends isValid {
    private static final String EMPTY_STRING = "";

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return !line.contains(Parser.LINE_SEPARATOR) && !line.equals(EMPTY_STRING);
        // TODO: put line seperator somewhere else
    }
}
