package mystars.data.valid;

public class NumberValidChecker extends ValidChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return line.chars().allMatch(Character::isDigit) && Integer.parseInt(line) >= 0;
    }
}