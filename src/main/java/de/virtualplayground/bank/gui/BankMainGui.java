package de.virtualplayground.bank.gui;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.lib.gui.Gui;
import de.virtualplayground.lib.gui.GuiIcon;
import de.virtualplayground.lib.item.ItemBuilder;
import de.virtualplayground.lib.lang.Lang;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class BankMainGui extends Gui {

    private DepositGui depositGui = new DepositGui(this);

    public BankMainGui() {
        super(Component.text("Bank"), 3);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        Player player = (Player) event.getPlayer();

        for (int slot = 0; slot < getInventory().getSize(); slot++) {
            setItem(slot, new GuiIcon(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(Component.text(""))));
        }

        setItem(11, new GuiIcon(new ItemBuilder(Material.YELLOW_WOOL)
                .setName(Lang.parse("<gold>Einzahlen"))
        ).onClick(e -> {
            depositGui.open(player);
        }));

        setItem(13, new GuiIcon(new ItemBuilder(Material.EMERALD)
                .setName(Lang.parse("<gold>Kontostand"))
                .setLore(Lang.parse("<yellow>" + VirtualAPI.getInstance().getPlayerManager().getPlayer(player).getCoins() + " <gray>VP Coins"))
        ));

        setItem(15, new GuiIcon(new ItemBuilder(Material.ORANGE_WOOL)
                .setName(Lang.parse("<gold>Auszahlen"))
        ));

        update();
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }
}
