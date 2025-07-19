package ru.decalium.std.adventure.message.statements;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import ru.decalium.std.adventure.message.Statement;
import ru.decalium.std.adventure.message.MiniString;
import ru.decalium.std.adventure.message.parser.StatementFactory;

public record ActionBarStatement(MiniString message) implements Statement {
    @Override
    public void send(Audience audience, TagResolver resolver) {
        audience.sendActionBar(this.message.asComponent(resolver));
    }

    public static StatementFactory factory() {
        return queue -> new ActionBarStatement(queue.pop().readMiniString());
    }
}
