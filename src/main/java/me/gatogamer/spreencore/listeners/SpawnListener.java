package me.gatogamer.spreencore.listeners;

import me.gatogamer.spreencore.SpreenCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class SpawnListener implements Listener {
    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent event) {
        if (SpreenCore.getInstance().getConfig().getBoolean("modules.spawn")) {
            event.setSpawnLocation(event.getPlayer().getWorld().getSpawnLocation());
        }
    }
}