package me.gatogamer.spreencore.tasks;

import me.gatogamer.spreencore.commands.LoadScreenCommand;
import me.gatogamer.spreencore.commands.SafeTpallCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * ask first, and give me the credits.
 * Arigato! n.n
 */
public class FadeScreenTask extends BukkitRunnable {
    private int ticks = 0;

    @Override
    public void run() {
        ticks++;
        String loadingType = "";
        String inverseLoadingType = "";
        String dots = "";
        if (ticks > 0) {
            loadingType = "Ooo";
            inverseLoadingType = "ooO";
            dots = "";
        }
        if (ticks > 4) {
            loadingType = "oOo";
            inverseLoadingType = "oOo";
            dots = ".";
        }
        if (ticks > 8) {
            loadingType = "ooO";
            inverseLoadingType = "Ooo";
            dots = "..";
        }
        if (ticks > 12) {
            loadingType = "oOo";
            inverseLoadingType = "oOo";
            dots = "...";
        }
        if (ticks > 16) {
            ticks = 0;
        }
        if (!LoadScreenCommand.LOAD_SCREEN) {
            return;
        }
        int staffs = Bukkit.getOnlinePlayers().size() - TickTask.players;
        String rgb = "";
        String text = ChatColor.translateAlternateColorCodes('&', "&cCargando evento" + dots);
        String subText = ChatColor.translateAlternateColorCodes('&', "&bEstamos cargándolo todo" + dots);
        if (SafeTpallCommand.TELEPORTING) {
            subText = ChatColor.translateAlternateColorCodes('&',
                    "&8[" + loadingType + "] &d" + (SafeTpallCommand.TELEPORTED - staffs) + "&5/&d" + (SafeTpallCommand.MAX_TELEPORT - staffs)
                            + " &8[" + inverseLoadingType + "]"
            );
            text = ChatColor.translateAlternateColorCodes('&',
                    "&bTeletransportando jugadores" + dots
            );
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 10));
            player.sendTitle(text, subText, 0, 25, 0);
        }
    }
}
