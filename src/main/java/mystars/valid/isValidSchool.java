package mystars.valid;

public class isValidSchool extends isValid {

    private static final int MAX_SCHOOL_LENGTH = 4;
    private static final int MIN_SCHOOL_LENGTH = 3;

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return line.length() >= MIN_SCHOOL_LENGTH && line.length() <= MAX_SCHOOL_LENGTH
                && line.chars().allMatch(Character::isLetter);
    }
}
