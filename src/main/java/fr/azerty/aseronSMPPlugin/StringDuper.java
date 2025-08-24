package fr.azerty.aseronSMPPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringDuper {

    // Liste des coffres StringDupper posés dans le monde
    public static final List<Chest> stringDupperChests = new ArrayList<>();

    // Enregistrer la recette et lancer le remplissage automatique
    public static void register(JavaPlugin plugin) {
        // Création du coffre spécial
        ItemStack result = new ItemStack(Material.CHEST);
        ItemMeta meta = result.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6StringDupper");
            result.setItemMeta(meta);
        }

        // Recette
        NamespacedKey key = new NamespacedKey(plugin, "stringdupper");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("WWW", "WGW", "WWW");

        // Toutes les planches
        List<Material> planks = Arrays.stream(Material.values())
                .filter(m -> m.name().endsWith("_PLANKS"))
                .collect(Collectors.toList());
        if (planks.isEmpty()) {
            planks = List.of(Material.OAK_PLANKS);
        }

        recipe.setIngredient('W', new RecipeChoice.MaterialChoice(planks));
        recipe.setIngredient('G', Material.GOLD_BLOCK);

        Bukkit.addRecipe(recipe);
        plugin.getLogger().info("Recette StringDupper enregistrée !");

        // Lancer le remplissage automatique toutes les secondes
        new BukkitRunnable() {
            @Override
            public void run() {
                int processed = 0;
                int maxPerTick = 50; // nombre maximum de coffres traités par tick

                for (Chest chest : stringDupperChests) {
                    if (chest != null && chest.getInventory() != null) {
                        // Ajoute 16 fils par coffre
                        chest.getInventory().addItem(new ItemStack(Material.STRING, 16));

                        processed++;
                        if (processed >= maxPerTick) {
                            break; // stoppe le traitement pour cette seconde
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20); // 20 ticks = 1 seconde




        // Listener pour détecter la pose d'un StringDupper
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlace(BlockPlaceEvent event) {
                ItemStack item = event.getItemInHand();
                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() &&
                        item.getItemMeta().getDisplayName().equals("§6StringDupper")) {
                    Block block = event.getBlockPlaced();
                    if (block.getState() instanceof Chest chest) {
                        if (!stringDupperChests.contains(chest)) {
                            stringDupperChests.add(chest);
                        }
                    }
                }
            }
        }, plugin);
    }
}
