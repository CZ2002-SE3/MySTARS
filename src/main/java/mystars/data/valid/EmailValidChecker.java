package mystars.data.valid;

public class EmailValidChecker extends ValidChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        // TODO: improve email checker
        return new InputValidChecker().check(line) && line.contains("@");
    }
}

