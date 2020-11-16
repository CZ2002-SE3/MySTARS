package mystars.data.shared;

import java.time.LocalDateTime;

/**
 * Access date/time.
 */
public class AccessDateTime {
    /**
     * Array of start date/time and end date/time.
     */
    private LocalDateTime[] accessDateTime;

    /**
     * Initializes access date/time.
     *
     * @param accessDateTime Date/time to initialize with.
     */
    public AccessDateTime(LocalDateTime[] accessDateTime) {
        setAccessDateTime(accessDateTime);
    }

    /**
     * Returns current access date/time.
     *
     * @return Current access date/time.
     */
    public LocalDateTime[] getAccessDateTime() {
        return accessDateTime;
    }

    /**
     * Sets access date/time.
     *
     * @param accessDateTime Access date/time to set.
     */
    public void setAccessDateTime(LocalDateTime[] accessDateTime) {
        this.accessDateTime = accessDateTime;
    }

    /**
     * Returns if it is access period.
     *
     * @return True if login time is within access period, false otherwise.
     */
    public boolean isAccessPeriod() {
        return !getAccessDateTime()[0].isAfter(LocalDateTime.now())
                && !getAccessDateTime()[1].isBefore(LocalDateTime.now());
    }
}
