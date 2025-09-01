package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;

public class ShellBlock extends FaceAttachedBlock implements SimpleWaterloggedBlock {
    public ShellBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,false));
    }

    protected static final VoxelShape CEILING_AABB = Block.box(6.0D, 14.0D, 5.0D, 10.0D, 16.0D, 11.0D);
    protected static final VoxelShape FLOOR_AABB = Block.box(6.0D, 0.0D, 5.0D, 10.0D, 2.0D, 11.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(5.0D, 6.0D, 14.0D, 11.0D, 10.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(5.0D, 6.0D, 0.0D, 11.0D, 10.0D, 2.0D);
    protected static final VoxelShape WEST_AABB = Block.box(14.0D, 6.0D, 5.0D, 16.0D, 10.0D, 11.0D);
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 6.0D, 5.0D, 2.0D, 10.0D, 11.0D);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        AttachFace attachFace = state.getValue(FACE);
        return switch (attachFace) {
            case FLOOR -> box(3,0,3,13,1,13);
            case WALL -> {
                Direction facing = state.getValue(FACING);
                yield AbyssalUtils.calculateShapes(facing,box(3, 3, 15, 13, 13, 16));
            }
            case CEILING -> box(3,15,3,13,16,13);
        };
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        AttachFace attachFace = state.getValue(FACE);

        return switch (attachFace) {
            case FLOOR -> DriedStarfishBlock.canSupportAtFace(level,pos,Direction.DOWN);
            case WALL -> {
                Direction direction = state.getValue(FACING).getOpposite();
                yield DriedStarfishBlock.canSupportAtFace(level,pos,direction);
            }
            case CEILING -> DriedStarfishBlock.canSupportAtFace(level,pos,Direction.UP);
        };
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED);
    }
}
