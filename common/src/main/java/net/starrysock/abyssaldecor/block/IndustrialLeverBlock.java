package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class IndustrialLeverBlock extends LeverBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public IndustrialLeverBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    protected static final VoxelShape ALT_NORTH_AABB = Block.box(3, 0, 12, 13, 16, 16);
    protected static final VoxelShape ALT_SOUTH_AABB = Block.box(3, 0, 0, 13, 16, 4);
    protected static final VoxelShape ALT_WEST_AABB = Block.box(12, 0, 3, 16, 16, 13);
    protected static final VoxelShape ALT_EAST_AABB = Block.box(0, 0, 3, 4, 16, 13);
    protected static final VoxelShape ALT_UP_AABB_Z = Block.box(3, 0, 0, 13, 4, 16);
    protected static final VoxelShape ALT_UP_AABB_X = Block.box(0, 0, 3, 16, 4, 13);
    protected static final VoxelShape ALT_DOWN_AABB_Z = Block.box(3, 12, 0, 13, 16, 16);
    protected static final VoxelShape ALT_DOWN_AABB_X = Block.box(0, 12, 3, 16, 16, 13);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACE)) {
            case FLOOR:
                switch (state.getValue(FACING).getAxis()) {
                    case X:
                        return ALT_UP_AABB_X;
                    case Z:
                    default:
                        return ALT_UP_AABB_Z;
                }
            case WALL:
                switch (state.getValue(FACING)) {
                    case EAST:
                        return ALT_EAST_AABB;
                    case WEST:
                        return ALT_WEST_AABB;
                    case SOUTH:
                        return ALT_SOUTH_AABB;
                    case NORTH:
                    default:
                        return ALT_NORTH_AABB;
                }
            case CEILING:
            default:
                switch (state.getValue(FACING).getAxis()) {
                    case X:
                        return ALT_DOWN_AABB_X;
                    case Z:
                    default:
                        return ALT_DOWN_AABB_Z;
                }
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState  state =super.getStateForPlacement(context);
        if (state == null) return null;
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(WATERLOGGED, fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8);
    }
}
