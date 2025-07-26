package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PearlBlock extends WaterloggedDirectionalBlock {
    public PearlBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        VoxelShape var10000;
        switch (state.getValue(FACING)) {
            case NORTH -> var10000 = box(5, 5, 10, 11, 11, 16);
            case EAST -> var10000 = box(0, 5, 5, 6, 11, 11);
            case WEST -> var10000 = box(10, 5, 5, 16, 11, 11);
            case UP -> var10000 = box(5, 0, 5, 11, 6, 11);
            case DOWN -> var10000 = box(5, 10, 5, 11, 16, 11);
            default -> var10000 = box(5, 5, 0, 11, 11, 6);
        }

        return var10000;
    }


}
