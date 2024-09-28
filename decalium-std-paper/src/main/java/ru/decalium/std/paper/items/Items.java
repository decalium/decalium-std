package ru.decalium.std.paper.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public final class Items {

    private Items() {}


    public static ItemStack nullToAir(@Nullable ItemStack item) {
        return item == null ? new ItemStack(Material.AIR) : item;
    }

    public static boolean isValid(@Nullable ItemStack item) {
        return item != null && !item.getType().isAir();
    }

    public static ItemStack withType(ItemStack stack, Material material) {
        if(stack.getType() == material) return stack;
        ItemStack itemStack = new ItemStack(material, stack.getAmount());
        if (stack.hasItemMeta()) {
            itemStack.setItemMeta(stack.getItemMeta());
        }
        return itemStack;
    }
}