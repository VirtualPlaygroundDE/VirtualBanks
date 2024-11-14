package de.virtualplayground.bank.config;

import de.virtualplayground.lib.config.ConfigHandler;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class LocationConfig extends ConfigHandler {

    private Location bankLocation;

    public LocationConfig(@NotNull JavaPlugin plugin) {
        super(plugin, "locations");
    }

    @Override
    public void onLoad(FileConfiguration config) {
        this.bankLocation = config.getLocation("bank");
    }

    @Override
    public void onPreSave(FileConfiguration config) {
        config.set("bank", bankLocation);
    }

}
