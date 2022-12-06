package me.gatogamer.spreencore.tasks;

import lombok.RequiredArgsConstructor;
import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.commands.ToggleTNTCommand;
import me.gatogamer.spreencore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
@RequiredArgsConstructor
public class MovementTask extends BukkitRunnable {
    private final SpreenCore spreenCore;

    @Override
    public void run() {
        if (!ToggleTNTCommand.TNT) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            User user = spreenCore.getUserManager().getUser(player.getUniqueId());
            Location loc = player.getLocation();
            Bukkit.getScheduler().runTaskLater(spreenCore, () -> handle(user, loc), 4L);
        });
    }

    public void handle(User user, Location location) {
        location.add(0, -1, 0);
        if (!isValidBlock(getPlayerStandOnBlockLocation(user, location))) {
            location.add(0, -1, 0);
        }
        Location locationToUse = getPlayerStandOnBlockLocation(user, location);
        if (isValidBlock(locationToUse)) {
            remove(locationToUse);
        }
    }

    public void remove(Location location) {
        location.getBlock().setType(Material.AIR);
        location.add(0, -1, 0);
        location.getBlock().setType(Material.AIR);
    }

    public boolean isValidBlock(Location location) {
        return new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ()).getBlock().getType().equals(Material.TNT);
    }

    private Location getPlayerStandOnBlockLocation(User user, Location locationUnderPlayer) {
        if ((System.currentTimeMillis() - user.getLastMove()) > 5000L) { // Handle if player haven't moved in 5 seconds.
            Location b11 = clone(locationUnderPlayer).add(0.3, 0, -0.3);
            if (b11.getBlock().getType() != Material.AIR) {
                return b11;
            }
            Location b12 = clone(locationUnderPlayer).add(-0.3, 0, -0.3);
            if (b12.getBlock().getType() != Material.AIR) {
                return b12;
            }
            Location b21 = clone(locationUnderPlayer).add(0.3, 0, 0.3);
            if (b21.getBlock().getType() != Material.AIR) {
                return b21;
            }
            Location b22 = clone(locationUnderPlayer).add(-0.3, 0, +0.3);
            if (b22.getBlock().getType() != Material.AIR) {
                return b22;
            }
        }
        return locationUnderPlayer;
    }

    public Location clone(Location location) {
        return new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
    }
}