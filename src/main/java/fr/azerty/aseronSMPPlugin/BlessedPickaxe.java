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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BlessedPickaxe implements Listener {

    private final ConcurrentHashMap<UUID, Boolean> playersHoldingPickaxe = new ConcurrentHashMap<>();
    private final JavaPlugin plugin;

    public BlessedPickaxe(JavaPlugin plugin) {
        this.plugin = plugin;

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (isBlessedPickaxe(item)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, 1, false, false));
                        playersHoldingPickaxe.put(player.getUniqueId(), true);
                    } else {
                        if (playersHoldingPickaxe.getOrDefault(player.getUniqueId(), false)) {
                            player.removePotionEffect(PotionEffectType.HASTE);
                            playersHoldingPickaxe.put(player.getUniqueId(), false);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 40L);
    }

    public static ItemStack getBlessedPickaxe() {
        ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = pickaxe.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "Pioche Bénie");
            meta.addEnchant(Enchantment.EFFICIENCY, 5, true);
            meta.addEnchant(Enchantment.FORTUNE, 4, true);

            meta.setUnbreakable(true);

            pickaxe.setItemMeta(meta);
        }
        return pickaxe;
    }

    private boolean isBlessedPickaxe(ItemStack item) {
        return item != null && item.hasItemMeta()
                && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Pioche Bénie");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        playersHoldingPickaxe.put(event.getPlayer().getUniqueId(), false);
    }
}
