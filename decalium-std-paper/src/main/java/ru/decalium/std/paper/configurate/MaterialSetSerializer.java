package ru.decalium.std.paper.configurate;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.std.paper.tag.MaterialSet;
import ru.decalium.std.paper.tag.MaterialSetTagMap;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class MaterialSetSerializer implements TypeSerializer<MaterialSet> {
    @Override
    public MaterialSet deserialize(Type type, ConfigurationNode node) throws SerializationException {
        List<String> list = node.getList(String.class, List.of());
        if(list.isEmpty()) return MaterialSet.EMPTY;
        List<MaterialSet.Entry> entries = new ArrayList<>();
        for(String key : list) {
            boolean exclude = key.startsWith("!");
            key = key.substring(exclude ? 1 : 0);
            if(key.startsWith("#")) { // tag
                key = key.substring(1);
                NamespacedKey parsedKey = NamespacedKey.fromString(key);
                if(parsedKey == null) continue;
                MaterialSetTagMap.tag(parsedKey).ifPresent(tag -> {
                    entries.add(new MaterialSet.TagEntry(tag, !exclude));
                });
                continue;
            }
            Material material = Material.matchMaterial(key);
            if(material != null) entries.add(new MaterialSet.SingleEntry(material, !exclude));
        }
        return MaterialSet.materialSet(entries);
    }

    @Override
    public void serialize(Type type, @Nullable MaterialSet obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) {
            node.setList(String.class, List.of());
            return;
        }
        List<String> strings = new ArrayList<>(obj.entries().size());
        for(MaterialSet.Entry entry : obj.entries()) {
            String s = entry.asString();
            if(!entry.include()) s = "!" + s;
            strings.add(s);
        }
        node.setList(String.class, strings);
    }
}
