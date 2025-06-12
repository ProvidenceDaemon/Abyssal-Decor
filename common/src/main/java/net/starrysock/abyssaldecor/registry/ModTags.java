package net.starrysock.abyssaldecor.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> AMARANTH_GROWABLE = mod("amaranth_growable");
        public static final TagKey<Block> MUCKROOT_GROWABLE = mod("muckroot_growable");

        public static final TagKey<Block> MOLDY_PLANT_VALID_BLOCKS = mod("moldy_plant_valid_blocks");

        static TagKey<Block> mod(String path) {
            return TagKey.create(Registries.BLOCK, AbyssalDecor.id(path));
        }
    }
}
