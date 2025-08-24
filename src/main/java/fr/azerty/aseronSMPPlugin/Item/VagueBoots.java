package fr.azerty.aseronSMPPlugin.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class VagueBoots {

    public static ItemStack getBottes() {
        ItemStack item = new ItemStack(Material.NETHERITE_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.BLUE + "Botte de la Vague");
            meta.setLore(List.of(
                    ChatColor.GRAY + "Des bottes rapides qui",
                    ChatColor.GRAY + "augmentent la vitesse sous l’eau."
            ));
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
        }

        return item;
    }

    public static void applyEffect(Player player) {
        if (player.getInventory().getBoots() != null
                && player.getInventory().getBoots().hasItemMeta()
                && player.getInventory().getBoots().getItemMeta().hasDisplayName()
                && player.getInventory().getBoots().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Botte de la Vague")) {

            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 60, 0, true, false));
        }
    }
}
