package ru.decalium.std.java.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class Decimal {


    private static final DecimalFormat DF = new DecimalFormat("#.##");

    static {
        DF.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
    }

    private Decimal() {}

    public static String format(double value) {
        return DF.format(value);
    }

    public static String format(float value) {
        return DF.format(value);
    }
}
