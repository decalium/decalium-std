package ru.decalium.std.paper.configurate.template;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.std.paper.items.ItemSource;

import java.lang.reflect.Type;
import java.util.List;

public final class ItemTemplateSerializer implements TypeSerializer<ItemTemplate> {


    @Override
    public ItemTemplate deserialize(Type type, ConfigurationNode node) throws SerializationException {
        ItemSource source = node.node("material").require(ItemSource.class);
        String displayName = node.node("display-name").require(String.class);
        List<String> lore = node.node("lore").getList(String.class, List.of());
        return new ItemTemplate(source, displayName, lore);
    }

    @Override
    public void serialize(Type type, @Nullable ItemTemplate obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) return;
        node.node("material").set(ItemSource.class, obj.source());
        node.node("display-name").set(obj.displayName());
        node.node("lore").setList(String.class, obj.lore());
    }
}
