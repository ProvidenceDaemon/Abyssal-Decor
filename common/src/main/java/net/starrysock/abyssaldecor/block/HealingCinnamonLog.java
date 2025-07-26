package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class HealingCinnamonLog extends RotatedPillarBlock {
    public HealingCinnamonLog(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        level.setBlockAndUpdate(pos, AbyssalDecorBlocks.CINNAMON_LOG.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS)));
    }
}
