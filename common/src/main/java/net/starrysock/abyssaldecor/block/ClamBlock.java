package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import org.jetbrains.annotations.Nullable;

public class ClamBlock extends AbstractHorizontalBlock implements SimpleWaterloggedBlock, BonemealableBlock {

    public final boolean hasPearl;

    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public static final VoxelShape shape_closed = box(2,0,2,14,2,14);
    public static final VoxelShape shape_open = box(2,0,2,14,6,14);

    public ClamBlock(Properties properties, boolean hasPearl) {
        super(properties);
        this.hasPearl = hasPearl;
        registerDefaultState(defaultBlockState().setValue(OPEN,false).setValue(POWERED,false)
                .setValue(BlockStateProperties.WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean open = state.getValue(OPEN);
        return open ? shape_open : shape_closed;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(interactionHand);
        boolean open = state.getValue(OPEN);
        if (!hasPearl && open && stack.is(ItemTags.SAND)) {
            return eatSand(state,level,pos,player,stack);
        } else if (open && hasPearl) {
            if (!level.isClientSide) {
                removePearl(state, level, pos, player);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            if (stack.is(Items.BONE_MEAL)) {//Item#use will be called after this
                return InteractionResult.PASS;
            }
            if (!level.isClientSide) {
                level.setBlockAndUpdate(pos, state.cycle(OPEN));
            }
            this.playSound(player, level, pos, state.getValue(OPEN));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    public static final double PEARL_CHANCE = .3;

    InteractionResult eatSand(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack) {
        if (!level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;
            serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 5, 1, 1, 1, 1);

            if (level.random.nextDouble() < PEARL_CHANCE) {
                placePearl(state,serverLevel,pos);
                level.playSound(null, pos, SoundEvents.PLAYER_BURP, SoundSource.BLOCKS, 1.0F, 1.2F);
            } else {
                level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 1.0F, 1.2F);
            }

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    void removePearl(BlockState state,Level level,BlockPos pos,Player player) {
        level.setBlockAndUpdate(pos, AbyssalDecorBlocks.CLAM.get().defaultBlockState()
                .setValue(FACING,state.getValue(FACING))
                        .setValue(POWERED,state.getValue(POWERED))
                        .setValue(OPEN,state.getValue(OPEN))
                .setValue(BlockStateProperties.WATERLOGGED,state.getValue(BlockStateProperties.WATERLOGGED))
        );
        if (player!= null) {
            player.addItem(AbyssalDecorItems.WHITE_PEARL.get().getDefaultInstance());
        } else {
            popResource(level,pos,AbyssalDecorItems.WHITE_PEARL.get().getDefaultInstance());
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) && state.getValue(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSupportCenter(level, pos.below(), Direction.UP);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        placePearl(state,level,pos);
    }

    void placePearl(BlockState state,ServerLevel level,BlockPos pos) {
        level.setBlockAndUpdate(pos, AbyssalDecorBlocks.CLAM_WITH_PEARL.get().defaultBlockState()
                .setValue(FACING,state.getValue(FACING))
                        .setValue(OPEN,state.getValue(OPEN))
                .setValue(BlockStateProperties.WATERLOGGED,state.getValue(BlockStateProperties.WATERLOGGED))
        );
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPEN,BlockStateProperties.WATERLOGGED,POWERED);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return hasPearl ? 15 : 0;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean isPowered = level.hasNeighborSignal(pos);

            if (hasPearl) {
                state = state.setValue(POWERED, isPowered);
                if (isPowered) {
                    if (state.getValue(OPEN)) {
                        removePearl(state, level, pos, null);
                    } else  {
                        level.setBlockAndUpdate(pos,state.setValue(OPEN,true));
                        this.playSound(null, level, pos, true);
                    }
                }
            } else if (isPowered != state.getValue(POWERED)) {
                if (state.getValue(OPEN) != isPowered) {
                    state = state.setValue(OPEN, isPowered);
                    this.playSound(null, level, pos, isPowered);
                }
                level.setBlock(pos, state.setValue(POWERED, isPowered), 2);
                if (state.getValue(BlockStateProperties.WATERLOGGED)) {
                    level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
                }
            }
        }
    }

    protected void playSound(@Nullable Player player, Level level, BlockPos pos, boolean isOpened) {
        level.playSound(player, pos,SoundEvents.WOOD_HIT, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
        level.gameEvent(player, isOpened ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        popResource(level, pos, new ItemStack(this));
    }
}
