package fr.azerty.aseronSMPPlugin.command;

import fr.azerty.aseronSMPPlugin.Item.DragonSword;
import fr.azerty.aseronSMPPlugin.Item.BlessedPickaxe;
import fr.azerty.aseronSMPPlugin.Item.MysticBow;
//import fr.azerty.aseronSMPPlugin.OracleMask;
import fr.azerty.aseronSMPPlugin.Item.BrasierChestplate;
import fr.azerty.aseronSMPPlugin.Item.GardienLeggings;
import fr.azerty.aseronSMPPlugin.Item.VagueBoots;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CraftCommand implements CommandExecutor, Listener, TabCompleter {

    private final JavaPlugin plugin;
    private final Map<String, Inventory> openCrafts = new HashMap<>();
    private final List<String> crafts = Arrays.asList(
            "DragonSword", "BlessedPickaxe", "MysticBow",
            "OracleMask", "BrasierChestplate", "GardienLeggings", "VagueBoots"
    );

    public CraftCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("crafts").setExecutor(this);
        plugin.getCommand("crafts").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /crafts <nom>");
            return true;
        }

        String craftName = args[0];

        Inventory craftInv = switch (craftName) {
            case "DragonSword" -> createDragonSwordCraft();
            case "BlessedPickaxe" -> createBlessedPickaxeCraft();
            case "MysticBow" -> createMysticBowCraft();
            case "OracleMask" -> createOracleMaskCraft();
            case "BrasierChestplate" -> createBrasierChestplateCraft();
            case "GardienLeggings" -> createGardienLeggingsCraft();
            case "VagueBoots" -> createVagueBootsCraft();
            default -> null;
        };

        if (craftInv == null) {
            player.sendMessage(ChatColor.RED + "Craft unknown!");
            return true;
        }

        player.openInventory(craftInv);
        openCrafts.put(player.getUniqueId().toString(), craftInv);
        return true;
    }

    // =========================
    // TabCompleter
    // =========================
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            for (String craft : crafts) {
                if (craft.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(craft);
                }
            }
        }
        return completions;
    }

    // =========================
    // Création des crafts
    // =========================
    private Inventory createDragonSwordCraft() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Craft: Dragon Sword");
        int[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
        ItemStack[] ingredients = {
                new ItemStack(Material.DIAMOND),
                new ItemStack(Material.DRAGON_EGG),
                new ItemStack(Material.DIAMOND),
                new ItemStack(Material.DIAMOND),
                new ItemStack(Material.NETHERITE_SWORD),
                new ItemStack(Material.DIAMOND),
                new ItemStack(Material.NETHER_STAR),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHER_STAR)
        };
        for (int i = 0; i < slots.length; i++) inv.setItem(slots[i], ingredients[i]);
        inv.setItem(24, DragonSword.getDragonSword());
        fillWithGlass(inv);
        return inv;
    }

    private Inventory createBlessedPickaxeCraft() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Craft: Blessed Pickaxe");
        int[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
        ItemStack[] ingredients = {
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.BLAZE_ROD),
                new ItemStack(Material.NETHERITE_PICKAXE),
                new ItemStack(Material.BLAZE_ROD),
                new ItemStack(Material.DIAMOND_BLOCK),
                new ItemStack(Material.NETHER_STAR),
                new ItemStack(Material.DIAMOND_BLOCK)
        };
        for (int i = 0; i < slots.length; i++) inv.setItem(slots[i], ingredients[i]);
        inv.setItem(24, BlessedPickaxe.getBlessedPickaxe());
        fillWithGlass(inv);
        return inv;
    }

    private Inventory createMysticBowCraft() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Craft: Mystic Bow");
        int[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
        ItemStack[] ingredients = {
                new ItemStack(Material.GOLD_BLOCK),
                new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),
                new ItemStack(Material.GOLD_BLOCK),
                new ItemStack(Material.TOTEM_OF_UNDYING),
                new ItemStack(Material.BOW),
                new ItemStack(Material.TOTEM_OF_UNDYING),
                new ItemStack(Material.GOLD_BLOCK),
                new ItemStack(Material.ENDER_PEARL),
                new ItemStack(Material.GOLD_BLOCK)
        };
        for (int i = 0; i < slots.length; i++) inv.setItem(slots[i], ingredients[i]);
        inv.setItem(24, MysticBow.getMysticBow());
        fillWithGlass(inv);
        return inv;
    }

    private Inventory createOracleMaskCraft() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Craft: Oracle Mask");
        int[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
        ItemStack[] ingredients = {
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.AIR),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.AIR),
                new ItemStack(Material.AIR),
                new ItemStack(Material.AIR)
        };
        for (int i = 0; i < slots.length; i++) inv.setItem(slots[i], ingredients[i]);
//        inv.setItem(24, OracleMask.getOracleMask());
        fillWithGlass(inv);
        return inv;
    }

    private Inventory createBrasierChestplateCraft() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Craft: Brasier Chestplate");
        int[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
        ItemStack[] ingredients = {
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.AIR),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT)
        };
        for (int i = 0; i < slots.length; i++) inv.setItem(slots[i], ingredients[i]);
        inv.setItem(24, BrasierChestplate.getPlastron());
        fillWithGlass(inv);
        return inv;
    }

    private Inventory createGardienLeggingsCraft() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Craft: Gardien Leggings");
        int[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
        ItemStack[] ingredients = {
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.AIR),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.AIR),
                new ItemStack(Material.NETHERITE_INGOT)
        };
        for (int i = 0; i < slots.length; i++) inv.setItem(slots[i], ingredients[i]);
        inv.setItem(24, GardienLeggings.getJambiere());
        fillWithGlass(inv);
        return inv;
    }

    private Inventory createVagueBootsCraft() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Craft: Vague Boots");
        int[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
        ItemStack[] ingredients = {
                new ItemStack(Material.AIR),
                new ItemStack(Material.AIR),
                new ItemStack(Material.AIR),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.AIR),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.NETHERITE_INGOT),
                new ItemStack(Material.AIR),
                new ItemStack(Material.NETHERITE_INGOT)
        };
        for (int i = 0; i < slots.length; i++) inv.setItem(slots[i], ingredients[i]);
        inv.setItem(24, VagueBoots.getBottes());
        fillWithGlass(inv);
        return inv;
    }

    // =========================
    // Remplir toutes les cases vides avec du verre noir
    // =========================
    private void fillWithGlass(Inventory inv) {
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, glass);
            }
        }
    }

    // =========================
    // Empêcher de retirer ou déplacer quoi que ce soit
    // =========================
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().startsWith(ChatColor.DARK_PURPLE + "Craft: ")) {
            event.setCancelled(true);
        }
    }
}
