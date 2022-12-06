package me.gatogamer.spreencore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * ask first, and give me the credits.
 * Arigato! n.n
 */
public class TeamSelectionCommand implements CommandExecutor {
    public static Location white;
    public static Location rose;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player && commandSender.hasPermission("spreencore.admin")) {
            Player player = (Player) commandSender;

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("rose")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEquipo rosado seteado."));
                    rose = player.getLocation();
                }
                if (args[0].equalsIgnoreCase("white")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEquipo blanco seteado."));
                    white = player.getLocation();
                }
                if (args[0].equalsIgnoreCase("clear")) {
                    Bukkit.getOnlinePlayers().forEach(player1 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nte player " + player1.getName() + " clear"));
                    white = player.getLocation();
                }
                if (args[0].equalsIgnoreCase("setteams")) {
                    AtomicBoolean atomicBoolean = new AtomicBoolean();
                    Bukkit.getOnlinePlayers().stream().filter(player1 -> !player1.hasPermission("spreencore.online-bypass")).forEach(player1 -> {
                        if (atomicBoolean.getAndSet(!atomicBoolean.get())) {
                            player1.teleport(white);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nte player " + player1.getName() + " prefix &f&l[BLANCO] &f");
                        } else {
                            player1.teleport(rose);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nte player " + player1.getName() + " prefix &d&l[ROSA] &f");
                        }
                    });
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso: /teamselection <rose/white/clear/setteams>"));
            }
        }
        return false;
    }
}