package me.gatogamer.spreencore.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class RandomChairsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && commandSender.hasPermission("spreencore.admin")) {
            Player player = (Player) commandSender;
            int quantity = Integer.parseInt(strings[0]);
            int radius = Integer.parseInt(strings[1]);

            List<Block> blocks = new ArrayList<>();

            Random random = ThreadLocalRandom.current();

            for (int i = 0; i < quantity; i++) {
                while (true) {
                    Block block =player.getLocation().clone().add(random.nextInt(radius), 2, random.nextInt(radius)).getBlock();

                    if (!blocks.contains(block)) {
                        blocks.add(block);
                        break;
                    }
                }
            }

            blocks.forEach(block -> block.getWorld().spawnFallingBlock(block.getLocation(), Material.OAK_STAIRS.createBlockData()));
        }
        return false;
    }
}
