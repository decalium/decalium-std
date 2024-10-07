package ru.decalium.std.adventure.configurate;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.std.adventure.message.Message;
import ru.decalium.std.adventure.message.Statement;
import ru.decalium.std.adventure.message.parser.ParsedStatement;
import ru.decalium.std.adventure.message.parser.StatementParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class MessageSerializer implements TypeSerializer<Message> {

    private final StatementParser parser;

    public MessageSerializer(StatementParser parser) {
        this.parser = parser;
    }

    public MessageSerializer() {
        this(StatementParser.DEFAULT_PARSER);
    }

    @Override
    public Message deserialize(Type type, ConfigurationNode node) throws SerializationException {
        List<String> strings;
        if(node.isList()) {
            strings = node.getList(String.class, List.of());
        } else {
            strings = List.of(node.require(String.class));
        }
        return Message.parsed(this.parser, strings);
    }

    @Override
    public void serialize(Type type, @Nullable Message obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) {
            node.raw(null);
            return;
        }
        List<String> strings = new ArrayList<>(obj.statements().size());
        for(Statement statement : obj.statements()) {
            strings.add(asString(statement));
        }
        if(strings.size() == 1) {
            node.set(strings.get(0));
            return;
        }
        node.setList(String.class, strings);
    }

    private String asString(Statement statement) throws SerializationException {
        if(!(statement instanceof ParsedStatement parsed)) {
            throw new SerializationException("Don't know how to serialize " + statement);
        }
        return parsed.string();
    }
}
