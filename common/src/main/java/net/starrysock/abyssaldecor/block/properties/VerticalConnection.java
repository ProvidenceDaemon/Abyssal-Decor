package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.util.StringRepresentable;

public enum VerticalConnection implements StringRepresentable {
    SOLO("solo"),
    BOTTOM("bottom"),
    MIDDLE("middle"),
    TOP("top");

    private final String name;

    VerticalConnection(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public static VerticalConnection getForPlacement(boolean above, boolean below) {
        if (above && below) {
            return MIDDLE;
        }
        if (above) {
            return BOTTOM;
        }
        if (below) {
            return TOP;
        }
        return SOLO;
    }
}
