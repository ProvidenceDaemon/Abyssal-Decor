package net.starrysock.abyssaldecor.content.lamps;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import net.starrysock.abyssaldecor.content.abstraction.IAdditionalDirection;
import net.starrysock.abyssaldecor.content.abstraction.lamps.WallMountedInteractibleLampBlock;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;

public class TubeLampBlock extends WallMountedInteractibleLampBlock implements IAdditionalDirection {

    protected final EnumMap<Direction,VoxelShape> shapes = new EnumMap<>(Direction.class);

    public TubeLampBlock(Properties properties) {
        super(properties);

        for (Direction direction : HORIZONTAL_FACING.getPossibleValues()) {
            shapes.put(direction, AbyssalUtils.calculateShapes(direction, box(0,0,14,16,2,16)));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UP);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.get(state.getValue(HORIZONTAL_FACING));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        boolean shouldBeTop = blockPlaceContext.getClickedFace() == Direction.DOWN || (blockPlaceContext.getClickedFace().getAxis().isHorizontal() && (blockPlaceContext.getClickLocation().y - blockPlaceContext.getClickedPos().getY()) >= 0.5);
        return super.getStateForPlacement(blockPlaceContext).setValue(UP, shouldBeTop);
    }
}
