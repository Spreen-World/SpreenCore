package me.gatogamer.spreencore.commands;

import me.gatogamer.spreencore.SpreenCore;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length > 0){
            if (args[0].equalsIgnoreCase("stop")){
                stopClock();
                clock(5);
                return true;
            }
            if (args[0].equalsIgnoreCase("forcestop")){
                stopClock();
                return true;
            }
            if (args.length == 2){
                String arg = args[0];
                String var2 = args[1];
                if (arg.equalsIgnoreCase("start")) {
                    int time;
                    try {
                        time = Integer.parseInt(var2);
                    } catch (NumberFormatException e) {
                        ScriptEngineManager manager = new ScriptEngineManager();
                        ScriptEngine sp = manager.getEngineByName("JavaScript");
                        try {
                            time = (int) sp.eval(var2);
                        } catch (ScriptException ex) {
                            sender.sendMessage("§c¡Eso no es un número!");
                            return true;
                        }
                    }
                    clock(time);
                    sender.sendMessage("§aTimer iniciado de: §r"+getFormattedTimer(time));
                    return true;
                }
            }
        }
        return true;
    }

    int clock_secs = 0;
    public BukkitTask clock;

    public BossBar bar;

    public void stopClock(){
        if (clock == null){
            return;
        }
        clock.cancel();
    }


    public BossBar getBar() {
        if (bar == null){
            bar = SpreenCore.getInstance().getServer().createBossBar("", BarColor.WHITE, BarStyle.SOLID);
        }
        return bar;
    }

    public void clock(int secs){
        getBar();
        clock_secs = secs;
        if (clock != null){
            clock.cancel();
        }
        clock = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player online : SpreenCore.getInstance().getServer().getOnlinePlayers()) {
                    if (getBar().getPlayers().contains(online)){
                        continue;
                    }
                    getBar().addPlayer(online);
                }
                getBar().setTitle(secToTime(clock_secs));
                if (clock_secs < 0){
                    cancel();
                    getBar().setTitle("");
                    getBar().removeAll();
                }
                clock_secs--;
            }
        }.runTaskTimer(SpreenCore.getInstance(), 0L, 20L);
    }

    public String secToTime(int sec) {
        int seconds = sec % 60;
        int minutes = sec / 60;
        if (minutes >= 60) {
            int hours = minutes / 60;
            minutes %= 60;
            if( hours >= 24) {
                int days = hours / 24;
                return String.format("%d days %02d:%02d:%02d", days,hours%24, minutes, seconds);
            }
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format("%02d:%02d", minutes, seconds);
    }

    public String getFormattedTimer(int var4){
        int var5 = var4 % 86400 % 3600 % 60;
        int var6 = var4 % 86400 % 3600 / 60;
        int var7 = var4 % 86400 / 3600;
        int var8 = var4 / 86400;
        boolean var9 = true;
        boolean var10 = true;
        boolean var11 = true;
        boolean var12 = true;
        if (var5 == 1) {
            var9 = false;
        }

        if (var6 == 1) {
            var10 = false;
        }

        if (var7 == 1) {
            var11 = false;
        }

        if (var8 == 1) {
            var12 = false;
        }

        String var13 = var9 ? "§f%s §asegs." : "§f%s §aseg.";
        String var14 = String.format(var13, var5);
        String var15 = var10 ? "§f%s §amins, " : "§f%s §amin, ";
        String var16 = String.format(var15, var6);
        String var17 = var11 ? "§f%s §ah, " : "§f%s §ahrs, ";
        String var18 = String.format(var17, var7);
        String var19 = var12 ? "§f%s §ads, " : "§f%s §ad, ";
        String var20 = String.format(var19, var8);
        if (var8 == 0) {
            var20 = "";
        }

        if (var7 == 0) {
            var18 = "";
        }

        if (var6 == 0) {
            var16 = "";
        }

        return var20+var18+var16+var14;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("spreencore.admin")){
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        if (args.length == 1){
            StringUtil.copyPartialMatches(args[0], Arrays.asList("start", "stop", "forcestop"), list);
            return list;
        }
        return list;
    }
}
