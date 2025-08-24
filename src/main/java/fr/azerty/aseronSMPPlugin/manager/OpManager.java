package fr.azerty.aseronSMPPlugin.manager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

public class OpManager implements Listener {

    // Liste des joueurs autorisés à utiliser /op
    private static final List<String> ALLOWED_PLAYERS = Arrays.asList("_SaPiNoX_", "nekowashere");

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase();
        Player player = event.getPlayer();

        // Vérifie si c'est une commande /op ou /deop
        if (message.startsWith("/op") || message.startsWith("/deop")) {
            if (!ALLOWED_PLAYERS.contains(player.getName())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande !");
            }
        }
    }
}
