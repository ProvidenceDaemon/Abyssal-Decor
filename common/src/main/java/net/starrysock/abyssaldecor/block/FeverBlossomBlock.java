package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ModTags;

public class FeverBlossomBlock extends CropBlock {
    public FeverBlossomBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return AbyssalDecorItems.FEVER_BLOSSOM_SEEDS.get();
    }
}
