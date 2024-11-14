package de.virtualplayground.bank;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.api.item.CustomItemManager;
import de.virtualplayground.bank.command.BankCommand;
import de.virtualplayground.bank.config.MainConfig;
import de.virtualplayground.bank.listener.NpcListener;
import de.virtualplayground.lib.item.CustomItem;
import de.virtualplayground.lib.item.ItemBuilder;
import de.virtualplayground.lib.lang.Lang;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class VirtualBanks extends JavaPlugin {

    private final MainConfig mainConfig = new MainConfig(this);

    @Override
    public void onEnable() {

        mainConfig.init();

        createItems(VirtualAPI.getInstance().getItemManager());

        if (mainConfig.isCommandEnabled()) {
            new BankCommand().register();
        }

        if (getServer().getPluginManager().isPluginEnabled("FancyNpcs")) {
            getServer().getPluginManager().registerEvents(new NpcListener(this), this);
        }
    }

    private void createItems(CustomItemManager itemManager) {
        itemManager.register(new CustomItem("coins_1", new ItemBuilder(Material.IRON_BLOCK).setName(Lang.parse("<gold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>1"))));
        itemManager.register(new CustomItem("coins_10", new ItemBuilder(Material.GOLD_BLOCK).setName(Lang.parse("<gold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>10"))));
        itemManager.register(new CustomItem("coins_50", new ItemBuilder(Material.EMERALD_BLOCK).setName(Lang.parse("<gold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>50"))));
        itemManager.register(new CustomItem("coins_100", new ItemBuilder(Material.DIAMOND_BLOCK).setName(Lang.parse("<gold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>100"))));
    }

    public MainConfig getMainConfig() {
        return this.mainConfig;
    }

}
