package utils;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class TimestampUtils {
    public static long getHoursBetweenTimestamps(Timestamp ts1, Timestamp ts2) {
        // Convert Timestamp to Instant
        Instant instant1 = ts1.toInstant();
        Instant instant2 = ts2.toInstant();

        // Calculate duration and return hours
        return Duration.between(instant1, instant2).toHours();
    }

}
