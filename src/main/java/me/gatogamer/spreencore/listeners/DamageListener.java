package me.gatogamer.spreencore.listeners;

import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.commands.DamageFallKillsCommand;
import me.gatogamer.spreencore.commands.TogglePvPCommand;
import me.gatogamer.spreencore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class DamageListener implements Listener {
    public static boolean PROTECT_ARMORSTANDS = true;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            if (DamageFallKillsCommand.ENABLED) {
                event.setDamage(1000);
                return;
            }
            if (SpreenCore.getInstance().getConfig().getBoolean("modules.disable-fall-damage") && !DamageFallKillsCommand.ENABLED) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
            if (PROTECT_ARMORSTANDS) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity().getType().equals(EntityType.HORSE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
            if (PROTECT_ARMORSTANDS) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
            if (PROTECT_ARMORSTANDS) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Guardian) {
            if (event.getDamager().hasMetadata("target-to")) {
                event.setDamage(0);
                return;
            }
        }
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player victim = (Player) event.getEntity();
            Player killer = (Player) event.getDamager();

            if (killer.hasPermission("spreencore.admin")) {
                if (killer.getItemInHand() != null && killer.getItemInHand().getItemMeta() != null
                        && killer.getItemInHand().getItemMeta().getDisplayName() != null
                ) {
                    if (killer.getItemInHand().getItemMeta().getDisplayName().contains("Palo kickeador")) {
                        handleKickStick(victim, event);
                    }
                    if (killer.getItemInHand().getItemMeta().getDisplayName().contains("Palo doxeador")) {
                        handleHackStick(victim, event);
                    }
                }
            }
            if (!TogglePvPCommand.PVP) {
                event.setCancelled(true);
            }
        }
    }

    public void handleKickStick(Player victim, EntityDamageByEntityEvent event) {
        event.setDamage(0);

        if (victim.hasMetadata("palitoxdddd")) {
            return;
        }
        victim.setMetadata("palitoxdddd", new FixedMetadataValue(SpreenCore.getPlugin(SpreenCore.class), "sí"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Location l1 = victim.getLocation();
                Location l2 = victim.getEyeLocation();

                org.bukkit.util.Vector dirBetweenLocations = l2.toVector().subtract(l1.toVector());
                dirBetweenLocations.setY(1.5);
                victim.setVelocity(dirBetweenLocations);
            }
        }.runTaskLater(SpreenCore.getPlugin(SpreenCore.class), 2L);

        new BukkitRunnable() {
            @Override
            public void run() {
                victim.removeMetadata("palitoxdddd", SpreenCore.getInstance());
                if (!victim.isOnline()) {
                    cancel();
                    return;
                }
                Location location = victim.getLocation();
                new ParticleBuilder(ParticleEffect.EXPLOSION_NORMAL, location).setAmount(1).setSpeed(1f).display();
                new ParticleBuilder(ParticleEffect.FLAME, location).setAmount(1).setSpeed(1f).display();
                //new ParticleBuilder(ParticleEffect., location).setAmount(1).setSpeed(1f).display();
                //victim.getWorld().spigot().playEffect(location, Effect.EXPLOSION, 0, 0, 0, 0, 0, 1, 2, 150);
                //victim.getWorld().spigot().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0, 0, 0, 0, 0, 0.25f, 20, 150);
                //victim.getWorld().spigot().playEffect(location, Effect.FLAME, 0, 0, 0, 0, 0, 0.25f, 20, 150);
            }
        }.runTaskTimer(SpreenCore.getPlugin(SpreenCore.class), 2L, 3);

        new BukkitRunnable() {
            @Override
            public void run() {
                InstantFirework(victim.getLocation(), FireworkEffect
                        .builder()
                        .withColor(Color.RED, Color.RED, Color.RED, Color.RED).build());
                Utils.eliminate(victim);
            }
        }.runTaskLater(SpreenCore.getPlugin(SpreenCore.class), 30L);
    }

    public void handleHackStick(Player victim, EntityDamageByEntityEvent event) {
        List<Entity> entities = new ArrayList<>();
        List<ArmorStand> stands = new ArrayList<>();

        AtomicBoolean negative = new AtomicBoolean();

        if (victim.hasMetadata("palitoxdddd")) {
            return;
        }
        victim.setMetadata("palitoxdddd", new FixedMetadataValue(SpreenCore.getPlugin(SpreenCore.class), "sí"));

        Utils.createCylinder2(victim.getLocation(), 5, 10).forEach(location -> {
            location.add(0, 2, 0);
            ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
            Guardian guardian = location.getWorld().spawn(location.clone().add(0, -1, 0), Guardian.class);

            entities.add(armorStand);
            stands.add(armorStand);
            entities.add(guardian);

            armorStand.setInvisible(true);
            armorStand.setMarker(true);
            armorStand.setGravity(false);
            armorStand.setBasePlate(false);
            armorStand.getEquipment().setHelmet(new ItemStack(Material.COMMAND_BLOCK));
            armorStand.setCollidable(false);
            armorStand.addPassenger(guardian);

            guardian.setLaser(true);
            guardian.setAI(true);
            guardian.setTarget(victim);
            guardian.setInvisible(true);
            guardian.attack(victim);
            guardian.setCollidable(false);
            guardian.setMetadata("target-to", new FixedMetadataValue(SpreenCore.getInstance(), victim.getName()));
        });

        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                ticks++;
                if (ticks >= 15) {
                    cancel();

                    Location l1 = victim.getLocation();
                    Location l2 = victim.getEyeLocation();

                    org.bukkit.util.Vector dirBetweenLocations = l2.toVector().subtract(l1.toVector());
                    dirBetweenLocations.setY(1.5);
                    victim.setVelocity(dirBetweenLocations);

                    Bukkit.getScheduler().runTaskLater(SpreenCore.getInstance(), () -> {
                        Utils.eliminate(victim);

                        entities.forEach(entity -> {
                            Location entityLoc = entity.getLocation();
                            entityLoc.setY(-500);
                            entity.teleport(entityLoc, true);
                        });
                    }, 30L);
                    return;
                }

                Random random = new Random();

                for (int i = 0; i < 5; i++) {
                    negative.set(!negative.get());
                    ArmorStand armorStand = victim.getEyeLocation().getWorld().spawn(victim.getEyeLocation().clone()
                            .add(
                                    (random.nextInt(1, 100) / 100.0) * (negative.get() ? -4 : 4),
                                    (random.nextInt(1, 100) / 100.0) * (negative.get() ? -Math.random() : Math.random()),
                                    (random.nextInt(1, 100) / 100.0) * (negative.get() ? -4 : 4)
                            ), ArmorStand.class);
                    armorStand.setInvisible(true);
                    armorStand.setMarker(true);
                    armorStand.setGravity(false);
                    armorStand.setCustomNameVisible(true);
                    stands.add(armorStand);
                    entities.add(armorStand);
                }

                stands.forEach(armorStand -> armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&4" + Utils.getAlphaNumericString(14))));
            }
        }.runTaskTimer(SpreenCore.getInstance(), 5L, 5L);
    }


    public static void InstantFirework(Location loc, FireworkEffect effect) {
        Firework f = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        fm.addEffect(effect);
        f.setFireworkMeta(fm);
        try {
            Object eF = f.getClass().getMethod("getHandle").invoke(f);
            Field fl = eF.getClass().getDeclaredField("expectedLifespan");
            fl.setAccessible(true);
            fl.set(eF, 1);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException |
                 NoSuchMethodError var6) {
        }
    }
}