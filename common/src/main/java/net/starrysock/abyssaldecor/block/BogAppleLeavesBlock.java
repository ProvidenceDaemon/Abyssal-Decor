package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BogAppleLeavesBlock extends CropBlock implements SimpleWaterloggedBlock {
    public BogAppleLeavesBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,true));
    }

    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return this.mayPlaceOn(level.getBlockState(blockpos), level, blockpos)&& level.getFluidState(pos).is(ModTags.Fluids.SUPPORTS_BOG_APPLES);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return tryRightClickHarvest(this,state, level, pos, player, hand);
    }

    public static InteractionResult tryRightClickHarvest(CropBlock block,BlockState state, Level level, BlockPos pos, Player player,InteractionHand hand) {
        if (block.isMaxAge(state)) {
            if (!level.isClientSide) {
                List<ItemStack> stacks = getDrops(state, (ServerLevel) level, pos, level.getBlockEntity(pos),player, player.getItemInHand(hand));
                for (ItemStack stack : stacks) {
                    player.addItem(stack);
                }
                level.setBlockAndUpdate(pos, block.defaultBlockState());
                level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.5F);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return AbyssalDecorItems.BOG_APPLE_LEAVES.get();
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state = fluidstate.is(ModTags.Fluids.SUPPORTS_BOG_APPLES) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(context) : null;
        return state;
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 1, 2);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.WATERLOGGED,AGE);
    }
}
