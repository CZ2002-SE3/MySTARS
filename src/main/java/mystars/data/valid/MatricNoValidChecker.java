package mystars.data.valid;

public class MatricNoValidChecker extends ValidChecker {

    private static final int MATRIC_NO_LENGTH = 9;

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return new InputValidChecker().check(line) && line.length() == MATRIC_NO_LENGTH
                && line.substring(1, MATRIC_NO_LENGTH - 1).chars().allMatch(Character::isDigit)
                && Character.isLetter(line.charAt(0)) && Character.isLetter(line.charAt(MATRIC_NO_LENGTH - 1));
    }
}
