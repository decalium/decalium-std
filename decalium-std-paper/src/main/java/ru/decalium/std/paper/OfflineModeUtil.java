package ru.decalium.std.paper;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class OfflineModeUtil {

    private OfflineModeUtil() {}


    public static UUID uuidFromName(String name) {
        String s = "OfflinePlayer:"+name;
        return UUID.nameUUIDFromBytes(s.getBytes(StandardCharsets.UTF_8));
    }
}
