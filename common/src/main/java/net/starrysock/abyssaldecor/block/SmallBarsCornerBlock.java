package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.properties.CornerDirection;
import net.starrysock.abyssaldecor.block.properties.CornerDirectionProperty;

public class SmallBarsCornerBlock extends Block {
    public static final CornerDirectionProperty CORNER = CornerDirectionProperty.INSTANCE;

    public SmallBarsCornerBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(CORNER, CornerDirection.NORTHEAST).setValue(BlockStateProperties.VERTICAL_DIRECTION, Direction.DOWN));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return super.getShape(state, level, pos, context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CORNER,BlockStateProperties.VERTICAL_DIRECTION);
    }
}
