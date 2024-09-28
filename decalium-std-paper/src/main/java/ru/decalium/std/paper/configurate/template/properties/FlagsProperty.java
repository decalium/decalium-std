package ru.decalium.std.paper.configurate.template.properties;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import ru.decalium.std.paper.configurate.template.ItemProperty;

import java.util.List;

public class FlagsProperty implements ItemProperty {
    @Override
    public void read(ItemMeta meta, ConfigurationNode node) throws SerializationException {
        List<ItemFlag> flags = node.node("flags").getList(ItemFlag.class, List.of());
        meta.addItemFlags(flags.toArray(new ItemFlag[0]));
    }

    @Override
    public void write(ItemMeta meta, ConfigurationNode node) throws SerializationException {
        if(meta.getItemFlags().isEmpty()) return;
        node.node("flags").setList(ItemFlag.class, List.copyOf(meta.getItemFlags()));
    }
}
