package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.starrysock.abyssaldecor.registry.AbyssalDecorSounds;

import static net.minecraft.world.level.block.RedstoneLampBlock.LIT;

public class ToggleableDirectionalLampBlock extends DirectionalLampBlock {

    public ToggleableDirectionalLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        return toggleLamp(blockState,level,blockPos);
    }

    public static InteractionResult toggleLamp(BlockState state,Level level,BlockPos pos) {
        if (!level.isClientSide) {
            level.setBlockAndUpdate(pos, state.cycle(LIT));
        }
        level.playLocalSound(pos, state.getValue(LIT) ? AbyssalDecorSounds.LAMP_OFF.get() :
                AbyssalDecorSounds.LAMP_ON.get(), SoundSource.BLOCKS, 1, 1, false);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
