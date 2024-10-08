package ru.decalium.std.java.utils;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class Optionals {

    private Optionals() {}


    public static OptionalInt ofNullable(@Nullable Integer value) {
        return value == null ? OptionalInt.empty() : OptionalInt.of(value);

    }

    public static OptionalLong ofNullable(@Nullable Long value) {
        return value == null ? OptionalLong.empty() : OptionalLong.of(value);
    }

    public static OptionalDouble ofNullable(@Nullable Double value) {
        return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
    }

    public static <T> Optional<T> ofIterator(Iterator<T> iterator) {
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }

    public static <T> Optional<T> ofIterable(Iterable<T> iterable) {
        return ofIterator(iterable.iterator());
    }
}
