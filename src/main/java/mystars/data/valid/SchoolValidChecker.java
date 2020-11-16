package mystars.data.valid;

/**
 * School code validity checker.
 */
public class SchoolValidChecker implements ValidChecker {

    /**
     * Maximum school code length.
     */
    private static final int MAX_SCHOOL_LENGTH = 4;

    /**
     * Minimum school code length.
     */
    private static final int MIN_SCHOOL_LENGTH = 3;

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return line.length() >= MIN_SCHOOL_LENGTH && line.length() <= MAX_SCHOOL_LENGTH
                && line.chars().allMatch(Character::isLetter);
    }
}
