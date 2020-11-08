package mystars.data.valid;

public abstract class ValidChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    public abstract boolean isValid(String line);
}
