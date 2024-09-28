package ru.decalium.std.java.collecitons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class NeatMap {

    public static <K, V> Map<K, V> createMap(Function<V, K> keyMapper, Iterable<V> values) {
        Map<K, V> map = new HashMap<>();
        for(V value : values) {
            map.put(keyMapper.apply(value), value);
        }
        return map;
    }

    @SafeVarargs
    public static <K, V> Map<K, V> createMap(Function<V, K> keyMapper, V... values) {
        return createMap(keyMapper, Arrays.asList(values));
    }
}
