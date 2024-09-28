package ru.decalium.std.adventure.resolver;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.tag.Modifying;
import org.jetbrains.annotations.NotNull;

final class SmallCapsTag implements Modifying {

    private static final char[] ALPHABET = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀsᴛᴜᴠᴡxʏᴢ".toCharArray();


    @Override
    public Component apply(@NotNull Component current, int depth) {
        if (depth != 0) return Component.empty();
        if (!(current instanceof TextComponent text)) return current;
        char[] c = text.content().toCharArray();
        for (int i = 0; i < c.length; i++) {
            char ch = c[i];
            if (ch >= 'a' && ch <= 'z') {
                c[i] = ALPHABET[ch - 'a'];
            }
        }
        return text.toBuilder().content(new String(c)).mapChildren(comp -> (net.kyori.adventure.text.BuildableComponent<?, ?>) apply(comp, 0)).build();
    }
}
