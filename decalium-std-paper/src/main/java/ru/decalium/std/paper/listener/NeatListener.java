package ru.decalium.std.paper.listener;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public final class NeatListener implements Listener {

    private final Plugin plugin;

    private NeatListener(Plugin plugin) {
        this.plugin = plugin;
    }

    public <E extends Event> NeatListener register(Class<E> eventType,
                                                   Consumer<E> consumer,
                                                   EventPriority priority,
                                                   boolean ignoreCancelled) {
        plugin.getServer().getPluginManager().registerEvent(eventType, this, priority, (l, e) -> {
            if(eventType.isInstance(e)) consumer.accept(eventType.cast(e));
        }, plugin, ignoreCancelled);
        return this;
    }

    public <E extends Event> NeatListener register(Class<E> eventType, Consumer<E> consumer, EventPriority priority) {
        return register(eventType, consumer, priority, false);
    }

    public <E extends Event> NeatListener register(Class<E> eventType, Consumer<E> consumer) {
        return register(eventType, consumer, EventPriority.NORMAL);
    }

    public <E extends Event> NeatListener register(Class<E> eventType, Consumer<E> consumer, boolean ignoreCancelled) {
        return register(eventType, consumer, EventPriority.NORMAL, ignoreCancelled);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }






}
