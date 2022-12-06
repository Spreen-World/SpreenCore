package me.gatogamer.spreencore.tasks;

import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.commands.GrefgCommand;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class TickTask extends BukkitRunnable {
    public static int players = 0;

    public static int MAX_TIME_STARTED_BOSSBAR = 0;
    public static BossBar bossBar;

    @Override
    public void run() {
        players = Math.toIntExact(Bukkit.getOnlinePlayers().stream().filter(player -> {
            if (bossBar != null) {
                if (!bossBar.getPlayers().contains(player)) {
                    bossBar.addPlayer(player);
                }
            }
            return !player.hasPermission("spreencore.online-bypass");
        }).count());
        if (GrefgCommand.RUNNING) {
            if (GrefgCommand.TIME > 0) {
                GrefgCommand.TIME--;
                if (bossBar != null) {
                    if (MAX_TIME_STARTED_BOSSBAR != GrefgCommand.START_TIME) {
                        bossBar.setVisible(false);
                        bossBar.removeAll();
                        bossBar = null;
                        System.out.println("Bossbar changed, deleting owo");
                    }
                }
                if (bossBar == null) {
                    System.out.println("Bossbar doesn't exist, creating owo");
                    MAX_TIME_STARTED_BOSSBAR = GrefgCommand.START_TIME;
                    bossBar = SpreenCore.getInstance().getServer().createBossBar(
                            GrefgCommand.REASON, BarColor.WHITE, BarStyle.SOLID
                    );
                    bossBar.setVisible(true);
                    Bukkit.getOnlinePlayers().forEach(bossBar::addPlayer);
                }
                double start = GrefgCommand.START_TIME;
                int actualTime = GrefgCommand.TIME;
                double actual = ((actualTime * 100) / start) / 100;
                System.out.println("progress: " + actual);
                bossBar.setProgress(actual);
            }
        } else {
            if (bossBar != null) {
                bossBar.setVisible(false);
                bossBar.removeAll();
            }
        }
    }
}