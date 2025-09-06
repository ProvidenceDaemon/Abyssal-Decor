package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.util.StringRepresentable;

public enum WallGrimeType implements StringRepresentable {
    FLOOR("floor"),
    MIDDLE_FLOOR("middle_floor"),
    MIDDLE("middle"),
    TOP("top");
    private final String name;

    WallGrimeType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
