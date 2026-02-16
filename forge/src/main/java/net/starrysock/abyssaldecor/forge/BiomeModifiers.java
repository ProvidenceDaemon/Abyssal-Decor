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
    public static final ResourceKey<BiomeModifier> ADD_BOG_APPLE = createKey("add_bog_apple");
    public static final ResourceKey<BiomeModifier> ADD_CLAM = createKey("add_clam");
    public static final ResourceKey<BiomeModifier> ADD_MUCKROOT = createKey("add_muckroot");
    public static final ResourceKey<BiomeModifier> ADD_SPIDERCORN = createKey("add_spidercorn");
    public static final ResourceKey<BiomeModifier> ADD_STARFISH = createKey("add_starfish");
    public static final ResourceKey<BiomeModifier> ADD_SEABRASS_ORE = createKey("add_seabrass_ore");

    public static ResourceKey<BiomeModifier> createKey(String key) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, AbyssalDecor.id(key));
    }


    public static void biomeModifiers(BootstapContext<BiomeModifier> context){
        HolderGetter<Biome> holdergetter = context.lookup(ForgeRegistries.Keys.BIOMES);
        HolderGetter<PlacedFeature> holdergetter1 = context.lookup(Registries.PLACED_FEATURE);
        context.register(ADD_AMARANTH,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(holdergetter.getOrThrow(ModTags.Biomes.HAS_AMARANTH),
                HolderSet.direct(holdergetter1.getOrThrow(ModPlacedFeatures.AMARANTH)), GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_BOG_APPLE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(holdergetter.getOrThrow(ModTags.Biomes.HAS_BOG_APPLE),
                HolderSet.direct(holdergetter1.getOrThrow(ModPlacedFeatures.BOG_APPLE)), GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CLAM,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(holdergetter.getOrThrow(ModTags.Biomes.HAS_CLAM),
                HolderSet.direct(holdergetter1.getOrThrow(ModPlacedFeatures.CLAM)), GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_MUCKROOT,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(holdergetter.getOrThrow(ModTags.Biomes.HAS_MUCKROOT),
                HolderSet.direct(holdergetter1.getOrThrow(ModPlacedFeatures.MUCKROOT)), GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_SPIDERCORN,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(holdergetter.getOrThrow(ModTags.Biomes.HAS_SPIDERCORN),
                HolderSet.direct(holdergetter1.getOrThrow(ModPlacedFeatures.SPIDERCORN)), GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_STARFISH,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(holdergetter.getOrThrow(ModTags.Biomes.HAS_STARFISH),
                HolderSet.direct(holdergetter1.getOrThrow(ModPlacedFeatures.STARFISH)), GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_SEABRASS_ORE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(holdergetter.getOrThrow(ModTags.Biomes.HAS_SEABRASS_ORE),
                HolderSet.direct(holdergetter1.getOrThrow(ModPlacedFeatures.SEABRASS_ORE)), GenerationStep.Decoration.UNDERGROUND_ORES));
    }
}
