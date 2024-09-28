package ru.decalium.std.paper.listener;

import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class Listeners {

    private final Plugin plugin;
    private final List<Listener> registeredListeners;

    public Listeners(Plugin plugin, List<Listener> registeredListeners) {
        this.plugin = plugin;
        this.registeredListeners = registeredListeners;
    }

    public Listeners(Plugin plugin) {
        this(plugin, new ArrayList<>());
    }


    public void register(Listener listener) {
        this.plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        this.registeredListeners.add(listener);
    }

    public void unregisterAll() {
        registeredListeners.removeIf(listener -> {
            HandlerList.unregisterAll(listener);
            return true;
        });
    }


}
