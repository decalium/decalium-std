package ru.decalium.std.paper.tag;

import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public final class MaterialSet {

    public static final MaterialSet EMPTY = materialSet(List.of());

    private final MaterialSetTag backingTag;
    private final List<Entry> entries;

    MaterialSet(MaterialSetTag backingTag, List<Entry> entries) {
        this.backingTag = backingTag;
        this.entries = entries;
    }

    public static MaterialSet materialSet(List<Entry> entries) {
        MaterialSetTag tag = new MaterialSetTag(NamespacedKey.randomKey());
        for(Entry entry : entries) {
            if(entry.include()) tag.add(entry.materials());
            else tag.not(entry.materials());
        }
        tag.lock();
        return new MaterialSet(tag, entries);
    }

    public boolean isTagged(@NotNull Material material) {
        return backingTag.isTagged(material);
    }

    public boolean isTagged(@NotNull BlockData block) {
        return isTagged(block.getMaterial());
    }

    public boolean isTagged(@NotNull BlockState block) {
        return isTagged(block.getType());
    }

    public boolean isTagged(@NotNull Block block) {
        return isTagged(block.getType());
    }

    public boolean isTagged(@NotNull ItemStack item) {
        return isTagged(item.getType());
    }

    public List<Entry> entries() {
        return this.entries;
    }


    public interface Entry {
        Set<Material> materials();
        boolean include();
        String asString();
    }

    public record SingleEntry(Material material, boolean include) implements Entry {
        @Override
        public Set<Material> materials() {
            return Set.of(material);
        }

        @Override
        public String asString() {
            return material.getKey().toString();
        }
    }

    public record TagEntry(MaterialSetTag tag, boolean include) implements Entry {

        @Override
        public Set<Material> materials() {
            return tag.getValues();
        }

        @Override
        public String asString() {
            return "#" + tag.getKey();
        }
    }

}
