package me.gatogamer.spreencore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class TargetListener implements Listener {
    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        target(event.getEntity(), event);
    }

    public void target(Entity entity, EntityTargetEvent event) {
        if (entity.hasMetadata("target-to")) {
            entity.getMetadata("target-to").forEach(metadataValue -> {
                Player player = Bukkit.getPlayer(metadataValue.asString());
                if (player != null && player.isOnline()) {
                    event.setTarget(player);
                }
            });
        }
    }
}