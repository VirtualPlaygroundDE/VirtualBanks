package de.virtualplayground.bank.command;

import de.virtualplayground.bank.gui.BankMainGui;
import de.virtualplayground.lib.command.BaseCommand;

public class BankCommand extends BaseCommand {

    public BankCommand() {

        super("bank");

        command.executesPlayer((player, args) -> {
            new BankMainGui().open(player);
        });
    }

}
