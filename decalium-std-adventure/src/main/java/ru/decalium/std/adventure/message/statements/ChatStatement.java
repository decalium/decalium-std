package ru.decalium.std.adventure.message.statements;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import ru.decalium.std.adventure.message.Statement;
import ru.decalium.std.adventure.message.TextMessage;
import ru.decalium.std.adventure.message.parser.StatementFactory;

public record ChatStatement(TextMessage message) implements Statement {

    public ChatStatement(String value, MiniMessage miniMessage) {
        this(TextMessage.message(value, miniMessage));
    }

    @Override
    public void send(Audience audience, TagResolver resolver) {
        audience.sendMessage(message.asComponent(resolver));
    }

    public static StatementFactory factory() {
        return queue -> new ChatStatement(queue.pop().readText());
    }


}