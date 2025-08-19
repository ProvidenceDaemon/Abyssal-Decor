package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DriedStarfishBlock extends AbstractDirectionalBlock implements SimpleWaterloggedBlock {

    public static final IntegerProperty COUNT = IntegerProperty.create("count", 1, 2);

    public DriedStarfishBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(COUNT, 1).setValue(FACING, Direction.UP)
                .setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        VoxelShape var10000;
        switch (state.getValue(FACING)) {
            case NORTH -> var10000 = box(1.0, 1.0, 15.0, 15.0, 15.0, 16.0);
            case EAST -> var10000 = box(0.0, 1.0, 1.0, 1.0, 15.0, 15.0);
            case WEST -> var10000 = box(15.0, 1.0, 1.0, 16.0, 15.0, 15.0);
            case UP -> var10000 = box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);
            case DOWN -> var10000 = box(1.0, 15.0, 1.0, 15.0, 16.0, 15.0);
            default -> var10000 = box(1.0, 1.0, 0.0, 15.0, 15.0, 1.0);
        }

        return var10000;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected Random random = new Random(1);

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState placed = super.getStateForPlacement(context);
        if (placed == null) return null;
        List<BlockState> possibleStates = new ArrayList<>(getStateDefinition().getPossibleStates());
        Collections.shuffle(possibleStates, random);
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state = possibleStates.get(0).setValue(FACING, placed.getValue(FACING))
                .setValue(BlockStateProperties.WATERLOGGED, fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8);
        return state;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING).getOpposite();
        return canSupportAtFace(level, pos, direction);
    }

    public static boolean canSupportAtFace(BlockGetter level, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction);
        return VineBlock.isAcceptableNeighbour(level, blockpos, direction);
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COUNT, BlockStateProperties.WATERLOGGED);
    }
}
