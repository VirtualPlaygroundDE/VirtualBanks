package de.virtualplayground.bank.command;

import de.virtualplayground.bank.VirtualBanks;
import de.virtualplayground.bank.gui.BankMainGui;
import de.virtualplayground.lib.command.BaseCommand;
import de.virtualplayground.lib.lang.Lang;
import dev.jorel.commandapi.CommandAPICommand;

public class BankCommand extends BaseCommand {

    public BankCommand(VirtualBanks plugin) {

        super("bank");

        command.executesPlayer((player, args) -> {
            new BankMainGui().open(player);
        });

        command.withSubcommand(new CommandAPICommand("setloc")
                .withPermission(getPermission() + ".setloc")
                .executesPlayer((player, args) -> {
                    plugin.getLocationConfig().setBankLocation(player.getLocation());
                    player.sendMessage(Lang.parse("<gray>Location für <yellow>Bank <dark_gray>(NPC) <gray>gespeichert."));
                })
        );
    }

}
