package me.gatogamer.spreencore.listeners;

import me.gatogamer.spreencore.commands.ToggleSpleefCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class BlockListener implements Listener {
    public static boolean BREAK = false;
    public static boolean PLACE = false;
    public static boolean INTERACT = false;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (BREAK) {
            return;
        }
        if (!event.getPlayer().hasPermission("spreencore.break")) {
            if (ToggleSpleefCommand.SPLEEF) {
                if (event.getBlock().getType().toString().contains("SNOW")) {
                    return;
                }
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (PLACE) {
            return;
        }
        if (!event.getPlayer().hasPermission("spreencore.place")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        if (INTERACT) {
            return;
        }
        if (event.getAction().equals(Action.PHYSICAL)) {
            if (!ToggleSpleefCommand.SPLEEF) {
                event.setCancelled(true);
            }
        }
    }
}