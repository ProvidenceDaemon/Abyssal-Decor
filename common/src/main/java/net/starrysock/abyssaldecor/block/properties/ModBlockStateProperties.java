package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockStateProperties {


    public static final EnumProperty<TriPart> TRI_PART = EnumProperty.create("tri_part", TriPart.class);
    public static final EnumProperty<HorizontalPart> PART = EnumProperty.create("horizontal_part", HorizontalPart.class);
    public static final EnumProperty<VerticalConnection> VERTICAL_CONNECTION = EnumProperty.create("vertical_connection", VerticalConnection.class);
}
