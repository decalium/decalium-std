package ru.decalium.std.adventure.message;

import net.kyori.adventure.text.ComponentLike;
import ru.decalium.std.adventure.message.parser.StatementParser;

import java.util.List;
import java.util.Objects;

public interface Message extends Statement, Format<Message> {

    List<Statement> statements();

    static Message message(List<Statement> statements) {
        Objects.requireNonNull(statements);
        return new MessageImpl(statements);
    }

    static Message message(Statement... statements) {
        return message(List.of(statements));
    }

    static Message parsed(StatementParser parser, List<String> strings) {
        return message(strings.stream().<Statement>map(parser::parse).toList());
    }

    static Message parsed(List<String> strings) {
        return parsed(StatementParser.DEFAULT_PARSER, strings);
    }

    static Message parsed(String... strings) {
        return parsed(List.of(strings));
    }
}
