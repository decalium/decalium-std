package ru.decalium.std.adventure.resolver;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.tag.Modifying;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

public class SmallCapsTag implements Modifying {

    private static final char[] ALPHABET = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀsᴛᴜᴠᴡxʏᴢ".toCharArray();

    @Override
    public Component apply(@NotNull Component current, int depth) {

        if (current instanceof TextComponent text) {
            char[] c = text.content().toLowerCase(Locale.ROOT).toCharArray();
            for(int i = 0; i < c.length; i++) {
                char ch = c[i];
                if(ch >= 'a' && ch <= 'z') {
                    c[i] = ALPHABET[ch - 'a'];
                }
            }
            return Component.text(new String(c), text.style());
        }

        return current.children(List.of());
    }
}