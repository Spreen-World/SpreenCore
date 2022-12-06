package me.gatogamer.spreencore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class HackStickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && commandSender.hasPermission("spreencore.admin")) {
            Player player = (Player) commandSender;

            ItemStack itemStack = new ItemStack(Material.STICK);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ;
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e¡Palo doxeador!"));
            itemMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "&c¡Pégale a un jugador para doxearlo!")));
            itemStack.setItemMeta(itemMeta);

            player.getInventory().addItem(itemStack);
        }
        return false;
    }
}
