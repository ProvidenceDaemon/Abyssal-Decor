package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Collection;

public class CornerDirectionProperty extends EnumProperty<CornerDirection> {
    protected CornerDirectionProperty(String name, Collection<CornerDirection> values) {
        super(name, CornerDirection.class, values);
    }
}
