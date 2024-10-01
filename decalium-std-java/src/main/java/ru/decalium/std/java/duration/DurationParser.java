package ru.decalium.std.java.duration;


import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public final class DurationParser {

    public static final DurationParser DEFAULT = DurationParser.create(
            Map.of(
                    'd', TimeUnit.DAYS,
                    'h', TimeUnit.HOURS,
                    'm', TimeUnit.MINUTES,
                    's', TimeUnit.SECONDS
            )
    );

    public static final DurationParser RUSSIAN = DurationParser.create(
            Map.of(
                    'д', TimeUnit.DAYS,
                    'ч', TimeUnit.HOURS,
                    'м', TimeUnit.MINUTES,
                    'с', TimeUnit.SECONDS
            )
    );

    public static DurationParser create(Map<Character, TimeUnit> units) {
        SortedMap<TimeUnit, Character> unitToChar = new TreeMap<>();
        units.forEach((c, unit) -> unitToChar.put(unit, c));
        return new DurationParser(units, unitToChar);
    }
    private DurationParser(Map<Character, TimeUnit> units, SortedMap<TimeUnit, Character> unitToChar) {
        this.units = units;
        this.unitToChar = unitToChar;
    }


    private final Map<Character, TimeUnit> units;
    private final SortedMap<TimeUnit, Character> unitToChar;

    public long parseToSeconds(final String duration) throws ParseException {

        long total = 0;
        final NumberFormat nf = NumberFormat.getInstance();
        final ParsePosition parsePosition = new ParsePosition(0);

        // Consume any starting whitespace of the string
        consumeWhitespace(duration, parsePosition);

        do {
            // Parse the number
            final Number numberOb = nf.parse(duration, parsePosition);
            if (numberOb == null)
                throw new ParseException("Unable to parse number.", parsePosition.getIndex());
            final double number = numberOb.doubleValue();

            // Extract the suffix
            if (duration.length() <= parsePosition.getIndex())
                throw new ParseException("Number '" + number + "' must be followed by a suffix.", parsePosition.getIndex());
            final char suffix = duration.charAt(parsePosition.getIndex());
            TimeUnit unit = units.get(suffix);
            if (unit == null) {
                throw new ParseException("unknown suffix: " + suffix, parsePosition.getIndex());
            }
            total += TimeUnit.SECONDS.convert(Math.round(number), unit);

            // Advance and consume whitespace
            parsePosition.setIndex(parsePosition.getIndex() + 1);
            consumeWhitespace(duration, parsePosition);

        } while (parsePosition.getIndex() < duration.length());

        return total;
    }
    
    public String secondsToString(long seconds) {
        if (seconds == 0) return "0 " + unitToChar.get(unitToChar.lastKey());
        StringJoiner joiner = new StringJoiner(" ");
        for (Map.Entry<TimeUnit, Character> entry : unitToChar.entrySet()) {
            long value = entry.getKey().convert(seconds, TimeUnit.SECONDS);
            if (value > 0) joiner.add(value + entry.getValue().toString());
            seconds = seconds - entry.getKey().toSeconds(value); // Duration.ofSeconds(duration.getSeconds() - unit.toSeconds(value));
        }
        return joiner.toString();
    }

    /**
     * Advance the parsePosition object to consume all whitespace characters from the current position.
     *
     * @param s             the string to inspect
     * @param parsePosition the current parse position identifying the index from which to start consuming
     */
    private static void consumeWhitespace(final String s, final ParsePosition parsePosition) {
        while (parsePosition.getIndex() < s.length() && Character.isWhitespace(s.charAt(parsePosition.getIndex())))
            parsePosition.setIndex(parsePosition.getIndex() + 1);
    }
}
