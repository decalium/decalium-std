package ru.decalium.std.commands.localization;

import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.incendo.cloud.caption.CaptionVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CaptionVariableResolver implements TagResolver.WithoutArguments {

    private final CaptionVariable[] variables;

    public CaptionVariableResolver(CaptionVariable... variables) {
        this.variables = variables;
    }
    @Override
    public @Nullable Tag resolve(@NotNull String name) {
        for(CaptionVariable variable : variables) {
            if(!variable.key().equals(name)) continue;

            return Tag.preProcessParsed(variable.value());
        }
        return null;
    }
}
