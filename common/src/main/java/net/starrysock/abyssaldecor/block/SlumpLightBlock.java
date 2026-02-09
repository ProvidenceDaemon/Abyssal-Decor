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

public class SlumpLightBlock extends AbstractDirectionalBlock implements SimpleWaterloggedBlock {
    public SlumpLightBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> box(4.0, 4.0, 1.0, 12.0, 12.0, 16.0);
            case EAST -> box(0.0, 4.0, 4.0, 15.0, 12.0, 12.0);
            case WEST -> box(1.0, 4.0, 4.0, 16.0, 12.0, 12.0);
            case UP -> box(4.0, 0.0, 4.0, 12.0, 15.0, 12.0);
            case DOWN -> box(4.0, 1.0, 4.0, 12.0, 16.0, 12.0);
            default -> box(4.0, 4.0, 0.0, 12.0, 12.0, 15.0);
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
