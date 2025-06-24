package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.util.StringRepresentable;

public enum TriPart implements StringRepresentable {
    BOTTOM("bottom"),
    MIDDLE("middle"),
    TOP("top");

    private final String name;

    TriPart(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
