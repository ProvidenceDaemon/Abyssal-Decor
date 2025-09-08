package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class CinnamonLeavesBlock extends LeavesBlock implements BonemealableBlock {
    private final boolean flowering;

    public CinnamonLeavesBlock(Properties properties, boolean flowering) {
        super(properties);
        this.flowering = flowering;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return !flowering;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos, AbyssalDecorBlocks.FLOWERING_CINNAMON_LEAVES.get().defaultBlockState());
    }
}
