package me.gatogamer.spreencore.listeners;

import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.commands.ProhibitCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class ConnectionListener implements Listener {

    private final List<String> list = SpreenCore.getInstance().getConfig().getStringList("whitelist");

    public ConnectionListener() {
        list.replaceAll(String::toLowerCase);
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent e) {
        if (SpreenCore.getInstance().getConfig().getBoolean("whitelist-mode")) {
            if (!list.contains(e.getName().toLowerCase())) {
                e.setKickMessage("No tienes permiso para entrar");
                e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (ProhibitCommand.PROHIBIT && !e.getPlayer().hasPermission("spreencore.prohibit.join")) {
            e.getPlayer().kickPlayer("No estás en la whitelist");
            e.setJoinMessage(null);
        }

        if (e.getPlayer().isDead()) {
            e.getPlayer().spigot().respawn();
            Bukkit.getScheduler().runTaskLater(SpreenCore.getInstance(), () -> {
                e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
            }, 1L);
        }
        e.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        SpreenCore.getInstance().getUserManager().invalidate(e.getPlayer().getUniqueId());
    }
}