package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DanglingWebBlock extends HangingDoubleBlock{
    public DanglingWebBlock(Properties properties) {
        super(properties);
    }

    public static final VoxelShape TOP = Shapes.or(box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0), box(1.0, 14.0, 1.0, 15.0, 16.0, 15.0));
    public static final VoxelShape BOTTOM = box(4.0, 6.0, 4.0, 12.0, 16.0, 12.0);

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(HALF)) {
            case LOWER -> BOTTOM;
            case UPPER -> TOP;
        };
    }
}
