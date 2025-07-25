package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluids;
import net.starrysock.abyssaldecor.block.properties.HorizontalPart;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;

import javax.annotation.Nullable;

public class HorizontalDoubleBlock extends HorizontalDirectionalBlock {

    public HorizontalDoubleBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(ModBlockStateProperties.PART,HorizontalPart.BACK));
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction neighborDirection=getNeighbourDirection(state.getValue(ModBlockStateProperties.PART), state.getValue(FACING));
        if (direction == neighborDirection) {
            return neighborState.is(this) && neighborState.getValue(ModBlockStateProperties.PART) != state.getValue(ModBlockStateProperties.PART) ? state : Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }
    }

    private static Direction getNeighbourDirection(HorizontalPart part, Direction direction) {
        return part == HorizontalPart.BACK ? direction : direction.getOpposite();
    }
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        boolean b = state.getValue(ModBlockStateProperties.PART) == HorizontalPart.BACK ? blockstate.isFaceSturdy(level, blockpos, direction) : blockstate.is(this);
        return b;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        Direction stateFacing =state.getValue(FACING);
        BlockPos blockpos = pos.relative(stateFacing);
        level.setBlock(blockpos, DoublePlantBlock.copyWaterloggedFrom(level, blockpos, this.defaultBlockState().setValue(FACING,stateFacing).setValue(ModBlockStateProperties.PART, HorizontalPart.FRONT)), 3);
    }

    /**
     * Called before this block is destroyed by a player (regardless of whether the correct tool is used or not).
     * This method is called before the block has been removed.
     */
    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                preventCreativeDropFromBottomPart(level, pos, state, player);
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

    protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        HorizontalPart part = state.getValue(ModBlockStateProperties.PART);
        Direction facing = state.getValue(FACING);
        if (part == HorizontalPart.FRONT) {
            BlockPos blockpos = pos.relative(facing.getOpposite());
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(ModBlockStateProperties.PART) == HorizontalPart.BACK) {
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
        Direction direction = context.getClickedFace();
        if (!direction.getAxis().isHorizontal()) return null;
        BlockPos offset = blockpos.relative(direction);

        Level level = context.getLevel();
        return level.isInWorldBounds(offset) && level.getBlockState(offset).canBeReplaced(context) ? super.getStateForPlacement(context).setValue(FACING,direction) : null;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, ModBlockStateProperties.PART);
    }
}
