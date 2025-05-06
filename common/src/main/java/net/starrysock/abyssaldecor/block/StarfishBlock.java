package net.starrysock.abyssaldecor.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class StarfishBlock extends Block implements SimpleWaterloggedBlock {

    //there is orange, purple and pink
    //2 of each

    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color",DyeColor.class,DyeColor.ORANGE,DyeColor.PINK,DyeColor.PURPLE);
    public static final IntegerProperty COUNT = IntegerProperty.create("count",1,2);

    public StarfishBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(COLOR,DyeColor.ORANGE).setValue(COUNT,1));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COLOR,COUNT);
    }
}
