package me.gatogamer.spreencore.commands;

import me.gatogamer.spreencore.SpreenCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.Iterator;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class SafeTpallCommand implements CommandExecutor {
    public static boolean TELEPORTING = false;
    public static int TELEPORTED = 0;
    public static int MAX_TELEPORT = 0;

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && commandSender.hasPermission("spreencore.admin")) {
            Player player = (Player) commandSender;

            if (TELEPORTING) {
                return true;
            }
            TELEPORTING = true;
            Location location = player.getLocation();

            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            MAX_TELEPORT = players.size();
            Iterator<? extends Player> iterator = players.iterator();

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (iterator.hasNext()) {
                        Player next = iterator.next();
                        if (next.getName().equals(next.getName())) {
                            next.teleport(location);
                        }
                        TELEPORTED++;
                    } else {
                        cancel();
                        TELEPORTING = false;
                    }
                }
            }.runTaskTimer(SpreenCore.getInstance(), 2L, 2L);
        }
        return false;
    }
}
