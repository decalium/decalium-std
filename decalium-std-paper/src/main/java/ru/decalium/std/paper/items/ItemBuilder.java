package ru.decalium.std.paper.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.decalium.action.message.Formatted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public final class ItemBuilder implements Formatted<ItemBuilder> {

    private final ItemStack item;
    private final ItemMeta meta;
    private final TagResolver.Builder builder = TagResolver.builder();
    private String displayName;
    private List<String> lore = new ArrayList<>();

    public static ItemBuilder builder(ItemStack item) {
        return new ItemBuilder(item);
    }

    public static ItemBuilder builder(ItemSource source, int amount) {
        return builder(source.create(amount));
    }

    public static ItemBuilder builder(ItemSource source) {
        return builder(source, 1);
    }

    public static ItemBuilder builder(Material material) {
        return builder(new ItemStack(material));
    }

    public static ItemBuilder head(String base64) {
        return builder(ItemSource.skullByBase64(base64));
    }

    private ItemBuilder(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    @Override
    public ItemBuilder with(TagResolver tagResolver) {
        this.builder.resolver(tagResolver);
        return this;
    }

    @Override
    public ItemBuilder with(String key, Tag tag) {
        this.builder.tag(key, tag);
        return this;
    }

    @Override
    public ItemBuilder with(Collection<? extends TagResolver> resolvers) {
        this.builder.resolvers(resolvers);
        return this;
    }

    public ItemBuilder name(String name) {
        this.displayName = name;
        return this;
    }

    public ItemBuilder lore(Collection<String> lore) {
        this.lore.addAll(lore);
        return this;
    }

    public ItemBuilder lore(String... lore) {
        return lore(Arrays.asList(lore));
    }

    public ItemBuilder amount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder attribute(Attribute attribute, AttributeModifier modifier) {
        this.meta.addAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemBuilder attribute(Attribute attribute, double value) {
        return this.attribute(attribute, new AttributeModifier(attribute.name(), value, AttributeModifier.Operation.ADD_NUMBER));
    }

    public ItemBuilder fillAttributes(EquipmentSlot slot) {
        this.meta.setAttributeModifiers(this.item.getType().getDefaultAttributeModifiers(slot));
        return this;
    }

    public ItemBuilder flags(ItemFlag... flags) {
        this.meta.addItemFlags(flags);
        return this;
    }

    public ItemBuilder allFlags() {
        this.meta.addItemFlags(ItemFlag.values());
        return this;
    }

    public <M extends ItemMeta> ItemBuilder edit(Class<M> metaClass, Consumer<M> consumer) {
        if(metaClass.isInstance(this.meta)) {
            consumer.accept(metaClass.cast(this.meta));
        }
        return this;
    }

    public ItemBuilder glint() {
        this.enchant(Enchantment.LUCK, 1);
        this.flags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public <T, Z> ItemBuilder pdc(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        this.meta.getPersistentDataContainer().set(key, type, value);
        return this;
    }

    public ItemBuilder pdc(NamespacedKey key, int value) {
        return pdc(key, PersistentDataType.INTEGER, value);
    }

    public ItemBuilder pdc(NamespacedKey key, long value) {
        return pdc(key, PersistentDataType.LONG, value);
    }

    public ItemBuilder pdc(NamespacedKey key, byte[] value) {
        return pdc(key, PersistentDataType.BYTE_ARRAY, value);
    }

    public ItemBuilder pdc(NamespacedKey key, String value) {
        return pdc(key, PersistentDataType.STRING, value);
    }

    public ItemStack build() {
        ItemStack stack = this.item.clone();
        TagResolver resolver = this.builder.build();
        Component displayName = this.displayName == null ? null : parse(this.displayName, resolver);
        List<Component> lore = this.lore.stream().map(s -> parse(s, resolver)).toList();
        meta.displayName(displayName);
        meta.lore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    private static Component parse(String text, TagResolver resolver) {
        return MiniMessage.miniMessage().deserialize(text, resolver)
                .decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }
}
