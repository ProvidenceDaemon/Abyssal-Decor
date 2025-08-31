package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.properties.HorizontalPart;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;

public class GargoyleBlock extends HorizontalDoubleBlock implements SimpleWaterloggedBlock {
    public GargoyleBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        HorizontalPart part = state.getValue(ModBlockStateProperties.PART);
        Direction facing = state.getValue(FACING);

        if (part == HorizontalPart.BACK) {
            return switch (facing) {
                case NORTH -> Shapes.or(box(1, 0, 13, 6, 3, 16), box(10, 0, 13, 15, 2, 16), box(1, 3, 8, 6, 14, 16), box(10, 3, 8, 15, 14, 16), box(6, 3, 8, 10, 14, 16), box(1, 4, 0, 15, 15, 8), box(14, 9, 0, 16, 16, 16), box(0, 9, 0, 2, 16, 16));
                case EAST -> Shapes.or(box(0, 0, 1, 3, 3, 6), box(0, 0, 10, 3, 2, 15), box(0, 3, 1, 8, 14, 6), box(0, 3, 10, 8, 14, 15), box(0, 3, 6, 8, 14, 10), box(8, 4, 1, 16, 15, 15), box(0, 9, 14, 16, 16, 16), box(0, 9, 0, 16, 16, 2));
                case WEST -> Shapes.or(box(13, 0, 10, 16, 3, 15), box(13, 0, 1, 16, 2, 6), box(8, 3, 10, 16, 14, 15), box(8, 3, 1, 16, 14, 6), box(8, 3, 6, 16, 14, 10), box(0, 4, 1, 8, 15, 15), box(0, 9, 0, 16, 16, 2), box(0, 9, 14, 16, 16, 16));
                default -> Shapes.or(box(10, 0, 0, 15, 3, 3), box(1, 0, 0, 6, 2, 3), box(10, 3, 0, 15, 14, 8), box(1, 3, 0, 6, 14, 8), box(6, 3, 0, 10, 14, 8), box(1, 4, 8, 15, 15, 16), box(0, 9, 0, 2, 16, 16), box(14, 9, 0, 16, 16, 16));
            };
        } else {
            return switch (facing) {
                case NORTH -> Shapes.or(box(1, 4, 10, 15, 15, 16), box(4, 3, 2, 12, 14, 10), box(14, 6, 7, 16, 16, 16), box(0, 6, 7, 2, 16, 16));
                case EAST -> Shapes.or(box(0, 4, 1, 6, 15, 15), box(6, 3, 4, 14, 14, 12), box(0, 6, 14, 9, 16, 16), box(0, 6, 0, 9, 16, 2));
                case WEST -> Shapes.or(box(10, 4, 1, 16, 15, 15), box(2, 3, 4, 10, 14, 12), box(7, 6, 0, 16, 16, 2), box(7, 6, 14, 16, 16, 16));
                default -> Shapes.or(box(1, 4, 0, 15, 15, 6), box(4, 3, 6, 12, 14, 14), box(0, 6, 0, 2, 16, 9), box(14, 6, 0, 16, 16, 9));
            };
        }
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
