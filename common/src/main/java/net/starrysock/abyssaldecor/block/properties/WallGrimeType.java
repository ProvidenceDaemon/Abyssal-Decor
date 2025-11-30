package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.util.StringRepresentable;

public enum WallGrimeType implements StringRepresentable {
    FLOOR("floor"),
    BOTTOM("bottom"),
    MIDDLE_FLOOR("middle_floor"),
    MIDDLE("middle"),
    MIDDLE_CEILING("middle_ceiling"),
    TOP("top"),
    CEILING("ceiling");
    private final String name;

    WallGrimeType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public enum Connection{
        NOTHING,GRIME,SOLID
    }


    public static WallGrimeType getGrimeType(Connection above,Connection below) {
        WallGrimeType grimeType = switch (above) {
            case NOTHING -> switch (below) {
                case NOTHING -> TOP;
                case GRIME -> BOTTOM;
                case SOLID -> FLOOR;
            };
            case GRIME -> switch (below) {
                case NOTHING -> TOP;
                case GRIME -> MIDDLE;
                case SOLID -> MIDDLE_FLOOR;
            };
            case SOLID -> switch (below) {
                case NOTHING -> CEILING;
                case GRIME -> MIDDLE_CEILING;
                case SOLID -> MIDDLE_FLOOR;//middle middle?
            };
        };

        return grimeType;
    }

}
