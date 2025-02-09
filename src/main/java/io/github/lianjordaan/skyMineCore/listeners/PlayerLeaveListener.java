package io.github.lianjordaan.skyMineCore.listeners;

import io.github.lianjordaan.skyMineCore.SkyMineCore;
import io.github.lianjordaan.skyMineCore.data.UserDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerLeaveListener implements Listener {
    private final UserDataManager userDataManager;

    public PlayerLeaveListener(SkyMineCore plugin) {
        this.userDataManager = plugin.getUserDataManager();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        userDataManager.loadOrUpdateUserData(uuid);
        userDataManager.setUserData(uuid, "last-logout", System.currentTimeMillis());
    }
}
