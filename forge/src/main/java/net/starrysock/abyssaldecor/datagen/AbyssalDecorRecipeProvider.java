package net.starrysock.abyssaldecor.datagen;

import com.google.common.collect.ImmutableMap;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.starrysock.abyssaldecor.registry.*;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class AbyssalDecorRecipeProvider extends RecipeProvider {
    public AbyssalDecorRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        ExtendedBlockFamilies.getAllFamilies().forEach(
                family -> generateRecipes(consumer, family));

        planksFromLog(consumer, AbyssalDecorBlocks.BLACKWOOD_PLANKS.get(), ModTags.Items.BLACKWOOD_LOGS, 4);
        woodFromLogs(consumer, AbyssalDecorBlocks.BLACKWOOD_WOOD.get(), AbyssalDecorBlocks.BLACKWOOD_LOG.get());

        planksFromLog(consumer, AbyssalDecorBlocks.CINNAMON_PLANKS.get(), ModTags.Items.CINNAMON_LOGS, 4);
        woodFromLogs(consumer, AbyssalDecorBlocks.CINNAMON_WOOD.get(), AbyssalDecorBlocks.CINNAMON_LOG.get());

        planksFromLog(consumer, AbyssalDecorBlocks.WHITEWOOD_PLANKS.get(), ModTags.Items.WHITEWOOD_LOGS, 4);
        woodFromLogs(consumer, AbyssalDecorBlocks.WHITEWOOD_WOOD.get(), AbyssalDecorBlocks.WHITEWOOD_LOG.get());


        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE.get(), AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.FROSTED_GLASS_PANE.get(), AbyssalDecorBlocks.FROSTED_GLASS.get());

        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE.get(), AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.AZURE_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.AZURE_PEARLY_GLASS.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.SUNNY_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.SUNNY_PEARLY_GLASS.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.VERDANT_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.VERDANT_PEARLY_GLASS.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.PEARLY_GLASS.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.WHITEWOOD_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.WHITEWOOD_PEARLY_GLASS.get());

        smeltingResultFromBase(consumer, AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get(), Blocks.GLOWSTONE);
        smeltingResultFromBase(consumer, AbyssalDecorItems.POPPED_SPIDERCORN.get(), AbyssalDecorItems.SPIDERCORN.get());

        smeltingResultFromBase(consumer, AbyssalDecorItems.LAVENTINE.get(), Blocks.AMETHYST_BLOCK);
        smeltingResultFromBase(consumer, AbyssalDecorItems.ELDER_WISTERIA.get(), AbyssalDecorItems.WISTERIA.get());

        smeltingResultFromBase(consumer, AbyssalDecorItems.LAVENTINE_GLASS.get(), AbyssalDecorBlocks.LAVENTINE.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.LAVENTINE_GLASS_PANE.get(), AbyssalDecorBlocks.LAVENTINE_GLASS.get());

        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.VERMILION_PANE.get(), AbyssalDecorBlocks.VERMILION_BLOCK.get());
        smeltingResultFromBase(consumer, AbyssalDecorBlocks.VERMILION_BLOCK.get(), Blocks.REDSTONE_BLOCK);

        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.STARGLASS_PANE.get(), AbyssalDecorBlocks.STARGLASS.get());

        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE.get(), AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.FRAMED_VERMILION_PANE.get(), AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.FRAMED_PITCHGLASS_PANE.get(), AbyssalDecorBlocks.FRAMED_PITCHGLASS.get());

        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.BLACKWOOD_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.BLACKWOOD_PEARLY_GLASS.get());

        smeltingResultFromBase(consumer, AbyssalDecorBlocks.INACTIVE_MOLDY_HANGER.get(), AbyssalDecorBlocks.MOLDY_HANGER.get());
        smeltingResultFromBase(consumer, AbyssalDecorBlocks.INACTIVE_MOLD.get(), AbyssalDecorBlocks.BLACK_MOLD.get());
        smeltingResultFromBase(consumer, AbyssalDecorBlocks.CRACKED_BRICKS.get(), Blocks.BRICKS);
        smeltingResultFromBase(consumer, AbyssalDecorBlocks.CRACKED_PEARL_TILES.get(), AbyssalDecorBlocks.WHITE_PEARL_TILES.get());
        smeltingResultFromBase(consumer, AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_BLOCK.get(), AbyssalDecorBlocks.BLACK_PEARL_BLOCK.get());
        smeltingResultFromBase(consumer, AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_BLOCK.get(), AbyssalDecorBlocks.WHITE_PEARL_BLOCK.get());

        smeltingResultFromBase(consumer, AbyssalDecorItems.COOKED_MUCKROOT.get(), AbyssalDecorItems.MUCKROOT.get());

        smeltingResultFromBase(consumer, AbyssalDecorBlocks.ABYSSAL_LANTERN.get(), AbyssalDecorBlocks.SEABRASS_LAMP.get());

        smeltingResultFromBase(consumer, AbyssalDecorItems.TOASTED_AMARANTH_SEEDS.get(), AbyssalDecorItems.AMARANTH_SEEDS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.IRON_LANTERN.get())
                .pattern("aaa")
                .pattern("aba")
                .pattern("aaa")
                .define('a', Items.IRON_NUGGET)
                .define('b', Blocks.GLOWSTONE)
                .unlockedBy(getHasName(Blocks.GLOWSTONE), has(Blocks.GLOWSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE.get())
                .pattern("aba")
                .pattern("bab")
                .pattern("aba")
                .define('a', AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get())
                .define('b', Items.IRON_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get()), has(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.FRAMED_PITCHGLASS.get())
                .pattern("aba")
                .pattern("bab")
                .pattern("aba")
                .define('a', AbyssalDecorBlocks.PITCHGLASS.get())
                .define('b', Items.IRON_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorBlocks.PITCHGLASS.get()), has(AbyssalDecorBlocks.PITCHGLASS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK.get())
                .pattern("aba")
                .pattern("bab")
                .pattern("aba")
                .define('a', AbyssalDecorBlocks.VERMILION_BLOCK.get())
                .define('b', Items.IRON_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorBlocks.VERMILION_BLOCK.get()), has(AbyssalDecorBlocks.VERMILION_BLOCK.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.TUBE_LAMP.get(), 6)
                .pattern("aaa")
                .define('a', AbyssalDecorBlocks.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorBlocks.LIGHTBULB.get()), has(AbyssalDecorBlocks.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.LION_STATUE.get())
                .pattern(" a ")
                .pattern(" b ")
                .pattern("ccc")
                .define('a', Blocks.POLISHED_ANDESITE)
                .define('b', Items.ANDESITE_WALL)
                .define('c', Items.POLISHED_ANDESITE_SLAB)
                .unlockedBy(getHasName(Blocks.POLISHED_ANDESITE), has(Blocks.ANDESITE_SLAB))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.GARGOYLE.get())
                .pattern("aa ")
                .pattern("bbc")
                .define('a', Blocks.POLISHED_ANDESITE_SLAB)
                .define('b', Items.STONE_BRICKS)
                .define('c', Items.CHISELED_STONE_BRICKS)
                .unlockedBy(getHasName(Blocks.POLISHED_ANDESITE), has(Blocks.ANDESITE_SLAB))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.LIGHTBULB.get())
                .pattern(" a ")
                .pattern("aba")
                .pattern(" c ")
                .define('a', Blocks.GLASS_PANE)
                .define('b', Items.GLOWSTONE_DUST)
                .define('c', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AbyssalDecorBlocks.BLACK_PEARL_BARS.get(), 16)
                .define('#', AbyssalDecorBlocks.BLACK_PEARL.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(AbyssalDecorBlocks.BLACK_PEARL.get()), has(AbyssalDecorBlocks.BLACK_PEARL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AbyssalDecorBlocks.WHITE_PEARL_BARS.get(), 16)
                .define('#', AbyssalDecorBlocks.WHITE_PEARL.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(AbyssalDecorBlocks.WHITE_PEARL.get()), has(AbyssalDecorBlocks.WHITE_PEARL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AbyssalDecorBlocks.BLOOD_CORAL_BARS.get(), 16)
                .define('#', AbyssalDecorBlocks.BLOOD_CORAL_PILLAR.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(AbyssalDecorBlocks.BLOOD_CORAL_PILLAR.get()), has(AbyssalDecorBlocks.BLOOD_CORAL_PILLAR.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AbyssalDecorBlocks.ELDER_WISTERIA_PETALS.get(), 4)
                .define('#', AbyssalDecorItems.ELDER_WISTERIA.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(AbyssalDecorBlocks.ELDER_WISTERIA.get()), has(AbyssalDecorBlocks.ELDER_WISTERIA.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AbyssalDecorBlocks.WISTERIA_PETALS.get(), 4)
                .define('#', AbyssalDecorItems.WISTERIA.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(AbyssalDecorBlocks.WISTERIA.get()), has(AbyssalDecorBlocks.WISTERIA.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES.get(), 2)
                .define('#', AbyssalDecorItems.ELDER_WISTERIA.get())
                .pattern("##")
                .unlockedBy(getHasName(AbyssalDecorBlocks.ELDER_WISTERIA.get()), has(AbyssalDecorBlocks.ELDER_WISTERIA.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorBlocks.NITHING_POLE.get())
                .pattern("a")
                .pattern("b")
                .pattern("b")
                .define('a', Items.SKELETON_SKULL)
                .define('b', Items.STICK)
                .unlockedBy(getHasName(Items.SKELETON_SKULL), has(Items.SKELETON_SKULL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.IRON_BARRIER.get(), 6)
                .pattern("aba")
                .pattern("a a")
                .pattern("a a")
                .define('a', Items.IRON_NUGGET)
                .define('b', Items.BLACK_WOOL)
                .unlockedBy(getHasName(Items.BLACK_WOOL), has(Items.BLACK_WOOL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.ROPE_BARRIER.get(), 6)
                .pattern("aba")
                .pattern("aba")
                .pattern("a a")
                .define('a', Items.STICK)
                .define('b', Items.STRING)
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.BARBED_WIRE_BARRIER.get(), 6)
                .pattern("aba")
                .pattern("aba")
                .pattern("a a")
                .define('a', Items.STICK)
                .define('b', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.IRON_BALL.get(), 5)
                .pattern(" a ")
                .pattern("aaa")
                .pattern(" a ")
                .define('a', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.IRON_INGOT)
                .requires(AbyssalDecorItems.IRON_BALL.get())
                .unlockedBy(getHasName(AbyssalDecorItems.IRON_BALL.get()), has(AbyssalDecorItems.IRON_BALL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.VELVET_BARRIER.get(), 6)
                .pattern("aba")
                .pattern("a a")
                .pattern("a a")
                .define('a', Items.GOLD_NUGGET)
                .define('b', AbyssalDecorItems.VELVET.get())
                .unlockedBy(getHasName(AbyssalDecorItems.VELVET.get()), has(AbyssalDecorItems.VELVET.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.BLAZE_LAMP.get())
                .pattern("a ")
                .pattern("ab")
                .pattern("aa")
                .define('a', Items.IRON_NUGGET)
                .define('b', Items.BLAZE_ROD)
                .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.SCRIMSHAW_ALTAR.get())
                .pattern(" a ")
                .pattern("bcb")
                .pattern("ded")
                .define('a', AbyssalDecorItems.SCRIMSHAW.get())
                .define('b', Items.STRING)
                .define('c', Items.CHEST)
                .define('d', Items.HONEYCOMB)
                .define('e', AbyssalDecorBlocks.GILDED_VELVET_CARPET.get())
                .unlockedBy(getHasName(AbyssalDecorItems.SCRIMSHAW.get()), has(AbyssalDecorItems.SCRIMSHAW.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.FROSTED_LAMP.get())
                .pattern(" a ")
                .pattern("bcb")
                .pattern("bbb")
                .define('a', Items.GOLD_NUGGET)
                .define('b', Items.WHITE_STAINED_GLASS_PANE)
                .define('c', AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()), has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.RAINBOW_LAMP.get(),4)
                .pattern(" a ")
                .pattern("aba")
                .pattern(" c ")
                .define('a', Items.GLASS_PANE)
                .define('b', Items.DIAMOND)
                .define('c', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.TELESCOPE.get())
                .pattern(" a ")
                .pattern(" b ")
                .pattern("b b")
                .define('a', Items.SPYGLASS)
                .define('b', Items.STICK)
                .unlockedBy(getHasName(Items.SPYGLASS), has(Items.SPYGLASS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.WALL_BULB_LAMP.get())
                .pattern("a  ")
                .pattern("a b")
                .pattern("aaa")
                .define('a', Items.IRON_NUGGET)
                .define('b', AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()), has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.HANGING_WEB.get(),4)
                .pattern("a")
                .pattern("a")
                .define('a', Items.COBWEB)
                .unlockedBy(getHasName(Items.COBWEB), has(Items.COBWEB))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.DANGLING_WEB.get(),6)
                .pattern("a")
                .pattern("a")
                .pattern("a")
                .define('a', Items.COBWEB)
                .unlockedBy(getHasName(Items.COBWEB), has(Items.COBWEB))
                .save(consumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorBlocks.WHITEWOOD_LOG.get(), 8)
                .requires(Ingredient.of(ItemTags.LOGS), 8)
                .requires(AbyssalDecorItems.WHITE_PEARL.get())
                .unlockedBy(getHasName(AbyssalDecorItems.WHITE_PEARL.get()), has(AbyssalDecorItems.WHITE_PEARL.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.SPIDERCORN_TORTILLA.get())
                .requires(Ingredient.of(AbyssalDecorItems.SPIDERCORN.get()), 3)
                .unlockedBy(getHasName(AbyssalDecorItems.SPIDERCORN.get()), has(AbyssalDecorItems.SPIDERCORN.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AbyssalDecorBlocks.BLACK_PEARL_BRICKS.get(), 4)
                .define('#', AbyssalDecorBlocks.BLACK_PEARL_BLOCK.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(AbyssalDecorBlocks.BLACK_PEARL_BLOCK.get())
                        , has(AbyssalDecorBlocks.BLACK_PEARL_BLOCK.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AbyssalDecorBlocks.WHITE_PEARL_BRICKS.get(), 4)
                .define('#', AbyssalDecorBlocks.WHITE_PEARL_BLOCK.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(AbyssalDecorBlocks.WHITE_PEARL_BLOCK.get())
                        , has(AbyssalDecorBlocks.WHITE_PEARL_BLOCK.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AbyssalDecorBlocks.WHITEWOOD_PICKET_FENCE.get(), 3)
                .define('#', AbyssalDecorBlocks.WHITEWOOD_FENCE.get())
                .pattern("###")
                .unlockedBy(getHasName(AbyssalDecorBlocks.WHITEWOOD_FENCE.get())
                        , has(AbyssalDecorBlocks.WHITEWOOD_FENCE.get())).save(consumer);

        AbyssalDecorItems.WALLPAPERS.map().forEach((key, value) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, value.get(),4)
                .requires(DyeItem.byColor(key))
                .requires(Items.PAPER)
                .requires(ItemTags.PLANKS)
                .unlockedBy(getHasName(DyeItem.byColor(key)), has(DyeItem.byColor(key)))
                .save(consumer));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.QUARTZ_LAMP.get())
                .pattern("aba")
                .pattern("aca")
                .pattern("ddd")
                .define('a', Items.GLASS_PANE)
                .define('b', Items.STRING)
                .define('c', ItemTags.COALS)
                .define('d', Items.GOLD_NUGGET)
                .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.SEABRASS_LAMP.get())
                .pattern(" a ")
                .pattern("aba")
                .pattern(" a ")
                .define('a', AbyssalDecorItems.SEABRASS_INGOT.get())
                .define('b', AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()), has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.JADE_LANTERN.get())
                .pattern(" a ")
                .pattern("aba")
                .pattern(" a ")
                .define('a', AbyssalDecorItems.POLISHED_JADE.get())
                .define('b', Items.GLOWSTONE_DUST)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.JADE_LAMP.get())
                .pattern("aaa")
                .pattern(" b ")
                .pattern("ccc")
                .define('a', AbyssalDecorItems.POLISHED_JADE.get())
                .define('b', AbyssalDecorItems.LIGHTBULB.get())
                .define('c',Items.GOLD_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()), has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.DEEPBRONZE_LANTERN.get())
                .pattern(" a ")
                .pattern("aba")
                .pattern(" a ")
                .define('a', AbyssalDecorItems.DEEPBRONZE_LANTERN.get())
                .define('b', AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()), has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.BULKHEAD_LAMP.get())
                .pattern("a")
                .pattern("b")
                .pattern("a")
                .define('a', Items.IRON_NUGGET)
                .define('b', AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()), has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.STARGLASS.get(),2)
                .pattern("a")
                .pattern("b")
                .pattern("a")
                .define('a', AbyssalDecorItems.BLACK_PEARL.get())
                .define('b', AbyssalDecorItems.STARSTONE.get())
                .unlockedBy(getHasName(AbyssalDecorItems.STARSTONE.get()), has(AbyssalDecorItems.STARSTONE.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.BLACK_PEARL_PILLAR.get(), 3)
                .pattern("a")
                .pattern("a")
                .pattern("a")
                .define('a', AbyssalDecorItems.BLACK_PEARL_BLOCK.get())
                .unlockedBy(getHasName(AbyssalDecorItems.BLACK_PEARL_BLOCK.get()), has(AbyssalDecorItems.BLACK_PEARL_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.WHITE_PEARL_PILLAR.get(), 3)
                .pattern("a")
                .pattern("a")
                .pattern("a")
                .define('a', AbyssalDecorItems.WHITE_PEARL_BLOCK.get())
                .unlockedBy(getHasName(AbyssalDecorItems.WHITE_PEARL_BLOCK.get()), has(AbyssalDecorItems.WHITE_PEARL_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.WHITE_PEARL_TILES.get(), 8)
                .pattern("aa")
                .pattern("aa")
                .define('a', AbyssalDecorItems.WHITE_PEARL_BRICKS.get())
                .unlockedBy(getHasName(AbyssalDecorItems.WHITE_PEARL_BRICKS.get()), has(AbyssalDecorItems.WHITE_PEARL_BRICKS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.CUT_WHITE_PEARL_BLOCK.get())
                .requires(AbyssalDecorItems.CHISELED_WHITE_PEARL.get())
                .unlockedBy(getHasName(AbyssalDecorItems.CHISELED_WHITE_PEARL.get()), has(AbyssalDecorItems.CHISELED_WHITE_PEARL.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AbyssalDecorBlocks.BLACK_PEARL_BLOCK.get())
                .define('#', AbyssalDecorBlocks.BLACK_PEARL.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(AbyssalDecorBlocks.BLACK_PEARL.get())
                        , has(AbyssalDecorBlocks.BLACK_PEARL.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AbyssalDecorBlocks.WHITE_PEARL_BLOCK.get())
                .define('#', AbyssalDecorBlocks.WHITE_PEARL.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(AbyssalDecorBlocks.WHITE_PEARL.get())
                        , has(AbyssalDecorBlocks.WHITE_PEARL.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.BLACK_PEARL.get(), 4)
                .requires(AbyssalDecorItems.BLACK_PEARL_BLOCK.get())
                .unlockedBy(getHasName(AbyssalDecorItems.BLACK_PEARL_BLOCK.get()), has(AbyssalDecorItems.BLACK_PEARL_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.SEAGLASS_LAMP.get())
                .pattern("aba")
                .pattern("aca")
                .pattern("bbb")
                .define('a',Items.WHITE_STAINED_GLASS_PANE)
                .define('b',Items.IRON_NUGGET)
                .define('c',AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()),has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.PEARLY_GLASS.get(),6)
                .pattern("aaa")
                .pattern("bbb")
                .pattern("aaa")
                .define('a',Blocks.SPRUCE_PLANKS)
                .define('b',AbyssalDecorItems.WHITE_PEARL.get())
                .unlockedBy(getHasName(AbyssalDecorItems.WHITE_PEARL.get()),has(AbyssalDecorItems.WHITE_PEARL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.WHITEWOOD_PEARLY_GLASS.get(),6)
                .pattern("aaa")
                .pattern("bbb")
                .pattern("aaa")
                .define('a',AbyssalDecorItems.WHITEWOOD_PLANKS.get())
                .define('b',AbyssalDecorItems.WHITE_PEARL.get())
                .unlockedBy(getHasName(AbyssalDecorItems.WHITE_PEARL.get()),has(AbyssalDecorItems.WHITE_PEARL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.BLACKWOOD_PEARLY_GLASS.get(),6)
                .pattern("aaa")
                .pattern("bbb")
                .pattern("aaa")
                .define('a',AbyssalDecorItems.BLACKWOOD_PLANKS.get())
                .define('b',AbyssalDecorItems.WHITE_PEARL.get())
                .unlockedBy(getHasName(AbyssalDecorItems.WHITE_PEARL.get()),has(AbyssalDecorItems.WHITE_PEARL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.WHITEWOOD_TRIM.get(),6)
                .pattern("aaa")
                .pattern("bbb")
                .pattern("aaa")
                .define('a',Items.STICK)
                .define('b',AbyssalDecorItems.WHITEWOOD_PLANKS.get())
                .unlockedBy(getHasName(AbyssalDecorItems.WHITEWOOD_PLANKS.get()),has(AbyssalDecorItems.WHITEWOOD_PLANKS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.IRON_LAMP.get())
                .pattern("aaa")
                .pattern("aba")
                .define('a',Items.IRON_NUGGET)
                .define('b',AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()),has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.FRESNEL_LAMP.get())
                .pattern(" a ")
                .pattern("bcb")
                .pattern(" d ")
                .define('a',Items.REDSTONE)
                .define('b',AbyssalDecorItems.FRESNEL_PANE.get())
                .define('c',AbyssalDecorItems.FRESNEL_BLOCK.get())
                .define('d',AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()),has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.BLOOD_CORAL_SCONCE.get(),4)
                .pattern("a a")
                .pattern("aa ")
                .pattern("a  ")
                .define('a',AbyssalDecorItems.BLOOD_CORAL_BARS.get())
                .unlockedBy(getHasName(AbyssalDecorItems.BLOOD_CORAL_BARS.get()),has(AbyssalDecorItems.BLOOD_CORAL_BARS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.WHITEWOOD_PLANTER.get(),6)
                .pattern("aba")
                .pattern("aaa")
                .define('a',AbyssalDecorItems.WHITEWOOD_PLANKS.get())
                .define('b',Blocks.DIRT)
                .unlockedBy(getHasName(AbyssalDecorItems.WHITEWOOD_PLANKS.get()),has(AbyssalDecorItems.WHITEWOOD_PLANKS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorItems.FLOWER_LAMP.get(),4)
                .pattern("a b")
                .pattern("a c")
                .pattern("aab")
                .define('a',Items.GOLD_NUGGET)
                .define('b',Blocks.WHITE_STAINED_GLASS_PANE)
                .define('c',AbyssalDecorItems.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorItems.LIGHTBULB.get()),has(AbyssalDecorItems.LIGHTBULB.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.VERDANT_PEARLY_GLASS.get())
                .requires(AbyssalDecorItems.PEARLY_GLASS.get())
                .requires(Items.LIME_DYE)
                .unlockedBy(getHasName(AbyssalDecorItems.PEARLY_GLASS.get()), has(AbyssalDecorItems.PEARLY_GLASS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.AZURE_PEARLY_GLASS.get())
                .requires(AbyssalDecorItems.PEARLY_GLASS.get())
                .requires(Items.LIGHT_BLUE_DYE)
                .unlockedBy(getHasName(AbyssalDecorItems.PEARLY_GLASS.get()), has(AbyssalDecorItems.PEARLY_GLASS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.SUNNY_PEARLY_GLASS.get())
                .requires(AbyssalDecorItems.PEARLY_GLASS.get())
                .requires(Items.YELLOW_DYE)
                .unlockedBy(getHasName(AbyssalDecorItems.PEARLY_GLASS.get()), has(AbyssalDecorItems.PEARLY_GLASS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.FROSTED_GLASS.get())
                .requires(Blocks.GLASS)
                .requires(Blocks.SAND)
                .unlockedBy(getHasName(Blocks.GLASS), has(Blocks.GLASS))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.MOLDWEAVE.get())
                .requires(AbyssalDecorItems.MOLDY_HANGER.get(),4)
                .unlockedBy(getHasName(AbyssalDecorItems.MOLDY_HANGER.get()), has(AbyssalDecorItems.MOLDY_HANGER.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.SHELL.get(),2)
                .requires(AbyssalDecorItems.CLAM.get())
                .unlockedBy(getHasName(AbyssalDecorItems.CLAM.get()), has(AbyssalDecorItems.CLAM.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.GILDED_BLACK_PEARL.get())
                .requires(AbyssalDecorItems.BLACK_PEARL_BLOCK.get())
                .requires(Items.GOLD_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorItems.BLACK_PEARL_BLOCK.get()), has(AbyssalDecorItems.BLACK_PEARL_BLOCK.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssalDecorItems.TRASH_BAG.get())
                .requires(ItemTags.DIRT)
                .requires(Items.STICK)
                .requires(Items.WHEAT_SEEDS)
                .requires(Items.STONE_BUTTON)
                .unlockedBy(getHasName(Items.WHEAT_SEEDS), has(Items.WHEAT_SEEDS))
                .save(consumer);


        smallBars(consumer, AbyssalDecorItems.SMALL_BLACK_PEARL_BARS.get(), AbyssalDecorItems.BLACK_PEARL_BARS.get());
        smallBars(consumer, AbyssalDecorItems.SMALL_BLOOD_CORAL_BARS.get(), AbyssalDecorItems.BLOOD_CORAL_BARS.get());
        smallBars(consumer, AbyssalDecorItems.SMALL_DEEPBRONZE_BARS.get(), AbyssalDecorItems.DEEPBRONZE_BARS.get());
        smallBars(consumer, AbyssalDecorItems.SMALL_SEABRASS_BARS.get(), AbyssalDecorItems.SEABRASS_BARS.get());
        smallBars(consumer, AbyssalDecorItems.SMALL_WHITE_PEARL_BARS.get(), AbyssalDecorItems.WHITE_PEARL_BARS.get());
        smallBars(consumer, AbyssalDecorItems.SMALL_CLEAN_IRON_BARS.get(), Items.IRON_BARS);
        smallBars(consumer, AbyssalDecorItems.SMALL_STONE_BARS.get(), AbyssalDecorItems.STONE_BARS.get());

        carpet(consumer, AbyssalDecorBlocks.MOLDWEAVE_CARPET.get(), AbyssalDecorBlocks.MOLDWEAVE.get());

        slab(consumer,RecipeCategory.MISC,AbyssalDecorBlocks.RIVETED_SEABRASS_SLAB.get(),AbyssalDecorBlocks.RIVETED_SEABRASS.get());

        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, Items.PRISMARINE_CRYSTALS, RecipeCategory.MISC, AbyssalDecorItems.PRISMARINE_CRYSTAL_BLOCK.get());

    }

    protected void smallBars(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 6)
                .pattern("aaa")
                .define('a', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer);
    }

    public static final Map<ExtendedBlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>> BUILDERS =
            ImmutableMap.<ExtendedBlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>>builder()
                    .put(ExtendedBlockFamily.Variant.BUTTON, (p_176733_, p_176734_) -> buttonBuilder(p_176733_, Ingredient.of(p_176734_)))
                    .put(ExtendedBlockFamily.Variant.CHISELED, (p_248037_, p_248038_) -> chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, p_248037_, Ingredient.of(p_248038_)))
                    .put(ExtendedBlockFamily.Variant.CUT, (p_248026_, p_248027_) -> cutBuilder(RecipeCategory.BUILDING_BLOCKS, p_248026_, Ingredient.of(p_248027_)))
                    .put(ExtendedBlockFamily.Variant.DOOR, (p_176714_, p_176715_) -> doorBuilder(p_176714_, Ingredient.of(p_176715_))).put(ExtendedBlockFamily.Variant.CUSTOM_FENCE, (p_176708_, p_176709_) -> fenceBuilder(p_176708_, Ingredient.of(p_176709_))).put(ExtendedBlockFamily.Variant.FENCE, (p_248031_, p_248032_) -> fenceBuilder(p_248031_, Ingredient.of(p_248032_))).put(ExtendedBlockFamily.Variant.CUSTOM_FENCE_GATE, (p_176698_, p_176699_) -> fenceGateBuilder(p_176698_, Ingredient.of(p_176699_))).put(ExtendedBlockFamily.Variant.FENCE_GATE, (p_248035_, p_248036_) -> fenceGateBuilder(p_248035_, Ingredient.of(p_248036_)))
                    .put(ExtendedBlockFamily.Variant.SIGN, (p_176688_, p_176689_) -> signBuilder(p_176688_, Ingredient.of(p_176689_)))
                    .put(ExtendedBlockFamily.Variant.SLAB, (p_248017_, p_248018_) -> slabBuilder(RecipeCategory.BUILDING_BLOCKS, p_248017_, Ingredient.of(p_248018_))).put(ExtendedBlockFamily.Variant.STAIRS, (p_176674_, p_176675_) -> stairBuilder(p_176674_, Ingredient.of(p_176675_)))
                    .put(ExtendedBlockFamily.Variant.PRESSURE_PLATE, (p_248039_, p_248040_) -> pressurePlateBuilder(RecipeCategory.REDSTONE, p_248039_, Ingredient.of(p_248040_)))
                    .put(ExtendedBlockFamily.Variant.POLISHED, (p_248019_, p_248020_) -> polishedBuilder(RecipeCategory.BUILDING_BLOCKS, p_248019_, Ingredient.of(p_248020_))).put(ExtendedBlockFamily.Variant.TRAPDOOR, (p_176638_, p_176639_) -> trapdoorBuilder(p_176638_, Ingredient.of(p_176639_))).put(ExtendedBlockFamily.Variant.WALL, (p_248024_, p_248025_) -> wallBuilder(RecipeCategory.DECORATIONS, p_248024_, Ingredient.of(p_248025_))).build();

    protected static void generateRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, ExtendedBlockFamily family) {
        family.getVariants().forEach((variant, block) -> {
            if (!family.excludeRecipe.contains(variant)) {
                BiFunction<ItemLike, ItemLike, RecipeBuilder> bifunction = BUILDERS.get(variant);
                ItemLike itemlike = getBaseBlock(family, variant);
                if (bifunction != null) {
                    RecipeBuilder recipebuilder = bifunction.apply(block, itemlike);
                    family.getRecipeGroupPrefix().ifPresent((p_176601_) -> recipebuilder.group(p_176601_ + (variant == ExtendedBlockFamily.Variant.CUT ? "" : "_" + variant.getName())));
                    recipebuilder.unlockedBy(family.getRecipeUnlockedBy().orElseGet(() -> getHasName(itemlike)), has(itemlike));
                    recipebuilder.save(finishedRecipeConsumer);
                }

                if (variant == ExtendedBlockFamily.Variant.CRACKED) {
                    smeltingResultFromBase(finishedRecipeConsumer, block, itemlike);
                }
            }
        });
    }

    protected static Block getBaseBlock(ExtendedBlockFamily family, ExtendedBlockFamily.Variant variant) {
        if (variant == ExtendedBlockFamily.Variant.CHISELED) {
            if (!family.getVariants().containsKey(ExtendedBlockFamily.Variant.SLAB)) {
                throw new IllegalStateException("Slab is not defined for the family.");
            } else {
                return family.get(ExtendedBlockFamily.Variant.SLAB);
            }
        } else {
            return family.getBaseBlock();
        }
    }
}
