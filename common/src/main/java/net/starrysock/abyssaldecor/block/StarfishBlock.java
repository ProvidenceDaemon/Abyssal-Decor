package net.starrysock.abyssaldecor.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class StarfishBlock extends DriedStarfishBlock {

    //there is orange, purple and pink
    //2 of each

    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color",DyeColor.class,DyeColor.ORANGE,DyeColor.PINK,DyeColor.PURPLE);

    public StarfishBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(COLOR,DyeColor.ORANGE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COLOR);
    }
}
