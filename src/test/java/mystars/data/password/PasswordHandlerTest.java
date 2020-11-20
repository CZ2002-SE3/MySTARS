package mystars.data.password;

import mystars.data.exception.MyStarsException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PasswordHandlerTest {

    @Test
    void validatePassword_invalidPassword_returnsFalse() throws MyStarsException {
        assertFalse(new PasswordHandler().validatePassword("pass0002".toCharArray(),
                "YRvD3JGuPY5LsFDWYHFGIg==~bahNo44Oxdodc8nenlAi/w==".toCharArray()));
    }

    @Test
    void validatePassword_validPassword_returnsTrue() throws MyStarsException {
        assertTrue(new PasswordHandler().validatePassword("pass0001".toCharArray(),
                "YRvD3JGuPY5LsFDWYHFGIg==~bahNo44Oxdodc8nenlAi/w==".toCharArray()));
    }
}
