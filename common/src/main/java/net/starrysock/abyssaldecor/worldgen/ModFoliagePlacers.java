package net.starrysock.abyssaldecor.worldgen;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class ModFoliagePlacers {

    public static final RegistrySupplier<FoliagePlacerType<CinnamonFoliagePlacer>> CINNAMON_FOLIAGE_PLACER = AbyssalDecor.FOLIAGE_PLACER_TYPES
            .register("cinnamon_foliage_placer",() -> new FoliagePlacerType<>(CinnamonFoliagePlacer.CODEC));

    public static void register() {
        AbyssalDecor.FOLIAGE_PLACER_TYPES.register();
    }

}
