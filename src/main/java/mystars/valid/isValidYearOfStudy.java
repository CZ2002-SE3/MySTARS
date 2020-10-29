package mystars.valid;

public class isValidYearOfStudy extends isValid {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return line.length() == 1 && Character.isDigit(line.charAt(0))
                && Integer.parseInt(line) >= 1 && Integer.parseInt(line) <= 5;
    }
}
