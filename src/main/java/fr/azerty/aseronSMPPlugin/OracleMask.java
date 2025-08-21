package fr.azerty.aseronSMPPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class OracleMask {

    public static ItemStack getOracleMask() {
        ItemStack item = new ItemStack(Material.NETHERITE_HELMET, 1);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.AQUA + "Masque de l’Oracle");
            meta.setLore(List.of(
                    ChatColor.GRAY + "Un casque mystique qui",
                    ChatColor.GRAY + "accorde la respiration infinie sous l’eau."
            ));
            meta.setUnbreakable(true); // indestructible
            item.setItemMeta(meta);
        }

        return item;
    }

    // ================== méthode pour appliquer l'effet ==================
    public static void applyEffect(Player player) {
        if (player.getInventory().getHelmet() != null
                && player.getInventory().getHelmet().hasItemMeta()
                && player.getInventory().getHelmet().getItemMeta().hasDisplayName()) {

            String helmetName = player.getInventory().getHelmet().getItemMeta().getDisplayName();
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 60, 0, true, false));
        }
    }


}
