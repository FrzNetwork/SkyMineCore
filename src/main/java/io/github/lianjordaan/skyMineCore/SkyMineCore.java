package io.github.lianjordaan.skyMineCore;

import io.github.lianjordaan.skyMineCore.data.UserDataManager;
import io.github.lianjordaan.skyMineCore.listeners.PlayerJoinListener;
import io.github.lianjordaan.skyMineCore.listeners.PlayerLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyMineCore extends JavaPlugin {
    private UserDataManager userDataManager;

    @Override
    public void onEnable() {
        this.userDataManager = new UserDataManager(this);

        // register events
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
    }

    public UserDataManager getUserDataManager() {
        return userDataManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
