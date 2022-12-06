package me.gatogamer.spreencore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class ToggleTNTCommand implements CommandExecutor {
    public static boolean TNT = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("spreencore.admin")) {
            TNT = !TNT;

            if (TNT) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cTNTRun &8> &aEl modo TNTRun ha sido activado."));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cTNTRun &8> &cEl modo TNTRun ha sido desactivado."));
            }
        }
        return false;
    }
}