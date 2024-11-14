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
import org.bukkit.inventory.ItemStack;

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

        for (int slot = 0; slot < 9; slot++) {
            setItem(slot, new GuiIcon(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(Component.text(""))));
        }

        for (int slot = 36; slot < 45; slot++) {
            setItem(slot, new GuiIcon(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(Component.text(""))));
        }

        setItem(4, new GuiIcon(new ItemBuilder(Material.EMERALD)
                .setName(Lang.parse("<gold>Kontostand"))
                .setLore(Lang.parse("<yellow>" + virtualPlayer.getCoins() + " <gray>VP Coins"))
        ));

        setItem(39, new GuiIcon(new ItemBuilder(Material.BARRIER)
                .setName(Lang.parse("<red>Zurück zur Startseite"))
        ).onClick(e -> {
            bankMainGui.open(player);
        }));

        setItem(41, new GuiIcon(new ItemBuilder(Material.BARRIER)
                .setName(Lang.parse("<gold>Blöcke einzahlen"))
        ).onClick(e -> {
            deposit();
            bankMainGui.open(player);
        }));

        update();
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        for (int slot = 9; slot < 36; slot++) {
            ItemStack itemStack = getInventory().getItem(slot);
            if (itemStack != null && !itemStack.getType().equals(Material.AIR)) {
                event.getPlayer().sendMessage(itemStack.displayName());
            }
        }
    }

    private void deposit() {

    }
}
