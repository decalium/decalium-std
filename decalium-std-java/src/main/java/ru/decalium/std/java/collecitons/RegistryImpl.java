package ru.decalium.std.java.collecitons;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

final class RegistryImpl<K, V> implements MutableRegistry<K, V> {


    private final Map<K, V> map;
    private final Function<V, K> keyMapper;

    public RegistryImpl(Map<K, V> map, Function<V, K> keyMapper) {
        this.map = map;
        this.keyMapper = keyMapper;
    }

    @Override
    public void add(V value) {
        K key = key(value);
        Preconditions.checkArgument(!map.containsKey(key), "Object with this key is already in registry");
        map.put(key(value), value);
    }

    @Override
    public void addAll(Collection<? extends V> collections) {
        for(V value : collections) add(value);
    }

    @Override
    public void addAll(Map<K, V> map) {
        this.map.putAll(map);
    }

    @Override
    public Optional<V> remove(K key) {
        return Optional.ofNullable(this.map.remove(key));
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Optional<V> get(Object key) {
        return Optional.ofNullable(this.map.get(key));
    }

    @Override
    public Collection<V> values() {
        return Collections.unmodifiableCollection(this.map.values());
    }

    @Override
    public Map<K, V> asMap() {
        return Collections.unmodifiableMap(this.map);
    }

    @Override
    public K key(V value) {
        return Objects.requireNonNull(keyMapper.apply(value));
    }

    @Override
    public @NotNull Iterator<V> iterator() {
        return this.map.values().iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistryImpl<?, ?> registry = (RegistryImpl<?, ?>) o;
        return Objects.equals(map, registry.map) && Objects.equals(keyMapper, registry.keyMapper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, keyMapper);
    }

    @Override
    public String toString() {
        return "RegistryImpl{" +
                "map=" + map +
                '}';
    }
}
