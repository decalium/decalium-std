package ru.decalium.std.paper.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public final class Conversation implements Listener {

	private final Plugin plugin;

	public Conversation(Plugin plugin) {

		this.plugin = plugin;
	}

	private final Map<UUID, CompletableFuture<Optional<String>>> conversations = new ConcurrentHashMap<>();


	@EventHandler(priority = EventPriority.LOW)
	@ApiStatus.Internal
	public void on(@SuppressWarnings("deprecation") AsyncPlayerChatEvent event) {
		var future = conversations.remove(event.getPlayer().getUniqueId());
		if(future == null) return;
		event.setCancelled(true);
		plugin.getServer().getScheduler().runTask(plugin, () -> {
			future.complete(Optional.of(event.getMessage()));
		});
	}

	@EventHandler(ignoreCancelled = true)
	public void on(PlayerDeathEvent event) {
		cancel(event.getEntity().getUniqueId());
	}

	@EventHandler
	public void on(PlayerQuitEvent event) {
		cancel(event.getPlayer().getUniqueId());
	}

	public void forgetAll() {
		conversations.forEach((key, value) -> value.cancel(true));
		conversations.clear();
	}


	public CompletableFuture<Optional<String>> ask(Player player, Duration timeout) {
		var uuid = player.getUniqueId();
		CompletableFuture<Optional<String>> future = new CompletableFuture<>();
		conversations.put(uuid, future);
		this.plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
			cancel(uuid);
		}, timeout.toMillis() / 50);
		return future;
	}

	private void cancel(UUID uuid) {
		var future = conversations.remove(uuid);
		if (future != null) future.complete(Optional.empty());
	}
}
