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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LifePreserverBlock extends AbstractDirectionalBlock implements SimpleWaterloggedBlock {
    public LifePreserverBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Shapes.join(box(2, 2, 13, 14, 14, 16), box(5, 5, 13, 11, 11, 16), BooleanOp.ONLY_FIRST);
            case EAST -> Shapes.join(box(0, 2, 2, 3, 14, 14), box(0, 5, 5, 3, 11, 11), BooleanOp.ONLY_FIRST);
            case WEST -> Shapes.join(box(13, 2, 2, 16, 14, 14), box(13, 5, 5, 16, 11, 11), BooleanOp.ONLY_FIRST);
            case UP -> Shapes.join(box(2, 0, 2, 14, 3, 14), box(5, 0, 5, 11, 3, 11), BooleanOp.ONLY_FIRST);
            case DOWN -> Shapes.join(box(2, 13, 2, 14, 16, 14), box(5, 13, 5, 11, 16, 11), BooleanOp.ONLY_FIRST);
            default -> Shapes.join(box(2, 2, 0, 14, 14, 3), box(5, 5, 0, 11, 11, 3), BooleanOp.ONLY_FIRST);
        };
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED);
    }
}
