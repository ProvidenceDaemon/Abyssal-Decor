package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.starrysock.abyssaldecor.registry.ModTags;

public class MoldyFeathersBlock extends BushBlock {
    public MoldyFeathersBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS);
    }
}
