package net.starrysock.abyssaldecor.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class WaterloggedRodBlock extends RodBlock implements SimpleWaterloggedBlock {
    public WaterloggedRodBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Direction direction = blockPlaceContext.getClickedFace();
        BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().relative(direction.getOpposite()));
        if (blockState.is(this) && blockState.getValue(FACING) == direction) {
            return this.defaultBlockState().setValue(FACING, direction.getOpposite());
        }
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,BlockStateProperties.WATERLOGGED);
    }
}
