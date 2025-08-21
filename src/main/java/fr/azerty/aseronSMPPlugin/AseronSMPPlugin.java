package fr.azerty.aseronSMPPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class AseronSMPPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Receipe.registerReceipes(this);
        getLogger().info("Custom recipes loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
