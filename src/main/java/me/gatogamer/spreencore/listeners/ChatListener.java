package me.gatogamer.spreencore.listeners;

import me.gatogamer.spreencore.commands.ToggleChatCommand;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!ToggleChatCommand.CHAT) {
            if (event.getPlayer().hasPermission("spreencore.chat-bypass")) {
                return;
            }
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEl chat se encuentra desactivado."));
            event.setCancelled(true);
        }
    }

}
