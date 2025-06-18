package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import org.jetbrains.annotations.Nullable;

public class IronSconceBlock extends AbstractHorizontalBlock{

    public static final DirectionProperty VERTICAL_FACING = BlockStateProperties.VERTICAL_DIRECTION;

    public IronSconceBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(VERTICAL_FACING,Direction.DOWN));
    }



    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape primary = box(6,0,13,10,16,16);
        Direction facing = state.getValue(FACING);

        VoxelShape anchor = state.getValue(VERTICAL_FACING) == Direction.UP ?  box(4,0,4,12,1,12) :
                box(4,15,4,12,16,12);

        return AbyssalUtils.calculateShapes(facing, Shapes.or(primary,anchor));
    }

    public static final VoxelShape SUPPORT_SHAPE = box(4,0,4,12,16,12);

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return SUPPORT_SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, direction);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERTICAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();

        Vec3 fraction =  AbyssalUtils.getFraction(context.getClickLocation());

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.setValue(FACING, direction).setValue(VERTICAL_FACING,fraction.y > .5 ? Direction.UP: Direction.DOWN);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }
        return null;
    }
}
