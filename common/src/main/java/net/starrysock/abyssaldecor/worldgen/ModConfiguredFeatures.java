package net.starrysock.abyssaldecor.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> AMARANTH = createKey("amaranth");
    public static final ResourceKey<ConfiguredFeature<?,?>> BOG_APPLE = createKey("bog_apple");
    public static final ResourceKey<ConfiguredFeature<?,?>> CLAM = createKey("clam");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUCKROOT = createKey("muckroot");
    public static final ResourceKey<ConfiguredFeature<?,?>> SPIDERCORN = createKey("spidercorn");
    public static final ResourceKey<ConfiguredFeature<?,?>> STARFISH = createKey("starfish");

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, AbyssalDecor.id(name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, ModTreeFeatures.CINNAMON, Feature.TREE, ModTreeFeatures.createCinnamon().build());
        FeatureUtils.register(context, AMARANTH,Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(AbyssalDecorBlocks.AMARANTH.get()))));

        FeatureUtils.register(context, BOG_APPLE,Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(96, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(AbyssalDecorBlocks.BOG_APPLE_LEAVES.get().defaultBlockState()
                        .setValue(BlockStateProperties.WATERLOGGED,true))), BlockPredicate.matchesBlocks(Blocks.WATER))));

        FeatureUtils.register(context, CLAM,Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(96, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(AbyssalDecorBlocks.CLAM.get().defaultBlockState()
                        .setValue(BlockStateProperties.WATERLOGGED,true))), BlockPredicate.matchesBlocks(Blocks.WATER))));

        FeatureUtils.register(context, MUCKROOT,Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(AbyssalDecorBlocks.MUCKROOT.get()))));

        FeatureUtils.register(context, SPIDERCORN,Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(AbyssalDecorBlocks.SPIDERCORN.get()))));

        FeatureUtils.register(context, STARFISH,Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(96, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(AbyssalDecorBlocks.STARFISH.get().defaultBlockState()
                        .setValue(BlockStateProperties.WATERLOGGED,true))), BlockPredicate.matchesBlocks(Blocks.WATER))));
    }


//Amaranth, muckroot, spidercorn, bogapples, clams, starfish, and seabrass ore don’t seem to generate
//Spidercorn is meant to appear somewhat uncommonly on cave ceilings under deepslate level
//Amaranth is meant to generate uncommonly in forests and dark forests
//Muckroot should generate in plains and all forests
//Bogapples should generate underwater in swamps and mangrove swamps
//Clams should generate in any ocean
//Starfish should generate (with a random variant) in all ocean biomes. Bonus points if they can generate clinging to walls as well as the ground
//Seabrass ore should spawn in gravel on ocean floors
}
