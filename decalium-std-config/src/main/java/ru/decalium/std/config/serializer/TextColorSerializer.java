package ru.decalium.std.config.serializer;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public final class TextColorSerializer implements TypeSerializer<TextColor> {

    @Override
    public TextColor deserialize(Type type, ConfigurationNode node) throws SerializationException {
        String s = node.require(String.class);
        if(s.startsWith("#")) {
            return TextColor.fromHexString(s);
        }
        return NamedTextColor.NAMES.valueOrThrow(s);
    }

    @Override
    public void serialize(Type type, @Nullable TextColor obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) return;
        if(obj instanceof NamedTextColor color) {
            node.set(color.toString());
            return;
        }
        node.set(obj.asHexString());
    }
}
