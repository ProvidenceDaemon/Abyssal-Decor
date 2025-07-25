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

import javax.annotation.Nullable;

public class PostBlock extends Block implements SimpleWaterloggedBlock {
    public PostBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.TRI_PART, TriPart.BOTTOM)
                .setValue(BlockStateProperties.WATERLOGGED,false));
    }

    public static final VoxelShape SHAPE = box(5,0,5,11,16,11);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader levelreader = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockPos below = pos.below();
        BlockPos above = pos.above();

        boolean lampAbove = levelreader.getBlockState(above).is(this);
        boolean lampBelow = levelreader.getBlockState(below).is(this);

        TriPart part = TriPart.getForPlacement(lampAbove,lampBelow);

        if (part == null) {
            part= TriPart.BOTTOM;
        }
        return defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,part);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() == Direction.Axis.Y) {

            BlockPos below = pos.below();
            BlockPos above = pos.above();

            boolean lampAbove = level.getBlockState(above).is(this);
            boolean lampBelow = level.getBlockState(below).is(this);

            TriPart triPart = TriPart.getForPlacement(lampAbove,lampBelow);

            if (triPart == null) {
                triPart = TriPart.BOTTOM;
            }

            level.setBlock(pos,defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,state.getValue(BlockStateProperties.WATERLOGGED))
                    .setValue(ModBlockStateProperties.TRI_PART, triPart),3);
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ModBlockStateProperties.TRI_PART,BlockStateProperties.WATERLOGGED);
    }
}
