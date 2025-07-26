package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

import javax.annotation.Nullable;

public class CurtainBlock extends AbstractHorizontalBlock implements SimpleWaterloggedBlock {
    public CurtainBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        VoxelShape var10000;
        switch (state.getValue(FACING)) {
            case NORTH -> var10000 = box(0.0, 1.0, 15.0, 16.0, 16.0, 16.0);
            case EAST -> var10000 = box(0.0, 1.0, 0.0, 1.0, 16.0, 16.0);
            case WEST -> var10000 = box(15.0, 1.0, 0.0, 16.0, 16.0, 16.0);
            default -> var10000 = box(0.0, 1.0, 0.0, 16.0, 16.0, 1.0);
        }

        return var10000;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {


        BlockState blockstate1 = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        BlockPos below = pos.below();
        BlockPos above = pos.above();

        boolean lampAbove = isSameBlock(levelreader.getBlockState(above));
        boolean lampBelow = isSameBlock(levelreader.getBlockState(below));

        TriPart part = TriPart.getForPlacement(lampAbove,lampBelow);

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate1 = blockstate1.setValue(FACING, direction.getOpposite());
                if (blockstate1.canSurvive(levelreader, pos)) {
                    blockstate1 = blockstate1.setValue(BlockStateProperties.WATERLOGGED, fluidstate.getType() == Fluids.WATER);
                    return part == null ? blockstate1 : getMultiBlock().defaultBlockState()
                            .setValue(ModBlockStateProperties.TRI_PART,part).setValue(FACING,direction.getOpposite());
                }
            }
        }

        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if (direction.getAxis() == Direction.Axis.Y) {
            if (direction == Direction.UP) {
                if (isSameBlock(neighborState)) {
                    level.setBlock(pos,getMultiBlock().defaultBlockState().setValue(ModBlockStateProperties.TRI_PART, TriPart.BOTTOM)
                            .setValue(FACING,state.getValue(FACING)),3);
                }
            } else if(direction == Direction.DOWN) {//a block below this one updated
                if (isSameBlock(neighborState)) {
                    BlockPos above = pos.above();
                boolean lampAbove = isSameBlock(level.getBlockState(above));
                level.setBlock(pos,getMultiBlock().defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,
                        lampAbove ? TriPart.MIDDLE:TriPart.TOP).setValue(FACING,state.getValue(FACING)),3);
                }
            }
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }


    public boolean isSameBlock(BlockState state) {
        return state.is(this) || (state.getBlock() instanceof CurtainMultiBlock cMB && cMB.isSameBlock(state));
    }

    Block getMultiBlock() {
        if (this == AbyssalDecorBlocks.VELVET_CURTAIN.get())
        return AbyssalDecorBlocks.VELVET_CURTAIN_MULTIBLOCK.get();
        else return AbyssalDecorBlocks.WOOL_CURTAIN_MULTIBLOCK.get();
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED);
    }
}
