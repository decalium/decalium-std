package ru.decalium.std.paper.configurate.template;

import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.ObjectInputFilter;

public interface ItemProperty {

    void read(ItemMeta meta, ConfigurationNode node) throws SerializationException;

    void write(ItemMeta meta, ConfigurationNode node) throws SerializationException;
}
