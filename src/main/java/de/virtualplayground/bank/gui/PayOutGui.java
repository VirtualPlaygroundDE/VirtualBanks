package de.virtualplayground.bank.gui;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.api.item.CustomItemManager;
import de.virtualplayground.api.player.VirtualPlayer;
import de.virtualplayground.bank.currency.Currency;
import de.virtualplayground.lib.gui.Gui;
import de.virtualplayground.lib.gui.GuiIcon;
import de.virtualplayground.lib.item.ItemBuilder;
import de.virtualplayground.lib.lang.Lang;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

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

        int slot = 20;
        for (Currency.Item item : Currency.Item.values()) {

            ItemStack itemStack = itemManager.getItem("coins_" + item.getValue()).build();

            setItem(slot, new GuiIcon(new ItemBuilder(item.getType())
                    .setName(Lang.parse("<yellow>" + item.getValue()))
            ).onClick(e -> {
                if (virtualPlayer.getCoins() >= item.getValue()) {
                    player.getInventory().addItem(itemStack);
                    virtualPlayer.removeCoins(item.getValue());
                    updateBalance(virtualPlayer);
                }
            }));

            slot++;
        }

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
