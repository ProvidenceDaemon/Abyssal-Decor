package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.lamp.ToggleableDirectionalLampBlock;

public class FrostedLampBlock extends ToggleableDirectionalLampBlock {
    public FrostedLampBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        VoxelShape var10000;
        switch (state.getValue(FACING)) {
            case NORTH -> var10000 = Shapes.or(box(2.0, 2.0, 5.0, 14.0, 14.0, 11.0), box(5.0, 5.0, 11.0, 11.0, 11.0, 16.0));
            case EAST -> var10000 = Shapes.or(box(5.0, 2.0, 2.0, 11.0, 14.0, 14.0), box(0.0, 5.0, 5.0, 5.0, 11.0, 11.0));
            case WEST -> var10000 = Shapes.or(box(5.0, 2.0, 2.0, 11.0, 14.0, 14.0), box(11.0, 5.0, 5.0, 16.0, 11.0, 11.0));
            case UP -> var10000 = Shapes.or(box(2.0, 5.0, 2.0, 14.0, 11.0, 14.0), box(5.0, 0.0, 5.0, 11.0, 5.0, 11.0));
            case DOWN -> var10000 = Shapes.or(box(2.0, 5.0, 2.0, 14.0, 11.0, 14.0), box(5.0, 11.0, 5.0, 11.0, 16.0, 11.0));
            default -> var10000 = Shapes.or(box(2.0, 2.0, 5.0, 14.0, 14.0, 11.0), box(5.0, 5.0, 0.0, 11.0, 11.0, 5.0));
        }

        return var10000;
    }
}
