package net.starrysock.abyssaldecor.block;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MixedBlock extends Block {

    public static final IntegerProperty VARIANT = IntegerProperty.create("variant",0,3);

    public MixedBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(VARIANT,0));
    }

    protected Random random = new Random(1);

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState placed = super.getStateForPlacement(context);
        if (placed == null) return null;
        List<BlockState> possibleStates = new ArrayList<>(getStateDefinition().getPossibleStates());
        Collections.shuffle(possibleStates,random);
        return possibleStates.get(0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VARIANT);
    }
}
