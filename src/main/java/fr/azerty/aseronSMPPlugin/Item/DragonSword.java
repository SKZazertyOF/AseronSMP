package fr.azerty.aseronSMPPlugin.Item;

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
import org.bukkit.enchantments.Enchantment;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DragonSword implements Listener {

    private final ConcurrentHashMap<UUID, Boolean> playersHoldingSword = new ConcurrentHashMap<>();

    private final JavaPlugin plugin;

    public DragonSword(JavaPlugin plugin) {
        this.plugin = plugin;

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (isDragonSword(item)) {

                        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, 1, false, false));
                        playersHoldingSword.put(player.getUniqueId(), true);
                    } else {
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
            meta.addEnchant(Enchantment.SHARPNESS, 6, true);
            meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
            meta.addEnchant(Enchantment.LOOTING, 3, true);
            meta.addEnchant(Enchantment.SWEEPING_EDGE, 3, true);
            meta.addEnchant(Enchantment.UNBREAKING, 3, true);
            meta.addEnchant(Enchantment.MENDING, 1, true);

            meta.setUnbreakable(true);

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
