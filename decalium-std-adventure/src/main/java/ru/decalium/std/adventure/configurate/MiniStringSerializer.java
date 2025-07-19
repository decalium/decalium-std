package ru.decalium.std.adventure.configurate;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.std.adventure.message.MiniString;

import java.lang.reflect.Type;

public final class MiniStringSerializer implements TypeSerializer<MiniString> {

    private final MiniMessage miniMessage;

    public MiniStringSerializer(MiniMessage miniMessage) {
        this.miniMessage = miniMessage;
    }

    public MiniStringSerializer() {
        this(MiniMessage.miniMessage());
    }

    @Override
    public MiniString deserialize(Type type, ConfigurationNode node) throws SerializationException {
        return MiniString.miniString(node.require(String.class), miniMessage);
    }

    @Override
    public void serialize(Type type, @Nullable MiniString obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) {
            node.raw(null);
            return;
        }

        node.set(obj.value());
    }
}
