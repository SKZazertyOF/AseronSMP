package fr.azerty.aseronSMPPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class GardienLeggings {

    public static ItemStack getJambiere() {
        ItemStack item = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.GREEN + "Jambière du Gardien");
            meta.setLore(List.of(
                    ChatColor.GRAY + "Des jambières puissantes qui",
                    ChatColor.GRAY + "accordent de l'absorption."
            ));
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
        }

        return item;
    }

    public static void applyEffect(Player player) {
        if (player.getInventory().getLeggings() != null
                && player.getInventory().getLeggings().hasItemMeta()
                && player.getInventory().getLeggings().getItemMeta().hasDisplayName()
                && player.getInventory().getLeggings().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Jambière du Gardien")) {

            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 60, 0, true, false));
        }
    }
}
