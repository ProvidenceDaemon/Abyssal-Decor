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
import net.starrysock.abyssaldecor.block.properties.VerticalConnection;

import javax.annotation.Nullable;

public class CurtainBlock extends AbstractHorizontalBlock implements SimpleWaterloggedBlock {
    public CurtainBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,false)
                .setValue(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.SOLO));
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

        boolean lampAbove = levelreader.getBlockState(above).is(this);
        boolean lampBelow = levelreader.getBlockState(below).is(this);

        VerticalConnection part = VerticalConnection.getForPlacement(lampAbove,lampBelow);

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate1 = blockstate1.setValue(FACING, direction.getOpposite());
                if (blockstate1.canSurvive(levelreader, pos)) {

                    if (lampAbove) {
                        Direction aboveDirection = levelreader.getBlockState(above).getValue(FACING);
                        if (aboveDirection != direction.getOpposite()) {//can only be top or solo
                            if (part == VerticalConnection.MIDDLE) {
                                part = VerticalConnection.TOP;
                            }
                            if (part == VerticalConnection.BOTTOM) {
                                part = VerticalConnection.SOLO;
                            }
                        }
                    }

                    if (lampBelow) {
                        Direction belowDirection = levelreader.getBlockState(below).getValue(FACING);
                        if (belowDirection != direction.getOpposite()) {//can only be bottom or solo
                            if (part == VerticalConnection.MIDDLE) {
                                part = VerticalConnection.BOTTOM;
                            }
                            if (part == VerticalConnection.TOP) {
                                part = VerticalConnection.SOLO;
                            }
                        }
                    }


                    blockstate1 = blockstate1.setValue(BlockStateProperties.WATERLOGGED, fluidstate.getType() == Fluids.WATER);
                    return part == null ? blockstate1 : defaultBlockState()
                            .setValue(ModBlockStateProperties.VERTICAL_CONNECTION,part).setValue(FACING,direction.getOpposite());
                }
            }
        }

        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() == Direction.Axis.Y) {

            BlockPos below = pos.below();
            BlockPos above = pos.above();

            Direction facing = state.getValue(FACING);

            boolean lampAbove = check(level.getBlockState(above),facing);

            boolean lampBelow = check(level.getBlockState(below),facing);

            VerticalConnection triPart = VerticalConnection.getForPlacement(lampAbove,lampBelow);

            level.setBlock(pos,defaultBlockState().setValue(ModBlockStateProperties.VERTICAL_CONNECTION, triPart)
                    .setValue(FACING,state.getValue(FACING)),3);
        }
        return state;
    }

    boolean check(BlockState otherState,Direction direction) {
        return otherState.is(this) && otherState.getValue(FACING) == direction;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED,ModBlockStateProperties.VERTICAL_CONNECTION);
    }
}
