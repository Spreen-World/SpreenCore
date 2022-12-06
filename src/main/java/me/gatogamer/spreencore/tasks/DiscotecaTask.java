package me.gatogamer.spreencore.tasks;

import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import me.gatogamer.spreencore.SpreenCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class DiscotecaTask implements Listener {

    private final Location location = new Location(Bukkit.getWorlds().get(0), 2585, 72, 2576);
    private final List<String> playerList = new ArrayList<>();
    private final RadioSongPlayer radioSongPlayer;

    public DiscotecaTask() {
        Song song = NBSDecoder.parse(new File("discoteca.nbs"));
        radioSongPlayer = new RadioSongPlayer(song);
        radioSongPlayer.setRepeatMode(RepeatMode.ALL);
        radioSongPlayer.setPlaying(true);
        radioSongPlayer.setPlaying(true);
        radioSongPlayer.setStereo(true);
        radioSongPlayer.setVolume((byte) 32);

        Bukkit.getScheduler().runTaskTimerAsynchronously(SpreenCore.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                Location playerLocation = player.getLocation();
                boolean contains = playerList.contains(player.getName());
                if (playerLocation.distance(location) > 30) {
                    if (contains) {
                        playerList.remove(player.getName());
                        radioSongPlayer.removePlayer(player);
                    }
                } else {
                    if (!contains) {
                        playerList.add(player.getName());
                        radioSongPlayer.addPlayer(player);
                    }
                }
            });
        }, 20L, 20L);
    }
}
