package ru.decalium.std.paper.tag;

import com.destroystokyo.paper.MaterialSetTag;
import com.destroystokyo.paper.MaterialTags;
import com.destroystokyo.paper.util.SneakyThrow;
import org.bukkit.NamespacedKey;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class MaterialSetTagMap {

    private MaterialSetTagMap() {}

    private static final Map<NamespacedKey, MaterialSetTag> TAGS;


    static {
        Map<NamespacedKey, MaterialSetTag> map = new HashMap<>();
        for(Field field : MaterialTags.class.getFields()) {
            if(!Modifier.isStatic(field.getModifiers()) || field.getType() != MaterialSetTag.class) continue;
            try {
                MaterialSetTag tag = (MaterialSetTag) field.get(null);
                map.put(new NamespacedKey("tags", field.getName().toLowerCase()), tag);
            } catch (IllegalAccessException e) {
                SneakyThrow.sneaky(e);
            }
        }
        TAGS = Map.copyOf(map);
    }

    public static Optional<MaterialSetTag> tag(NamespacedKey key) {
        return Optional.ofNullable(TAGS.get(key));
    }
}