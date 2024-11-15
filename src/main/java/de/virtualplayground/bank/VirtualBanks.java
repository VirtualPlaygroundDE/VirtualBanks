package de.virtualplayground.bank;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.api.item.CustomItemManager;
import de.virtualplayground.bank.command.BankCommand;
import de.virtualplayground.bank.config.MainConfig;
import de.virtualplayground.bank.currency.Currency;
import de.virtualplayground.bank.listener.NpcListener;
import de.virtualplayground.lib.item.CustomItem;
import de.virtualplayground.lib.item.ItemBuilder;
import de.virtualplayground.lib.lang.Lang;
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
        for (Currency.Item item : Currency.Item.values()) {
            itemManager.register(new CustomItem("coins_" + item.getValue(), new ItemBuilder(item.getType()).setName(Lang.parse("<gold><bold>VP Coins")).setLore(Lang.parse("<gray>Wert: <yellow>" + item.getValue()))));
        }
    }

    public MainConfig getMainConfig() {
        return this.mainConfig;
    }

}
