package mystars.data.valid;

/**
 * Year of study validity checker.
 */
public class YearOfStudyValidChecker implements ValidChecker {

    /**
     * Maximum year of study.
     */
    private static final int MAX_YEAR_OF_STUDY = 5;

    /**
     * Minimum year of study.
     */
    private static final int MIN_YEAR_OF_STUDY = 1;

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return line.length() == 1 && Character.isDigit(line.charAt(0))
                && Integer.parseInt(line) >= MIN_YEAR_OF_STUDY && Integer.parseInt(line) <= MAX_YEAR_OF_STUDY;
    }
}
