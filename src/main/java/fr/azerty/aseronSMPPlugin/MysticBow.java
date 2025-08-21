package fr.azerty.aseronSMPPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
import java.util.ArrayList;
import java.util.List;

public class MysticBow implements Listener {

    private final ConcurrentHashMap<UUID, Boolean> playersHoldingBow = new ConcurrentHashMap<>();
    private final JavaPlugin plugin;

    public MysticBow(JavaPlugin plugin) {
        this.plugin = plugin;

        // Redonne Speed II toutes les 2s tant que l'arc est en main
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (isMysticBow(item)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1, false, false));
                        playersHoldingBow.put(player.getUniqueId(), true);
                    } else {
                        if (playersHoldingBow.getOrDefault(player.getUniqueId(), false)) {
                            // pas indispensable, mais propre
                            player.removePotionEffect(PotionEffectType.SPEED);
                            playersHoldingBow.put(player.getUniqueId(), false);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 40L);
    }

    // ==== ICI : getMysticBow ====
    public static ItemStack getMysticBow() {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.DARK_PURPLE + "Arc Mystique");

            meta.addEnchant(Enchantment.POWER, 5, true);
            meta.addEnchant(Enchantment.FLAME, 1, true);
            meta.addEnchant(Enchantment.PUNCH, 2, true);
            meta.addEnchant(Enchantment.INFINITY, 1, true);
            meta.setUnbreakable(true);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Un arc mystique qui donne Speed II");
            lore.add(ChatColor.GRAY + "quand on le tient");
            meta.setLore(lore);

            bow.setItemMeta(meta);
        }
        return bow;
    }

    private boolean isMysticBow(ItemStack item) {
        return item != null
                && item.hasItemMeta()
                && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Arc Mystique");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        playersHoldingBow.put(event.getPlayer().getUniqueId(), false);
    }
}
