package ru.decalium.std.java.collecitons;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface MutableRegistry<K, V> extends Registry<K, V> {

    void add(V value);

    void addAll(Collection<? extends V> collections);

    void addAll(Map<K, V> map);

    Optional<V> remove(K key);

    void clear();
}
