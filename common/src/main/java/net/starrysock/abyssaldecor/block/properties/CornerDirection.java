package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.util.StringRepresentable;

public enum CornerDirection implements StringRepresentable {
    NORTHEAST("northeast"),
    SOUTHEAST("southeast"),
    SOUTHWEST("southwest"),
    NORTHWEST("northwest");
    private final String name;

    CornerDirection(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
