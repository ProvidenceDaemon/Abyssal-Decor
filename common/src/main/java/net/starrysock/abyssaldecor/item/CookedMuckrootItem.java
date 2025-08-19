package net.starrysock.abyssaldecor.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CookedMuckrootItem extends Item {
    public CookedMuckrootItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 48;
    }
}
