package ru.decalium.std.paper.raytrace;

import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

public final class RayTraceBuilder {

    private Location start;
    private Vector direction;
    private double maxDistance = Bukkit.getServer().getSimulationDistance() * 16;
    private FluidCollisionMode fluidCollisionMode = FluidCollisionMode.NEVER;
    private boolean ignorePassableBlocks = false;
    private double raySize = 1f;
    private Predicate<Entity> filter = null;

    public RayTraceBuilder(Location start) {
        this.start = start;
    }

    public RayTraceBuilder start(Location start) {
        this.start = Objects.requireNonNull(start);
        return this;
    }

    public RayTraceBuilder direction(Vector direction) {
        this.direction = Objects.requireNonNull(direction);
        return this;
    }

    public RayTraceBuilder maxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
        return this;
    }

    public RayTraceBuilder fluidCollisionMode(FluidCollisionMode fluidCollisionMode) {
        this.fluidCollisionMode = Objects.requireNonNull(fluidCollisionMode);
        return this;
    }

    public RayTraceBuilder ignorePassableBlocks(boolean ignorePassableBlocks) {
        this.ignorePassableBlocks = ignorePassableBlocks;
        return this;
    }

    public RayTraceBuilder raySize(double raySize) {
        this.raySize = raySize;
        return this;
    }

    public RayTraceBuilder filter(@Nullable Predicate<Entity> filter) {
        this.filter = filter;
        return this;
    }

    private void checkNonNull() {
        Objects.requireNonNull(this.start);
        Objects.requireNonNull(this.direction);
    }

    public RayTraceResult rayTrace() {
        checkNonNull();
        return this.start.getWorld().rayTrace(this.start,
                this.direction,
                this.maxDistance,
                this.fluidCollisionMode,
                this.ignorePassableBlocks,
                this.raySize,
                this.filter
        );
    }

    public RayTraceResult rayTraceEntities() {
        checkNonNull();
        return this.start.getWorld().rayTraceEntities(
                this.start,
                this.direction,
                this.maxDistance,
                this.raySize,
                this.filter
        );
    }

    public RayTraceResult rayTraceBlocks() {
        checkNonNull();
        return this.start.getWorld().rayTraceBlocks(
                this.start,
                this.direction,
                this.maxDistance,
                this.fluidCollisionMode,
                this.ignorePassableBlocks
        );
    }


}
