package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.Nullable;

public enum CornerDirection implements StringRepresentable {
    NORTHEAST("northeast",Direction.NORTH),
    SOUTHEAST("southeast",Direction.EAST),
    SOUTHWEST("southwest",Direction.SOUTH),
    NORTHWEST("northwest",Direction.WEST);
    private final String name;
    private final Direction shapeDirection;

    CornerDirection(String name,Direction shapeDirection) {
        this.name = name;
        this.shapeDirection = shapeDirection;
    }

    public String toString() {
        return this.name;
    }

    public int yRotation() {
        return (90 * ordinal()+90) %360;
    }

    public Direction getShapeDirection() {
        return shapeDirection;
    }

    @Nullable
    public static CornerDirection from(Direction direction0,Direction direction1) {
        if (direction0.getAxis().isVertical() || direction1.getAxis().isVertical()) return null;
        switch (direction0) {
            //assume north
            default -> {
                if (direction1 == Direction.WEST) {
                    return NORTHWEST;
                } else if (direction1 ==  Direction.EAST) {
                    return NORTHEAST;
                }
            }
            case SOUTH -> {
                if (direction1 == Direction.WEST) {
                    return SOUTHWEST;
                } else if (direction1 ==  Direction.EAST) {
                    return SOUTHEAST;
                }
            }
            case WEST -> {
                if (direction1 == Direction.NORTH) {
                    return NORTHWEST;
                } else if (direction1 == Direction.SOUTH) {
                    return SOUTHWEST;
                }
            }
            case EAST -> {
                if (direction1 == Direction.NORTH) {
                    return NORTHEAST;
                } else if (direction1 == Direction.SOUTH) {
                    return SOUTHEAST;
                }
            }
        }
        return null;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
