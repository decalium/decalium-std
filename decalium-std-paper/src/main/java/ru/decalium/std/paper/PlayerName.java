package ru.decalium.std.paper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class PlayerName implements ComponentLike {

    private final UUID uuid;
    private final Server server;

    public PlayerName(UUID uuid, Server server) {
        this.uuid = uuid;
        this.server = server;
    }

    public PlayerName(UUID uuid) {
        this(uuid, Bukkit.getServer());
    }

    @Override
    public @NotNull Component asComponent() {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        Player onlinePlayer = player.getPlayer();
        if(onlinePlayer != null) return onlinePlayer.displayName();
        String name = player.getName();
        if(name != null) return Component.text(name);
        return Component.text("Неизвестный игрок", NamedTextColor.GRAY);
    }
}
