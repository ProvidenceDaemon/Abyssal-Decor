package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShipWheelBlock extends AbstractDirectionalBlock implements SimpleWaterloggedBlock {
    public ShipWheelBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,false));
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

        return switch (state.getValue(FACING)) {
            case NORTH -> box(0.0, 0.0, 14.0, 16.0, 16.0, 16.0);
            case EAST -> box(0.0, 0.0, 0.0, 2.0, 16.0, 16.0);
            case WEST -> box(14.0, 0.0, 0.0, 16.0, 16.0, 16.0);
            case UP -> box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
            case DOWN -> box(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
            default -> box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
