package io.github.lianjordaan.skyMineCore.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UserDataManager {
    private final JavaPlugin plugin;
    private final File userDataFolder;

    public UserDataManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.userDataFolder = new File(plugin.getDataFolder(), "userData");

        if (!userDataFolder.exists()) {
            userDataFolder.mkdirs();
        }
    }

    public File getUserFile(UUID uuid) {
        return new File(userDataFolder, uuid.toString() + ".yml");
    }

    public FileConfiguration getUserConfig(UUID uuid) {
        File userFile = getUserFile(uuid);
        if (!userFile.exists()) {
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not create user data file for " + uuid);
                e.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(userFile);
    }

    public void saveUserData(UUID uuid, FileConfiguration config) {
        File userFile = getUserFile(uuid);
        try {
            config.save(userFile);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Could not save user data for " + uuid);
            e.printStackTrace();
        }
    }

    public void setUserData(UUID uuid, String path, Object value) {
        FileConfiguration config = getUserConfig(uuid);
        config.set(path, value);
        saveUserData(uuid, config);
    }

    public Object getUserData(UUID uuid, String path) {
        FileConfiguration config = getUserConfig(uuid);
        return config.get(path);
    }

    public boolean userDataExists(UUID uuid) {
        return getUserFile(uuid).exists();
    }

    // Method to create or update user data with new fields
    public void loadOrUpdateUserData(UUID uuid) {
        FileConfiguration config = getUserConfig(uuid);

        // Set defaults if missing
        // first join date
        setIfMissing(config, "first-join", System.currentTimeMillis());

        // last join date
        setIfMissing(config, "last-join", System.currentTimeMillis());

        // logout timestamp
        setIfMissing(config, "last-logout", 0);

        // blocks mined
        setIfMissing(config, "blocks-mined", 0);

        // experience since last prestige
        setIfMissing(config, "experience", 0.0);

        // experience since first join
        setIfMissing(config, "total-experience", 0.0);

        // prestige count
        setIfMissing(config, "prestige", 0);

        // rebirth count
        setIfMissing(config, "rebirth", 0);

        // experience multiplier
        setIfMissing(config, "experience-multiplier", 1.0);

        // if the player has access to private mines
        setIfMissing(config, "private-mine-access", false);

        // selected private mine theme
        setIfMissing(config, "private-mine-theme", 0);

        // private mine size
        setIfMissing(config, "private-mine-upgrade-size", 9);

        // private mine ore distribution upgrade
        setIfMissing(config, "private-mine-upgrade-ore-distribution", 5);

        // private mine reset timer upgrade
        setIfMissing(config, "private-mine-upgrade-reset-timer", 300);

        // selected ore distribution setting for private mine
        setIfMissing(config, "private-mine-ore-distribution", 0);

        // access to /enderchest
        setIfMissing(config, "enderchest-access", false);

        // access to /autocompact
        setIfMissing(config, "autocompact-access", false);

        // if auto compact is enabled
        setIfMissing(config, "autocompact-enabled", false);

        // access to /autofeed
        setIfMissing(config, "autofeed-access", false);

        // if auto feed is enabled
        setIfMissing(config, "autofeed-enabled", false);

        // kill count
        setIfMissing(config, "kills", 0);

        // death count
        setIfMissing(config, "deaths", 0);

        // magic upgrade level
        setIfMissing(config, "magic-upgrade-level", 0);

        // duper upgrade level
        setIfMissing(config, "duper-upgrade-level", 0);

        // Save updated config
        saveUserData(uuid, config);
    }

    private void setIfMissing(FileConfiguration config, String path, Object defaultValue) {
        if (!config.contains(path)) {
            config.set(path, defaultValue);
        }
    }
}
