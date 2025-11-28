package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class HealingCinnamonLogBlock extends RotatedPillarBlock {
    public HealingCinnamonLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        level.setBlockAndUpdate(pos, AbyssalDecorBlocks.CINNAMON_LOG.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS)));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        CinnamonLogBlock.spawnCinnamonStick(level, pos, player, hand);
        return super.use(state, level, pos, player, hand, hit);
    }
}
