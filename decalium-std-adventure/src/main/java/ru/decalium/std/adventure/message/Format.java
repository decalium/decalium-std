package ru.decalium.std.adventure.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.Formatter;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Arrays;
import java.util.Collection;

public interface Format<T extends Format<T>> {

    T with(TagResolver tagResolver);

    T with(String key, Tag tag);

    T with(Collection<? extends TagResolver> resolvers);

    default T with(TagResolver... resolvers) {
        return this.with(Arrays.asList(resolvers));
    }

    default T with(String key, ComponentLike like) {
        return this.with(key, like.asComponent());
    }

    default T with(String key, Component component) {
        return this.with(key, Tag.selfClosingInserting(component));
    }

    default T withMiniMessage(String key, String value) {
        return this.with(key, Tag.preProcessParsed(value));
    }

    default T with(String key, String value) {
        return this.with(key, Component.text(value));
    }

    default T with(String key, CharSequence sequence) {
        return this.with(key, sequence.toString());
    }

    default T with(String key, int value) {
        return this.with(key, Component.text(value));
    }

    default T with(String key, double value) {
        return this.with(key, Component.text(value));
    }

    default T with(String key, float value) {
        return this.with(key, Component.text(value));
    }

    default T with(String key, short value) {
        return this.with(key, Component.text(value));
    }

    default T with(String key, long value) {
        return this.with(key, Component.text(value));
    }

    default T with(String key, boolean value) {
        return this.with(key, Component.text(value));
    }

    default T with(String key, char value) {
        return this.with(key, Component.text(value));
    }

    default T booleanChoice(String key, boolean value) {
        return this.with(Formatter.booleanChoice(key, value));
    }

    default T choice(String key, Number number) {
        return this.with(Formatter.choice(key, number));
    }
}
