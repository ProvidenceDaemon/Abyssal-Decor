package net.starrysock.abyssaldecor.block.lamp;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.AbstractDirectionalBlock;

public class DirectionalLampBlock extends AbstractDirectionalBlock implements SimpleWaterloggedBlock {

    public DirectionalLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case DOWN -> {
                return box(5,10,5,11,16,11);
            }
            case UP -> {
                return box(5,0,5,11,6,11);
            }
            case NORTH -> {
                return box(5,5,10,11,11,16);
            }
            case SOUTH -> {
                return box(5,5,0,11,11,6);
            }
            case WEST -> {
                return box(10,5,5,16,11,11);
            }
            case EAST -> {
                return box(0,5,5,6,11,11);
            }
        }

        return super.getShape(state, level, pos, context);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
