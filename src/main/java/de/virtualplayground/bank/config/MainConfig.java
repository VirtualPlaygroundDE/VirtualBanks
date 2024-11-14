package de.virtualplayground.bank.config;

import de.virtualplayground.lib.config.ConfigHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MainConfig extends ConfigHandler {

    private String npcName;
    private boolean commandEnabled;

    public MainConfig(@NotNull JavaPlugin plugin) {
        super(plugin, "config");
    }

    @Override
    public void onLoad(FileConfiguration config) {
        npcName = config.getString("npcName");
        commandEnabled = config.getBoolean("enableCommand");
    }

    @Override
    public void onPreSave(FileConfiguration config) {

    }

    public String getNpcName() {
        return this.npcName;
    }

    public boolean isCommandEnabled() {
        return this.commandEnabled;
    }
}
