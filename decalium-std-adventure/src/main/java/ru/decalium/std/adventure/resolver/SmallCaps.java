package ru.decalium.std.adventure.resolver;

import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SmallCaps implements TagResolver.WithoutArguments {

    private static final SmallCapsTag TAG = new SmallCapsTag();

    @Override
    public @Nullable Tag resolve(@NotNull String name) {
        if(name.equalsIgnoreCase("small")) {
            return TAG;
        }
        return null;
    }
}
