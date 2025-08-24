package fr.azerty.aseronSMPPlugin.Item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NekoHammer implements Listener {

    private final JavaPlugin plugin;

    private static final Set<Material> MINERALS = new HashSet<>(Arrays.asList(

    ));

    public NekoHammer(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        registerRecipe();
    }

    private void registerRecipe() {
        NamespacedKey key = new NamespacedKey(plugin, "neko_hammer");
        ShapedRecipe recipe = new ShapedRecipe(key, getNekoHammer());
        recipe.shape("NNN","NNN"," S ");
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getInventory().getItemInMainHand();

        if (!isNekoHammer(tool)) return;

        // Supprime Mending
        if (tool.containsEnchantment(Enchantment.MENDING)) {
            tool.removeEnchantment(Enchantment.MENDING);
        }

        Block block = event.getBlock();
        event.setCancelled(true); // annule le break normal
        mine3x3Block(player, block, tool);
        damageTool(player, tool);
        if (tool.containsEnchantment(Enchantment.MENDING)) {
            tool.removeEnchantment(Enchantment.MENDING);
        }

    }

    private boolean isNekoHammer(ItemStack item) {
        if (item == null) return false;
        if (item.getType() != Material.NETHERITE_PICKAXE) return false;
        if (!item.hasItemMeta()) return false;

        ItemMeta meta = item.getItemMeta();
        return meta.getDisplayName() != null && meta.getDisplayName().equals(ChatColor.DARK_PURPLE + "Neko Hammer");
    }

    private void mine3x3Block(Player player, Block center, ItemStack tool) {
        Vector dir = player.getEyeLocation().getDirection().normalize();
        double absX = Math.abs(dir.getX());
        double absY = Math.abs(dir.getY());
        double absZ = Math.abs(dir.getZ());

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Block target;
                    if (absY >= absX && absY >= absZ) {
                        target = center.getRelative(x, 0, z);
                    } else if (absX >= absY && absX >= absZ) {
                        target = center.getRelative(0, y, z);
                    } else {
                        target = center.getRelative(x, y, 0);
                    }

                    if (!target.getType().isAir() && !MINERALS.contains(target.getType()) && target.getType() != Material.BEDROCK) {
                        target.breakNaturally(tool);
                        player.getWorld().playSound(target.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
                    }
                }
            }
        }
    }

    private void damageTool(Player player, ItemStack tool) {
        ItemMeta meta = tool.getItemMeta();
        if (meta instanceof Damageable damageable) {
            int newDamage = damageable.getDamage() + 1;
            damageable.setDamage(newDamage);
            tool.setItemMeta(meta);

            if (newDamage >= tool.getType().getMaxDurability()) {
                player.getInventory().setItemInMainHand(null);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
            }
        }
    }

    public static ItemStack getNekoHammer() {
        ItemStack hammer = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = hammer.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_PURPLE + "Neko Hammer");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY + "Un marteau puissant en néthérite",
                ChatColor.RED + "L'enchant Mending ne peut pas être appliqué"
        ));

        hammer.setItemMeta(meta);
        return hammer;
    }

}
