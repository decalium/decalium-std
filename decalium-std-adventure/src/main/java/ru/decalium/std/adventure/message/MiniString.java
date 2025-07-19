package ru.decalium.std.adventure.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public interface MiniString extends Format<MiniString>, ComponentLike {

    String value();

    MiniMessage miniMessage();

    static MiniString miniString(String value, MiniMessage miniMessage) {
        return new MiniStringImpl(value, miniMessage);
    }

    default Component asComponent(TagResolver resolver) {
        return this.miniMessage().deserialize(this.value(), resolver);
    }

    static MiniString miniString(String value) {
        return miniString(value, MiniMessage.miniMessage());
    }
}
