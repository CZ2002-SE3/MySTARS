package mystars.data.valid;

import mystars.data.shared.Option;

import java.util.Arrays;

public class OptionValidChecker implements ValidChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String line) {
        return Arrays.stream(Option.values()).map(Option::name).anyMatch(line::equalsIgnoreCase);
    }
}
