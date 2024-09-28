package ru.decalium.std.paper.configurate.template.properties;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import ru.decalium.std.paper.configurate.NamespacedKeySerializer;
import ru.decalium.std.paper.configurate.template.ItemProperty;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentsProperty implements ItemProperty {
    @Override
    public void read(ItemMeta meta, ConfigurationNode node) throws SerializationException {
        node = node.node("enchantments");
        if(node.empty()) return;
        List<String> list = node.getList(String.class, List.of());
        for(String s : list) {
            String[] t = s.split(" ");
            if(t.length != 2) throw new SerializationException("Invalid enchantment");
            NamespacedKey key = NamespacedKey.fromString(t[0]);
            if(key == null) throw new SerializationException("Invalid enchantment");
            int level;
            try {
                 level = Integer.parseInt(t[1]);
            } catch(NumberFormatException ex) {
                throw new SerializationException(ex);
            }
            Enchantment enchantment = Registry.ENCHANTMENT.get(key);
            if(enchantment == null) throw new SerializationException("Invalid enchantment");
            meta.addEnchant(enchantment, level, true);
        }
    }

    @Override
    public void write(ItemMeta meta, ConfigurationNode node) throws SerializationException {
        node = node.node("enchantments");
        List<String> enchants = new ArrayList<>();
        meta.getEnchants().forEach((enchant, level) -> {
            enchants.add(enchant.key().asString() + " " + level);
        });
        node.setList(String.class, enchants);
    }
}
