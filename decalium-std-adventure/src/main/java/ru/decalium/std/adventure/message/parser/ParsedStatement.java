package ru.decalium.std.adventure.message.parser;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import ru.decalium.std.adventure.message.Statement;

public record ParsedStatement(String string, Statement statement) implements Statement {

    @Override
    public void send(Audience audience, TagResolver resolver) {
        this.statement.send(audience, resolver);
    }
}
