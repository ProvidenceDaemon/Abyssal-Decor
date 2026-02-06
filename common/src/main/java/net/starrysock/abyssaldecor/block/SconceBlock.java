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
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import org.jetbrains.annotations.Nullable;

public class SconceBlock extends AbstractHorizontalBlock {

    public static final DirectionProperty VERTICAL_FACING = BlockStateProperties.VERTICAL_DIRECTION;

    public SconceBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(VERTICAL_FACING, Direction.DOWN));
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        return switch (state.getValue(VERTICAL_FACING)) {
            case UP -> switch (state.getValue(FACING)) {
                case NORTH -> box(6.0, 0.0, 6.0, 10.0, 14.0, 16.0);
                case EAST -> box(0.0, 0.0, 6.0, 10.0, 14.0, 10.0);
                case WEST -> box(6.0, 0.0, 6.0, 16.0, 14.0, 10.0);
                default -> box(6.0, 0.0, 0.0, 10.0, 14.0, 10.0);
            };
            default -> switch (state.getValue(FACING)) {
                case NORTH -> box(6.0, 2.0, 6.0, 10.0, 16.0, 16.0);
                case EAST -> box(0.0, 2.0, 6.0, 10.0, 16.0, 10.0);
                case WEST -> box(6.0, 2.0, 6.0, 16.0, 16.0, 10.0);
                default -> box(6.0, 2.0, 0.0, 10.0, 16.0, 10.0);
            };
        };
    }

    public static final VoxelShape SUPPORT_SHAPE = box(4, 0, 4, 12, 16, 12);

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return SUPPORT_SHAPE;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERTICAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();

        Vec3 fraction = AbyssalUtils.getFraction(context.getClickLocation());
        Direction[] adirection = context.getNearestLookingDirections();

        for (Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.setValue(FACING, direction1).setValue(VERTICAL_FACING,fraction.y < .5 ? Direction.UP : Direction.DOWN);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }
        return null;
    }
}
