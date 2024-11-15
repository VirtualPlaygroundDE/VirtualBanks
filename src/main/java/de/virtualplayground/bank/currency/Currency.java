package de.virtualplayground.bank.currency;

import org.bukkit.Material;

public class Currency {

    public enum Item {

        FIVE(5, Material.IRON_BLOCK),
        TEN(10, Material.GOLD_BLOCK),
        HUNDRED(100, Material.EMERALD_BLOCK),
        THOUSAND(1000, Material.DIAMOND_BLOCK),
        TEN_THOUSAND(10000, Material.NETHERITE_BLOCK);

        final int value;
        final Material type;

        private Item(int v, Material t) {
            value = v;
            type = t;
        }

        public int getValue() {
            return value;
        }

        public Material getType() {
            return type;
        }
    }

}
