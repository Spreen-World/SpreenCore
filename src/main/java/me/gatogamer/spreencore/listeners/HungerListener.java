package me.gatogamer.spreencore.listeners;

import me.gatogamer.spreencore.SpreenCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class HungerListener implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (SpreenCore.getInstance().getConfig().getBoolean("modules.disable-hunger")) {
            //event.setCancelled(true);
            event.setFoodLevel(20);
        }
    }
}
