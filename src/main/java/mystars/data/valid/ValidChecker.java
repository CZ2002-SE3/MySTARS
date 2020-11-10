package mystars.data.valid;

public interface ValidChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    boolean isValid(String line);
}
