package ru.decalium.std.config.serializer;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.time.LocalTime;

public final class LocalTimeSerializer implements TypeSerializer<LocalTime> {
    @Override
    public LocalTime deserialize(Type type, ConfigurationNode node) throws SerializationException {
        return LocalTime.parse(node.require(String.class));
    }

    @Override
    public void serialize(Type type, @Nullable LocalTime obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) return;
        node.set(obj.toString());
    }
}
