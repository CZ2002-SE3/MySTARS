package mystars.data.shared;

import java.time.LocalDateTime;

public class AccessDateTime {
    private LocalDateTime[] accessDateTime;

    public AccessDateTime(LocalDateTime[] accessDateTime) {
        this.accessDateTime = accessDateTime;
    }

    public LocalDateTime[] getAccessDateTime() {
        return accessDateTime;
    }

    public void setAccessDateTime(LocalDateTime[] accessDateTime) {
        this.accessDateTime = accessDateTime;
    }

    public boolean isAccessPeriod() {
        return !getAccessDateTime()[0].isAfter(LocalDateTime.now())
                && !getAccessDateTime()[1].isBefore(LocalDateTime.now());
    }
}
