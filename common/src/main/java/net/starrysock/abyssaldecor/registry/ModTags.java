package net.starrysock.abyssaldecor.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> AMARANTH_GROWABLE = mod("amaranth_growable");
        public static final TagKey<Block> MUCKROOT_GROWABLE = mod("muckroot_growable");
        public static final TagKey<Block> SPIDERCORN_GROWABLE = mod("spidercorn_growable");

        public static final TagKey<Block> MOLDY_PLANT_VALID_BLOCKS = mod("moldy_plant_valid_blocks");
        public static final TagKey<Block> MOLDY_STALK_PLANTABLE_ON = mod("moldy_stalk_plantable_on");
        public static final TagKey<Block> CINNAMON_LOGS = mod("cinnamon_logs");

        public static final TagKey<Block> MOLD_SPREADABLES = mod("mold_spreadables");
        public static final TagKey<Block> MOLD_IMMUNE = mod("mold_immune");

        public static final TagKey<Block> WHITEWOOD_LOGS = mod("whitewood_logs");
        public static final TagKey<Block> BLACKWOOD_LOGS = mod("blackwood_logs");

        public static final TagKey<Block> WALLPAPER = mod("wallpaper");


        static TagKey<Block> mod(String path) {
            return TagKey.create(Registries.BLOCK, AbyssalDecor.id(path));
        }
    }

    public static class Fluids {
        public static final TagKey<Fluid> SUPPORTS_BOG_APPLES = mod("supports_bog_apples");
        static TagKey<Fluid> mod(String path) {
            return TagKey.create(Registries.FLUID, AbyssalDecor.id(path));
        }
    }

    public static class Items {
        public static final TagKey<Item> WHITEWOOD_LOGS = mod("whitewood_logs");
        public static final TagKey<Item> CINNAMON_LOGS = mod("cinnamon_logs");
        public static final TagKey<Item> BLACKWOOD_LOGS = mod("blackwood_logs");
        public static final TagKey<Item> WALLPAPER = mod("wallpaper");


        static TagKey<Item> mod(String path) {
            return TagKey.create(Registries.ITEM, AbyssalDecor.id(path));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> HAS_AMARANTH = mod("has_amaranth");
        public static final TagKey<Biome> HAS_MUCKROOT = mod("has_muckroot");
        public static final TagKey<Biome> HAS_SPIDERCORN = mod("has_spidercorn");

        static TagKey<Biome> mod(String path) {
            return TagKey.create(Registries.BIOME, AbyssalDecor.id(path));
        }
    }
}
