package net.starrysock.abyssaldecor;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.starrysock.abyssaldecor.block.AmaranthBlock;
import net.starrysock.abyssaldecor.block.TallAmaranthBlock;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

class Datagen {
    static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeServer()) {
            BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(output, lookupProvider, helper);
            generator.addProvider(true, blockTagsProvider);
        }
        if (event.includeClient()) {
            generator.addProvider(true, new ModBlockStateProvider(output, helper));
        }
    }

    public static class ModBlockTagProvider extends BlockTagsProvider {

        public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, AbyssalDecor.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            tag(ModTags.Blocks.MUCKROOT_GROWABLE).add(Blocks.FARMLAND);
            tag(ModTags.Blocks.AMARANTH_GROWABLE).addTag(BlockTags.DIRT);
        }
    }

    public static class ModBlockStateProvider extends BlockStateProvider {

        public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, AbyssalDecor.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            getVariantBuilder(AbyssalDecorBlocks.AMARANTH.get()).forAllStates(
                    blockState -> {
                        int age = blockState.getValue(AmaranthBlock.AGE);
                        ModelFile modelFile = models().getExistingFile(modLoc("block/amaranth_stage" + age));
                        return ConfiguredModel.builder().modelFile(modelFile).build();
                    }
            );

            getVariantBuilder(AbyssalDecorBlocks.TALL_AMARANTH.get()).forAllStates(
                    blockState -> {
                        int age = blockState.getValue(TallAmaranthBlock.AGE);
                        DoubleBlockHalf half = blockState.getValue(TallAmaranthBlock.HALF);

                        ModelFile modelFile = models().getExistingFile(modLoc("block/tall_amaranth_stage" + age+"_"+half.getSerializedName()));
                        return ConfiguredModel.builder().modelFile(modelFile).build();
                    }
            );

            simpleBlock(AbyssalDecorBlocks.WISTERIA_PETALS.get());
            simpleBlock(AbyssalDecorBlocks.ELDER_WISTERIA_PETALS.get());
            simpleBlock(AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES.get());
        }
    }
}
