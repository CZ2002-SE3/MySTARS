package mystars.valid;

public class isValidCourseCode extends isValid {

    private static final int COURSE_CODE_LENGTH = 6;

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return line.length() == COURSE_CODE_LENGTH && line.substring(0, 1).chars().allMatch(Character::isLetter)
                && line.substring(2, COURSE_CODE_LENGTH - 1).chars().allMatch(Character::isDigit);
    }
}
