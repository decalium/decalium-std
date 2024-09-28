package ru.decalium.std.paper.papi;

import me.clip.placeholderapi.PlaceholderAPI;

import java.util.function.UnaryOperator;
import java.util.regex.Matcher;

public final class PapiPreprocessor implements UnaryOperator<String> {
    @Override
    public String apply(String s) {
        Matcher matcher = PlaceholderAPI.getPlaceholderPattern().matcher(s);
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            String match = matcher.group();
            matcher.appendReplacement(builder, placeholder(match));
        }
        matcher.appendTail(builder);
        return builder.toString();
    }


    private String placeholder(String placeholder) {
        return "<papi:'" + placeholder.substring(1, placeholder.length() - 1) + "'>";
    }
}