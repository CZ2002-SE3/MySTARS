package mystars.data.exception;

/**
 * Throws exceptions relating to MySTARS.
 */
public class MyStarsException extends Exception {

    /**
     * Sorry message.
     */
    private static final String SORRY = "Sorry! ";

    /**
     * Initializes message to throw.
     *
     * @param message Exception message to show.
     */
    public MyStarsException(String message) {
        super(SORRY + message);
    }
}
