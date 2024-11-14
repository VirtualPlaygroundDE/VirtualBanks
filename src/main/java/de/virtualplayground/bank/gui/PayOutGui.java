package de.virtualplayground.bank.gui;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.api.item.CustomItemManager;
import de.virtualplayground.api.player.VirtualPlayer;
import de.virtualplayground.lib.gui.Gui;
import de.virtualplayground.lib.gui.GuiIcon;
import de.virtualplayground.lib.item.ItemBuilder;
import de.virtualplayground.lib.lang.Lang;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import javax.annotation.Nonnull;

public class PayOutGui extends Gui {

    private final BankMainGui bankMainGui;

    public PayOutGui(@Nonnull BankMainGui bankMainGui) {
        super(Component.text("Bank > Auszahlen"), 5);
        this.bankMainGui = bankMainGui;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        Player player = (Player) event.getPlayer();
        VirtualPlayer virtualPlayer = VirtualAPI.getInstance().getPlayerManager().getPlayer(player);
        CustomItemManager itemManager = VirtualAPI.getInstance().getItemManager();
        GuiIcon placeholder = new GuiIcon(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(Component.text("")));

        fill(new GuiIcon(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)));
        fillRow(placeholder, 0);
        fillRow(placeholder, 4);

        setItem(40, new GuiIcon(new ItemBuilder(Material.BARRIER)
                .setName(Lang.parse("<red>Zurück zur Startseite"))
        ).onClick(e -> {
            bankMainGui.open(player);
        }));

        setItem(19, new GuiIcon(new ItemBuilder(Material.IRON_BLOCK)
                .setName(Lang.parse("<yellow>1"))
        ).onClick(e -> {
            if (virtualPlayer.getCoins() >= 1) {
                player.getInventory().addItem(itemManager.getItem("coins_1").build());
                virtualPlayer.removeCoins(1);
                updateBalance(virtualPlayer);
            }
        }));

        setItem(21, new GuiIcon(new ItemBuilder(Material.GOLD_BLOCK)
                .setName(Lang.parse("<yellow>10"))
        ).onClick(e -> {
            if (virtualPlayer.getCoins() >= 10) {
                player.getInventory().addItem(itemManager.getItem("coins_10").build());
                virtualPlayer.removeCoins(10);
                updateBalance(virtualPlayer);
            }
        }));

        setItem(23, new GuiIcon(new ItemBuilder(Material.EMERALD_BLOCK)
                .setName(Lang.parse("<yellow>50"))
        ).onClick(e -> {
            if (virtualPlayer.getCoins() >= 50) {
                player.getInventory().addItem(itemManager.getItem("coins_50").build());
                virtualPlayer.removeCoins(50);
                updateBalance(virtualPlayer);
            }
        }));

        setItem(25, new GuiIcon(new ItemBuilder(Material.DIAMOND_BLOCK)
                .setName(Lang.parse("<yellow>100"))
        ).onClick(e -> {
            if (virtualPlayer.getCoins() >= 100) {
                player.getInventory().addItem(itemManager.getItem("coins_100").build());
                virtualPlayer.removeCoins(100);
                updateBalance(virtualPlayer);
            }
        }));

        updateBalance(virtualPlayer);
    }

    private void updateBalance(VirtualPlayer virtualPlayer) {
        setItem(4, new GuiIcon(new ItemBuilder(Material.EMERALD)
                .setName(Lang.parse("<gold>Kontostand"))
                .setLore(Lang.parse("<yellow>" + virtualPlayer.getCoins() + " <gray>VP Coins"))
        ));
        update();
    }
}
