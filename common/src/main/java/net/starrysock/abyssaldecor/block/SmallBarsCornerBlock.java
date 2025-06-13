package net.starrysock.abyssaldecor.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.starrysock.abyssaldecor.block.properties.CornerDirection;
import net.starrysock.abyssaldecor.block.properties.CornerDirectionProperty;

public class SmallBarsCornerBlock extends Block {
    public static final EnumProperty<CornerDirection> CORNER = new CornerDirectionProperty()
    public SmallBarsCornerBlock(Properties properties) {
        super(properties);
    }
}
