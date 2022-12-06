package me.gatogamer.spreencore.animations.gangnamstyle;

import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class Animation1 {

    public void play(Player player, String name, Location location) {
        Horse horse = location.getWorld().spawn(location, Horse.class);
        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);

        horse.setAdult();
        horse.setColor(Horse.Color.BLACK);

        armorStand.setHelmet(Utils.buildItemSkull("&7", name, 1));
        armorStand.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        armorStand.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        armorStand.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        armorStand.setBasePlate(false);

        armorStand.setArms(true);
        armorStand.setItemInHand(new ItemStack(Material.LEAD));
        armorStand.setMarker(true);
        armorStand.setGravity(true);

        EulerAngle leftLeg = new EulerAngle(Math.toRadians(276), Math.toRadians(335), Math.toRadians(0));
        EulerAngle rightLeg = new EulerAngle(Math.toRadians(276), Math.toRadians(25), Math.toRadians(0));

        armorStand.setLeftLegPose(leftLeg);
        armorStand.setRightLegPose(rightLeg);


        new BukkitRunnable() {
            int i = 4;

            @Override
            public void run() {
                if (horse.getPassenger() == null) {
                    horse.setPassenger(armorStand);
                }
                EulerAngle rightPose = armorStand.getRightArmPose();
                if (i == 4) {
                    rightPose = new EulerAngle(
                            Math.toRadians(215),
                            Math.toRadians(33f),
                            Math.toRadians(0f)
                    );
                }
                if (i == 3) {
                    rightPose = new EulerAngle(
                            Math.toRadians(215),
                            Math.toRadians(119f),
                            Math.toRadians(0f)
                    );
                }
                if (i == 2) {
                    rightPose = new EulerAngle(
                            Math.toRadians(215),
                            Math.toRadians(174f),
                            Math.toRadians(0f)
                    );
                }
                if (i == 1) {
                    rightPose = new EulerAngle(
                            Math.toRadians(215),
                            Math.toRadians(327f),
                            Math.toRadians(0f)
                    );
                }
                armorStand.setRightArmPose(rightPose);
                i--;
                if (i <= 0) {
                    horse.setVelocity(new Vector(0, 0.1, 0));
                    i = 4;
                }

                if (armorStand.isDead() || horse.isDead()) {
                    armorStand.remove();
                    horse.remove();
                    cancel();
                }
            }
        }.runTaskTimer(SpreenCore.getInstance(), 1L, 2L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.remove();
                horse.remove();
            }
        }.runTaskLater(SpreenCore.getInstance(), 30*20L);
    }
}
