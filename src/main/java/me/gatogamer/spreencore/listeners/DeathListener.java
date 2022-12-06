package me.gatogamer.spreencore.listeners;

import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.commands.ResetEliminationsCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import static me.gatogamer.spreencore.listeners.DamageListener.InstantFirework;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (SpreenCore.getInstance().getConfig().getBoolean("modules.death-kick")) {
            event.setDeathMessage(null);

            Player victim = event.getEntity();

            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            "&5Evento &8> &f¡Se ha eliminado a &d" + event.getEntity().getName() + "&f!"
                    ));

                    ResetEliminationsCommand.ELIMINATIONS++;

                    Location location = victim.getLocation();

                    new ParticleBuilder(ParticleEffect.EXPLOSION_HUGE, location).setAmount(5).setSpeed(1f).display();
                    new ParticleBuilder(ParticleEffect.FLAME, location).setAmount(5).setSpeed(1f).display();
                    //new ParticleBuilder(ParticleEffect., location).setAmount(5).setSpeed(1f).display();

                    //victim.getWorld().spigot().playEffect(location, Effect.EXPLOSION, 0, 0, 0, 0, 0, 1, 2, 150);
                    //victim.getWorld().spigot().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0, 0, 0, 0, 0, 0.25f, 20, 150);
                    //victim.getWorld().spigot().playEffect(location, Effect.FLAME, 0, 0, 0, 0, 0, 0.25f, 20, 150);

                    InstantFirework(victim.getLocation(), FireworkEffect
                            .builder()
                            .withColor(Color.RED, Color.RED, Color.RED, Color.RED).build());

                    event.getEntity().kickPlayer(ChatColor.translateAlternateColorCodes('&', "&c¡Gracias por participar!"));
                    victim.getLocation().getWorld().spigot().strikeLightningEffect(victim.getLocation(), false);

                    event.getEntity().spigot().respawn();
                }
            }.runTaskLater(SpreenCore.getInstance(), 2L);
        }
    }
}