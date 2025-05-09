package net.starrysock.abyssaldecor.registry;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class ExtendedBlockFamilies {
    private static final Map<Block, ExtendedBlockFamily> MAP = Maps.newHashMap();
    private static final String RECIPE_GROUP_PREFIX_WOODEN = "wooden";
    private static final String RECIPE_UNLOCKED_BY_HAS_PLANKS = "has_planks";


    public static final ExtendedBlockFamily WHITEWOOD_PLANKS = familyBuilder(AbyssalDecorBlocks.WHITEWOOD_PLANKS.get())
            .button(AbyssalDecorBlocks.WHITEWOOD_BUTTON.get())
            .fence(AbyssalDecorBlocks.WHITEWOOD_FENCE.get())
            .fenceGate(AbyssalDecorBlocks.WHITEWOOD_FENCE_GATE.get())
            .pressurePlate(AbyssalDecorBlocks.WHITEWOOD_PRESSURE_PLATE.get())
            .sign(AbyssalDecorBlocks.WHITEWOOD_SIGN.get(), AbyssalDecorBlocks.WHITEWOOD_WALL_SIGN.get())
            .hangingSign(AbyssalDecorBlocks.WHITEWOOD_HANGING_SIGN.get(), AbyssalDecorBlocks.WHITEWOOD_WALL_HANGING_SIGN.get())
            .slab(AbyssalDecorBlocks.WHITEWOOD_SLAB.get())
            .stairs(AbyssalDecorBlocks.WHITEWOOD_STAIRS.get()).door(AbyssalDecorBlocks.WHITEWOOD_DOOR.get())
            .trapdoor(AbyssalDecorBlocks.WHITEWOOD_TRAPDOOR.get()).recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS).getFamily();



    private static ExtendedBlockFamily.Builder familyBuilder(Block baseBlock) {
        ExtendedBlockFamily.Builder blockfamily$builder = new ExtendedBlockFamily.Builder(baseBlock);
        ExtendedBlockFamily blockfamily = MAP.put(baseBlock, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<ExtendedBlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }

}
