package net.starrysock.abyssaldecor;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.starrysock.abyssaldecor.block.AmaranthBlock;
import net.starrysock.abyssaldecor.block.TallAmaranthBlock;
import net.starrysock.abyssaldecor.block.DirectionalInteractibleLampBlock;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;

import java.util.Arrays;
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
            generator.addProvider(true, new ModItemModelProvider(output, helper));
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

    static class ModItemModelProvider extends ItemModelProvider {

        public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
            super(output, AbyssalDecor.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            generatedItem(AbyssalDecorItems.AMARANTH_SEEDS.get());
            generatedItem(AbyssalDecorItems.AMARANTH_PINNACLE.get());
            simpleBlockItem(AbyssalDecorItems.AMARANTH_CRATE.get());
            simpleBlockItem(AbyssalDecorItems.WISTERIA_PETALS.get());
            simpleBlockItem(AbyssalDecorItems.ELDER_WISTERIA_PETALS.get());
            simpleBlockItem(AbyssalDecorItems.ELDER_WISTERIA_LEAVES.get());

            simpleBlockItem(AbyssalDecorItems.ANCIENT_BIRCH_LOG.get());
            simpleBlockItem(AbyssalDecorItems.STRIPPED_ANCIENT_BIRCH_LOG.get());
            simpleBlockItem(AbyssalDecorItems.FOXY_PILLAR.get());

            simpleBlockItem(AbyssalDecorItems.LIGHTBULB.get());

            generatedItem(AbyssalDecorItems.DUSTY_CD.get());

            simpleBlockItem(AbyssalDecorItems.WALL_BULB_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.TUBE_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.IRON_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.FLOWER_LAMP.get());
        }


        protected void simpleBlockItem(Item item, ResourceLocation loc) {
            String s = BuiltInRegistries.ITEM.getKey(item).toString();
            getBuilder(s)
                    .parent(getExistingFile(loc));
        }

        protected String name(Item item) {
            return BuiltInRegistries.ITEM.getKey(item).getPath();
        }

        protected void simpleBlockItem(Item item) {
            simpleBlockItem(item,modLoc("block/" + name(item)));
        }


        private void generatedItem(Item item ,ResourceLocation texture) {
            String path = name(item);
            singleTexture(path, mcLoc("item/generated"),
                    "layer0", texture);
        }

        private void generatedItem(Item item) {
            generatedItem(item,modLoc("item/"+name(item)));
        }

        private void generatedItemBlockTexture(Item item) {
            generatedItem(item,modLoc("block/"+name(item)));
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

            getVariantBuilder(AbyssalDecorBlocks.LIGHTBULB.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);
                Direction orientation = blockState.getValue(DirectionalInteractibleLampBlock.FACING);
                ModelFile modelFile = models().getExistingFile(modLoc("block/lightbulb"+(lit? "_lit":"")));
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);

            getVariantBuilder(AbyssalDecorBlocks.IRON_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);
                Direction orientation = blockState.getValue(DirectionalInteractibleLampBlock.FACING);
                ModelFile modelFile = models().getExistingFile(modLoc("block/lightbulb"+(lit? "_lit":"")));
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            });

            simpleBlock(AbyssalDecorBlocks.WISTERIA_PETALS.get());
            simpleBlock(AbyssalDecorBlocks.ELDER_WISTERIA_PETALS.get());
            simpleBlock(AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES.get());
        }

        static ConfiguredModel[] allRotations(ModelFile base) {
            return Arrays.stream(Direction.values()).map(direction -> {
                Vector2i vector2i = getRotation(direction);
               return new ConfiguredModel(base,vector2i.x,vector2i.y,true);
            }).toArray(ConfiguredModel[]::new);
        }

        static Vector2i getRotation(Direction direction) {
            return switch (direction) {
                case DOWN -> new Vector2i(180,0);
                case UP -> new Vector2i();
                case NORTH -> new Vector2i(90,0);
                case SOUTH -> new Vector2i(90,180);
                case WEST -> new Vector2i(90,270);
                case EAST -> new Vector2i(90,90);
            };
        }
    }
}
