package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Collection;
import java.util.List;

public class CornerDirectionProperty extends EnumProperty<CornerDirection> {
    protected CornerDirectionProperty(String name, Collection<CornerDirection> values) {
        super(name, CornerDirection.class, values);
    }

    public static final CornerDirectionProperty INSTANCE = new CornerDirectionProperty("corner", List.of(CornerDirection.values()));

}
