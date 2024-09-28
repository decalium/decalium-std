package ru.decalium.std.java.duration;

import java.text.ParseException;
import java.time.Duration;

public interface DurationFormat {

    String format(Duration duration);

    Duration parse(String string) throws ParseException;
}
