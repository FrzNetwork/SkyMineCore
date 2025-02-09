package io.github.lianjordaan.skyMineCore.listeners;

import io.github.lianjordaan.skyMineCore.SkyMineCore;
import io.github.lianjordaan.skyMineCore.data.UserDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {
    private final UserDataManager userDataManager;

    public PlayerJoinListener(SkyMineCore plugin) {
        this.userDataManager = plugin.getUserDataManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        userDataManager.loadOrUpdateUserData(uuid);
        userDataManager.setUserData(uuid, "last-join", System.currentTimeMillis());
    }
}
