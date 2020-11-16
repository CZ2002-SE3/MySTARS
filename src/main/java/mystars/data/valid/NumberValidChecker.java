package mystars.data.valid;

/**
 * Number validity checker.
 */
public class NumberValidChecker implements ValidChecker {

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return line.chars().allMatch(Character::isDigit) && Integer.parseInt(line) >= 0;
    }
}
