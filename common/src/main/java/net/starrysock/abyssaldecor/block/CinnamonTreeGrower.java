package net.starrysock.abyssaldecor.block;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.starrysock.abyssaldecor.worldgen.ModTreeFeatures;
import org.jetbrains.annotations.Nullable;

public class CinnamonTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        return ModTreeFeatures.CINNAMON;
    }
}
