package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;

public class StackedIronBallsBlock extends FallingBlock implements SimpleWaterloggedBlock {

    protected static final VoxelShape ONE_AABB = Block.box(5, 0, 5, 11, 6, 11);
    protected static final VoxelShape TWO_AABB = Block.box(1, 0, 5, 15, 6, 11);
    protected static final VoxelShape THREE_AABB = Block.box(1, 0, 1, 15, 6, 15);
    protected static final VoxelShape FOUR_AABB = Block.box(1, 0, 1, 15, 12, 15);

    public static final IntegerProperty BALLS = IntegerProperty.create("balls", 1, 4);
    public StackedIronBallsBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BALLS,1).setValue(BlockStateProperties.WATERLOGGED,false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BALLS,BlockStateProperties.WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(BALLS, Math.min(4, blockstate.getValue(BALLS) + 1));
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(context).setValue(BlockStateProperties.WATERLOGGED, flag);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(BALLS)) {
            default -> ONE_AABB;
            case 2 -> TWO_AABB;
            case 3 -> THREE_AABB;
            case 4 -> FOUR_AABB;
        };
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return !useContext.isSecondaryUseActive() && useContext.getItemInHand().is(AbyssalDecorItems.IRON_BALL.get()) && state.getValue(BALLS) < 4 ||
                super.canBeReplaced(state, useContext);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

}
