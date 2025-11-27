package net.starrysock.abyssaldecor.block.lamp;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class VerticalTubeLamp extends HorizontalLampBlock {
    public VerticalTubeLamp(Properties properties, VoxelShape shape) {
        super(properties, shape);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return AbyssalDecorBlocks.TUBE_LAMP.get().getCloneItemStack(level, pos, state);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.isCrouching()) {
            level.setBlockAndUpdate(blockPos, AbyssalDecorBlocks.TUBE_LAMP.get().defaultBlockState()
                    .setValue(RedstoneLampBlock.LIT,blockState.getValue(RedstoneLampBlock.LIT)).setValue(FACING,blockState.getValue(FACING).getOpposite()));
            level.playSound(null,blockPos, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundSource.BLOCKS,1,1);
        } else {
            return TubeLampBlock.toggleTubeLamp(blockState,level,blockPos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
