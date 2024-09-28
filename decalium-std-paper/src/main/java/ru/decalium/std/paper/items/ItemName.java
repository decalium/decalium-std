package ru.decalium.std.paper.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ItemName implements ComponentLike {

    private final ItemStack item;
    public ItemName(ItemStack item) {
        this.item = item;
    }
    @Override
    public @NotNull Component asComponent() {
        ItemMeta meta = item.getItemMeta();
        @Nullable Component displayName = meta.displayName();
        return displayName == null ? Component.translatable(item) : displayName;
    }

    public Component hover() {
        return asComponent().hoverEvent(this.item);
    }
}
