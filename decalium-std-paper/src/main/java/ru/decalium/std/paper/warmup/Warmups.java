package ru.decalium.std.paper.warmup;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Warmups implements Listener {

    private record WarmupTask(BukkitTask task, Warmup warmup) {}

    private final Plugin plugin;
    private final Map<UUID, WarmupTask> warmups = new HashMap<>();

    public Warmups(Plugin plugin) {
        this.plugin = plugin;
    }

    public void add(Warmup warmup, Player player) {
        if(warmups.containsKey(player.getUniqueId()))  return;
        var task = new BukkitRunnable() {
            private int seconds = warmup.seconds();
            @Override
            public void run() {
                if(seconds <= 0) {
                    warmup.success();
                    warmups.remove(player.getUniqueId());
                    this.cancel();
                    return;
                }
                warmup.prepare(seconds);
                seconds--;
            }
        }.runTaskTimer(plugin, 0, 20);
        warmups.put(player.getUniqueId(), new WarmupTask(task, warmup));
    }

    public boolean hasWarmup(Player player) {
        return warmups.containsKey(player.getUniqueId());
    }

    public void cancelAll() {
        warmups.clear();
    }

    public void cancel(Player player) {
        var task = warmups.remove(player.getUniqueId());
        if(task == null) return;
        task.task.cancel();
        task.warmup.cancelled();
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        if(event.getFrom().getBlock().equals(event.getTo().getBlock())) return;
        cancel(event.getPlayer());
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        cancel(event.getPlayer());
    }

    @EventHandler
    public void on(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player player) cancel(player);
    }
}
