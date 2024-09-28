package ru.decalium.std.commands.configurate;

import net.kyori.adventure.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.incendo.cloud.minecraft.extras.MinecraftHelp;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public final class HelpColorsSerializer implements TypeSerializer<MinecraftHelp.HelpColors> {
    @Override
    public MinecraftHelp.HelpColors deserialize(Type type, ConfigurationNode node) throws SerializationException {
        TextColor primary = node.node("primary").require(TextColor.class);
        TextColor highlight = node.node("highlight").require(TextColor.class);
        TextColor alternateHighlight = node.node("alternate-highlight").require(TextColor.class);
        TextColor text = node.node("text").require(TextColor.class);
        TextColor accent = node.node("accent").require(TextColor.class);
        return MinecraftHelp.helpColors(primary, highlight, alternateHighlight, text, accent);
    }

    @Override
    public void serialize(Type type, MinecraftHelp.@Nullable HelpColors obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) {
            node.raw(null);
            return;
        }
        node.node("primary").set(TextColor.class, obj.primary());
        node.node("highlight").set(TextColor.class, obj.highlight());
        node.node("alternate-highlight").set(TextColor.class, obj.alternateHighlight());
        node.node("text").set(TextColor.class, obj.text());
        node.node("accent").set(TextColor.class, obj.accent());
    }
}
