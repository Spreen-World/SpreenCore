package me.gatogamer.spreencore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * ask first, and give me the credits.
 * Arigato! n.n
 */
public class ProhibitCommand implements CommandExecutor {
    public static boolean PROHIBIT = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("spreencore.prohibit")) {
            PROHIBIT = !PROHIBIT;

            if (PROHIBIT) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEl modo whitelist ha sido activado."));
            } else {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEl modo whitelist ha sido desactivado."));
            }
        }
        return false;
    }
}