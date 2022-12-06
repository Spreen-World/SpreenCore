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
public class ToggleSpleefCommand implements CommandExecutor {
    public static boolean SPLEEF = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("spreencore.admin")) {
            SPLEEF = !SPLEEF;

            if (SPLEEF) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cSpleef &8> &aEl modo Spleef ha sido activado."));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cSpleef &8> &cEl modo Spleef ha sido desactivado."));
            }
        }
        return false;
    }
}