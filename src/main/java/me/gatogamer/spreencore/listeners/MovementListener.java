package me.gatogamer.spreencore.listeners;

import me.gatogamer.spreencore.commands.SetBackPlaceCommand;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class MovementListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (SetBackPlaceCommand.location != null) {
            Location loc = event.getTo();
            Block block = loc.getBlock();
            if (block.getType().toString().toUpperCase().contains("WATER")) {
                if (SetBackPlaceCommand.teleported.size() >= SetBackPlaceCommand.maxTeleport) {
                    return;
                }
                SetBackPlaceCommand.teleported.put(event.getPlayer(), event.getPlayer());
                event.setTo(SetBackPlaceCommand.location);
            }
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        //if (event.getEntityType().equals(EntityType.FISHING_HOOK)) {
        //}
        event.getEntity().setVelocity(event.getEntity().getVelocity().multiply(1.5));
    }
}