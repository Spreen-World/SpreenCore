package me.gatogamer.spreencore.commands;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import me.gatogamer.spreencore.SpreenCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class RockolaCommand implements CommandExecutor {
    private RadioSongPlayer radioSongPlayer;
    private final Map<String, File> songs;

    public RockolaCommand() {
        songs = new LinkedHashMap<>();

        File songsFolder = new File(SpreenCore.getInstance().getDataFolder(), "songs");

            /*songs.put("Never Gonna Give You Up", new File(songsFolder, "Never Gonna Give You Up.nbs"));
            songs.put("The Final Countdown", new File(songsFolder, "The Final Countdown.nbs"));
            songs.put("Levels", new File(songsFolder, "Levels.nbs"));
            songs.put("Animals", new File(songsFolder, "Animals.nbs"));
            songs.put("Billie Jean", new File(songsFolder, "Billie Jean.nbs"));
            songs.put("Gravity Falls", new File(songsFolder, "Gravity Falls.nbs"));
            songs.put("Call Me Maybe", new File(songsFolder, "Call Me Maybe.nbs"));
            songs.put("Megalovania", new File(songsFolder, "Megalovania.nbs"));
            songs.put("Nyan Cat", new File(songsFolder, "Nyan Cat.nbs"));
            songs.put("Payphone", new File(songsFolder, "Payphone.nbs"));*/
        for (File file : songsFolder.listFiles()) {
            String name = file.getName();
            if (name.endsWith(".nbs")){
                songs.put(name.replace(".nbs", ""), file);
            }
        }

        List<Song> songList = new ArrayList<>();
        songs.forEach((s, file) -> songList.add(NBSDecoder.parse(file)));
        Playlist playlist = new Playlist(songList.toArray(new Song[]{}));

        createRadio(playlist);
        updateSound();
    }

    public void destroyRadio() {
        if (radioSongPlayer != null) {
            radioSongPlayer.getPlayerUUIDs().forEach(uuid -> radioSongPlayer.removePlayer(uuid));
            radioSongPlayer.destroy();
        }
    }

    public void createRadio(Playlist playlist) {
        radioSongPlayer = new RadioSongPlayer(playlist);
        radioSongPlayer.setRepeatMode(RepeatMode.ALL);
        radioSongPlayer.setStereo(true);
        radioSongPlayer.setVolume((byte) 32);
        radioSongPlayer.setPlaying(false);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if (commandSender instanceof Player && commandSender.hasPermission("spreencore.admin")) {
            Player player = (Player) commandSender;
            ChestGui chestGui = new ChestGui(6, ChatColor.translateAlternateColorCodes('&', "&8Spreenland - Música"));
            OutlinePane pane = new OutlinePane(0, 0, 9, 3);

            songs.forEach((songName, file) -> {
                GuiItem guiItem = new GuiItem(create("&a" + songName, Material.JUKEBOX), event -> {
                    if (radioSongPlayer != null) {
                        radioSongPlayer.setPlaying(false);
                    }
                    destroyRadio();
                    Playlist playlist = new Playlist(NBSDecoder.parse(file));
                    createRadio(playlist);
                    radioSongPlayer.setPlaying(true);
                    updateSound();
                    event.getWhoClicked().closeInventory();
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5Rockola &8> &7¡Reproduciendo &b" + songName + "&7!"));
                });
                pane.addItem(guiItem);
            });

            GuiItem guiItem = new GuiItem(create("&c¡Pausar!", Material.RED_TERRACOTTA), event -> {
                Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', "&5Rockola &8> &c¡Música pausada!"), "spreencore.admin");
                if (radioSongPlayer != null) {
                    radioSongPlayer.setPlaying(false);
                }
                event.getWhoClicked().closeInventory();
            });
            GuiItem guiItem2 = new GuiItem(create("&a¡Reanudar!", Material.GREEN_TERRACOTTA), event -> {
                Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', "&5Rockola &8> &c¡Música reanudada!"), "spreencore.admin");
                if (radioSongPlayer != null) {
                    radioSongPlayer.setPlaying(true);
                }
                event.getWhoClicked().closeInventory();
            });

            chestGui.addPane(pane);

            OutlinePane staticPane = new OutlinePane(4, 5, 2, 1);
            staticPane.addItem(guiItem);
            staticPane.addItem(guiItem2);

            chestGui.addPane(staticPane);

            chestGui.show(player);
        }
        return false;
    }

    public ItemStack create(String name, Material material) {
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void updateSound() {
        Bukkit.getOnlinePlayers().forEach(radioSongPlayer::addPlayer);
        radioSongPlayer.getPlayerUUIDs().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null || !player.isOnline()) {
                radioSongPlayer.removePlayer(uuid);
            }
        });
    }
}