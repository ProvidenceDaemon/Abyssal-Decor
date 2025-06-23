package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.BarrierRibbonBlock;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class BarrierPoleBlock extends DoubleBlock {

    public BarrierPoleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(HALF)) {
            case LOWER -> box(5,0,5,11,16,11);
            case UPPER -> box(5,0,5,11,8,11);
        };
    }

    public BarrierRibbonBlock getRibbon() {
        if (this == AbyssalDecorBlocks.VELVET_BARRIER.get()) {
            return AbyssalDecorBlocks.VELVET_BARRIER_RIBBON.get();
        } else if (this == AbyssalDecorBlocks.IRON_BARRIER.get()) {
            return AbyssalDecorBlocks.IRON_BARRIER_RIBBON.get();
        } else if (this ==  AbyssalDecorBlocks.ROPE_BARRIER.get()) {
            return AbyssalDecorBlocks.ROPE_BARRIER_RIBBON.get();
        } else if (this == AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get()) {
            return AbyssalDecorBlocks.BARBED_WIRE_RIBBON.get();
        }
        return null;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        for (Direction direction : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()) {
            BlockPos check = pos.relative(direction,2);
            BlockState other = level.getBlockState(check);
            if (other.getBlock() == this && state.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockPos inbetween1 = pos.relative(direction);
                BlockPos inbetween2 = inbetween1.above();
                if (level.getBlockState(inbetween1).canBeReplaced() && level.getBlockState(inbetween2).canBeReplaced()) {
                    BarrierRibbonBlock ribbonBlock = getRibbon();
                    level.setBlockAndUpdate(inbetween2,ribbonBlock.defaultBlockState().setValue(BarrierRibbonBlock.FACING,direction));
                }
            }
        }
    }
}
