package me.gatogamer.spreencore.animations.gangnamstyle;

import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
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
public class Animation2 {

    public void play(Player player, String name, Location location) {
        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);

        armorStand.setHelmet(Utils.buildItemSkull("&7", name, 1));
        armorStand.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        armorStand.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        armorStand.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        armorStand.setBasePlate(false);

        armorStand.setArms(true);
        armorStand.setItemInHand(new ItemStack(Material.LEAD));
        armorStand.setMarker(true);
        armorStand.setGravity(true);

        EulerAngle leftLeg = new EulerAngle(Math.toRadians(0), Math.toRadians(0), Math.toRadians(335));
        EulerAngle rightLeg = new EulerAngle(Math.toRadians(0), Math.toRadians(0), Math.toRadians(25));

        armorStand.setLeftLegPose(leftLeg);
        armorStand.setRightLegPose(rightLeg);

        new BukkitRunnable() {
            int i = 20;

            boolean last = false;

            @Override
            public void run() {
                EulerAngle rightPose = armorStand.getRightArmPose();
                EulerAngle leftPose = armorStand.getLeftArmPose();

                if (i == 20 || i == 19 || i == 18 || i == 17 || i == 12 || i == 11 || i == 10 || i == 9) {
                    leftPose = new EulerAngle(
                            Math.toRadians(313),
                            Math.toRadians(57),
                            Math.toRadians(0f)
                    );
                    rightPose = new EulerAngle(
                            Math.toRadians(316),
                            Math.toRadians(315),
                            Math.toRadians(0f)
                    );
                }

                if (i == 16 || i == 15 || i == 14 || i == 13 || i == 8 || i == 7 || i == 6 || i == 5) {
                    leftPose = new EulerAngle(
                            Math.toRadians(313),
                            Math.toRadians(30),
                            Math.toRadians(10f)
                    );
                    rightPose = new EulerAngle(
                            Math.toRadians(304),
                            Math.toRadians(303),
                            Math.toRadians(360f)
                    );
                }

                if (i == 16 || i == 8 || i == 20 || i == 12) {
                    World world = player.getLocation().getWorld();
                    //world.playEffect(armorStand.getLocation().add(0, 1.2, 0), Particle.FLAME, 0, 0, 0, 0, 0, 0.05f, 10, 50);

                    armorStand.setVelocity(new Vector(0, 0.15, 0));
                }

                if (i == 4 || i == -1) {
                    rightPose = new EulerAngle(
                            Math.toRadians(215),
                            Math.toRadians(33f),
                            Math.toRadians(0f)
                    );
                }
                if (i == 3 || i == -2) {
                    rightPose = new EulerAngle(
                            Math.toRadians(215),
                            Math.toRadians(119f),
                            Math.toRadians(0f)
                    );
                }
                if (i == 2 || i == -3) {
                    rightPose = new EulerAngle(
                            Math.toRadians(215),
                            Math.toRadians(174f),
                            Math.toRadians(0f)
                    );
                }
                if (i == 1 || i == -4) {
                    rightPose = new EulerAngle(
                            Math.toRadians(215),
                            Math.toRadians(327f),
                            Math.toRadians(0f)
                    );
                }
                armorStand.setRightArmPose(rightPose);
                armorStand.setLeftArmPose(leftPose);
                i--;
                if (i <= -4) {
                    i = 20;
                }

                if (armorStand.isDead()) {
                    armorStand.remove();
                    cancel();
                }
            }
        }.runTaskTimer(SpreenCore.getInstance(), 1L, 2L);


        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.remove();
            }
        }.runTaskLater(SpreenCore.getInstance(), 30*20L);
    }
}
