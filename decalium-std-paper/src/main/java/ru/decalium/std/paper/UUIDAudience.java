package ru.decalium.std.paper;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class UUIDAudience implements ForwardingAudience {

    private final Iterable<UUID> uuids;

    public static UUIDAudience uuidAudience(UUID... uuids) {
        return uuidAudience(Arrays.asList(uuids));
    }

    public static UUIDAudience uuidAudience(Iterable<UUID> uuids) {
        return new UUIDAudience(uuids);
    }

    UUIDAudience(Iterable<UUID> uuids) {
        this.uuids = uuids;
    }

    @Override
    public @NotNull Iterable<? extends Audience> audiences() {
        List<Player> players = new ArrayList<>();
        for(UUID uuid : uuids) {
            Player player = Bukkit.getPlayer(uuid);
            if(player == null) continue;
            players.add(player);
        }
        return players;
    }
}
