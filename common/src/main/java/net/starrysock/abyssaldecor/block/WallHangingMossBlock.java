package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import org.jetbrains.annotations.Nullable;

public class WallHangingMossBlock extends WallHangingBlock{

    public static final DirectionProperty VERTICAL_FACING = BlockStateProperties.VERTICAL_DIRECTION;

    public WallHangingMossBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(VERTICAL_FACING, Direction.DOWN));
    }

    protected static final VoxelShape THIN_EAST_AABB_UPPER = Block.box(0, 0, 0, 1, 16, 16);
    protected static final VoxelShape THIN_WEST_AABB_UPPER = Block.box(15, 0, 0, 16, 16, 16);
    protected static final VoxelShape THIN_SOUTH_AABB_UPPER = Block.box(0, 0, 0, 16, 16, 1);
    protected static final VoxelShape THIN_NORTH_AABB_UPPER = Block.box(0, 0, 15, 16, 16, 16);


    protected static final VoxelShape THIN_EAST_AABB = Block.box(0, 0, 0, 1, 16, 16);
    protected static final VoxelShape THIN_WEST_AABB = Block.box(15, 0, 0, 16, 16, 16);
    protected static final VoxelShape THIN_SOUTH_AABB = Block.box(0, 0, 0, 16, 16, 1);
    protected static final VoxelShape THIN_NORTH_AABB = Block.box(0, 0, 15, 16, 16, 16);


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> THIN_NORTH_AABB;
            case SOUTH -> THIN_SOUTH_AABB;
            case WEST -> THIN_WEST_AABB;
            default -> THIN_EAST_AABB;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERTICAL_FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState stateForPlacement = super.getStateForPlacement(context);

        if (stateForPlacement != null) {
            Vec3 fraction = AbyssalUtils.getFraction(context.getClickLocation());
            stateForPlacement = stateForPlacement.setValue(VERTICAL_FACING,fraction.y < .5 ? Direction.UP: Direction.DOWN);
        }

        return stateForPlacement;
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }


}
