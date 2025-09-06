package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class FancyIronBarsBlock extends IronBarsBlock {
    public static final DirectionProperty VERTICAL_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;

    public FancyIronBarsBlock(Properties p_54198_) {
        super(p_54198_);
        registerDefaultState(defaultBlockState().setValue(VERTICAL_DIRECTION, Direction.DOWN));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        boolean isThisAbove = level.getBlockState(pos.above()).is(this);
        Direction direction = isThisAbove ? Direction.DOWN : Direction.UP;
        return state.setValue(VERTICAL_DIRECTION,direction);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState update = super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        if (direction == Direction.UP) {
            if (neighborState.is(this)) {
                update = update.setValue(VERTICAL_DIRECTION,Direction.DOWN);
            } else if (!neighborState.is(this)) {
                update = update.setValue(VERTICAL_DIRECTION,Direction.UP);
            }
        }
        return update;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERTICAL_DIRECTION);
    }
}
