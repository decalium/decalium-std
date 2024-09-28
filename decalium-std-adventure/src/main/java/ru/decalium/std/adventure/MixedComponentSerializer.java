package ru.decalium.std.adventure;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public final class MixedComponentSerializer implements ComponentSerializer<Component, Component, String> {

    private final MiniMessage miniMessage;
    private final LegacyComponentSerializer legacySerializer;

    public MixedComponentSerializer(MiniMessage miniMessage, LegacyComponentSerializer legacySerializer) {

        this.miniMessage = miniMessage;
        this.legacySerializer = legacySerializer;
    }

    @Override
    public @NotNull Component deserialize(@NotNull String input) {
        return deserialize(input, TagResolver.empty());
    }

    public @NotNull Component deserialize(@NotNull String input, TagResolver resolver) {
        input = miniMessage.serialize(legacySerializer.deserialize(input)).replace("\\<", "<");
        return miniMessage.deserialize(input, resolver);
    }

    @Override
    public @NotNull String serialize(@NotNull Component component) {
        return miniMessage.serialize(component);
    }
}