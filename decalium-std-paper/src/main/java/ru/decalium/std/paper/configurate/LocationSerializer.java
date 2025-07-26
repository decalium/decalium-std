package ru.decalium.std.paper.configurate;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public final class LocationSerializer implements TypeSerializer<Location> {
    @Override
    public Location deserialize(Type type, ConfigurationNode node) throws SerializationException {
        String worldString = node.node("world").getString();
        World world = worldString == null ? null : Bukkit.getWorld(worldString);
        return new Location(
                world,
                node.node("x").getDouble(),
                node.node("y").getDouble(),
                node.node("z").getDouble()
        );
    }

    @Override
    public void serialize(Type type, @Nullable Location obj, ConfigurationNode node) throws SerializationException {
        if(obj == null) {
            node.raw(null);
            return;
        }

        World world = obj.getWorld();
        if(world != null) node.node("world").set(world.getName());
        node.node("x").set(obj.x());
        node.node("y").set(obj.y());
        node.node("z").set(obj.z());
    }
}
