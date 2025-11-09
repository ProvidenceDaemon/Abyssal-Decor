package net.starrysock.abyssaldecor.datagen;

import com.google.common.collect.ImmutableMap;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
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

        ExtendedBlockFamilies.getAllFamilies().filter(family -> family.shouldGenerateRecipe(FeatureFlags.VANILLA_SET)).forEach(
                family -> generateRecipes(consumer, family));

        planksFromLog(consumer, AbyssalDecorBlocks.WHITEWOOD_PLANKS.get(), ModTags.Items.WHITEWOOD_LOGS, 4);
        woodFromLogs(consumer, AbyssalDecorBlocks.WHITEWOOD_WOOD.get(), AbyssalDecorBlocks.WHITEWOOD_LOG.get());


        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE.get(), AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.FROSTED_GLASS_PANE.get(), AbyssalDecorBlocks.FROSTED_GLASS.get());

        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE.get(), AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK.get());

        smeltingResultFromBase(consumer,AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get(), Blocks.GLOWSTONE);
        smeltingResultFromBase(consumer,AbyssalDecorItems.POPPED_SPIDERCORN.get(), AbyssalDecorItems.SPIDERCORN.get());

        smeltingResultFromBase(consumer,AbyssalDecorItems.LAVENTINE.get(), Blocks.AMETHYST_BLOCK);

        smeltingResultFromBase(consumer,AbyssalDecorItems.LAVENTINE_GLASS.get(), AbyssalDecorBlocks.LAVENTINE.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.LAVENTINE_GLASS_PANE.get(), AbyssalDecorBlocks.LAVENTINE_GLASS.get());

        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.VERMILION_PANE.get(), AbyssalDecorBlocks.VERMILION_BLOCK.get());
        smeltingResultFromBase(consumer,AbyssalDecorBlocks.VERMILION_BLOCK.get(), Blocks.REDSTONE_BLOCK);

        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE.get(), AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.FRAMED_VERMILION_PANE.get(), AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK.get());
        stainedGlassPaneFromStainedGlass(consumer, AbyssalDecorBlocks.FRAMED_PITCHGLASS_PANE.get(), AbyssalDecorBlocks.FRAMED_PITCHGLASS.get());

        smeltingResultFromBase(consumer,AbyssalDecorBlocks.INACTIVE_MOLDY_HANGER.get(), AbyssalDecorBlocks.MOLDY_HANGER.get());
        smeltingResultFromBase(consumer,AbyssalDecorBlocks.INACTIVE_MOLD.get(), AbyssalDecorBlocks.BLACK_MOLD.get());
        smeltingResultFromBase(consumer,AbyssalDecorBlocks.CRACKED_BRICKS.get(), Blocks.BRICKS);
        smeltingResultFromBase(consumer,AbyssalDecorBlocks.CRACKED_PEARL_TILES.get(), AbyssalDecorBlocks.WHITE_PEARL_TILES.get());
        smeltingResultFromBase(consumer,AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_BLOCK.get(), AbyssalDecorBlocks.WHITE_PEARL_BLOCK.get());

        smeltingResultFromBase(consumer,AbyssalDecorBlocks.ABYSSAL_LANTERN.get(), AbyssalDecorBlocks.SEABRASS_LAMP.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorBlocks.IRON_LANTERN.get())
                .pattern("aaa")
                .pattern("aba")
                .pattern("aaa")
                .define('a', Items.IRON_NUGGET)
                .define('b',Blocks.GLOWSTONE)
                .unlockedBy(getHasName(Blocks.GLOWSTONE),has(Blocks.GLOWSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE.get())
                .pattern("aba")
                .pattern("bab")
                .pattern("aba")
                .define('a',AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get())
                .define('b', Items.IRON_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get()),has(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorBlocks.FRAMED_PITCHGLASS.get())
                .pattern("aba")
                .pattern("bab")
                .pattern("aba")
                .define('a',AbyssalDecorBlocks.PITCHGLASS.get())
                .define('b', Items.IRON_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorBlocks.PITCHGLASS.get()),has(AbyssalDecorBlocks.PITCHGLASS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK.get())
                .pattern("aba")
                .pattern("bab")
                .pattern("aba")
                .define('a',AbyssalDecorBlocks.VERMILION_BLOCK.get())
                .define('b', Items.IRON_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorBlocks.VERMILION_BLOCK.get()),has(AbyssalDecorBlocks.VERMILION_BLOCK.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorBlocks.TUBE_LAMP.get())
                .pattern("aaa")
                .define('a', AbyssalDecorBlocks.LIGHTBULB.get())
                .unlockedBy(getHasName(AbyssalDecorBlocks.LIGHTBULB.get()),has(AbyssalDecorBlocks.LIGHTBULB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorBlocks.LION_STATUE.get())
                .pattern(" a ")
                .pattern(" b ")
                .pattern("ccc")
                .define('a',Blocks.POLISHED_ANDESITE)
                .define('b', Items.IRON_NUGGET)
                .define('c', Items.IRON_NUGGET)
                .unlockedBy(getHasName(AbyssalDecorBlocks.VERMILION_BLOCK.get()),has(AbyssalDecorBlocks.VERMILION_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AbyssalDecorBlocks.BLACK_PEARL_BARS.get(), 16)
                .define('#', AbyssalDecorBlocks.BLACK_PEARL.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(AbyssalDecorBlocks.BLACK_PEARL.get()), has(AbyssalDecorBlocks.BLACK_PEARL.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,AbyssalDecorBlocks.NITHING_POLE.get())
                .pattern("a")
                .pattern("b")
                .pattern("b")
                .define('a', Items.SKELETON_SKULL)
                .define('b',Items.STICK)
                .unlockedBy(getHasName(Items.SKELETON_SKULL),has(Items.SKELETON_SKULL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.VELVET_BARRIER.get())
                .pattern("aba")
                .pattern("a a")
                .pattern("a a")
                .define('a', Items.GOLD_NUGGET)
                .define('b',AbyssalDecorItems.VELVET.get())
                .unlockedBy(getHasName(AbyssalDecorItems.VELVET.get()),has(AbyssalDecorItems.VELVET.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssalDecorItems.SCRIMSHAW_ALTAR.get())
                .pattern(" a ")
                .pattern("bcb")
                .pattern("ded")
                .define('a', AbyssalDecorItems.SCRIMSHAW.get())
                .define('b',Items.STRING)
                .define('c',Items.CHEST)
                .define('d',Items.HONEYCOMB)
                .define('e',AbyssalDecorBlocks.GILDED_VELVET_CARPET.get())
                .unlockedBy(getHasName(AbyssalDecorItems.SCRIMSHAW.get()),has(AbyssalDecorItems.SCRIMSHAW.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,AbyssalDecorBlocks.WHITEWOOD_LOG.get(),8)
                .requires(Ingredient.of(ItemTags.LOGS),8)
                .requires(AbyssalDecorItems.WHITE_PEARL.get())
                .unlockedBy(getHasName(AbyssalDecorItems.WHITE_PEARL.get()),has(AbyssalDecorItems.WHITE_PEARL.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,AbyssalDecorItems.SPIDERCORN_TORTILLA.get())
                .requires(Ingredient.of(AbyssalDecorItems.SPIDERCORN.get()),3)
                .unlockedBy(getHasName(AbyssalDecorItems.SPIDERCORN.get()),has(AbyssalDecorItems.SPIDERCORN.get()))
                .save(consumer);

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

        AbyssalDecorItems.WALLPAPERS.map().forEach((key, value) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, value.get())
                .requires(DyeItem.byColor(key))
                .requires(Items.PAPER)
                .requires(ItemTags.PLANKS)
                .unlockedBy(getHasName(DyeItem.byColor(key)),has(DyeItem.byColor(key)))
                .save(consumer));

    }


    public static final Map<ExtendedBlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>> BUILDERS = 
            ImmutableMap.<ExtendedBlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>>builder().put(ExtendedBlockFamily.Variant.BUTTON, (p_176733_, p_176734_) -> {
        return buttonBuilder(p_176733_, Ingredient.of(p_176734_));
    }).put(ExtendedBlockFamily.Variant.CHISELED, (p_248037_, p_248038_) -> {
        return chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, p_248037_, Ingredient.of(p_248038_));
    }).put(ExtendedBlockFamily.Variant.CUT, (p_248026_, p_248027_) -> {
        return cutBuilder(RecipeCategory.BUILDING_BLOCKS, p_248026_, Ingredient.of(p_248027_));
    }).put(ExtendedBlockFamily.Variant.DOOR, (p_176714_, p_176715_) -> {
        return doorBuilder(p_176714_, Ingredient.of(p_176715_));
    }).put(ExtendedBlockFamily.Variant.CUSTOM_FENCE, (p_176708_, p_176709_) -> {
        return fenceBuilder(p_176708_, Ingredient.of(p_176709_));
    }).put(ExtendedBlockFamily.Variant.FENCE, (p_248031_, p_248032_) -> {
        return fenceBuilder(p_248031_, Ingredient.of(p_248032_));
    }).put(ExtendedBlockFamily.Variant.CUSTOM_FENCE_GATE, (p_176698_, p_176699_) -> {
        return fenceGateBuilder(p_176698_, Ingredient.of(p_176699_));
    }).put(ExtendedBlockFamily.Variant.FENCE_GATE, (p_248035_, p_248036_) -> {
        return fenceGateBuilder(p_248035_, Ingredient.of(p_248036_));
    }).put(ExtendedBlockFamily.Variant.SIGN, (p_176688_, p_176689_) -> {
        return signBuilder(p_176688_, Ingredient.of(p_176689_));
    }).put(ExtendedBlockFamily.Variant.SLAB, (p_248017_, p_248018_) -> {
        return slabBuilder(RecipeCategory.BUILDING_BLOCKS, p_248017_, Ingredient.of(p_248018_));
    }).put(ExtendedBlockFamily.Variant.STAIRS, (p_176674_, p_176675_) -> {
        return stairBuilder(p_176674_, Ingredient.of(p_176675_));
    }).put(ExtendedBlockFamily.Variant.PRESSURE_PLATE, (p_248039_, p_248040_) -> {
        return pressurePlateBuilder(RecipeCategory.REDSTONE, p_248039_, Ingredient.of(p_248040_));
    }).put(ExtendedBlockFamily.Variant.POLISHED, (p_248019_, p_248020_) -> {
        return polishedBuilder(RecipeCategory.BUILDING_BLOCKS, p_248019_, Ingredient.of(p_248020_));
    }).put(ExtendedBlockFamily.Variant.TRAPDOOR, (p_176638_, p_176639_) -> {
        return trapdoorBuilder(p_176638_, Ingredient.of(p_176639_));
    }).put(ExtendedBlockFamily.Variant.WALL, (p_248024_, p_248025_) -> {
        return wallBuilder(RecipeCategory.DECORATIONS, p_248024_, Ingredient.of(p_248025_));
    }).build();

    protected static void generateRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, ExtendedBlockFamily family) {
        family.getVariants().forEach((p_176529_, p_176530_) -> {
            BiFunction<ItemLike, ItemLike, RecipeBuilder> bifunction = BUILDERS.get(p_176529_);
            ItemLike itemlike = getBaseBlock(family, p_176529_);
            if (bifunction != null) {
                RecipeBuilder recipebuilder = bifunction.apply(p_176530_, itemlike);
                family.getRecipeGroupPrefix().ifPresent((p_176601_) -> {
                    recipebuilder.group(p_176601_ + (p_176529_ == ExtendedBlockFamily.Variant.CUT ? "" : "_" + p_176529_.getName()));
                });
                recipebuilder.unlockedBy(family.getRecipeUnlockedBy().orElseGet(() -> {
                    return getHasName(itemlike);
                }), has(itemlike));
                recipebuilder.save(finishedRecipeConsumer);
            }

            if (p_176529_ == ExtendedBlockFamily.Variant.CRACKED) {
                smeltingResultFromBase(finishedRecipeConsumer, p_176530_, itemlike);
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
