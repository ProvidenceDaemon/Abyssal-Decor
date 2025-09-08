package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlazeLampBlock extends AbstractHorizontalBlock {

    public BlazeLampBlock(Properties properties) {
        super(properties);
    }

    @Override

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(box(6.5, 5.0, 10.5, 9.5, 16.0, 13.5), box(6.0, 3.0, 15.0, 10.0, 13.0, 16.0));
            case EAST -> Shapes.or(box(2.5, 5.0, 6.5, 5.5, 16.0, 9.5), box(0.0, 3.0, 6.0, 1.0, 13.0, 10.0));
            case WEST -> Shapes.or(box(10.5, 5.0, 6.5, 13.5, 16.0, 9.5), box(15.0, 3.0, 6.0, 16.0, 13.0, 10.0));
            default -> Shapes.or(box(6.5, 5.0, 2.5, 9.5, 16.0, 5.5), box(6.0, 3.0, 0.0, 10.0, 13.0, 1.0));
        };

    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING).getOpposite();
        return DriedStarfishBlock.canSupportAtFace(level, pos, direction);
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction[] adirection = context.getNearestLookingDirections();

        for (Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.setValue(FACING, direction1);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }
        return null;
    }
}
