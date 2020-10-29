package mystars.valid;

public class isValidIndexNumber extends isValid {

    private static final int INDEX_NO_LENGTH = 5;

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return line.length() == INDEX_NO_LENGTH && line.chars().allMatch(Character::isDigit);
    }
}
