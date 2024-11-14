package de.virtualplayground.bank.gui;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.api.player.VirtualPlayer;
import de.virtualplayground.lib.gui.Gui;
import de.virtualplayground.lib.gui.GuiIcon;
import de.virtualplayground.lib.item.ItemBuilder;
import de.virtualplayground.lib.lang.Lang;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import javax.annotation.Nonnull;

public class DepositGui extends Gui {

    private final BankMainGui bankMainGui;

    public DepositGui(@Nonnull BankMainGui bankMainGui) {
        super(Component.text("Bank > Auszahlen"), 5);
        this.bankMainGui = bankMainGui;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        Player player = (Player) event.getPlayer();
        VirtualPlayer virtualPlayer = VirtualAPI.getInstance().getPlayerManager().getPlayer(player);

        for (int slot = 0; slot < getInventory().getSize(); slot++) {
            setItem(slot, new GuiIcon(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(Component.text(""))));
        }

        setItem(4, new GuiIcon(new ItemBuilder(Material.EMERALD)
                .setName(Lang.parse("<gold>Kontostand"))
                .setLore(Lang.parse("<yellow>" + virtualPlayer.getCoins() + " <gray>VP Coins"))
        ).onClick(e -> {
            bankMainGui.open(player);
        }));

        setItem(41, new GuiIcon(new ItemBuilder(Material.BARRIER)
                .setName(Lang.parse("<red>Zurück zur Startseite"))
        ).onClick(e -> {
            bankMainGui.open(player);
        }));

        update();
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }
}
