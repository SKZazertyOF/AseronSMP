package fr.azerty.aseronSMPPlugin;

import fr.azerty.aseronSMPPlugin.Item.*;
import fr.azerty.aseronSMPPlugin.command.CraftCommand;
import fr.azerty.aseronSMPPlugin.listener.TableCraftListener;
import fr.azerty.aseronSMPPlugin.manager.OpManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import fr.azerty.aseronSMPPlugin.command.SpawnCommand;

public final class AseronSMPPlugin extends JavaPlugin {

    private static AseronSMPPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        new NekoHammer(this);



        Receipe.registerReceipes(this);
        getServer().getPluginManager().registerEvents(new DragonSword(this), this);
        getServer().getPluginManager().registerEvents(new BlessedPickaxe(this), this);
        getServer().getPluginManager().registerEvents(new MysticBow(this), this);
        getServer().getPluginManager().registerEvents(new TableCraftListener(), this);
        getServer().getPluginManager().registerEvents(new OpManager(), this);
        getCommand("crafts").setExecutor(new CraftCommand(this));
        getCommand("ae").setExecutor(new AECommand());

        StringDuper.register(this);

        printConsoleHeader();
        sendConsoleMessage(ChatColor.DARK_GREEN + "Version: " + ChatColor.GOLD + getDescription().getVersion());
        sendConsoleMessage(ChatColor.GREEN + "Custom recipes loaded successfully!");
        printConsoleFooter();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
//                    OracleMask.applyEffect(player);
                    BrasierChestplate.applyEffect(player);
                    GardienLeggings.applyEffect(player);
                    VagueBoots.applyEffect(player);
                }
            }
        }.runTaskTimer(this, 0, 40); // 40 ticks = 2 secondes

        SpawnCommand spawnCmd = new SpawnCommand();
        getCommand("spawn").setExecutor(spawnCmd);

        getServer().getPluginManager().registerEvents(new org.bukkit.event.Listener() {
            @EventHandler
            public void onDamage(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
                spawnCmd.registerHitEvent(event);
            }
        }, this);
    }

    @Override
    public void onDisable() {
        sendConsoleSeparator(ChatColor.RED);
        sendConsoleMessage(ChatColor.RED + "                 AseronSMP Plugin disabled        ");
        sendConsoleSeparator(ChatColor.RED);
    }

    public static AseronSMPPlugin getInstance() {
        return instance;
    }


    private void printConsoleHeader() {
        sendConsoleSeparator(ChatColor.DARK_GRAY);
        sendConsoleMessage(ChatColor.DARK_PURPLE + "                                       _____ __  __ _____  ");
        sendConsoleMessage(ChatColor.DARK_PURPLE + "     /\\                               / ____|  \\/  |  __ \\ ");
        sendConsoleMessage(ChatColor.DARK_PURPLE + "    /  \\   ___  ___ _ __ ___  _ __   | (___ | \\  / | |__) |");
        sendConsoleMessage(ChatColor.DARK_PURPLE + "   / /\\ \\ / __|/ _ \\ '__/ _ \\| '_ \\   \\___ \\| |\\/| |  ___/ ");
        sendConsoleMessage(ChatColor.DARK_PURPLE + "  / ____ \\\\__ \\  __/ | | (_) | | | |  ____) | |  | | |     ");
        sendConsoleMessage(ChatColor.DARK_PURPLE + " /_/    \\_\\___/\\___|_|  \\___/|_| |_| |_____/|_|  |_|_|     ");
        sendConsoleSeparator(ChatColor.DARK_GRAY);
    }

    private void printConsoleFooter() {
        sendConsoleSeparator(ChatColor.DARK_GRAY);
    }

    private void sendConsoleSeparator(ChatColor color) {
        Bukkit.getConsoleSender().sendMessage(color + "==================================================");
    }

    private void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
