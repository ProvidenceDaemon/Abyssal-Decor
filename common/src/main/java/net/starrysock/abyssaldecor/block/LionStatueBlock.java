package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import org.jetbrains.annotations.Nullable;

public class LionStatueBlock extends DoubleBlock{

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public LionStatueBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        DoubleBlockHalf half = state.getValue(HALF);
        if (this == AbyssalDecorBlocks.LION_STATUE.get()) {

            if (half == DoubleBlockHalf.UPPER) {
                return switch (facing) {
                    case NORTH -> Shapes.or(box(1, 0, 2, 15, 12, 11), box(4, 2, 0, 12, 10, 2));
                    case EAST -> Shapes.or(box(5, 0, 1, 14, 12, 15), box(14, 2, 4, 16, 10, 12));
                    case WEST -> Shapes.or(box(2, 0, 1, 11, 12, 15), box(0, 2, 4, 2, 10, 12));
                    default -> Shapes.or(box(1, 0, 5, 15, 12, 14), box(4, 2, 14, 12, 10, 16));
                };
            } else return switch (facing) {
                case NORTH -> Shapes.or(box(0, 0, 0, 16, 3, 16), box(2, 3, 3, 14, 11, 16), box(2, 11, 3, 14, 16, 12));
                case EAST -> Shapes.or(box(0, 0, 0, 16, 3, 16), box(0, 3, 2, 13, 11, 14), box(4, 11, 2, 13, 16, 14));
                case WEST ->  Shapes.or(box(0, 0, 0, 16, 3, 16), box(3, 3, 2, 16, 11, 14), box(3, 11, 2, 12, 16, 14));
                default -> Shapes.or(box(0, 0, 0, 16, 3, 16), box(2, 3, 0, 14, 11, 13), box(2, 11, 4, 14, 16, 13));
            };
        } else if (this == AbyssalDecorBlocks.TELESCOPE.get()) {
            if (half == DoubleBlockHalf.UPPER) {
                return switch (facing.getAxis()) {
                    case X ->  Shapes.or(box(6, 0, 6, 10, 5, 10), box(0, 5, 6, 16, 9, 10));
                    default -> Shapes.or(box(6, 0, 6, 10, 5, 10), box(6, 5, 0, 10, 9, 16));
                };
            } else {
                return box(6, 0, 6, 10, 16,10);
            }
        }
        else if (this == AbyssalDecorBlocks.NITHING_POLE.get()) {
            if (half == DoubleBlockHalf.UPPER) {
                return switch (facing) {
                    case NORTH -> box(6, 0, 6, 10, 15, 13);
                    case EAST -> box(3, 0, 6, 10, 15, 10);
                    case WEST -> box(6, 0, 6, 13, 15, 10);
                    default -> box(6, 0, 3, 10, 15, 10);
                };
            } else {
                return switch (facing) {
                    case NORTH -> box(6, 0, 6, 10, 16, 13);
                    case EAST -> box(3, 0, 6, 10, 16, 10);
                    case WEST -> box(6, 0, 6, 13, 16, 10);
                    default -> box(6, 0, 3, 10, 16, 10);
                };
            }
        }

        return Shapes.block();
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        BlockPos blockpos = pos.above();
        level.setBlock(blockpos, DoublePlantBlock.copyWaterloggedFrom(level, blockpos,
                this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(FACING,state.getValue(FACING))), 3);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState placed = super.getStateForPlacement(context);
        if (placed != null) {
            return placed.setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
