package ru.decalium.std.config.serializer;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.std.java.duration.DurationParser;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.Duration;

public final class DurationSerializer implements TypeSerializer<Duration> {

    private final DurationParser parser;

    public DurationSerializer(DurationParser parser) {
        this.parser = parser;
    }
    @Override
    public Duration deserialize(Type type, ConfigurationNode node) throws SerializationException {
        String s = node.require(String.class);
        try {
            return Duration.ofSeconds(parser.parseToSeconds(s));
        } catch (ParseException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void serialize(Type type, @Nullable Duration obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) {
            node.raw(null);
            return;
        }
        node.set(parser.secondsToString(obj.toSeconds()));
    }
}
