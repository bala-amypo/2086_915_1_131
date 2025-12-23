package com.example.demo.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    /**
     * Returns the current timestamp in UTC.
     */
    public static Instant now() {
        return Instant.now();
    }

    /**
     * Returns the current timestamp in a specific timezone.
     */
    public static ZonedDateTime now(ZoneId zone) {
        return ZonedDateTime.now(zone);
    }
}
