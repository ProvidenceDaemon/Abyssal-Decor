package net.starrysock.abyssaldecor.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.forge.BiomeModifiers;
import net.starrysock.abyssaldecor.worldgen.ModConfiguredFeatures;
import net.starrysock.abyssaldecor.worldgen.ModPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifiers::biomeModifiers)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            ;


    public ModDataPackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AbyssalDecor.MOD_ID));
    }

    //1.21.1
    public static void paintings(BootstapContext<PaintingVariant> context) {
    }
}
