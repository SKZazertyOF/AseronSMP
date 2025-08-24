package fr.azerty.aseronSMPPlugin.command;

import fr.azerty.aseronSMPPlugin.AseronSMPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpawnCommand implements CommandExecutor {

    private final Map<UUID, Long> lastHitTime = new HashMap<>();
    private final Map<UUID, Long> spawnCooldown = new HashMap<>();

    public void playerHit(Player player) {
        lastHitTime.put(player.getUniqueId(), System.currentTimeMillis());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        UUID uuid = player.getUniqueId();
        long now = System.currentTimeMillis();

        // Combat cooldown de 20 secondes
        long lastHit = lastHitTime.getOrDefault(uuid, 0L);
        if (now - lastHit < 20_000) {
            long wait = (20_000 - (now - lastHit)) / 1000;
            player.sendMessage("§cVous êtes en combat ! Attendez " + wait + " secondes avant de pouvoir faire /spawn.");
            return true;
        }

        // Cooldown de 10 minutes pour /spawn
        long lastSpawn = spawnCooldown.getOrDefault(uuid, 0L);
        if (now - lastSpawn < 10 * 60 * 1000) { // 10 minutes
            long wait = (10 * 60 * 1000 - (now - lastSpawn)) / 1000;
            player.sendMessage("§cVous devez attendre " + wait + " secondes avant de refaire /spawn.");
            return true;
        }

        // TP après 10 secondes sans bouger
        Location startLoc = player.getLocation();
        player.sendMessage("§aRestez immobile pendant 10 secondes pour être téléporté...");

        new BukkitRunnable() {
            int seconds = 10;

            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    return;
                }

                // Si le joueur bouge, annuler le TP
                if (!player.getLocation().getBlock().equals(startLoc.getBlock())) {
                    player.sendMessage("§cVous avez bougé ! Téléportation annulée.");
                    cancel();
                    return;
                }

                seconds--;
                if (seconds <= 0) {
                    // Téléportation
                    World overworld = Bukkit.getWorld("world");
                    if (overworld != null) {
                        Location spawnLoc = new Location(overworld, 64, 71, 355);
                        player.teleport(spawnLoc);
                        player.sendMessage("§aVous avez été téléporté au spawn !");
                        // Activer le cooldown
                        spawnCooldown.put(uuid, System.currentTimeMillis());
                    } else {
                        player.sendMessage("§cImpossible de trouver l'Overworld !");
                    }
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(AseronSMPPlugin.getInstance(), 0L, 20L); // 20 ticks = 1 seconde

        return true;
    }

    public void registerHitEvent(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getDamager() instanceof Player || event.getDamager() instanceof org.bukkit.entity.LivingEntity) {
                playerHit(player);
            }
        }
    }
}
