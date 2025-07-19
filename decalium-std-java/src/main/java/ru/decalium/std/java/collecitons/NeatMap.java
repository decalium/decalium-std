package ru.decalium.std.java.collecitons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public final class NeatMap {

    public static <K, V> Map<K, V> createMap(Map<K, V> map, Function<V, K> keyMapper, Iterable<V> values) {
        for(V value : values) {
            map.put(keyMapper.apply(value), value);
        }
        return map;
    }

    public static <K, V> Map<K, V> createHashMap(Function<V, K> keyMapper, Iterable<V> values) {
        return createMap(new HashMap<>(), keyMapper, values);
    }

    public static <K, V> Map<K, V> createLinkedHashMap(Function<V, K> keyMapper, Iterable<V> values) {
        return createMap(new LinkedHashMap<>(), keyMapper, values);
    }

    @SafeVarargs
    public static <K, V> Map<K, V> createMap(Map<K, V> map, Function<V, K> keyMapper, V... values) {
        return createMap(map, keyMapper, Arrays.asList(values));
    }

    @SafeVarargs
    public static <K, V> Map<K, V> createHashMap(Function<V, K> keyMapper, V... values) {
        return createMap(new HashMap<>(), keyMapper, values);
    }

    @SafeVarargs
    public static <K, V> Map<K, V> createLinkedHashMap(Function<V, K> keyMapper, V... values) {
        return createMap(new LinkedHashMap<>(), keyMapper, values);
    }



}
