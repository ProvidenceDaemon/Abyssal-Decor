package net.starrysock.abyssaldecor.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;

public class PostBlock extends Block implements SimpleWaterloggedBlock {
    public PostBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.TRI_PART, TriPart.BOTTOM)
                .setValue(BlockStateProperties.WATERLOGGED,false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ModBlockStateProperties.TRI_PART,BlockStateProperties.WATERLOGGED);
    }
}
