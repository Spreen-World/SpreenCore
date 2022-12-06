package me.gatogamer.spreencore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * ask first, and give me the credits.
 * Arigato! n.n
 */
public class LoadScreenCommand implements CommandExecutor {
    public static boolean LOAD_SCREEN = false;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("spreencore.admin")) {
            LOAD_SCREEN = !LOAD_SCREEN;
        }
        return false;
    }
}