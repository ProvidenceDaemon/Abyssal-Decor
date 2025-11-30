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


    static final VoxelShape topShape = box(0,8,15,16,16,16);
    static final VoxelShape bottomShape = box(0,0,15,16,8,16);
    static final VoxelShape fullShape = box(0,1,15,16,16,16);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        WallGrimeType wallGrimeType = state.getValue(ModBlockStateProperties.WALL_GRIME_TYPE);
        VoxelShape shape = switch (wallGrimeType){
            case BOTTOM, FLOOR -> bottomShape;
            case MIDDLE_FLOOR,MIDDLE,MIDDLE_CEILING -> fullShape;
            case TOP,CEILING -> topShape;
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

        switch (facing) {
            case UP, DOWN -> {
                WallGrimeType.Connection below = getConnection(level, currentPos, Direction.DOWN);
                WallGrimeType.Connection above = getConnection(level, currentPos, Direction.UP);


                WallGrimeType type = WallGrimeType.getGrimeType(above, below);
                state = state.setValue(ModBlockStateProperties.WALL_GRIME_TYPE, type);
            }
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

    public WallGrimeType.Connection getConnection(LevelAccessor level,BlockPos pos,Direction direction) {
        BlockState state = level.getBlockState(pos.relative(direction));
        if (state.is(this)) return WallGrimeType.Connection.GRIME;
        boolean solid = DriedStarfishBlock.canSupportAtFace(level, pos, direction);
        return solid ? WallGrimeType.Connection.SOLID : WallGrimeType.Connection.NOTHING;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        WallGrimeType.Connection below = getConnection(level,pos,Direction.DOWN);
        WallGrimeType.Connection above =  getConnection(level,pos,Direction.UP);

        WallGrimeType grimeType = WallGrimeType.getGrimeType(above,below);

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
