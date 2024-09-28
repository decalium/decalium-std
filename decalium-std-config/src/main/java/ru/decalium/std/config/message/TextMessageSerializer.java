package ru.decalium.std.config.message;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.action.message.impl.TextMessage;

import java.lang.reflect.Type;

public final class TextMessageSerializer implements TypeSerializer<TextMessage> {

    private final MiniMessage miniMessage;

    public TextMessageSerializer(MiniMessage miniMessage) {
        this.miniMessage = miniMessage;
    }
    @Override
    public TextMessage deserialize(Type type, ConfigurationNode node) throws SerializationException {
        return TextMessage.message(node.require(String.class), miniMessage);
    }

    @Override
    public void serialize(Type type, @Nullable TextMessage obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) return;
        node.set(obj.value());
    }
}
