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

    public static final ExtendedBlockFamily BLACKWOOD_PLANKS = familyBuilder(AbyssalDecorBlocks.BLACKWOOD_PLANKS.get())
            .button(AbyssalDecorBlocks.BLACKWOOD_BUTTON.get())
            .fence(AbyssalDecorBlocks.BLACKWOOD_FENCE.get())
            .fenceGate(AbyssalDecorBlocks.BLACKWOOD_FENCE_GATE.get())
            .pressurePlate(AbyssalDecorBlocks.BLACKWOOD_PRESSURE_PLATE.get())
            .sign(AbyssalDecorBlocks.BLACKWOOD_SIGN.get(), AbyssalDecorBlocks.BLACKWOOD_WALL_SIGN.get())
            .hangingSign(AbyssalDecorBlocks.BLACKWOOD_HANGING_SIGN.get(), AbyssalDecorBlocks.BLACKWOOD_WALL_HANGING_SIGN.get())
            .slab(AbyssalDecorBlocks.BLACKWOOD_SLAB.get())
            .stairs(AbyssalDecorBlocks.BLACKWOOD_STAIRS.get()).door(AbyssalDecorBlocks.BLACKWOOD_DOOR.get())
            .trapdoor(AbyssalDecorBlocks.BLACKWOOD_TRAPDOOR.get()).recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS).getFamily();

    public static final ExtendedBlockFamily WHITE_PEARL = familyBuilder(AbyssalDecorBlocks.WHITE_PEARL_BLOCK.get())
            .door(AbyssalDecorBlocks.WHITE_PEARL_DOOR.get())
            .trapdoor(AbyssalDecorBlocks.WHITE_PEARL_TRAPDOOR.get())
            .stairs(AbyssalDecorBlocks.WHITE_PEARL_STAIRS.get())
            .slab(AbyssalDecorBlocks.WHITE_PEARL_SLAB.get())
            .chiseled(AbyssalDecorBlocks.CHISELED_WHITE_PEARL.get())
            .wall(AbyssalDecorBlocks.WHITE_PEARL_WALL.get())
            .recipeUnlockedBy("has_white_pearl").getFamily();

    public static final ExtendedBlockFamily WHITE_PEARL_BRICKS = familyBuilder(AbyssalDecorBlocks.WHITE_PEARL_BRICKS.get())
            .stairs(AbyssalDecorBlocks.WHITE_PEARL_BRICK_STAIRS.get())
            .slab(AbyssalDecorBlocks.WHITE_PEARL_BRICK_SLAB.get())
            .wall(AbyssalDecorBlocks.WHITE_PEARL_BRICK_WALL.get())
            .recipeUnlockedBy("has_white_pearl_brick").getFamily();

    public static final ExtendedBlockFamily SMOOTH_WHITE_PEARL = familyBuilder(AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_BLOCK.get())
            .stairs(AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_STAIRS.get())
            .slab(AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_SLAB.get())
            .wall(AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_WALL.get())
            .recipeUnlockedBy("has_smooth_white_pearl").getFamily();

    public static final ExtendedBlockFamily BLACK_PEARL = familyBuilder(AbyssalDecorBlocks.BLACK_PEARL_BLOCK.get())
            .stairs(AbyssalDecorBlocks.BLACK_PEARL_STAIRS.get())
            .slab(AbyssalDecorBlocks.BLACK_PEARL_SLAB.get())
            .chiseled(AbyssalDecorBlocks.CHISELED_BLACK_PEARL.get())
            .wall(AbyssalDecorBlocks.BLACK_PEARL_WALL.get())
            .recipeUnlockedBy("has_black_pearl").getFamily();

    public static final ExtendedBlockFamily BLACK_PEARL_BRICKS = familyBuilder(AbyssalDecorBlocks.BLACK_PEARL_BRICKS.get())
            .stairs(AbyssalDecorBlocks.BLACK_PEARL_BRICK_STAIRS.get())
            .slab(AbyssalDecorBlocks.BLACK_PEARL_BRICK_SLAB.get())
            .wall(AbyssalDecorBlocks.BLACK_PEARL_BRICK_WALL.get())
            .recipeUnlockedBy("has_black_pearl_brick").getFamily();

    public static final ExtendedBlockFamily SMOOTH_BLACK_PEARL = familyBuilder(AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_BLOCK.get())
            .stairs(AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_STAIRS.get())
            .slab(AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_SLAB.get())
            .wall(AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_WALL.get())
            .recipeUnlockedBy("has_smooth_black_pearl").getFamily();

    public static final ExtendedBlockFamily IRON_PANEL = familyBuilder(AbyssalDecorBlocks.IRON_PANEL.get())
            .stairs(AbyssalDecorBlocks.IRON_PANEL_STAIRS.get())
            .slab(AbyssalDecorBlocks.IRON_PANEL_SLAB.get())
            .wall(AbyssalDecorBlocks.IRON_PANEL_WALL.get())
            .recipeUnlockedBy("has_iron_panel").getFamily();

    public static final ExtendedBlockFamily POLISHED_BLOOD_CORAL = familyBuilder(AbyssalDecorBlocks.POLISHED_BLOOD_CORAL.get())
            .door(AbyssalDecorBlocks.BLOOD_CORAL_DOOR.get())
            .slab(AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_SLAB.get())
            .stairs(AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_STAIRS.get())
            .trapdoor(AbyssalDecorBlocks.BLOOD_CORAL_TRAPDOOR.get())
            .wall(AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_WALL.get())
            .getFamily();

    public static final ExtendedBlockFamily SMOOTH_BLOOD_CORAL = familyBuilder(AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL.get())
            .slab(AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_SLAB.get())
            .stairs(AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_STAIRS.get())
            .wall(AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_WALL.get())
            .getFamily();

    public static final ExtendedBlockFamily BLOOD_CORAL_BRICKS = familyBuilder(AbyssalDecorBlocks.BLOOD_CORAL_BRICKS.get())
            .slab(AbyssalDecorBlocks.BLOOD_CORAL_BRICK_SLAB.get())
            .stairs(AbyssalDecorBlocks.BLOOD_CORAL_BRICK_STAIRS.get())
            .wall(AbyssalDecorBlocks.BLOOD_CORAL_BRICK_WALL.get())
            .getFamily();


    public static final ExtendedBlockFamily SEABRASS_BLOCK = familyBuilder(AbyssalDecorBlocks.SEABRASS_BLOCK.get())
            .button(AbyssalDecorBlocks.SEABRASS_BUTTON.get())
            .door(AbyssalDecorBlocks.SEABRASS_DOOR.get())
            .pressurePlate(AbyssalDecorBlocks.SEABRASS_PRESSURE_PLATE.get())
            .slab(AbyssalDecorBlocks.SEABRASS_SLAB.get())
            .stairs(AbyssalDecorBlocks.SEABRASS_STAIRS.get())
            .trapdoor(AbyssalDecorBlocks.SEABRASS_TRAPDOOR.get())
            .wall(AbyssalDecorBlocks.SEABRASS_WALL.get())
            .getFamily();

    public static final ExtendedBlockFamily DEEPBRONZE_BLOCK = familyBuilder(AbyssalDecorBlocks.DEEPBRONZE_BLOCK.get())
            .button(AbyssalDecorBlocks.DEEPBRONZE_BUTTON.get())
            .door(AbyssalDecorBlocks.DEEPBRONZE_DOOR.get())
            .pressurePlate(AbyssalDecorBlocks.DEEPBRONZE_PRESSURE_PLATE.get())
            .slab(AbyssalDecorBlocks.DEEPBRONZE_SLAB.get())
            .stairs(AbyssalDecorBlocks.DEEPBRONZE_STAIRS.get())
            .trapdoor(AbyssalDecorBlocks.DEEPBRONZE_TRAPDOOR.get())
            .wall(AbyssalDecorBlocks.DEEPBRONZE_WALL.get())
            .getFamily();

    public static final ExtendedBlockFamily RIVETED_DEEPBRONZE = familyBuilder(AbyssalDecorBlocks.RIVETED_DEEPBRONZE.get())
            .slab(AbyssalDecorBlocks.RIVETED_DEEPBRONZE_SLAB.get())
            .stairs(AbyssalDecorBlocks.RIVETED_DEEPBRONZE_STAIRS.get())
            .wall(AbyssalDecorBlocks.RIVETED_DEEPBRONZE_WALL.get())
            .getFamily();


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
