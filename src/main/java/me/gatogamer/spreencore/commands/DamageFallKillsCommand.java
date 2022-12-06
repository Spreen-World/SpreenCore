package me.gatogamer.spreencore.commands;

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
public class DamageFallKillsCommand implements CommandExecutor {
    public static boolean ENABLED = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("spreencore.admin")) {
            ENABLED = !ENABLED;

            if (ENABLED) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEl daño de caída ahora matará."));
            } else {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEl daño de caída ya no matará."));
            }
        }
        return false;
    }
}
