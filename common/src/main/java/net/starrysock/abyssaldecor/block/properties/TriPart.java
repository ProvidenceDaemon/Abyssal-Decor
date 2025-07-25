package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    public static TriPart getForPlacement(boolean above,boolean below) {
        if (above && below) {
            return MIDDLE;
        }

        if (above) {
            return BOTTOM;
        }

        if (below) {
            return TOP;
        }
        return null;
    }
}
