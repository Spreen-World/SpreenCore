package me.gatogamer.spreencore.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Named;
import me.fixeddev.commandflow.annotated.annotation.Text;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
@Command(names = {"grefg"}, permission = "spreencore.grefg", permissionMessage = "§cNo tienes permiso para hacer esto. :p")
public class GrefgCommand implements CommandClass {

    public static String REASON = "BadEndinggg laburado";
    public static int START_TIME = 0;
    public static int TIME = 0;
    public static boolean RUNNING = false;

    @Command(names = {"tiempo", "timer"})
    public void onTimer(@Sender CommandSender sender, @Named("tiempo") int time) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Temporizador puesto en: &c" + time + " segundos&7, ¿ya está puesta la música épica? :p"));
        START_TIME = time;
        TIME = time;
    }

    @Command(names = {"toggle", "switch"})
    public void onSwitch(@Sender CommandSender sender) {
        RUNNING = !RUNNING;
        if (RUNNING) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b¡Bueno chavales, qué hemos arrancao!"));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cc cancela xdxd"));
        }
    }

    @Command(names = {"reason", "razon"})
    public void onReason(@Sender CommandSender sender, @Text @Named("reason") String reason) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7La razón del temporizador ahora es: &c" + reason));
        REASON = reason;
    }
}