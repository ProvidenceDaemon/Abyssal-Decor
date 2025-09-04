package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class LaventineGlassBlock extends GlassBlock {

    public static final DirectionProperty VERTICAL_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;

    public LaventineGlassBlock(Properties p_53640_) {
        super(p_53640_);
        registerDefaultState(defaultBlockState().setValue(VERTICAL_DIRECTION, Direction.DOWN));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        boolean isThisAbove = level.getBlockState(pos).is(this);
        Direction direction = isThisAbove ? Direction.DOWN : Direction.UP;
        return defaultBlockState().setValue(VERTICAL_DIRECTION,direction);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP) {
            if (neighborState.is(this)) {
                state = state.setValue(VERTICAL_DIRECTION,Direction.DOWN);
            } else if (!neighborState.is(this)) {
                state = state.setValue(VERTICAL_DIRECTION,Direction.UP);
            }
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERTICAL_DIRECTION);
    }
}
