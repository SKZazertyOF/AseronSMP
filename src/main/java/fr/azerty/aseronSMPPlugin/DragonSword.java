package fr.azerty.aseronSMPPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DragonSword implements Listener {

    private final ConcurrentHashMap<UUID, Boolean> playersHoldingSword = new ConcurrentHashMap<>();

    private final JavaPlugin plugin;

    public DragonSword(JavaPlugin plugin) {
        this.plugin = plugin;

        // Tâche répétée toutes les 2 secondes
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (isDragonSword(item)) {
                        // Applique Strength II pour 2 secondes (40 ticks)
                        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, 1, false, false));
                        playersHoldingSword.put(player.getUniqueId(), true);
                    } else {
                        // Retire l'effet si le joueur ne tient pas l'épée
                        if (playersHoldingSword.getOrDefault(player.getUniqueId(), false)) {
                            player.removePotionEffect(PotionEffectType.STRENGTH);
                            playersHoldingSword.put(player.getUniqueId(), false);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 40L); // 40 ticks = 2 secondes
    }

    public static ItemStack getDragonSword() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = sword.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.YELLOW + "Épée du Dragon");
            sword.setItemMeta(meta);
        }
        return sword;
    }

    private boolean isDragonSword(ItemStack item) {
        return item != null && item.hasItemMeta()
                && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Épée du Dragon");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        playersHoldingSword.put(event.getPlayer().getUniqueId(), false);
    }
}
