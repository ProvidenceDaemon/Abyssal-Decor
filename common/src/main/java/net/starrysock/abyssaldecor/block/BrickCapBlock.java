package net.starrysock.abyssaldecor.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BrickCapBlock extends RotatedPillarBlock {
    public static final BooleanProperty FLIPPED = BooleanProperty.create("flipped");
    public BrickCapBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FLIPPED,false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState stateForPlacement = super.getStateForPlacement(context);

        if (context.getClickedFace().getAxisDirection() == Direction.AxisDirection.NEGATIVE) {
            stateForPlacement = stateForPlacement.setValue(FLIPPED,true);
        }

        return stateForPlacement;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FLIPPED);
    }
}
