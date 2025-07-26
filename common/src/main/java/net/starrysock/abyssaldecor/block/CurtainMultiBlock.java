package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;

public class CurtainMultiBlock extends CurtainBlock {
    public final Block soloBlock;

    public CurtainMultiBlock(Properties properties, Block soloBlock) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,TriPart.TOP));
        this.soloBlock = soloBlock;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() == Direction.Axis.Y) {

            BlockPos below = pos.below();
            BlockPos above = pos.above();

            boolean lampAbove = isSameBlock(level.getBlockState(above));
            boolean lampBelow = isSameBlock(level.getBlockState(below));

            TriPart triPart = TriPart.getForPlacement(lampAbove,lampBelow);

            if (triPart == null) {
                return soloBlock.defaultBlockState().setValue(FACING,state.getValue(FACING));
            }

            level.setBlock(pos,defaultBlockState().setValue(ModBlockStateProperties.TRI_PART, triPart)
                    .setValue(FACING,state.getValue(FACING)),3);
        }
        return state;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return soloBlock.getCloneItemStack(level, pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ModBlockStateProperties.TRI_PART);
    }

    public boolean isSameBlock(BlockState other) {
        return other.is(this) || other.is(soloBlock);
    }
}
