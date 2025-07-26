package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class IronBallBlock extends PearlBlock {
    public IronBallBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState placed = super.getStateForPlacement(blockPlaceContext);
        BlockPos pos = blockPlaceContext.getClickedPos();
        Level level = blockPlaceContext.getLevel();

        BlockState exist = level.getBlockState(pos);

        if (exist.is(AbyssalDecorBlocks.STACKED_IRON_BALLS.get())) {
            return AbyssalDecorBlocks.STACKED_IRON_BALLS.get().getStateForPlacement(blockPlaceContext);
        }

        if (level.getBlockState(pos).is(this) && exist.getValue(FACING) == Direction.UP) placed = AbyssalDecorBlocks.STACKED_IRON_BALLS.get().defaultBlockState()
                .setValue(BlockStateProperties.WATERLOGGED,placed.getValue(BlockStateProperties.WATERLOGGED)).setValue(StackedIronBallsBlock.BALLS,2);
        return placed;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return state.getValue(FACING) == Direction.UP && !useContext.isSecondaryUseActive() && useContext.getItemInHand().is(this.asItem()) || super.canBeReplaced(state, useContext);
    }
}
