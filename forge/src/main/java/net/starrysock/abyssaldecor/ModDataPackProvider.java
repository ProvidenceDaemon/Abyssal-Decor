package net.starrysock.abyssaldecor;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.worldgen.CinnamonFoliagePlacer;
import net.starrysock.abyssaldecor.worldgen.ModTreeFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModDataPackProvider::biomeModifiers)
            .add(Registries.CONFIGURED_FEATURE, ModDataPackProvider::configureFeatures)
            ;


    public ModDataPackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AbyssalDecor.MOD_ID));
    }

    public static void biomeModifiers(BootstapContext<BiomeModifier> context) {

    }

    //1.21.1
    public static void paintings(BootstapContext<PaintingVariant> context) {
    }

    private static TreeConfiguration.TreeConfigurationBuilder createCinnamon() {
        return createStraightBlobTree(AbyssalDecorBlocks.CINNAMON_LOG.get(),AbyssalDecorBlocks.CINNAMON_LEAVES.get(),
                12, 2, 0, 2).ignoreVines();
    }

    public static void configureFeatures(BootstapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, ModTreeFeatures.CINNAMON, Feature.TREE, createCinnamon().build());
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int heightRandB, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(logBlock), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(leavesBlock.defaultBlockState(),3)
                        .add(AbyssalDecorBlocks.FLOWERING_CINNAMON_LEAVES.get().defaultBlockState(),1)
                ),
                //radius offset, height
                new CinnamonFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(7), 12), new TwoLayersFeatureSize(1, 0, 1));
    }


}
