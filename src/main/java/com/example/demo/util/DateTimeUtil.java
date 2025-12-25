package com.example.demo.util;

import java.time.Instant;

public final class DateTimeUtil {

    private DateTimeUtil() {
        // prevent instantiation
    }

    public static Instant now() {
        return Instant.now();
    }
}
