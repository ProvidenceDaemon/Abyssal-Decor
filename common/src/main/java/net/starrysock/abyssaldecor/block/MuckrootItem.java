package net.starrysock.abyssaldecor.block;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class MuckrootItem extends ItemNameBlockItem {
    public MuckrootItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 64;
    }
}
