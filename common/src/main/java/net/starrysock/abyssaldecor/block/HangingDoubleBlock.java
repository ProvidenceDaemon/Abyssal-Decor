package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class HangingDoubleBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public HangingDoubleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.UPPER));
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.makeStuckInBlock(state, new Vec3(0.25D, 0.05F, 0.25D));
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.UPPER != (facing == Direction.DOWN) ||
                facingState.is(this) && facingState.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.UPPER && facing == Direction.UP && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = level.getBlockState(blockpos);
        boolean b = state.getValue(HALF) == DoubleBlockHalf.UPPER ? DriedStarfishBlock.canSupportAtFace(level, pos, Direction.UP) : blockstate.is(this);
        return b;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        BlockPos blockpos = pos.below();
        level.setBlock(blockpos, DoublePlantBlock.copyWaterloggedFrom(level, blockpos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER)), 3);
    }

    /**
     * Called before this block is destroyed by a player (regardless of whether the correct tool is used or not).
     * This method is called before the block has been removed.
     */
    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                preventCreativeDropFromTopPart(level, pos, state, player);
            } else {
                dropResources(state, level, pos, null, player, player.getMainHandItem());
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    /**
     * Called after a player has successfully harvested this block. This method will only be called if the player has used the correct tool and drops should be spawned.
     */
    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
    }

    /**
     * @see DoublePlantBlock#preventCreativeDropFromBottomPart(Level, BlockPos, BlockState, Player)
     * @param level
     * @param pos
     * @param state
     * @param player
     */
    protected static void preventCreativeDropFromTopPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.LOWER) {
            BlockPos blockpos = pos.above();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER) {
                BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState state = blockpos.getY() > level.getMinBuildHeight() + 1 && level.getBlockState(blockpos.below()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
        return state;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HALF);
    }
}
