package io.github.lianjordaan.skyMineCore;

import io.github.lianjordaan.skyMineCore.data.UserDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class UserEvents {
    private final UserDataManager userDataManager;

    public UserEvents(SkyMineCore plugin) {
        this.userDataManager = plugin.getUserDataManager();
    }

    public void breakBlock(UUID uuid) {
        Double experience = (Double) userDataManager.getUserData(uuid, "experience");
        Double totalExperience = (Double) userDataManager.getUserData(uuid, "total-experience");
        Double experienceMultiplier = (Double) userDataManager.getUserData(uuid, "experience-multiplier");
        if (experience != null) {
            userDataManager.setUserData(uuid, "experience", experience + 1 * experienceMultiplier);
        }
        if (totalExperience != null) {
            userDataManager.setUserData(uuid, "total-experience", totalExperience + 1 * experienceMultiplier);
        }
    }
}
