package me.gatogamer.spreencore.commands;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.animations.gangnamstyle.Animation1;
import me.gatogamer.spreencore.animations.gangnamstyle.Animation2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Arrays;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class GangnamStyleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!player.hasPermission("spreencore.admin")) {
            return true;
        }

        Location location = player.getLocation();

        Song song = NBSDecoder.parse(new File("gangnam-style.nbs"));
        RadioSongPlayer radioSongPlayer = new RadioSongPlayer(song);
        radioSongPlayer.addPlayer(player);
        radioSongPlayer.setPlaying(true);
        Bukkit.getOnlinePlayers().forEach(radioSongPlayer::addPlayer);

        Arrays.asList(
                location.clone().add(4, 0, 4),
                location.clone().add(-4, 0, 4),
                location.clone().add(4, 0, -4),
                location.clone().add(-4, 0, -4),
                location.clone().add(0, 0, 4),
                location.clone().add(0, 0, 4),
                location.clone().add(4, 0, 0),
                location.clone().add(-4, 0, 0)
        ).forEach(location1 -> {
            new Animation1().play(player, args[0], location1);
        });

        Arrays.asList(
                location.clone().add(8, 0, 8),
                location.clone().add(-8, 0, 8),
                location.clone().add(8, 0, -8),
                location.clone().add(-8, 0, -8),
                location.clone().add(0, 0, 8),
                location.clone().add(0, 0, 8),
                location.clone().add(8, 0, 0),
                location.clone().add(-8, 0, 0)
        ).forEach(location1 -> {
            new Animation2().play(player, args[0],location1);
        });


        new BukkitRunnable() {
            @Override
            public void run() {
                radioSongPlayer.destroy();
            }
        }.runTaskLater(SpreenCore.getInstance(), 30*20L);

        return false;
    }
}
