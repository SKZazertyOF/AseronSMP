package fr.azerty.aseronSMPPlugin.Item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Receipe {

    public static void registerReceipes(JavaPlugin plugin) {
        // =========================
        // 1) Recette Épée du Dragon
        // =========================
        var dragonSword = DragonSword.getDragonSword();
        NamespacedKey key1 = new NamespacedKey(plugin, "dragon_sword");
        ShapedRecipe recipe1 = new ShapedRecipe(key1, dragonSword);

        recipe1.shape("did",
                "ded",
                "nun");

        recipe1.setIngredient('d', Material.DIAMOND);
        recipe1.setIngredient('n', Material.NETHER_STAR);
        recipe1.setIngredient('u', Material.NETHERITE_INGOT);
        recipe1.setIngredient('e', Material.NETHERITE_SWORD);
        recipe1.setIngredient('i', Material.DRAGON_EGG);

        Bukkit.addRecipe(recipe1);

        // =========================
        // 2) Recette Pioche Bénie
        // =========================
        var blessepickaxe = BlessedPickaxe.getBlessedPickaxe();
        NamespacedKey key2 = new NamespacedKey(plugin, "blessed_pickaxe");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, blessepickaxe);

        recipe2.shape("nnn",
                "bpb",
                "lil");

        recipe2.setIngredient('n', Material.NETHERITE_INGOT);
        recipe2.setIngredient('b', Material.BLAZE_ROD);
        recipe2.setIngredient('p', Material.NETHERITE_PICKAXE);
        recipe2.setIngredient('i', Material.NETHER_STAR);
        recipe2.setIngredient('l', Material.DIAMOND_BLOCK);

        Bukkit.addRecipe(recipe2);

        // =========================
        // 3) Recette Arc Mystique
        // =========================
        var mysticbow = MysticBow.getMysticBow();
        NamespacedKey key3 = new NamespacedKey(plugin, "mystic_bow");
        ShapedRecipe recipe3 = new ShapedRecipe(key3, mysticbow);

        recipe3.shape("bpb",
                "tat",
                "beb");

        recipe3.setIngredient('b', Material.GOLD_BLOCK);
        recipe3.setIngredient('p', Material.ENCHANTED_GOLDEN_APPLE);
        recipe3.setIngredient('t', Material.TOTEM_OF_UNDYING);
        recipe3.setIngredient('a', Material.BOW);
        recipe3.setIngredient('e', Material.ENDER_PEARL);

        Bukkit.addRecipe(recipe3);

        // =========================
        // 4) Masque de l’Oracle (Casque)
        // =========================
//        ItemStack oracleMask = OracleMask.getOracleMask();
//        NamespacedKey key4 = new NamespacedKey(plugin, "oracle_mask");
//        ShapedRecipe recipe4 = new ShapedRecipe(key4, oracleMask);
//
//        recipe4.shape("ooo",
//                "o o",
//                "   ");
//
//        recipe4.setIngredient('o', Material.NETHERITE_INGOT);
//
//        Bukkit.addRecipe(recipe4);

        // =========================
        // 5) Plastron du Brasier
        // =========================
        ItemStack brasierChestplate = BrasierChestplate.getPlastron();
        NamespacedKey key5 = new NamespacedKey(plugin, "brasier_chestplate");
        ShapedRecipe recipe5 = new ShapedRecipe(key5, brasierChestplate);

        recipe5.shape("o o",
                "ooo",
                "ooo");

        recipe5.setIngredient('o', Material.NETHERITE_INGOT);

        Bukkit.addRecipe(recipe5);

        // =========================
        // 6) Jambières du Gardien
        // =========================
        ItemStack gardienLeggings = GardienLeggings.getJambiere();
        NamespacedKey key6 = new NamespacedKey(plugin, "gardien_leggings");
        ShapedRecipe recipe6 = new ShapedRecipe(key6, gardienLeggings);

        recipe6.shape("ooo",
                "o o",
                "o o");

        recipe6.setIngredient('o', Material.NETHERITE_INGOT);

        Bukkit.addRecipe(recipe6);

        // =========================
        // 7) Bottes de la Vague
        // =========================
        ItemStack vagueBoots = VagueBoots.getBottes();
        NamespacedKey key7 = new NamespacedKey(plugin, "vague_boots");
        ShapedRecipe recipe7 = new ShapedRecipe(key7, vagueBoots);

        recipe7.shape("   ",
                "o o",
                "o o");

        recipe7.setIngredient('o', Material.NETHERITE_INGOT);

        Bukkit.addRecipe(recipe7);
    }
}
