package ru.decalium.std.commands.configurate;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.incendo.cloud.caption.Caption;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.std.adventure.message.Message;
import ru.decalium.std.commands.localization.ParserMessages;
import ru.decalium.std.commands.localization.ParserMessagesImpl;

import java.lang.reflect.Type;
import java.util.Map;

public final class ParserMessagesSerializer implements TypeSerializer<ParserMessages> {

    @Override
    public ParserMessages deserialize(Type type, ConfigurationNode node) throws SerializationException {
        ParserMessages.Builder builder = ParserMessages.builder();
        for(var entry : node.childrenMap().entrySet()) {
            String key = entry.getKey().toString();
            ConfigurationNode child = entry.getValue();
            builder.caption(Caption.of(key), child.require(Message.class));
        }
        return builder.build();
    }

    @Override
    public void serialize(Type type, @Nullable ParserMessages obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) {
            node.raw(null);
            return;
        }
        if(!(obj instanceof ParserMessagesImpl impl)) throw new SerializationException("Too bad");
        for(var entry : impl.messageMap().entrySet()) {
            node.node(entry.getKey().key()).set(Message.class, entry.getValue());
        }
    }
}
