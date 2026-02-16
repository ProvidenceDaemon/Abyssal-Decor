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
    public static final ResourceKey<PlacedFeature> MUCKROOT = createKey("muckroot");
    public static final ResourceKey<PlacedFeature> SPIDERCORN = createKey("spidercorn");

    public static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, AbyssalDecor.id(key));
    }

    public static final PlacementModifier BELOW_DEEPSLATE_LEVEL =HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0));

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context, AMARANTH, holdergetter.getOrThrow(ModConfiguredFeatures.AMARANTH), RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        PlacementUtils.register(context, MUCKROOT, holdergetter.getOrThrow(ModConfiguredFeatures.MUCKROOT), RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        PlacementUtils.register(context, SPIDERCORN, holdergetter.getOrThrow(ModConfiguredFeatures.SPIDERCORN), RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), BELOW_DEEPSLATE_LEVEL, BiomeFilter.biome());

    }
}
