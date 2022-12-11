package me.gatogamer.spreencore;

import lombok.Getter;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.gatogamer.spreencore.commands.*;
import me.gatogamer.spreencore.listeners.BlockListener;
import me.gatogamer.spreencore.listeners.ChatListener;
import me.gatogamer.spreencore.listeners.ConnectionListener;
import me.gatogamer.spreencore.listeners.DamageListener;
import me.gatogamer.spreencore.listeners.DeathListener;
import me.gatogamer.spreencore.listeners.HungerListener;
import me.gatogamer.spreencore.listeners.MovementListener;
import me.gatogamer.spreencore.listeners.SpawnListener;
import me.gatogamer.spreencore.listeners.TargetListener;
import me.gatogamer.spreencore.placeholders.PlaceholderService;
import me.gatogamer.spreencore.tasks.FadeScreenTask;
import me.gatogamer.spreencore.tasks.MovementTask;
import me.gatogamer.spreencore.tasks.TickTask;
import me.gatogamer.spreencore.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
@Getter
public class SpreenCore extends JavaPlugin {

    @Getter
    private static SpreenCore instance;

    private UserManager userManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        userManager = new UserManager();

        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new HungerListener(), this);
        Bukkit.getPluginManager().registerEvents(new MovementListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new TargetListener(), this);

        getCommand("kickstick").setExecutor(new KickStickCommand());
        getCommand("hackstick").setExecutor(new HackStickCommand());
        getCommand("togglepvp").setExecutor(new TogglePvPCommand());
        getCommand("togglechat").setExecutor(new ToggleChatCommand());
        getCommand("togglespleef").setExecutor(new ToggleSpleefCommand());
        getCommand("toggleinteract").setExecutor(new ToggleInteractCommand());
        getCommand("rockola").setExecutor(new RockolaCommand());
        getCommand("safetpall").setExecutor(new SafeTpallCommand());
        getCommand("damagefallkills").setExecutor(new DamageFallKillsCommand());
        getCommand("setbackplace").setExecutor(new SetBackPlaceCommand());
        getCommand("gangnamstyle").setExecutor(new GangnamStyleCommand());
        getCommand("prohibit").setExecutor(new ProhibitCommand());
        getCommand("toggletnt").setExecutor(new ToggleTNTCommand());
        getCommand("giveinventory").setExecutor(new GiveInventoryCommand());
        getCommand("randomchairs").setExecutor(new RandomChairsCommand());
        getCommand("spreencore-reload").setExecutor(new ReloadCommand());
        getCommand("teamselection").setExecutor(new TeamSelectionCommand());
        getCommand("reseteliminations").setExecutor(new ResetEliminationsCommand());
        getCommand("loadscreen").setExecutor(new LoadScreenCommand());
        getCommand("settpzone").setExecutor(new SetTpZoneCommand());
        getCommand("tptozonewitharmor").setExecutor(new TpToZoneWithArmorCommand());
        getCommand("cancelarmorstand").setExecutor(new CancelArmorStandCommand());
        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("timer").setTabCompleter(new TimerCommand());

        bootstrapCommands();

        new PlaceholderService().register();
        //new DiscotecaTask();
    }

    private CommandManager commandManager;

    public void bootstrapCommands() {
        commandManager = new BukkitCommandManager(this.getName());

        PartInjector partInjector = PartInjector.create();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder treeBuilder = AnnotatedCommandTreeBuilder.create(partInjector);

        commandManager.registerCommands(treeBuilder.fromClass(new GrefgCommand()));

        new TickTask().runTaskTimer(this, 20L, 20L);
        new MovementTask(this).runTaskTimer(this, 10L, 3L);
        new FadeScreenTask().runTaskTimer(this, 5L, 5L);
    }

    @Override
    public void onDisable() {
        commandManager.unregisterAll();
        super.onDisable();

        Bukkit.getOnlinePlayers().forEach(player -> player.removeMetadata("palitoxdddd", this));
    }
}