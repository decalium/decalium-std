package ru.decalium.std.adventure.resolver;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.ParsingException;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static java.util.Map.*;

public final class DecaliumColors implements TagResolver.WithoutArguments {

    public static final TextColor BLUE = TextColor.color(0x63FFE8);
    public static final TextColor DARK_BLUE = TextColor.color(0x42C4FB);

    public static final TextColor AQUA = TextColor.color(0x7CD8D8);
    public static final TextColor CECER = TextColor.color(0xDBFDFF);

    public static final TextColor ORANGE = TextColor.color(0xFDA624);
    public static final TextColor YELLOW = TextColor.color(0xFFD84A);
    public static final TextColor LIGHT_YELLOW = TextColor.color(0xFCF3CF);

    public static final TextColor GREEN = TextColor.color(0x42C4FB);
    public static final TextColor SMARAGDINE = TextColor.color(0xDBFF4B);
    public static final TextColor LIGHT_GREEN = TextColor.color(0xEAFCD7);

    public static final TextColor RED = TextColor.color(0xFB2727);
    public static final TextColor PINK = TextColor.color(0xFD439c);
    public static final TextColor LIGHT_PINK = TextColor.color(0xFCDCEB);

    private static final Map<String, TextColor> MAP = ofEntries(
            entry("blue", BLUE),
            entry("dark_blue", DARK_BLUE),
            entry("aqua", AQUA),
            entry("cecer", CECER),
            entry("orange", ORANGE),
            entry("yellow", YELLOW),
            entry("light_yellow", LIGHT_YELLOW),
            entry("green", GREEN),
            entry("smaragdine", SMARAGDINE),
            entry("light_green", LIGHT_GREEN),
            entry("red", RED),
            entry("pink", PINK),
            entry("light_pink", LIGHT_PINK),
            entry("negative", RED),
            entry("neutral", CECER),
            entry("positive", GREEN)
    );

    private static final DecaliumColors COLORS = new DecaliumColors();

    public static DecaliumColors colors() {
        return COLORS;
    }


    @Override
    public @Nullable Tag resolve(@NotNull String name) {
        TextColor color = MAP.get(name);
        if(color == null) return null;
        return Tag.styling(color);
    }
}
