package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;

public class MoldyHangersBlock extends Block implements BonemealableBlock {

    public static final BooleanProperty BERRIES = BlockStateProperties.BERRIES;
    protected static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 16, 12);

    public MoldyHangersBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.TRI_PART, TriPart.TOP)
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

        return defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,triPart);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) && state.getValue(ModBlockStateProperties.TRI_PART) != TriPart.BOTTOM && !state.getValue(BERRIES);
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
                boolean canContinueGrowing = random.nextDouble() < .875;
                TriPart part = canContinueGrowing ? TriPart.MIDDLE: TriPart.BOTTOM;
                level.setBlockAndUpdate(below,defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,part));

                if (random.nextDouble() < .5 && state.getValue(ModBlockStateProperties.TRI_PART) == TriPart.MIDDLE) {
                    level.setBlockAndUpdate(pos,state.setValue(BERRIES,true));
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (state.getValue(BERRIES)) {
            if (!level.isClientSide) {
                level.setBlockAndUpdate(pos, state.setValue(BERRIES, false));
                int berries = 1+player.getRandom().nextInt(4);
                ItemStack stack = new ItemStack(Items.SPIDER_EYE,berries);
                player.addItem(stack);
            }
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
        builder.add(ModBlockStateProperties.TRI_PART,BERRIES);
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
