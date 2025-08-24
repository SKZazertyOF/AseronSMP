package fr.azerty.aseronSMPPlugin.Item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AECommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécutée que par un joueur.");
            return true;
        }

        if (!player.isOp()) {
            player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }



        // Crée l'inventaire spécial avec 27 slots (3 lignes)
        Inventory menu = Bukkit.createInventory(null, 27, ChatColor.DARK_PURPLE + "Items Spéciaux");

        // Récupère tous les items spéciaux
        ItemStack hammer = NekoHammer.getNekoHammer();
        ItemStack dragonSword = DragonSword.getDragonSword();
        ItemStack blessedPickaxe = BlessedPickaxe.getBlessedPickaxe();
        ItemStack mysticBow = MysticBow.getMysticBow();
//        ItemStack oracleMask = OracleMask.getOracleMask();
        ItemStack brasierChestplate = BrasierChestplate.getPlastron();
        ItemStack gardienLeggings = GardienLeggings.getJambiere();
        ItemStack vagueBoots = VagueBoots.getBottes();

        // Place les items dans des slots spécifiques
        menu.setItem(10, hammer);
        menu.setItem(11, dragonSword);
        menu.setItem(12, blessedPickaxe);
        menu.setItem(13, mysticBow);
//        menu.setItem(14, oracleMask);
        menu.setItem(15, brasierChestplate);
        menu.setItem(16, gardienLeggings);
        menu.setItem(17, vagueBoots);

        // Ouvre l'inventaire pour le joueur
        player.openInventory(menu);

        return true;
    }
}
