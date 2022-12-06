package me.gatogamer.spreencore.commands;

import me.gatogamer.spreencore.listeners.BlockListener;
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
public class ToggleInteractCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("spreencore.admin")) {
            BlockListener.INTERACT = !BlockListener.INTERACT;

            if (BlockListener.INTERACT) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ainteract ha sido activado."));
            } else {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cinteract ha sido desactivado."));
            }
        }
        return false;
    }
}