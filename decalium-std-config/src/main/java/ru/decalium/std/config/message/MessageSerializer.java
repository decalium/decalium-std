package ru.decalium.std.config.message;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.action.message.Action;
import ru.decalium.action.message.Message;
import ru.decalium.action.message.impl.ActionParser;
import ru.decalium.action.message.impl.CombinedAction;
import ru.decalium.action.message.impl.ParsedAction;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.StreamSupport;

public final class MessageSerializer implements TypeSerializer<Message> {

    private final ActionParser parser;

    public MessageSerializer(ActionParser parser) {
        this.parser = parser;
    }


    @Override
    public Message deserialize(Type type, ConfigurationNode node) throws SerializationException {
        List<String> strings = node.isList() ? node.getList(String.class, List.of()) : List.of(node.require(String.class));
        return Message.create(parser.parse(strings));
    }

    @Override
    public void serialize(Type type, @Nullable Message value, ConfigurationNode node) throws SerializationException {
        if(value == null) return;
        if (value.action() instanceof ParsedAction action) {
            node.set(action.value());
        } else if (value.action() instanceof CombinedAction action) {
            List<String> strings = StreamSupport.stream(action.actions().spliterator(), false)
                    .filter(ParsedAction.class::isInstance)
                    .map(ParsedAction.class::cast).map(ParsedAction::value).toList();
            node.setList(String.class, strings);
        } else if (value.action() == Action.EMPTY) {
            node.setList(String.class, List.of());
        } else {
            throw new SerializationException("Dont know how to serialize " + value.action());
        }
    }
}
