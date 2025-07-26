package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.ModTags;

import javax.annotation.Nullable;

public class MoldyStalkBlock extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public MoldyStalkBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,TriPart.BOTTOM).setValue(ACTIVE,true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ModBlockStateProperties.TRI_PART,ACTIVE);
    }

    public static final VoxelShape SHAPE =  box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) && state.getValue(ModBlockStateProperties.TRI_PART) == TriPart.TOP && state.getValue(ACTIVE);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        if (!fluidstate.isEmpty()) {
            return null;
        } else {
            BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().below());
            if (blockstate.is(ModTags.Blocks.MOLDY_STALK_PLANTABLE_ON)) {
                if (blockstate.is(AbyssalDecorBlocks.MOLDY_STALK_SPROUT.get())) {
                    return this.defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,TriPart.TOP);
                } else if (blockstate.is(this)) {
                  //  int i = blockstate.getValue(AGE) > 0 ? 1 : 0;
                    return this.defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,TriPart.TOP);
                } else {
                    BlockState blockstate1 = context.getLevel().getBlockState(context.getClickedPos().above());
                    return blockstate1.is(this) ? this.defaultBlockState() : AbyssalDecorBlocks.MOLDY_STALK_SPROUT.get().defaultBlockState();
                }
            } else {
                return null;
            }
        }
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModTags.Blocks.MOLDY_STALK_PLANTABLE_ON);
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        if (direction == Direction.UP) {
            if (neighborState.is(this)) {
                TriPart part = (level.getBlockState(pos.below()).is(this) ? TriPart.MIDDLE : TriPart.BOTTOM);
                level.setBlock(pos, state.setValue(ModBlockStateProperties.TRI_PART, part), 2);
            } else {
                level.setBlock(pos, state.setValue(ModBlockStateProperties.TRI_PART, TriPart.TOP), 2);
            }
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        } else {
            if (level.getBlockState(pos.above()).canBeReplaced()) {
                boolean canKeepGrowing = random.nextDouble() < 0.6875;
                level.setBlockAndUpdate(pos.above(), state.setValue(ModBlockStateProperties.TRI_PART, TriPart.TOP).setValue(ACTIVE, canKeepGrowing));
            }
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}
