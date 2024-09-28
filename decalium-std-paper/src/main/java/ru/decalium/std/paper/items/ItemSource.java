package ru.decalium.std.paper.items;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public interface ItemSource {

    static MaterialSource material(Material material) {
        return new MaterialSource(material);
    }

    static SkullSource skull(PlayerProfile profile) {
        return new SkullSource(profile);
    }

    static SkullSource skullByBase64(String base64) {
        return skull(Heads.base64(base64));
    }

    static SkullSource skullById(String id) {
        return skull(Heads.fromId(id));
    }

    static ItemStackSource itemStack(ItemStack itemStack) {
        return new ItemStackSource(itemStack);
    }

    ItemStack create(int amount);


    record MaterialSource(Material material) implements ItemSource {

        @Override
        public ItemStack create(int amount) {
            return new ItemStack(material, amount);
        }
    }

    record SkullSource(PlayerProfile profile) implements ItemSource {

        @Override
        public ItemStack create(int amount) {
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            skull.editMeta(SkullMeta.class, meta -> meta.setPlayerProfile(profile));
            return skull;
        }
    }

    record ItemStackSource(ItemStack base) implements ItemSource {

        @Override
        public ItemStack create(int amount) {
            ItemStack stack = base.clone();
            stack.setAmount(amount);
            return stack;
        }
    }

}
