package de.virtualplayground.bank.listener;

import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import de.virtualplayground.bank.VirtualBanks;
import de.virtualplayground.bank.gui.BankMainGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcListener implements Listener {

    private final VirtualBanks plugin;

    public NpcListener(VirtualBanks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNpcInteract(NpcInteractEvent e) {
        if (e.getNpc().getData().getName().equals(plugin.getMainConfig().getNpcName())) {
            new BankMainGui().open(e.getPlayer());
        }
    }

}
