package de.virtualplayground.bank;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.api.item.CustomItemManager;
import de.virtualplayground.bank.command.BankCommand;
import de.virtualplayground.bank.config.LocationConfig;
import de.virtualplayground.lib.item.CustomItem;
import de.virtualplayground.lib.item.ItemBuilder;
import de.virtualplayground.lib.lang.Lang;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class VirtualBanks extends JavaPlugin {

    private final LocationConfig locationConfig = new LocationConfig(this);

    @Override
    public void onEnable() {

        this.locationConfig.init();
        createItems(VirtualAPI.getInstance().getItemManager());

        registerCommands();
    }

    private void registerCommands() {
        new BankCommand(this).register();
    }

    private void createItems(CustomItemManager itemManager) {
        itemManager.register(new CustomItem("coin_1", new ItemBuilder(Material.IRON_BLOCK).setName(Lang.parse("<gold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>1"))));
        itemManager.register(new CustomItem("coin_10", new ItemBuilder(Material.GOLD_BLOCK).setName(Lang.parse("<gold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>10"))));
        itemManager.register(new CustomItem("coin_50", new ItemBuilder(Material.EMERALD_BLOCK).setName(Lang.parse("<gold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>50"))));
        itemManager.register(new CustomItem("coin_100", new ItemBuilder(Material.DIAMOND_BLOCK).setName(Lang.parse("<gold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>100"))));
    }

}
