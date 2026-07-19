package net.starrysock.abyssaldecor.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;

public class MoldyHangersBlock extends Block implements BonemealableBlock {

    public static final BooleanProperty BERRIES = BlockStateProperties.BERRIES;
    protected static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 16, 12);

    public MoldyHangersBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.TRI_PART_NO_BOTTOM, TriPart.TOP)
                .setValue(BERRIES,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader levelreader = context.getLevel();
        BlockPos pos = context.getClickedPos();

        TriPart triPart = TriPart.MIDDLE;
        if (levelreader.getBlockState(pos.above()).is(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS)) {
            triPart = TriPart.TOP;
        }

        return defaultBlockState().setValue(ModBlockStateProperties.TRI_PART_NO_BOTTOM,triPart);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) && !state.getValue(BERRIES);
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.UP && !state.canSurvive(level, currentPos)) {
            level.scheduleTick(currentPos, this, 1);
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }


    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        } else {
            BlockPos below = pos.below();
            if (this.canGrowInto(level.getBlockState(below))) {
                TriPart part = TriPart.MIDDLE;
                level.setBlockAndUpdate(below,defaultBlockState().setValue(ModBlockStateProperties.TRI_PART_NO_BOTTOM,part));

                if (random.nextDouble() < .5 && state.getValue(ModBlockStateProperties.TRI_PART_NO_BOTTOM) == TriPart.MIDDLE) {
                    level.setBlockAndUpdate(pos,state.setValue(BERRIES,true));
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        if (state.getValue(BERRIES)) {
            if (!level.isClientSide) {
                level.setBlockAndUpdate(pos, state.setValue(BERRIES, false));
                int berries = 1+player.getRandom().nextInt(4);
                ItemStack berryStack = new ItemStack(Items.SPIDER_EYE,berries);
                Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5D, 1.01D, 0.5D).offsetRandom(level.random, 0.2F);
                ItemEntity itementity = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), berryStack);
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        TriPart part = state.getValue(ModBlockStateProperties.TRI_PART_NO_BOTTOM);
        if (stack.getItem() instanceof ShearsItem && part == TriPart.MIDDLE){
            state = AbyssalDecorBlocks.INACTIVE_MOLDY_HANGER.get().defaultBlockState();
            if (!level.isClientSide) {
                level.setBlockAndUpdate(pos,state);
            }

            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, stack);
            }
            level.playSound(player, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0F, 1.0F);

            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            stack.hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(hand));

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    private boolean canGrowInto(BlockState blockState) {
        return blockState.canBeReplaced();
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);
        return aboveState.is(this) || aboveState.is(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ModBlockStateProperties.TRI_PART_NO_BOTTOM,BERRIES);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return state.isRandomlyTicking();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return state.isRandomlyTicking();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos,state.setValue(BERRIES,true));
    }
}
