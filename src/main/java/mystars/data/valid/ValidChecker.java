package mystars.data.valid;

/**
 * Validity checker.
 */
public interface ValidChecker {

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    boolean isValid(String line);
}
