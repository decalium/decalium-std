package ru.decalium.std.java.collecitons;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;

public interface Registry<K, V> extends Iterable<V> {

    Optional<V> get(Object key);

    Collection<V> values();

    Map<K, V> asMap();

    K key(V value);

    static <K, V> Registry<K, V> registry(Map<K, V> backingMap, Function<V, K> keyMapper) {
        return new RegistryImpl<>(backingMap, keyMapper);
    }

    static <K, V> Registry<K, V> registry(Function<V, K> keyMapper) {
        return registry(new HashMap<>(), keyMapper);
    }

    static <K, V> Collector<V, ?, Registry<K, V>> toRegistry(Function<V, K> keyMapper) {
        return Collector.of(
                HashMap::new,
                (map, value) -> map.put(keyMapper.apply(value), value),
                (HashMap<K, V> a, HashMap<K, V> b) -> {
                    a.putAll(b);
                    return a;
                },
                map -> registry(map, keyMapper)
        );
    }
}
