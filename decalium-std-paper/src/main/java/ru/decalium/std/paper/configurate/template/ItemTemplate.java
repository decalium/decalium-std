package ru.decalium.std.paper.configurate.template;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ru.decalium.std.paper.items.ItemBuilder;
import ru.decalium.std.paper.items.ItemSource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class ItemTemplate {

    private final MiniMessage miniMessage;

    private final ItemSource source;
    private final String displayName;
    private final List<String> lore;

    public static ItemTemplate template(MiniMessage miniMessage,
            ItemSource source, String displayName, String... lore) {
        return new ItemTemplate(miniMessage, source, displayName, Arrays.asList(lore));
    }

    public static ItemTemplate template(MiniMessage miniMessage, Material material, String displayName, String... lore) {
        return template(miniMessage, ItemSource.material(material), displayName, lore);
    }

    public static ItemTemplate template(ItemSource source, String displayName, String... lore) {
        return template(MiniMessage.miniMessage(), source, displayName, lore);
    }

    public static ItemTemplate template(Material material, String displayName, String... lore) {
        return template(ItemSource.material(material), displayName, lore);
    }

    public ItemTemplate(MiniMessage miniMessage, ItemSource source, String displayName, List<String> lore) {
        this.miniMessage = miniMessage;
        this.source = source;
        this.displayName = displayName;
        this.lore = lore;
    }

    public ItemBuilder builder() {
        return ItemBuilder.builder(source).miniMessage(miniMessage)
                .name(displayName).lore(lore);
    }

    public ItemStack item() {
        return builder().miniMessage(miniMessage).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTemplate that = (ItemTemplate) o;
        return Objects.equals(source, that.source) && Objects.equals(displayName, that.displayName) && Objects.equals(lore, that.lore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, displayName, lore);
    }

    public ItemSource source() {
        return source;
    }

    public String displayName() {
        return displayName;
    }

    public List<String> lore() {
        return lore;
    }
}
