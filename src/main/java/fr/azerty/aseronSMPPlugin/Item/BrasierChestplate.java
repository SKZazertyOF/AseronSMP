package fr.azerty.aseronSMPPlugin.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class BrasierChestplate {

    public static ItemStack getPlastron() {
        ItemStack item = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.RED + "Plastron du Brasier");
            meta.setLore(List.of(
                    ChatColor.GRAY + "Un plastron ardent qui",
                    ChatColor.GRAY + "protège contre le feu."
            ));
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
        }

        return item;
    }

    public static void applyEffect(Player player) {
        if (player.getInventory().getChestplate() != null
                && player.getInventory().getChestplate().hasItemMeta()
                && player.getInventory().getChestplate().getItemMeta().hasDisplayName()
                && player.getInventory().getChestplate().getItemMeta().getDisplayName().equals(ChatColor.RED + "Plastron du Brasier")) {

            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 0, true, false));
        }
    }
}
