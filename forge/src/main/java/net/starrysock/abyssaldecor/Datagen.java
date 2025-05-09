package net.starrysock.abyssaldecor;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.starrysock.abyssaldecor.block.*;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;

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
            generator.addProvider(true,ModLootTableProvider.create(output));
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

            simpleBlockItem(AbyssalDecorItems.ANCIENT_BIRCH_LOG.get());
            simpleBlockItem(AbyssalDecorItems.STRIPPED_ANCIENT_BIRCH_LOG.get());
            simpleBlockItem(AbyssalDecorItems.FOXY_PILLAR.get());

            simpleBlockItem(AbyssalDecorItems.LIGHTBULB.get());

            generatedItem(AbyssalDecorItems.DUSTY_CD.get());

            simpleBlockItem(AbyssalDecorItems.WALL_BULB_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.TUBE_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.IRON_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.FLOWER_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.FROSTED_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.QUARTZ_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.JADE_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.SEAGLASS_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.BLAZE_LAMP.get());

            simpleBlockItem(AbyssalDecorItems.RAINBOW_LAMP.get());
            simpleBlockItem(AbyssalDecorItems.SEABRASS_ORE.get());
            simpleBlockItem(AbyssalDecorItems.FRESNEL_LAMP.get());

            simpleBlockItem(AbyssalDecorItems.JADE_LANTERN.get());

            simpleBlockItem(AbyssalDecorItems.VELVET_BARRIER.get(),modLoc("block/velvet_barrier_top"));
            simpleBlockItem(AbyssalDecorItems.IRON_BARRIER.get(),modLoc("block/iron_barrier_top"));

            generatedItem(AbyssalDecorItems.LION_STATUE.get());
            generatedItem(AbyssalDecorItems.GARGOYLE.get());
            generatedItem(AbyssalDecorItems.NITHING_POLE.get());
            generatedItem(AbyssalDecorItems.TELESCOPE.get());
            generatedItemBlockTexture(AbyssalDecorItems.HANGING_WEB.get());
            generatedItem(AbyssalDecorItems.DANGLING_WEB.get(),modLoc("block/dangling_web_lower"));
            simpleBlockItem(AbyssalDecorItems.PRISMARINE_CRYSTAL_BLOCK.get());
            generatedItem(AbyssalDecorItems.PRISMARINE_CRYSTAL_PANE.get(),modLoc("block/prismarine_crystal_block"));
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

            getVariantBuilder(AbyssalDecorBlocks.FROSTED_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);
                Direction orientation = blockState.getValue(DirectionalInteractibleLampBlock.FACING);

                ResourceLocation texture0 = modLoc("block/frostedceilinglamp"+(lit? "lit":""));

                ModelFile modelFile = models().withExistingParent("block/frosted_lamp"+(lit? "_lit":""),
                                modLoc("custom/frostedceilinglamp"))
                        .texture("all",texture0)
                        .texture("particle",texture0)
                        .texture("0",texture0)
                        .texture("1",modLoc("block/frostedceilinglamp2"));
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);

            getVariantBuilder(AbyssalDecorBlocks.RAINBOW_LAMP.get()).forAllStatesExcept(blockState -> {
                Direction orientation = blockState.getValue(DirectionalInteractibleLampBlock.FACING);

                ResourceLocation texture0 = modLoc("block/rainbow2");
                ResourceLocation texture1 = modLoc("block/rainbowlampbase");

                ModelFile modelFile = models().withExistingParent("block/rainbow_lamp",
                                modLoc("custom/rainbowlamp"))
                        .texture("all",texture0)
                        .texture("particle",texture0)
                        .texture("1",texture0)
                        .texture("2",texture1);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);


            getVariantBuilder(AbyssalDecorBlocks.SEAGLASS_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);
                Direction orientation = blockState.getValue(DirectionalInteractibleLampBlock.FACING);

                ResourceLocation texture0 = modLoc("block/seaglasslamp"+(lit? "lit":""));

                ModelFile modelFile = models().withExistingParent("block/seaglass_lamp"+(lit? "_lit":""),
                                modLoc("custom/seaglasslamp"))
                        .texture("all",texture0)
                        .texture("particle",texture0)
                        .texture("0",texture0);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);



            getVariantBuilder(AbyssalDecorBlocks.IRON_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);

                //    "all": "abyssaldecor:block/ironlamp1",
                //    "particle": "abyssaldecor:block/ironlamp1",
                //    "0": "abyssaldecor:block/ironlamp1",
                //    "1": "abyssaldecor:block/ironlamp2" add lit

                ModelFile modelFile = models().withExistingParent("block/iron_lamp"+(lit? "_lit":""),
                        modLoc("custom/ironlampceiling"))
                        .texture("all",modLoc("block/ironlamp1"))
                        .texture("particle",modLoc("block/ironlamp1"))
                        .texture("0",modLoc("block/ironlamp1"))
                        .texture("1",modLoc("block/ironlamp2"+(lit? "lit":"")));
                return ConfiguredModel.builder().modelFile(modelFile).build();
            },BlockStateProperties.WATERLOGGED);

            getVariantBuilder(AbyssalDecorBlocks.WALL_IRON_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);
                Direction orientation = blockState.getValue(HorizontalLampBlock.FACING);

                ModelFile modelFile = models().withExistingParent("block/wall_iron_lamp"+(lit? "_lit":""),
                                modLoc("custom/wallironlamp"))
                        .texture("all",modLoc("block/ironlamp1"))
                        .texture("particle",modLoc("block/ironlamp1"))
                        .texture("0",modLoc("block/ironlamp1"))
                        .texture("1",modLoc("block/ironlamp2"+(lit? "lit":"")));
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).build();
            },BlockStateProperties.WATERLOGGED);

            wallLamp(AbyssalDecorBlocks.FLOWER_LAMP.get(),modLoc("custom/flowerlamp"),modLoc("block/flower_lamp"));

            getVariantBuilder(AbyssalDecorBlocks.TUBE_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);
                Direction orientation = blockState.getValue(HorizontalLampBlock.FACING);

                ResourceLocation texture = modLoc("block/tubelamp");

                ModelFile modelFile = models().withExistingParent("block/tube_lamp"+(lit? "_lit":""),
                                modLoc("custom/tubelamp"))
                        .texture("all",texture)
                        .texture("particle",texture)
                        .texture("0",texture);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).build();
            },BlockStateProperties.WATERLOGGED);

            simplestBlockWithItem(AbyssalDecorBlocks.WISTERIA_PETALS.get());
            simplestBlockWithItem(AbyssalDecorBlocks.ELDER_WISTERIA_PETALS.get());
            simplestBlockWithItem(AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES.get());

            logBlock(AbyssalDecorBlocks.ANCIENT_BIRCH_LOG.get());
            logBlock(AbyssalDecorBlocks.STRIPPED_ANCIENT_BIRCH_LOG.get());
            logBlock(AbyssalDecorBlocks.FOXY_PILLAR.get());

            lamp(AbyssalDecorBlocks.QUARTZ_LAMP.get(),modLoc("custom/floorgaslamp"),modLoc("block/quartz_lamp"));
            wallLamp(AbyssalDecorBlocks.WALL_QUARTZ_LAMP.get(),modLoc("custom/wallgaslamp"),modLoc("block/wall_quartz_lamp"));
            lamp(AbyssalDecorBlocks.CEILING_QUARTZ_LAMP.get(),modLoc("custom/ceilingquartzlamp"),modLoc("block/ceiling_quartz_lamp"));
            wallLamp(AbyssalDecorBlocks.JADE_LAMP.get(),modLoc("custom/jadelamp"),modLoc("block/jade_lamp"));
            wallLamp(AbyssalDecorBlocks.WALL_JADE_LAMP.get(),modLoc("custom/walljadelamp"),modLoc("block/wall_jade_lamp"));

            getVariantBuilder(AbyssalDecorBlocks.BLAZE_LAMP.get()).forAllStatesExcept(blockState -> {
                Direction orientation = blockState.getValue(HorizontalLampBlock.FACING);

                ResourceLocation texture0 = modLoc("block/blazelamp");

                ModelFile modelFile = models().withExistingParent("block/blaze_lamp",
                                modLoc("custom/blazelamp"))
                        .texture("all",texture0)
                        .texture("particle",texture0)
                        .texture("0",texture0);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).build();
            },BlockStateProperties.WATERLOGGED);

            simplestBlockWithItem(AbyssalDecorBlocks.SEABRASS_ORE.get());

            lamp(AbyssalDecorBlocks.JADE_LANTERN.get(),mcLoc("block/cube_all"),modLoc("block/jade_lantern"));

            //simplestBlockWithItem(AbyssalDecorBlocks.IRON_LANTERN.get());


            //buttonLampBlock(AbyssalDecorBlocks.BULKHEAD_LAMP.get(),);

            barrierPoleBlock(AbyssalDecorBlocks.VELVET_BARRIER.get(),modLoc("custom/velvetbarrierbottom"),modLoc("custom/velvetbarriertop"),
                    modLoc("block/velvetbarrier1"));

            barrierPoleBlock(AbyssalDecorBlocks.IRON_BARRIER.get(),modLoc("custom/ironbarrierbottom"),modLoc("custom/ironbarriertop"),
                    modLoc("block/ironbarrier1"));

         //   barrierPoleBlock(AbyssalDecorBlocks.ROPE_BARRIER.get(),modLoc("custom/ironbarrierbottom"),modLoc("custom/ironbarriertop"),
          //          modLoc("block/velvetbarrier1"));

        //    barrierPoleBlock(AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get(),modLoc("custom/ironbarrierbottom"),modLoc("custom/ironbarriertop"),
        //            modLoc("block/velvetbarrier1"));

            starfish(AbyssalDecorBlocks.STARFISH.get());
            driedStarfish(AbyssalDecorBlocks.DRIED_STARFISH.get());
            simpleBlock(AbyssalDecorBlocks.STARLIGHT.get(),models().withExistingParent("block/starlight","block/cross").texture("cross","block/starlight"));

            lionStatue(AbyssalDecorBlocks.LION_STATUE.get());
            lionStatue(AbyssalDecorBlocks.NITHING_POLE.get());
            lionStatue(AbyssalDecorBlocks.TELESCOPE.get());

            simpleBlock(AbyssalDecorBlocks.HANGING_WEB.get(),models().withExistingParent("block/hanging_web","block/cross").texture("cross","block/hanging_web"));
            horizontalBlock(AbyssalDecorBlocks.WALL_HANGING_WEB.get(),models()
                    .withExistingParent("block/wall_hanging_web","block/vine").texture("particle",modLoc("block/hanging_web"))
                    .texture("vine",modLoc("block/hanging_web")));

            doubleBlock(AbyssalDecorBlocks.DANGLING_WEB.get(),models()
                    .withExistingParent("block/dangling_web_upper","block/cross").texture("cross","block/dangling_web_upper"),models()
                    .withExistingParent("block/dangling_web_lower","block/cross").texture("cross","block/dangling_web_lower"));

            lionStatue(AbyssalDecorBlocks.WALL_DANGLING_WEB.get());

            simpleBlock(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK.get());
            paneBlock(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE.get(),modLoc("block/prismarine_crystal_block"),modLoc("block/prismarine_crystal_block"));

        }


        void doubleBlock(Block block,ModelFile top,ModelFile bottom) {
            getVariantBuilder(block).forAllStates(
                    blockState -> {
                        DoubleBlockHalf half = blockState.getValue(TallAmaranthBlock.HALF);

                        ModelFile modelFile = half == DoubleBlockHalf.LOWER ? bottom : top;
                        return ConfiguredModel.builder().modelFile(modelFile).build();
                    }
            );
        }

        void lionStatue(Block block) {
            String name = name(block);
            getVariantBuilder(block).forAllStates(
                    blockState -> {
                        Direction direction = blockState.getValue(LionStatueBlock.FACING);
                        DoubleBlockHalf half = blockState.getValue(TallAmaranthBlock.HALF);

                        ModelFile modelFile = models().getExistingFile(modLoc("block/"+name+"_"+half.getSerializedName()));
                        return ConfiguredModel.builder().modelFile(modelFile).rotationY(getRotation(direction).y).build();
                    }
            );
        }

        void starfish(Block block) {
            getVariantBuilder(block)
                    .forAllStatesExcept(blockState -> {
                        DyeColor color = blockState.getValue(StarfishBlock.COLOR);
                        int count = blockState.getValue(StarfishBlock.COUNT);
                        String s = "block/"+count+"_"+color.getName()+"_starfish";
                        ModelFile file = models().withExistingParent(s,mcLoc("block/lily_pad"))
                                .texture("texture",s).texture("particle",s);

                        Direction orientation = blockState.getValue(AbstractDirectionalBlock.FACING);
                        Vector2i vector2i = getRotation(orientation);
                        return ConfiguredModel.builder().modelFile(file).rotationY(vector2i.y).build();
                    });
        }

        void driedStarfish(Block block) {
            getVariantBuilder(block)
                    .forAllStatesExcept(blockState -> {
                        int count = blockState.getValue(StarfishBlock.COUNT);
                        String s = "block/"+count+"_dried_starfish";
                        ModelFile file = models().withExistingParent(s,mcLoc("block/lily_pad"))
                                .texture("texture",s).texture("particle",s);

                        Direction orientation = blockState.getValue(AbstractDirectionalBlock.FACING);
                        Vector2i vector2i = getRotation(orientation);

                        return ConfiguredModel.builder().modelFile(file).rotationY(vector2i.y).build();
                    });
        }

        void barrierPoleBlock(Block block,ResourceLocation modelBottom,ResourceLocation modelTop,ResourceLocation texture) {
            String name = name(block);
            ModelFile bottom = models().withExistingParent(name+"_bottom",modelBottom)
                    .texture("all",texture).texture("particle",texture).texture("0",texture);

            ModelFile top = models().withExistingParent(name+"_top",modelTop)
                    .texture("all",texture).texture("particle",texture).texture("0",texture);

            getVariantBuilder(block)
                    .forAllStatesExcept(blockState -> {
                        DoubleBlockHalf half = blockState.getValue(BarrierPoleBlock.HALF);
                        return ConfiguredModel.builder().modelFile(half == DoubleBlockHalf.UPPER ? top : bottom).build();
                    },BlockStateProperties.WATERLOGGED);

        }

        String name(Block block) {
            return BuiltInRegistries.BLOCK.getKey(block).getPath();
        }

        public void simplestBlockWithItem(Block block) {
            simpleBlockWithItem(block,cubeAll(block));
        }

        public void buttonLampBlock(ButtonLampBlock block, ResourceLocation baseModel) {
            String name = name(block);

            ModelFile buttonModel = models().withExistingParent(name,baseModel)
                    ;

            ModelFile buttonModelLit = models().withExistingParent(name+"_lit",baseModel);

            getVariantBuilder(block).forAllStates(state -> {
                Direction facing = state.getValue(ButtonBlock.FACING);
                AttachFace face = state.getValue(ButtonBlock.FACE);
                boolean powered = state.getValue(ButtonBlock.POWERED);

                return ConfiguredModel.builder()
                        .modelFile(powered ? buttonModelLit : buttonModel)
                        .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                        .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                        .uvLock(face == AttachFace.WALL)
                        .build();
            });
        }


        protected void lamp(Block block,ResourceLocation model,ResourceLocation texture0) {
            String name = name(block);
            ResourceLocation texture0Lit = texture0.withSuffix("_lit");
            getVariantBuilder(block).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);

                ResourceLocation texture = lit ? texture0Lit : texture0;

                ModelFile modelFile = models().withExistingParent("block/"+name+(lit? "_lit":""),
                                model)
                        .texture("all",texture)
                        .texture("particle",texture)
                        .texture("0",texture);
                return ConfiguredModel.builder().modelFile(modelFile).build();
            },BlockStateProperties.WATERLOGGED);
        }


        protected void wallLamp(Block block,ResourceLocation model,ResourceLocation texture0) {
            String name = name(block);
            ResourceLocation texture0Lit = texture0.withSuffix("_lit");
            getVariantBuilder(block).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(DirectionalInteractibleLampBlock.LIT);
                Direction orientation = blockState.getValue(HorizontalLampBlock.FACING);

                ResourceLocation texture = lit ? texture0Lit : texture0;

                ModelFile modelFile = models().withExistingParent("block/"+name+(lit? "_lit":""),
                                model)
                        .texture("all",texture)
                        .texture("particle",texture)
                        .texture("0",texture);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).build();
            },BlockStateProperties.WATERLOGGED);

           // simpleBlock(AbyssalDecorBlocks.FRESNEL_LAMP.get());
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
