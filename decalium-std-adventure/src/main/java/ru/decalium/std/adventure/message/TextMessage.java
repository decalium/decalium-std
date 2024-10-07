package ru.decalium.std.adventure.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public interface TextMessage extends Format<TextMessage>, ComponentLike {

    String value();

    MiniMessage miniMessage();

    static TextMessage message(String value, MiniMessage miniMessage) {
        return new TextMessageImpl(value, miniMessage);
    }

    default Component asComponent(TagResolver resolver) {
        return this.miniMessage().deserialize(this.value(), resolver);
    }

    static TextMessage message(String value) {
        return message(value, MiniMessage.miniMessage());
    }
}
