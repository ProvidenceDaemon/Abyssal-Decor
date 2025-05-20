package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BeamBlock extends FaceAttachedBlock {
    public BeamBlock(Properties properties) {
        super(properties);
    }

    //todo cache these

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction value = state.getValue(FACING);
        return switch (value) {
            case NORTH -> {
                yield switch (state.getValue(FACE)) {
                    case FLOOR, CEILING -> Shapes.or(box(0, 0, 14, 16, 16, 16), box(7, 0, 2, 9, 16, 14), box(0, 0, 0, 16, 16, 2));
                    case WALL -> Shapes.or(box(0, 14, 0, 16, 16, 16), box(7, 2, 0, 9, 14, 16), box(0, 0, 0, 16, 2, 16));
                };
            }
            case EAST -> {
                yield switch (state.getValue(FACE)) {
                    case FLOOR, CEILING ->  Shapes.or(box(0, 0, 0, 2, 16, 16), box(2, 0, 7, 14, 16, 9), box(14, 0, 0, 16, 16, 16));
                    case WALL -> Shapes.or(box(0, 14, 0, 16, 16, 16), box(0, 2, 7, 16, 14, 9), box(0, 0, 0, 16, 2, 16));
                };
            }
            case WEST -> {
                yield switch (state.getValue(FACE)) {
                    case FLOOR, CEILING ->  Shapes.or(box(14, 0, 0, 16, 16, 16), box(2, 0, 7, 14, 16, 9), box(0, 0, 0, 2, 16, 16));
                    case WALL -> Shapes.or(box(0, 14, 0, 16, 16, 16), box(0, 2, 7, 16, 14, 9), box(0, 0, 0, 16, 2, 16));
                };
            }
            case SOUTH -> {
                yield switch (state.getValue(FACE)) {
                    case FLOOR, CEILING -> Shapes.or(box(0, 0, 0, 16, 16, 2), box(7, 0, 2, 9, 16, 14), box(0, 0, 14, 16, 16, 16));
                    case WALL -> Shapes.or(box(0, 14, 0, 16, 16, 16), box(7, 2, 0, 9, 14, 16), box(0, 0, 0, 16, 2, 16));
                };
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}
