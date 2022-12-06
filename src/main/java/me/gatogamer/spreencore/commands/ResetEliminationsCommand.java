package me.gatogamer.spreencore.commands;

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
public class ResetEliminationsCommand implements CommandExecutor {
    public static int ELIMINATIONS = 0;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("spreencore.admin")) {
            ELIMINATIONS = 0;
        }
        return false;
    }
}
