package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import org.jetbrains.annotations.Nullable;

public class SmallBarsBlock extends AbstractHorizontalBlock {

    public static final DirectionProperty VERTICAL_FACING = BlockStateProperties.VERTICAL_DIRECTION;

    public SmallBarsBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(VERTICAL_FACING,Direction.DOWN));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return switch (state.getValue(VERTICAL_FACING)) {
            case UP -> {
                VoxelShape primary = box(0,8,15,16,16,16);
                yield AbyssalUtils.calculateShapes(facing,primary);
                } default -> {
                VoxelShape primary = box(0,0,15,16,8,16);
                yield AbyssalUtils.calculateShapes(facing,primary);
            }
        };
    }

    public SmallBarsCornerBlock getCornerBars() {
        if (this == AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_BLOOD_CORAL_CORNER_BARS.get();
        }
        return null;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        Vec3 fraction = AbyssalUtils.getFraction(context.getClickLocation());

        if (blockstate.is(this)) {
            SmallBarsCornerBlock cornerBlock = getCornerBars();
        } else if (blockstate.isAir()) {

            BlockState blockstate1 = this.defaultBlockState();
            LevelReader levelreader = context.getLevel();
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

            for(Direction direction : context.getNearestLookingDirections()) {
                if (direction.getAxis().isHorizontal()) {
                    blockstate1 = blockstate1.setValue(FACING, direction.getOpposite()).setValue(VERTICAL_FACING,fraction.y > .5 ? Direction.UP: Direction.DOWN);
                    if (blockstate1.canSurvive(levelreader, blockpos)) {
                        return blockstate1;//.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
                    }
                }
            }

            return null;
        }



        return super.getStateForPlacement(context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERTICAL_FACING);
    }
}
