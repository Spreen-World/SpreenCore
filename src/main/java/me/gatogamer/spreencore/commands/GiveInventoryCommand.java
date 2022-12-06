package me.gatogamer.spreencore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class GiveInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && commandSender.hasPermission("spreencore.admin")) {
            Player player = (Player) commandSender;
            ItemStack[] armor = player.getInventory().getArmorContents();
            ItemStack[] contents = player.getInventory().getContents();

            Bukkit.getOnlinePlayers().forEach(player1 -> {
                if (!player.equals(player1)) {
                    player1.getInventory().clear();
                    player1.getInventory().setArmorContents(armor);
                    player1.getInventory().setContents(contents);
                    player1.updateInventory();
                }
            });
        }
        return false;
    }
}
