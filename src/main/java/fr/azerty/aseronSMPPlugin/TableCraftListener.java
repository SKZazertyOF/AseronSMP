package fr.azerty.aseronSMPPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class TableCraftListener implements Listener {

    @EventHandler
    public void onCraftItem(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        // Vérifie si c'est une table de craft
        if (event.getInventory().getType() != InventoryType.WORKBENCH) return;

        // Vérifie si c'est le slot résultat
        if (event.getRawSlot() != 0) return;

        ItemStack result = event.getCurrentItem();
        if (result == null || !result.hasItemMeta() || !result.getItemMeta().hasDisplayName()) return;

        String name = result.getItemMeta().getDisplayName();

        if (name.equals(ChatColor.YELLOW + "Épée du Dragon")) {
            Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " a crafté l'Épée du Dragon !");
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1f, 1f);
        } else if (name.equals(ChatColor.GOLD + "Pioche Bénie")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " a crafté la Pioche Bénie !");
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 1f);
        } else if (name.equals(ChatColor.DARK_PURPLE + "Arc Mystique")) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + player.getName() + " a crafté l'Arc Mystique !");
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1f, 1f);
        }
    }
}
