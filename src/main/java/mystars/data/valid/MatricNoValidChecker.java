package mystars.data.valid;

/**
 * Matriculation number validity checker.
 */
public class MatricNoValidChecker implements ValidChecker {

    /**
     * Matriculation number length.
     */
    private static final int MATRIC_NO_LENGTH = 9;

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return new InputValidChecker().isValid(line) && line.length() == MATRIC_NO_LENGTH
                && line.substring(1, MATRIC_NO_LENGTH - 1).chars().allMatch(Character::isDigit)
                && Character.isLetter(line.charAt(0)) && Character.isLetter(line.charAt(MATRIC_NO_LENGTH - 1));
    }
}
