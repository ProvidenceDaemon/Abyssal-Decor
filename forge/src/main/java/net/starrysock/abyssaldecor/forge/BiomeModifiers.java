package net.starrysock.abyssaldecor.forge;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.registry.ModTags;
import net.starrysock.abyssaldecor.worldgen.ModPlacedFeatures;

public class BiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_AMARANTH = createKey("add_amaranth");

    public static ResourceKey<BiomeModifier> createKey(String key) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, AbyssalDecor.id(key));
    }


    public static void biomeModifiers(BootstapContext<BiomeModifier> context){
        HolderGetter<Biome> holdergetter = context.lookup(ForgeRegistries.Keys.BIOMES);
        HolderGetter<PlacedFeature> holdergetter1 = context.lookup(Registries.PLACED_FEATURE);
        context.register(ADD_AMARANTH,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(holdergetter.getOrThrow(ModTags.Biomes.HAS_AMARANTH),
                HolderSet.direct(holdergetter1.getOrThrow(ModPlacedFeatures.AMARANTH)), GenerationStep.Decoration.VEGETAL_DECORATION));
    }
}
