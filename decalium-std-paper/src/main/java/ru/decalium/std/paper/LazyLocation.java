package ru.decalium.std.paper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public record LazyLocation(String worldName, double x, double y, double z,
                           float yaw, float pitch) {

    public LazyLocation(String worldName, double x, double y, double z) {
        this(worldName, x, y, z, 0f, 0f);
    }

    public LazyLocation(World world, double x, double y, double z, float yaw, float pitch) {
        this(world.getName(), x, y, z, yaw, pitch);

    }

    public LazyLocation(World world, double x, double y, double z) {
        this(world.getName(), x, y, z);
    }

    public LazyLocation(Location location) {
        this(location.getWorld(),
                location.getX(), location.getY(), location.getZ(),
                location.getYaw(), location.getPitch());
    }

    public Location location() {
        World world = Bukkit.getWorld(worldName);
        if(world == null) throw new IllegalStateException("World " + worldName + " not loaded");
        return new Location(world, x, y, z, yaw, pitch);
    }
}
