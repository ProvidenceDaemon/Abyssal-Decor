package net.starrysock.abyssaldecor.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> AMARANTH = createKey("amaranth");
    public static final ResourceKey<PlacedFeature> BOG_APPLE = createKey("bog_apple");
    public static final ResourceKey<PlacedFeature> CLAM = createKey("clam");
    public static final ResourceKey<PlacedFeature> MUCKROOT = createKey("muckroot");
    public static final ResourceKey<PlacedFeature> SPIDERCORN = createKey("spidercorn");
    public static final ResourceKey<PlacedFeature> STARFISH = createKey("starfish");
    public static final ResourceKey<PlacedFeature> SEABRASS_ORE = createKey("seabrass_ore");

    public static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, AbyssalDecor.id(key));
    }

    public static final PlacementModifier BELOW_DEEPSLATE_LEVEL =HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0));

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context, AMARANTH, holdergetter.getOrThrow(ModConfiguredFeatures.AMARANTH), RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        PlacementUtils.register(context, BOG_APPLE, holdergetter.getOrThrow(ModConfiguredFeatures.BOG_APPLE), RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        PlacementUtils.register(context, CLAM, holdergetter.getOrThrow(ModConfiguredFeatures.CLAM), RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        PlacementUtils.register(context, MUCKROOT, holdergetter.getOrThrow(ModConfiguredFeatures.MUCKROOT), RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        PlacementUtils.register(context, SPIDERCORN, holdergetter.getOrThrow(ModConfiguredFeatures.SPIDERCORN), RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), BELOW_DEEPSLATE_LEVEL, BiomeFilter.biome());
        PlacementUtils.register(context, STARFISH, holdergetter.getOrThrow(ModConfiguredFeatures.STARFISH), RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        PlacementUtils.register(context, SEABRASS_ORE, holdergetter.getOrThrow(ModConfiguredFeatures.SEABRASS_ORE), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    }
}
