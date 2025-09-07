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
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.VerticalConnection;

public class FrostedGlassBlock extends GlassBlock {

    public FrostedGlassBlock(Properties p_53640_) {
        super(p_53640_);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.SOLO));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockPos below = pos.below();
        BlockPos above = pos.above();

        boolean lampAbove = level.getBlockState(above).is(this);
        boolean lampBelow = level.getBlockState(below).is(this);

        VerticalConnection part = VerticalConnection.getForPlacement(lampAbove,lampBelow);
        return super.getStateForPlacement(context).setValue(ModBlockStateProperties.VERTICAL_CONNECTION,part);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState update =super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        if (facing.getAxis() == Direction.Axis.Y) {

            BlockPos below = currentPos.below();
            BlockPos above = currentPos.above();

            boolean lampAbove = level.getBlockState(above).is(this);
            boolean lampBelow = level.getBlockState(below).is(this);

            VerticalConnection triPart = VerticalConnection.getForPlacement(lampAbove,lampBelow);

            update = update.setValue(ModBlockStateProperties.VERTICAL_CONNECTION, triPart);
        }
        return update;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ModBlockStateProperties.VERTICAL_CONNECTION);
    }
}
