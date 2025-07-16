package net.starrysock.abyssaldecor.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class ModTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CINNAMON = createKey("cinnamon");


    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, AbyssalDecor.id(name));
    }

}
