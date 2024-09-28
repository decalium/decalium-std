package ru.decalium.std.paper;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.Optional;

public final class PluginServices {

    private final Plugin plugin;
    private final ServicesManager servicesManager;

    public PluginServices(Plugin plugin) {
        this.plugin = plugin;
        this.servicesManager = plugin.getServer().getServicesManager();
    }

    public <C> void register(Class<C> clazz, C service, ServicePriority priority) {
        this.servicesManager.register(clazz, service, plugin, priority);
    }

    public <C> void register(Class<C> clazz, C service) {
        register(clazz, service, ServicePriority.Normal);
    }

    public <C> Optional<C> service(Class<C> clazz) {
        return Optional.ofNullable(servicesManager.load(clazz));
    }

    public <C> void unregister(C service) {
        this.servicesManager.unregister(service);
    }

}
