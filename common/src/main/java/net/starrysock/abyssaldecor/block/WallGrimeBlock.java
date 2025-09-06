package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.WallGrimeType;
import org.jetbrains.annotations.Nullable;

public class WallGrimeBlock extends AbstractHorizontalBlock{
    public WallGrimeBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.WALL_GRIME_TYPE, WallGrimeType.FLOOR));
    }


    static final VoxelShape halfShape = box(0,8,15,16,16,16);
    static final VoxelShape bottomShape = box(0,0,15,16,8,16);
    static final VoxelShape fullShape = box(0,1,15,16,16,16);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        WallGrimeType wallGrimeType = state.getValue(ModBlockStateProperties.WALL_GRIME_TYPE);
        VoxelShape shape = switch (wallGrimeType){
            case FLOOR -> bottomShape;
            case MIDDLE_FLOOR,MIDDLE -> fullShape;
            case TOP -> halfShape;
        };

        return AbyssalUtils.calculateShapes(facing, shape);
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {

        if (facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        }

        if (facing == Direction.UP) {
            WallGrimeType type = null;
            boolean isGrimeAbove = facingState.is(this);
            boolean isFloorBelow = DriedStarfishBlock.canSupportAtFace(level,currentPos,Direction.DOWN);
            if (isGrimeAbove) {
                type = isFloorBelow ? WallGrimeType.MIDDLE_FLOOR : WallGrimeType.MIDDLE;
            } else {
                type = isFloorBelow ? WallGrimeType.FLOOR : WallGrimeType.MIDDLE;
            }
            state = state.setValue(ModBlockStateProperties.WALL_GRIME_TYPE,type);

        } else {
            WallGrimeType type = null;
            boolean isGrimeBelow = facingState.is(this);
            boolean isFloorBelow = DriedStarfishBlock.canSupportAtFace(level,currentPos,Direction.DOWN);
            if (isGrimeBelow) {
                type = isFloorBelow ? WallGrimeType.MIDDLE_FLOOR : WallGrimeType.MIDDLE;
            } else {
                type = isFloorBelow ? WallGrimeType.FLOOR : WallGrimeType.TOP;
            }
            state = state.setValue(ModBlockStateProperties.WALL_GRIME_TYPE,type);
        }

        return state;
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockPos = pos.relative(direction.getOpposite());
        BlockState blockState = level.getBlockState(blockPos);
        return blockState.isFaceSturdy(level, blockPos, direction);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        boolean floor = DriedStarfishBlock.canSupportAtFace(level, pos, Direction.DOWN);
        boolean grimeBelow = level.getBlockState(pos.below()).is(this);
        boolean grimeAbove =  level.getBlockState(pos.above()).is(this);

        WallGrimeType grimeType;

        if (floor) {
            grimeType = grimeAbove ? WallGrimeType.MIDDLE_FLOOR : WallGrimeType.FLOOR;
        } else {
            grimeType = grimeBelow ? WallGrimeType.MIDDLE : WallGrimeType.TOP;
        }


        Direction[] adirection = context.getNearestLookingDirections();
        BlockState blockstate = this.defaultBlockState().setValue(ModBlockStateProperties.WALL_GRIME_TYPE,grimeType);
        for (Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.setValue(FACING, direction1);
                if (blockstate.canSurvive(level, pos)) {

                    return blockstate;
                }
            }
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ModBlockStateProperties.WALL_GRIME_TYPE);
    }
}
