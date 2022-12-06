package me.gatogamer.spreencore.utils;

import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.commands.ResetEliminationsCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class Utils {
    public static ItemStack buildItemSkull(String name, String owner, int amount) {
        ItemStack Item = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta ItemM = (SkullMeta) Item.getItemMeta();
        ItemM.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        ItemM.setOwner(owner);
        Item.setItemMeta(ItemM);
        return Item;
    }

    public static List<Location> createCylinder2(Location base, double radius, int quantity) {
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            double angle, x, z;
            angle = 2 * Math.PI * i / quantity;
            x = Math.cos(angle) * radius;
            z = Math.sin(angle) * radius;
            locations.add(base.clone().add(x, 0, z));
        }
        return locations;
    }

    public static void eliminate(Player player) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                "&5Evento &8> &f¡Se ha eliminado a &d" + player.getName() + "&f!"
        ));
        ResetEliminationsCommand.ELIMINATIONS++;
        player.getLocation().getWorld().spigot().strikeLightningEffect(player.getLocation(), false);
        player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&c¡Gracias por participar!"));
        player.removeMetadata("palitoxdddd", SpreenCore.getInstance());
    }

    public static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}