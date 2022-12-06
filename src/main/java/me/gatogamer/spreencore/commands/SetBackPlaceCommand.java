package me.gatogamer.spreencore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class SetBackPlaceCommand implements CommandExecutor {
    public static Location location;

    public static Map<Player, Player> teleported = new ConcurrentHashMap<>();
    public static int maxTeleport = 0;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player && commandSender.hasPermission("spreencore.admin")) {
            Player player = (Player) commandSender;

            if (args.length > 0){
                maxTeleport = Integer.parseInt(args[0]);
            } else {
                maxTeleport = Integer.MAX_VALUE;
            }

            if (location == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSe ha establecido el spawnpoint para cuándo los jugadores se caigan al agua."));
                location = player.getLocation();
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSe ha quitado el spawnpoint para cuándo los jugadores se caigan al agua."));
                teleported.clear();
                location = null;
            }
        }
        return false;
    }
}
