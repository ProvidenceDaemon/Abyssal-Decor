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
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.VerticalConnection;

import java.util.HashMap;
import java.util.Map;

public class FancierIronBarsBlock extends IronBarsBlock {

    public static final Map<Block,Block> ORNATE = new HashMap<>();

    public FancierIronBarsBlock(Properties p_54198_) {
        super(p_54198_);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.SOLO));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockPos below = pos.below();
        BlockPos above = pos.above();

        boolean lampAbove = verticalConnection(level.getBlockState(above));
        boolean lampBelow = verticalConnection(level.getBlockState(below));

        VerticalConnection part = VerticalConnection.getForPlacement(lampAbove,lampBelow);
        return super.getStateForPlacement(context).setValue(ModBlockStateProperties.VERTICAL_CONNECTION,part);
    }

    boolean verticalConnection(BlockState state){
        return state.is(this) || state.is(ORNATE.get(this));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState update =super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        if (facing.getAxis() == Direction.Axis.Y) {

            BlockPos below = currentPos.below();
            BlockPos above = currentPos.above();

            boolean lampAbove = verticalConnection(level.getBlockState(above));
            boolean lampBelow = verticalConnection(level.getBlockState(below));

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
