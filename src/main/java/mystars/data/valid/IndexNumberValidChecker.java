package mystars.data.valid;

public class IndexNumberValidChecker implements ValidChecker {

    private static final int INDEX_NO_LENGTH = 5;

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return line.length() == INDEX_NO_LENGTH && line.chars().allMatch(Character::isDigit);
    }
}
