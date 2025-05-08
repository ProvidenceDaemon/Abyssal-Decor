package net.starrysock.abyssaldecor.block.properties;

import net.minecraft.util.StringRepresentable;

public enum HorizontalPart implements StringRepresentable {
        FRONT("front"),
        BACK("back");

        private final String name;

        HorizontalPart(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }
}

