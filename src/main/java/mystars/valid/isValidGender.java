package mystars.valid;

import mystars.data.shared.Gender;

import java.util.Arrays;

public class isValidGender extends isValid {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    @Override
    public boolean check(String line) {
        return Arrays.stream(Gender.values()).map(Gender::name).anyMatch(line::equalsIgnoreCase);
    }
}
