package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class HealingCinnamonLogBlock extends CinnamonLogBlock {

    private final Supplier<? extends Block> healsTo;

    public HealingCinnamonLogBlock(Properties properties, Supplier<? extends Block> healsTo) {
        super(properties);
        this.healsTo = healsTo;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        if (random.nextDouble() < .5) {
            level.setBlockAndUpdate(pos, healsTo.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS)));
        }
    }
}
