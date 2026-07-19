package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

import java.util.List;

public class TallAmaranthBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;

    public TallAmaranthBlock(Properties properties) {
        super(properties);
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return blockState.getValue(AGE) < 1 && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 1 && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        grow(state, level, pos, random);
    }

    protected void grow(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.canSurvive(level,pos)) {
            DoublePlantBlock.placeAt(level, state.setValue(AGE,1), pos, 2);
        }
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return tryRightClickHarvest(state, level, pos, player, hand);
    }

    public InteractionResult tryRightClickHarvest(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand) {
        if (isMaxAge(state)) {
            DoubleBlockHalf half = state.getValue(HALF);
            BlockPos pickPos = half == DoubleBlockHalf.LOWER ? pos : pos.below();
            if (!level.isClientSide) {
                List<ItemStack> stacks = getDrops(state, (ServerLevel) level, pickPos, level.getBlockEntity(pickPos),player, player.getItemInHand(hand));
                for (ItemStack stack : stacks) {
                    Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5D, 1.01D, 0.5D).offsetRandom(level.random, 0.2F);
                    ItemEntity itementity = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), stack);
                    itementity.setDefaultPickUpDelay();
                    level.addFreshEntity(itementity);
                }
                level.setBlockAndUpdate(pickPos, AbyssalDecorBlocks.AMARANTH.get().defaultBlockState());
                level.playSound(null, pickPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.5F);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    boolean isMaxAge(BlockState state){
        return state.getValue(AGE) == 1;
    }

    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        //todo popResource(level, blockPos, new ItemStack(this));
        grow(blockState,level,blockPos,randomSource);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        super.createBlockStateDefinition($$0);
        $$0.add(AGE);
    }
}
