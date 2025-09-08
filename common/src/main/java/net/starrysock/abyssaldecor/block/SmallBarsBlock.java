package net.starrysock.abyssaldecor.block;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import net.starrysock.abyssaldecor.block.properties.CornerDirection;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SmallBarsBlock extends AbstractHorizontalBlock implements SimpleWaterloggedBlock {

    public static final DirectionProperty VERTICAL_FACING = BlockStateProperties.VERTICAL_DIRECTION;

    public SmallBarsBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(VERTICAL_FACING,Direction.DOWN).setValue(BlockStateProperties.WATERLOGGED,false));
    }

    static VoxelShape UPPER = box(0,8,15,16,16,16);


    static VoxelShape LOWER = box(0,0,15,16,8,16);


    public static final Map<Direction,VoxelShape> LOWER_SHAPES = Util.make(Maps.newEnumMap(Direction.class),directionVoxelShapeEnumMap -> {
        for (Direction direction : BlockStateProperties.FACING.getPossibleValues()) {
            directionVoxelShapeEnumMap.put(direction,AbyssalUtils.calculateShapes(direction,LOWER));
        }
    });

    public static final Map<Direction,VoxelShape> UPPER_SHAPES = Util.make(Maps.newEnumMap(Direction.class),directionVoxelShapeEnumMap -> {
        for (Direction direction : BlockStateProperties.FACING.getPossibleValues()) {
            directionVoxelShapeEnumMap.put(direction,AbyssalUtils.calculateShapes(direction,UPPER));
        }
    });

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return switch (state.getValue(VERTICAL_FACING)) {
            case UP -> UPPER_SHAPES.get(facing);
                 default -> LOWER_SHAPES.get(facing);
        };
    }

    public SmallBarsCornerBlock getCornerBars() {
        if (this == AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS_CORNER.get();
        } else if (this == AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS_CORNER.get();
        } else if (this == AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS_CORNER.get();
        }else if (this == AbyssalDecorBlocks.SMALL_STONE_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_STONE_BARS_CORNER.get();
        } else if (this == AbyssalDecorBlocks.SMALL_SEABRASS_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_SEABRASS_BARS_CORNER.get();
        } else if (this== AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS_CORNER.get();
        } else if (this == AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS_CORNER.get();
        } else if (this == AbyssalDecorBlocks.SMALL_DULL_IRON_BARS.get()) {
            return AbyssalDecorBlocks.SMALL_DULL_IRON_BARS_CORNER.get();
        }
        throw new RuntimeException("Corner not defined for: "+this);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return !useContext.isSecondaryUseActive() && useContext.getItemInHand().getItem() == this.asItem() && canBeCorner(state,useContext.getClickedFace()) || super.canBeReplaced(state, useContext);
    }

    static boolean canBeCorner(BlockState state, Direction clickedFace) {
        Direction direction = state.getValue(FACING);
        return CornerDirection.from(clickedFace,direction)!= null;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        Vec3 fraction = AbyssalUtils.getFraction(context.getClickLocation());

        if (blockstate.is(this)) {
            Direction existingDirection = blockstate.getValue(FACING);

            SmallBarsCornerBlock cornerBlock = getCornerBars();
            Direction clickedFace = context.getClickedFace();
            CornerDirection cornerDirection = CornerDirection.from(existingDirection,clickedFace);
            if (cornerDirection != null) {
                BlockState newState = cornerBlock.defaultBlockState().setValue(VERTICAL_FACING,fraction.y > .5 ? Direction.UP: Direction.DOWN)
                        .setValue(SmallBarsCornerBlock.CORNER,cornerDirection);
                return newState;
            }

        } else if (blockstate.isAir()) {

            BlockState blockstate1 = this.defaultBlockState();
            LevelReader levelreader = context.getLevel();
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

            for(Direction direction : context.getNearestLookingDirections()) {
                if (direction.getAxis().isHorizontal()) {
                    blockstate1 = blockstate1.setValue(FACING, direction.getOpposite()).setValue(VERTICAL_FACING,fraction.y > .5 ? Direction.UP: Direction.DOWN);
                    if (blockstate1.canSurvive(levelreader, blockpos)) {
                        return blockstate1.setValue(BlockStateProperties.WATERLOGGED, fluidstate.getType() == Fluids.WATER);
                    }
                }
            }

            return null;
        }



        return super.getStateForPlacement(context);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERTICAL_FACING,BlockStateProperties.WATERLOGGED);
    }
}
