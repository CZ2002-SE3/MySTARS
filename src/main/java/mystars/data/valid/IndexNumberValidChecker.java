package mystars.data.valid;

/**
 * Index number validity checker.
 */
public class IndexNumberValidChecker implements ValidChecker {

    /**
     * Index number length.
     */
    private static final int INDEX_NO_LENGTH = 5;

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return line.length() == INDEX_NO_LENGTH && line.chars().allMatch(Character::isDigit);
    }
}
