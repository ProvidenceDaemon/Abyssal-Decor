package net.starrysock.abyssaldecor.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class ModTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CINNAMON = createKey("cinnamon");


    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, AbyssalDecor.id(name));
    }

    static TreeConfiguration.TreeConfigurationBuilder createCinnamon() {
        return createStraightBlobTree(AbyssalDecorBlocks.CINNAMON_LOG.get(),AbyssalDecorBlocks.CINNAMON_LEAVES.get(),
                10, 2, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int heightRandB, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(logBlock), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(leavesBlock.defaultBlockState(),3)
                        .add(AbyssalDecorBlocks.FLOWERING_CINNAMON_LEAVES.get().defaultBlockState(),1)
                ),
                //radius offset, height
                new CinnamonFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(7), 10), new TwoLayersFeatureSize(1, 0, 1));
    }

}
