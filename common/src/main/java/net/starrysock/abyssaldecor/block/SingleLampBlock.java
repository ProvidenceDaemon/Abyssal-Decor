package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import org.jetbrains.annotations.Nullable;

public class SingleLampBlock extends LampBlock {
    public SingleLampBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if (direction.getAxis() == Direction.Axis.Y) {
            if (direction == Direction.UP) {
                if (isSameBlock(neighborState)) {
                    level.setBlock(pos,getMultiBlock().defaultBlockState().setValue(ModBlockStateProperties.TRI_PART, TriPart.BOTTOM),3);
                }
            } else if(direction == Direction.DOWN) {

            }
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader levelreader = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockPos below = pos.below();
        BlockPos above = pos.above();

        boolean lampAbove = isSameBlock(levelreader.getBlockState(above));
        boolean lampBelow = isSameBlock(levelreader.getBlockState(below));

        TriPart part = TriPart.getForPlacement(lampAbove,lampBelow);

        if (part == null) {
            return super.getStateForPlacement(context);
        } else {
            return getMultiBlock().defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,part);
        }
    }

    public boolean isSameBlock(BlockState state) {
        return state.is(this) || (state.getBlock() instanceof BloodLampMultiBlock bloodLampMultiBlock && bloodLampMultiBlock.isSameBlock(state));
    }

    BloodLampMultiBlock getMultiBlock() {
        return AbyssalDecorBlocks.BLOOD_LANTERN_MULTIBLOCK.get();
    }
}
