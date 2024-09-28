package ru.decalium.std.paper.configurate;

import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Material;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ru.decalium.std.paper.items.ItemSource;

import java.lang.reflect.Type;

public final class ItemSourceSerializer implements TypeSerializer<ItemSource> {

    @Override
    public ItemSource deserialize(Type type, ConfigurationNode node) throws SerializationException {
        String s = node.require(String.class);
        Material material = Material.matchMaterial(s);
        if(material != null) return ItemSource.material(material);
        if(s.startsWith("skull:")) {
            return ItemSource.skullByBase64(s.substring("skull:".length()));
        }
        throw new SerializationException("Don't know how serialize " + s);
    }

    @Override
    public void serialize(Type type, @Nullable ItemSource obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) {
            node.raw(null);
            return;
        }
        if(obj instanceof ItemSource.MaterialSource materialSource) {
            node.set(materialSource.material().key().asString());
        } else if(obj instanceof ItemSource.SkullSource skull) {
            String base64 = skull.profile().getProperties().stream().filter(property -> "textures".equals(property.getName()))
                    .map(ProfileProperty::getValue).findAny().orElseThrow();
            node.set("skull:" + base64);
        }
    }
}
