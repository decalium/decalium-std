package ru.decalium.std.adventure.message;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public interface Statement {

    void send(Audience audience, TagResolver resolver);

    default void send(Audience audience) {
        this.send(audience, TagResolver.empty());
    }
}
