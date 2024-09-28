package ru.decalium.std.java.duration;

import java.time.Duration;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class DurationFormatter {

    private final SortedMap<TimeUnit, String> units;

    public DurationFormatter(SortedMap<TimeUnit, String> units) {

        this.units = units;
    }

    public String format(Duration duration) {
        long seconds = duration.toSeconds();
        if (seconds == 0) return "0 " + units.getOrDefault(units.lastKey(), "");
        StringJoiner joiner = new StringJoiner(" ");
        for (Map.Entry<TimeUnit, String> entry : units.entrySet()) {
            long value = entry.getKey().convert(seconds, TimeUnit.SECONDS);
            if (value > 0) joiner.add(value + entry.getValue());
            seconds = seconds - entry.getKey().toSeconds(value); // Duration.ofSeconds(duration.getSeconds() - unit.toSeconds(value));
        }
        return joiner.toString();
    }
}
