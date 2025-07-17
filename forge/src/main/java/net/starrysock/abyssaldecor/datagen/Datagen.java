package net.starrysock.abyssaldecor.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.starrysock.abyssaldecor.*;
import net.starrysock.abyssaldecor.block.*;
import net.starrysock.abyssaldecor.block.properties.CornerDirection;
import net.starrysock.abyssaldecor.block.properties.TriPart;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ExtendedBlockFamilies;
import net.starrysock.abyssaldecor.registry.ExtendedBlockFamily.Variant;
import org.joml.Vector2i;

public class Datagen {
    public static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeServer()) {
            generator.addProvider(true, ModLootTableProvider.create(output));
            generator.addProvider(true, new ModDataPackProvider(output, lookupProvider));
            TagDatagen.gather(event);
        }


        if (event.includeClient()) {
            generator.addProvider(true, new ModBlockStateProvider(output, helper));
            generator.addProvider(true, new ModItemModelProvider(output, helper));
            generator.addProvider(true, new ModLangProvider(output));
        }
    }

    static class ModItemModelProvider extends ItemModelProvider {

        public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
            super(output, AbyssalDecor.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {


            ExtendedBlockFamilies.getAllFamilies().forEach(family -> {
                Block baseBlock = family.getBaseBlock();
                String name = name(baseBlock.asItem());
                simpleBlockItem(family.getBaseBlock().asItem());
                //     ResourceLocation location = Registry.BLOCK.getKey(family.getBaseBlock());
                //     signBlock((StandingSignBlock) family.get(BlockFamily.Variant.SIGN),(WallSignBlock) family.get(BlockFamily.Variant.WALL_SIGN),
                //             modLoc("block/"+location.getPath()));

                if (family.exists(Variant.TRAPDOOR)) {

                    TrapDoorBlock trapDoor = (TrapDoorBlock) family.get(Variant.TRAPDOOR);

                    ResourceLocation trapDoorName = BuiltInRegistries.ITEM.getKey(trapDoor.asItem());
                    String modelName = "block/" + trapDoorName.getPath() + "_bottom";
                    simpleBlockItem(trapDoor.asItem(), modLoc(modelName));
                }

                if (family.exists(Variant.DOOR)) {
                    DoorBlock door = (DoorBlock) family.get(Variant.DOOR);
                    generatedItem(door.asItem());
                }

                if (family.exists(Variant.STAIRS)) {
                    simpleBlockItem(family.get(Variant.SLAB).asItem());
                    simpleBlockItem(family.get(Variant.STAIRS).asItem());
                }

                if (family.exists(Variant.PRESSURE_PLATE)) {

                    simpleBlockItem(family.get(Variant.PRESSURE_PLATE).asItem());

                    ButtonBlock buttonBlock = (ButtonBlock) family.get(Variant.BUTTON);
                    String buttonName = name(buttonBlock.asItem());
                    buttonInventory(buttonName, modLoc("block/" + name));


                }

                if (family.exists(Variant.FENCE)) {
                    FenceBlock fenceBlock = (FenceBlock) family.get(Variant.FENCE);
                    fenceInventory(name(fenceBlock.asItem()), modLoc("block/" + name));

                    simpleBlockItem(family.get(Variant.FENCE_GATE).asItem());
                }
            });


            generatedItem(AbyssalDecorItems.AMARANTH_SEEDS.get());
            generatedItem(AbyssalDecorItems.AMARANTH_PINNACLE.get());

            simpleBlockItem(AbyssalDecorItems.AMARANTH_CRATE.get());

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

            generatedItem(AbyssalDecorItems.LION_STATUE.get());
            generatedItem(AbyssalDecorItems.GARGOYLE.get());
            generatedItem(AbyssalDecorItems.NITHING_POLE.get());
            generatedItem(AbyssalDecorItems.TELESCOPE.get());
            generatedItemBlockTexture(AbyssalDecorItems.HANGING_WEB.get());
            generatedItem(AbyssalDecorItems.DANGLING_WEB.get(), modLoc("block/dangling_web_lower"));
            simpleBlockItem(AbyssalDecorItems.PRISMARINE_CRYSTAL_BLOCK.get());
            generatedItem(AbyssalDecorItems.PRISMARINE_CRYSTAL_PANE.get(), modLoc("block/prismarine_crystal_block"));
            simpleBlockItem(AbyssalDecorItems.FRESNEL_BLOCK.get());
            generatedItem(AbyssalDecorItems.FRESNEL_PANE.get(), modLoc("block/fresnel_block"));
            simpleBlockItem(AbyssalDecorItems.CRYSTALLIZED_GLOWSTONE.get());
            generatedItem(AbyssalDecorItems.CRYSTALLIZED_GLOWSTONE_PANE.get(), modLoc("block/crystallized_glowstone"));
            simpleBlockItem(AbyssalDecorItems.FRAMED_CRYSTALLIZED_GLOWSTONE.get());
            generatedItem(AbyssalDecorItems.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE.get(), modLoc("block/framed_crystallized_glowstone"));
            simpleBlockItem(AbyssalDecorItems.VERMILION_BLOCK.get());
            generatedItem(AbyssalDecorItems.VERMILION_PANE.get(), modLoc("block/vermilion_block"));
            simpleBlockItem(AbyssalDecorItems.FRAMED_VERMILION_BLOCK.get());
            generatedItem(AbyssalDecorItems.FRAMED_VERMILION_PANE.get(), modLoc("block/framed_vermilion_block"));

            generatedItem(AbyssalDecorItems.WHITE_PEARL.get(), modLoc("item/white_pearl"));
            generatedItem(AbyssalDecorItems.BLACK_PEARL.get(), modLoc("item/black_pearl"));

            generatedItem(AbyssalDecorItems.WHITE_PEARL_BARS.get(), modLoc("block/white_pearl_bars_top"));

            generatedItem(AbyssalDecorItems.SHELL.get(), modLoc("block/shelltop"));

            generatedItemBlockTexture(AbyssalDecorItems.BLOOD_CORAL_BUD.get());

            generatedItem(AbyssalDecorItems.BLOOD_CORAL_BARS.get(), modLoc("block/blood_coral_bars_solo"));
            generatedItem(AbyssalDecorItems.DEEPBRONZE_BARS.get(), modLoc("block/deepbronze_bars"));

            generatedItem(AbyssalDecorItems.BOG_APPLE.get());
            generatedItem(AbyssalDecorItems.BOG_APPLE_PIE.get());
            generatedItem(AbyssalDecorItems.BOG_ROLL.get());
            generatedItem(AbyssalDecorItems.CANDY_BOG_APPLE.get());
            generatedItem(AbyssalDecorItems.CAVE_TACO.get());
            generatedItem(AbyssalDecorItems.SPIDERCORN.get());
            generatedItem(AbyssalDecorItems.SPIDERCORN_TORTILLA.get());
            generatedItem(AbyssalDecorItems.POPPED_SPIDERCORN.get());
            generatedItem(AbyssalDecorItems.TOASTED_AMARANTH_SEEDS.get());
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
            simpleBlockItem(item, modLoc("block/" + name(item)));
        }


        private void generatedItem(Item item, ResourceLocation texture) {
            String path = name(item);
            singleTexture(path, mcLoc("item/generated"),
                    "layer0", texture);
        }

        private void generatedItem(Item item) {
            generatedItem(item, modLoc("item/" + name(item)));
        }

        private void generatedItemBlockTexture(Item item) {
            generatedItem(item, modLoc("block/" + name(item)));
        }
    }

    public static class ModBlockStateProvider extends BlockStateProvider {

        public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, AbyssalDecor.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {

            ExtendedBlockFamilies.getAllFamilies().forEach(family -> {
                Block baseBlock = family.getBaseBlock();
                simpleBlock(baseBlock);
                ResourceLocation location = BuiltInRegistries.BLOCK.getKey(baseBlock);
                ResourceLocation baseTexture = modLoc("block/" + location.getPath());
                //todo signBlock((StandingSignBlock) family.get(Variant.SIGN),(WallSignBlock) family.get(Variant.WALL_SIGN), baseTexture);

                if (family.exists(Variant.TRAPDOOR)) {
                    TrapDoorBlock trapdoor = (TrapDoorBlock) family.get(Variant.TRAPDOOR);
                    ResourceLocation trapLoc = BuiltInRegistries.BLOCK.getKey(trapdoor);
                    trapdoorBlock(trapdoor, modLoc("block/" + trapLoc.getPath()), false);
                }

                if (family.exists(Variant.DOOR)) {
                    DoorBlock door = (DoorBlock) family.get(Variant.DOOR);

                    simpleDoorBlock(door);
                }


                if (family.exists(Variant.CHISELED)) {
                    Block chiseled = family.get(Variant.CHISELED);
                    simplestBlockWithItem(chiseled);
                }

                if (family.exists(Variant.STAIRS)) {
                    StairBlock stairBlock = (StairBlock) family.get(Variant.STAIRS);
                    stairsBlock(stairBlock, baseTexture);
                    SlabBlock slabBlock = (SlabBlock) family.get(Variant.SLAB);
                    slabBlock(slabBlock, baseTexture, baseTexture);
                }

                if (family.exists(Variant.BUTTON)) {
                    buttonBlock((ButtonBlock) family.get(Variant.BUTTON), baseTexture);
                    pressurePlateBlock((PressurePlateBlock) family.get(Variant.PRESSURE_PLATE), baseTexture);
                }

                if (family.exists(Variant.FENCE)) {
                    fenceBlock((FenceBlock) family.get(Variant.FENCE), baseTexture);
                    fenceGateBlock((FenceGateBlock) family.get(Variant.FENCE_GATE), baseTexture);
                }

                if (family.exists(Variant.SIGN)) {
                    StandingSignBlock signBlock = (StandingSignBlock) family.get(Variant.SIGN);
                    signBlock(signBlock, (WallSignBlock) family.get(Variant.WALL_SIGN), modLoc("block/" + name(baseBlock)));
                    iconTexture(name(signBlock), modLoc("item/" + name(signBlock)));

                    //uses stripped logs but whatever
                    CeilingHangingSignBlock ceilingHangingSignBlock = (CeilingHangingSignBlock) family.get(Variant.HANGING_SIGN);
                    hangingSignBlock(ceilingHangingSignBlock, (WallHangingSignBlock) family.get(Variant.HANGING_WALL_SIGN), modLoc("block/" + name(baseBlock)));
                    iconTexture(name(ceilingHangingSignBlock), modLoc("item/" + name(ceilingHangingSignBlock)));
                }

                if (family.exists(Variant.WALL)) {
                    WallBlock wallBlock = (WallBlock) family.get(Variant.WALL);
                    wallBlock(wallBlock, baseTexture);
                    simpleBlockItem(wallBlock, models().wallInventory(BuiltInRegistries.BLOCK.getKey(wallBlock).getPath(), baseTexture));
                }
            });


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

                        ModelFile modelFile = models().getExistingFile(modLoc("block/tall_amaranth_stage" + age + "_" + half.getSerializedName()));
                        return ConfiguredModel.builder().modelFile(modelFile).build();
                    }
            );

            getVariantBuilder(AbyssalDecorBlocks.LIGHTBULB.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
                Direction orientation = blockState.getValue(DirectionalBlock.FACING);
                ModelFile modelFile = models().getExistingFile(modLoc("block/lightbulb" + (lit ? "_lit" : "")));
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);

            getVariantBuilder(AbyssalDecorBlocks.FROSTED_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
                Direction orientation = blockState.getValue(DirectionalBlock.FACING);

                ResourceLocation texture0 = modLoc("block/frostedceilinglamp" + (lit ? "lit" : ""));

                ModelFile modelFile = models().withExistingParent("block/frosted_lamp" + (lit ? "_lit" : ""),
                                modLoc("custom/frostedceilinglamp"))
                        .texture("all", texture0)
                        .texture("particle", texture0)
                        .texture("0", texture0)
                        .texture("1", modLoc("block/frostedceilinglamp2"));
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);

            getVariantBuilder(AbyssalDecorBlocks.RAINBOW_LAMP.get()).forAllStatesExcept(blockState -> {
                Direction orientation = blockState.getValue(DirectionalBlock.FACING);

                ResourceLocation texture0 = modLoc("block/rainbow2");
                ResourceLocation texture1 = modLoc("block/rainbowlampbase");

                ModelFile modelFile = models().withExistingParent("block/rainbow_lamp",
                                modLoc("custom/rainbowlamp"))
                        .texture("all", texture0)
                        .texture("particle", texture0)
                        .texture("1", texture0)
                        .texture("2", texture1);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);


            getVariantBuilder(AbyssalDecorBlocks.SEAGLASS_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
                Direction orientation = blockState.getValue(DirectionalBlock.FACING);

                ResourceLocation texture0 = modLoc("block/seaglass_lamp" + (lit ? "_lit" : ""));

                ModelFile modelFile = models().withExistingParent("block/seaglass_lamp" + (lit ? "_lit" : ""),
                                modLoc("custom/seaglasslamp"))
                        .texture("all", texture0)
                        .texture("particle", texture0)
                        .texture("0", texture0);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationX(vector2i.x).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);


            getVariantBuilder(AbyssalDecorBlocks.IRON_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);

                //    "all": "abyssaldecor:block/ironlamp1",
                //    "particle": "abyssaldecor:block/ironlamp1",
                //    "0": "abyssaldecor:block/ironlamp1",
                //    "1": "abyssaldecor:block/ironlamp2" add lit

                ModelFile modelFile = models().withExistingParent("block/iron_lamp" + (lit ? "_lit" : ""),
                                modLoc("custom/ironlampceiling"))
                        .texture("all", modLoc("block/ironlamp1"))
                        .texture("particle", modLoc("block/ironlamp1"))
                        .texture("0", modLoc("block/ironlamp1"))
                        .texture("1", modLoc("block/ironlamp2" + (lit ? "lit" : "")));
                return ConfiguredModel.builder().modelFile(modelFile).build();
            }, BlockStateProperties.WATERLOGGED);

            getVariantBuilder(AbyssalDecorBlocks.WALL_IRON_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
                Direction orientation = blockState.getValue(HorizontalDirectionalBlock.FACING);

                ModelFile modelFile = models().withExistingParent("block/wall_iron_lamp" + (lit ? "_lit" : ""),
                                modLoc("custom/wallironlamp"))
                        .texture("all", modLoc("block/ironlamp1"))
                        .texture("particle", modLoc("block/ironlamp1"))
                        .texture("0", modLoc("block/ironlamp1"))
                        .texture("1", modLoc("block/ironlamp2" + (lit ? "lit" : "")));
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);

            wallLamp(AbyssalDecorBlocks.FLOWER_LAMP.get(), modLoc("custom/flowerlamp"), modLoc("block/flower_lamp"));

            getVariantBuilder(AbyssalDecorBlocks.TUBE_LAMP.get()).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
                Direction orientation = blockState.getValue(HorizontalDirectionalBlock.FACING);

                ResourceLocation texture = modLoc("block/tubelamp");

                ModelFile modelFile = models().withExistingParent("block/tube_lamp" + (lit ? "_lit" : ""),
                                modLoc("custom/tubelamp"))
                        .texture("all", texture)
                        .texture("particle", texture)
                        .texture("0", texture);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);

            simplestBlockWithItem(AbyssalDecorBlocks.WISTERIA_PETALS.get());
            simplestBlockWithItem(AbyssalDecorBlocks.ELDER_WISTERIA_PETALS.get());
            simplestBlockWithItem(AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES.get());

            logBlockWithItem(AbyssalDecorBlocks.ANCIENT_BIRCH_LOG.get());
            logBlockWithItem(AbyssalDecorBlocks.STRIPPED_ANCIENT_BIRCH_LOG.get());
            logBlockWithItem(AbyssalDecorBlocks.FOXY_PILLAR.get());

            lamp(AbyssalDecorBlocks.QUARTZ_LAMP.get(), modLoc("custom/floorgaslamp"), modLoc("block/quartz_lamp"));
            wallLamp(AbyssalDecorBlocks.WALL_QUARTZ_LAMP.get(), modLoc("custom/wallgaslamp"), modLoc("block/wall_quartz_lamp"));
            lamp(AbyssalDecorBlocks.CEILING_QUARTZ_LAMP.get(), modLoc("custom/ceilingquartzlamp"), modLoc("block/ceiling_quartz_lamp"));
            wallLamp(AbyssalDecorBlocks.JADE_LAMP.get(), modLoc("custom/jadelamp"), modLoc("block/jade_lamp"));
            wallLamp(AbyssalDecorBlocks.WALL_JADE_LAMP.get(), modLoc("custom/walljadelamp"), modLoc("block/wall_jade_lamp"));

            getVariantBuilder(AbyssalDecorBlocks.BLAZE_LAMP.get()).forAllStatesExcept(blockState -> {
                Direction orientation = blockState.getValue(HorizontalLampBlock.FACING);

                ResourceLocation texture0 = modLoc("block/blazelamp");

                ModelFile modelFile = models().withExistingParent("block/blaze_lamp",
                                modLoc("custom/blazelamp"))
                        .texture("all", texture0)
                        .texture("particle", texture0)
                        .texture("0", texture0);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);

            simplestBlockWithItem(AbyssalDecorBlocks.SEABRASS_ORE.get());

            lamp(AbyssalDecorBlocks.JADE_LANTERN.get(), mcLoc("block/cube_all"), modLoc("block/jade_lantern"));

            //simplestBlockWithItem(AbyssalDecorBlocks.IRON_LANTERN.get());


            //buttonLampBlock(AbyssalDecorBlocks.BULKHEAD_LAMP.get(),);

            barrierPoleBlock(AbyssalDecorBlocks.VELVET_BARRIER.get(), modLoc("custom/velvetbarrierbottom"), modLoc("custom/velvetbarriertop"),
                    modLoc("block/velvetbarrier1"),modLoc("item/velvet_barrier"));

            barrierPoleBlock(AbyssalDecorBlocks.IRON_BARRIER.get(), modLoc("custom/ironbarrierbottom"), modLoc("custom/ironbarriertop"),
                    modLoc("block/ironbarrier1"),modLoc("item/iron_barrier"));

            barrierPoleBlock(AbyssalDecorBlocks.ROPE_BARRIER.get(), modLoc("custom/ropebarrierpostbottom"), modLoc("custom/ropebarrierposttop"),
                    modLoc("block/ropebarriertexture"),modLoc("item/rope_barrier"));

            barrierPoleBlock(AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get(), modLoc("custom/barbwirepostbottom"), modLoc("custom/barbwireposttop"),
                    modLoc("block/barbwirebarrier1"),modLoc("item/barbed_wire_barrier"));

            //   barrierPoleBlock(AbyssalDecorBlocks.ROPE_BARRIER.get(),modLoc("custom/ironbarrierbottom"),modLoc("custom/ironbarriertop"),
            //          modLoc("block/velvetbarrier1"));

            //    barrierPoleBlock(AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get(),modLoc("custom/ironbarrierbottom"),modLoc("custom/ironbarriertop"),
            //            modLoc("block/velvetbarrier1"));

            starfish(AbyssalDecorBlocks.STARFISH.get());
            driedStarfish(AbyssalDecorBlocks.DRIED_STARFISH.get());
            simpleBlock(AbyssalDecorBlocks.STARLIGHT.get(), models().withExistingParent("block/starlight", "block/cross").texture("cross", "block/starlight"));

            lionStatue(AbyssalDecorBlocks.LION_STATUE.get());
            lionStatue(AbyssalDecorBlocks.NITHING_POLE.get());
            lionStatue(AbyssalDecorBlocks.TELESCOPE.get());

            simpleBlock(AbyssalDecorBlocks.HANGING_WEB.get(), models().withExistingParent("block/hanging_web", "block/cross").texture("cross", "block/hanging_web"));
            horizontalBlock(AbyssalDecorBlocks.WALL_HANGING_WEB.get(), models()
                    .withExistingParent("block/wall_hanging_web", "block/vine").texture("particle", modLoc("block/hanging_web"))
                    .texture("vine", modLoc("block/hanging_web")));

            doubleBlock(AbyssalDecorBlocks.DANGLING_WEB.get(), models()
                    .withExistingParent("block/dangling_web_upper", "block/cross").texture("cross", "block/dangling_web_upper"), models()
                    .withExistingParent("block/dangling_web_lower", "block/cross").texture("cross", "block/dangling_web_lower"));

            lionStatue(AbyssalDecorBlocks.WALL_DANGLING_WEB.get());

            simpleBlock(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK.get());
            paneBlock(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE.get(), modLoc("block/prismarine_crystal_block"), modLoc("block/prismarine_crystal_block"));

            simpleBlock(AbyssalDecorBlocks.FRESNEL_BLOCK.get());
            paneBlock(AbyssalDecorBlocks.FRESNEL_PANE.get(), modLoc("block/fresnel_block"), modLoc("block/fresnel_block"));

            simpleBlock(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get());
            paneBlock(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE.get(), modLoc("block/crystallized_glowstone"), modLoc("block/crystallized_glowstone"));

            simpleBlock(AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE.get());
            paneBlock(AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE.get(), modLoc("block/framed_crystallized_glowstone"), modLoc("block/framed_crystallized_glowstone"));

            simpleBlock(AbyssalDecorBlocks.VERMILION_BLOCK.get());
            paneBlock(AbyssalDecorBlocks.VERMILION_PANE.get(), modLoc("block/vermilion_block"), modLoc("block/vermilion_block"));

            simpleBlock(AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK.get());
            paneBlock(AbyssalDecorBlocks.FRAMED_VERMILION_PANE.get(), modLoc("block/framed_vermilion_block"), modLoc("block/framed_vermilion_block"));

            logBlockWithItem(AbyssalDecorBlocks.WHITEWOOD_LOG.get());
            woodBlockWithItem(AbyssalDecorBlocks.WHITEWOOD_WOOD.get(), modLoc("block/whitewood_log"));
            logBlockWithItem(AbyssalDecorBlocks.WHITEWOOD_TRIM.get());

            logBlockWithItem(AbyssalDecorBlocks.BLACKWOOD_LOG.get());

            logBlockWithItem(AbyssalDecorBlocks.STRIPPED_BLACKWOOD_LOG.get());

            woodBlockWithItem(AbyssalDecorBlocks.BLACKWOOD_WOOD.get(), modLoc("block/blackwood_log"));
            logBlockWithItem(AbyssalDecorBlocks.BLACKWOOD_TRIM.get());

            logBlockWithItem(AbyssalDecorBlocks.WHITE_PEARL_PILLAR.get());
            logBlockWithItem(AbyssalDecorBlocks.BLACK_PEARL_PILLAR.get());

            simplestBlockWithItem(AbyssalDecorBlocks.CUT_WHITE_PEARL_BLOCK.get());
            simplestBlockWithItem(AbyssalDecorBlocks.CUT_BLACK_PEARL_BLOCK.get());

            paneBlock(AbyssalDecorBlocks.WHITE_PEARL_BARS.get(), modLoc("block/white_pearl_bars_top"), modLoc("block/white_pearl_bars_top"));

            simplestBlockWithItem(AbyssalDecorBlocks.WHITE_PEARL_TILES.get());
            crackedBlock(AbyssalDecorBlocks.CRACKED_PEARL_TILES.get());
            simplestBlockWithItem(AbyssalDecorBlocks.MIXED_PEARL_TILES.get());
            simplestBlockWithItem(AbyssalDecorBlocks.STARRY_PEARL_TILES.get());

            clam(AbyssalDecorBlocks.CLAM.get());
            clam(AbyssalDecorBlocks.CLAM_WITH_PEARL.get());

            logBlockWithItem(AbyssalDecorBlocks.PEARLY_GLASS.get());
            logBlockWithItem(AbyssalDecorBlocks.SUNNY_PEARLY_GLASS.get());
            logBlockWithItem(AbyssalDecorBlocks.AZURE_PEARLY_GLASS.get());
            logBlockWithItem(AbyssalDecorBlocks.VERDANT_PEARLY_GLASS.get());
            logBlockWithItem(AbyssalDecorBlocks.WHITEWOOD_PEARLY_GLASS.get());
            logBlockWithItem(AbyssalDecorBlocks.BLACKWOOD_PEARLY_GLASS.get());

            simplePaneBlock(AbyssalDecorBlocks.PEARLY_GLASS_PANE.get());
            simplePaneBlock(AbyssalDecorBlocks.SUNNY_PEARLY_GLASS_PANE.get());
            simplePaneBlock(AbyssalDecorBlocks.AZURE_PEARLY_GLASS_PANE.get());
            simplePaneBlock(AbyssalDecorBlocks.VERDANT_PEARLY_GLASS_PANE.get());
            simplePaneBlock(AbyssalDecorBlocks.WHITEWOOD_PEARLY_GLASS_PANE.get());
            simplePaneBlock(AbyssalDecorBlocks.BLACKWOOD_PEARLY_GLASS_PANE.get());

            mixedBlock(AbyssalDecorBlocks.MIXED_BRICKS.get());
            mixedBlock(AbyssalDecorBlocks.MOSSY_MIXED_BRICKS.get());
            simplestBlockWithItem(AbyssalDecorBlocks.BRITTLE_TUFF.get());

            trapdoor(AbyssalDecorBlocks.STONE_TRAPDOOR.get());
            trapdoor(AbyssalDecorBlocks.MOSSY_STONE_TRAPDOOR.get());
            trapdoor(AbyssalDecorBlocks.ORNATE_STONE_TRAPDOOR.get());

            logBlockWithItem(AbyssalDecorBlocks.IRON_PILLAR.get());

            logBlockWithItem(AbyssalDecorBlocks.BLOOD_CORAL_PILLAR.get());
            logBlockWithItem(AbyssalDecorBlocks.ROUGH_BLOOD_CORAL.get());
            logBlockWithItem(AbyssalDecorBlocks.GILDED_BLOOD_CORAL_PILLAR.get());

            paneBlock(AbyssalDecorBlocks.BLOOD_CORAL_BARS.get(), modLoc("block/blood_coral_bars_solo"), modLoc("block/blood_coral_bars_solo"));

            logBlockWithItem(AbyssalDecorBlocks.GILDED_JADE_PILLAR.get());

            simplestBlockWithItem(AbyssalDecorBlocks.SERPENT_SCALES.get());
            simplestBlockWithItem(AbyssalDecorBlocks.DAMAGED_SERPENT_SCALES.get());

            slabBlock(AbyssalDecorBlocks.SERPENT_SCALE_SLAB.get(), modLoc("block/serpent_scales"), modLoc("block/serpent_scales"));
            simpleBlockItem(AbyssalDecorBlocks.SERPENT_SCALE_SLAB.get(), models().withExistingParent("serpent_scale_slab",
                    mcLoc("block/slab")));

            simpleSlab(AbyssalDecorBlocks.DAMAGED_SERPENT_SCALE_SLAB.get(), modLoc("block/damaged_serpent_scales"));

            logBlockWithItem(AbyssalDecorBlocks.RIBBED_SERPENT_VEINS.get());

            ResourceLocation skin = modLoc("block/serpentskinside");

            serpentEye(AbyssalDecorBlocks.SERPENT_SKIN.get(), models().withExistingParent("serpent_skin", mcLoc("block/cube"))
                    .texture("down", modLoc("block/serpentskinbottom"))
                    .texture("up", modLoc("block/serpent_scales"))
                    .texture("north", skin)
                    .texture("east", skin)
                    .texture("south", skin)
                    .texture("west", skin)
                    .texture("particle", modLoc("block/serpentskinbottom")));

            serpentEye(AbyssalDecorBlocks.DAMAGED_SERPENT_SKIN.get(), models().withExistingParent("damaged_serpent_skin", mcLoc("block/cube"))
                    .texture("down", modLoc("block/serpentskinbottom"))
                    .texture("up", modLoc("block/damaged_serpent_scales"))
                    .texture("north", skin)
                    .texture("east", skin)
                    .texture("south", skin)
                    .texture("west", skin)
                    .texture("particle", modLoc("block/serpentskinbottom")));

            serpentEye(AbyssalDecorBlocks.SERPENT_EYE.get(), models().withExistingParent("serpent_eye", mcLoc("block/cube"))
                    .texture("down", modLoc("block/serpentskinbottom"))
                    .texture("up", modLoc("block/serpent_eye"))
                    .texture("north", skin)
                    .texture("east", skin)
                    .texture("south", skin)
                    .texture("west", skin)
                    .texture("particle", modLoc("block/serpentskinbottom")));
            serpentEye(AbyssalDecorBlocks.SMALL_SERPENT_EYES.get(), models().withExistingParent("small_serpent_eyes", mcLoc("block/cube"))
                    .texture("down", modLoc("block/serpentskinbottom"))
                    .texture("up", modLoc("block/serpentsmalleyes"))
                    .texture("north", skin)
                    .texture("east", skin)
                    .texture("south", skin)
                    .texture("west", skin)
                    .texture("particle", modLoc("block/serpentskinbottom")));

            simplestBlockWithItem(AbyssalDecorBlocks.NETTED_SERPENT_EYE.get());
            simplestBlockWithItem(AbyssalDecorBlocks.SMALL_NETTED_SERPENT_EYE.get());

            simplestBlockWithItem(AbyssalDecorBlocks.RIVETED_SEABRASS.get());
            blockLamp(AbyssalDecorBlocks.SEABRASS_LAMP.get(), modLoc("block/seabrass_lamp"));
            blockLamp(AbyssalDecorBlocks.DEEPBRONZE_LANTERN.get(), modLoc("block/deepbronze_lantern"));
            blockLamp(AbyssalDecorBlocks.BLOOD_LANTERN.get(), modLoc("block/blood_lantern"));
            simpleSlab(AbyssalDecorBlocks.RIVETED_SEABRASS_SLAB.get(), modLoc("block/riveted_seabrass"));

            paneBlock(AbyssalDecorBlocks.DEEPBRONZE_BARS.get(), modLoc("block/deepbronze_bars"), modLoc("block/deepbronze_bars"));


            AbyssalDecorBlocks.WALLPAPERS.forEach(block -> {
                woodBlockWithItem(block, modLoc("block/" + name(block)));
            });

            simplestBlockWithItem(AbyssalDecorBlocks.LAVENTINE.get());
            //simplestBlockWithItem(AbyssalDecorBlocks.LAVENTINE_GLASS.get());

            //paneBlock(AbyssalDecorBlocks.LAVENTINE_GLASS_PANE.get(),modLoc("block/laventine_glass"),modLoc("block/deepbronze_bars"));

            simplestBlockWithItem(AbyssalDecorBlocks.SMOOTH_STARSTONE.get());

            simplestBlockWithItem(AbyssalDecorBlocks.STARGLASS.get());

            paneBlockWithItem(AbyssalDecorBlocks.STARGLASS_PANE.get(), modLoc("block/starglass"), modLoc("block/starglass"));

            woodBlockWithItem(AbyssalDecorBlocks.BLACKWOOD_SHINGLES.get(), modLoc("block/blackwood_shingles"));

            simplestBlockWithItem(AbyssalDecorBlocks.MOLDWEAVE.get());

            simpleBlockItem(AbyssalDecorBlocks.MOLDWEAVE_CARPET.get(), models().withExistingParent("moldweave_carpet", mcLoc("block/carpet"))
                    .texture("wool", modLoc("block/moldweave")));

            paneBlockWithItem(AbyssalDecorBlocks.BLACK_PEARL_BARS.get(), modLoc("block/black_pearl_bars_top"), modLoc("block/black_pearl_bars_top"));

            horizontalBlock(AbyssalDecorBlocks.WALL_HANGING_MOSS.get(), models().getExistingFile(modLoc("block/hanging_moss_wall")));

            simpleBlock(AbyssalDecorBlocks.HANGING_MOSS.get(), models().getExistingFile(modLoc("block/hanging_moss_ceiling")));
            simpleBlockItem(AbyssalDecorBlocks.HANGING_MOSS.get(), models().getExistingFile(modLoc("block/hanging_moss_wall")));

            simpleBlock(AbyssalDecorBlocks.DAFFODIL.get(), models().cross("daffodil", modLoc("block/daffodil")));
            iconTexture("daffodil", modLoc("block/daffodil"));

            horizontalBlock(AbyssalDecorBlocks.SCRIMSHAW.get(), models().getExistingFile(modLoc("block/scrimshaw_cave")));
            simpleBlockItem(AbyssalDecorBlocks.SCRIMSHAW.get(), models().getExistingFile(modLoc("block/scrimshaw_cave")));

            centerable(AbyssalDecorBlocks.WOOD_SUPPORT.get(),
                    models().withExistingParent("wood_support", modLoc("custom/woodsupport"))
                            .texture("all", modLoc("block/woodsupport"))
                            .texture("particle", modLoc("block/woodsupport")),

                    models().withExistingParent("wood_support_centered", modLoc("custom/woodsupportcentered"))
                            .texture("all", modLoc("block/woodsupport"))
                            .texture("particle", modLoc("block/woodsupport"))
            );

            simplestBlockWithItem(AbyssalDecorBlocks.DEEPBRONZE_TILES.get());

            paneBlockWithItem(AbyssalDecorBlocks.STONE_BARS.get(), modLoc("block/stone_bars_solo"),
                    modLoc("block/stone_bars_solo"));

            simplestBlockWithItem(AbyssalDecorBlocks.FROSTED_GLASS.get());
            paneBlockWithItem(AbyssalDecorBlocks.FROSTED_GLASS_PANE.get(), modLoc("block/frosted_glass"),
                    modLoc("block/frosted_glass"));

            simplestBlockWithItem(AbyssalDecorBlocks.SEABRASS_PLATING.get());

            logBlockWithItem(AbyssalDecorBlocks.STRIPPED_CINNAMON_LOG.get());

            directionalBlock(AbyssalDecorBlocks.WHITE_PEARL.get(), models().withExistingParent("white_pearl",
                            modLoc("custom/tinywhitepearl"))
                    .texture("all", modLoc("block/white_pearl"))
                    .texture("particle", modLoc("block/white_pearl")));

            directionalBlock(AbyssalDecorBlocks.IRON_BALL.get(), models().withExistingParent("iron_ball",
                            modLoc("custom/tinywhitepearl"))
                    .texture("all", modLoc("block/ironball"))
                    .texture("particle", modLoc("block/ironball")));


            directionalBlock(AbyssalDecorBlocks.BLACK_PEARL.get(), models().withExistingParent("black_pearl",
                            modLoc("custom/tinywhitepearl"))
                    .texture("all", modLoc("block/black_pearl"))
                    .texture("particle", modLoc("block/black_pearl")));

            simpleBlockItem(AbyssalDecorBlocks.IRON_BALL.get(), models().getExistingFile(modLoc("block/iron_ball")));

            paneBlockWithItem(AbyssalDecorBlocks.WHITEWOOD_PICKET_FENCE.get(), modLoc("block/whitewood_picket_fence"),
                    modLoc("block/whitewood_picket_fence"));

            serpentEye(AbyssalDecorBlocks.BRICK_PILLAR.get(), models().withExistingParent("brick_pillar", mcLoc("block/cube"))
                    .texture("down", modLoc("block/brick_pillar_top"))
                    .texture("up", modLoc("block/brick_pillar_top"))
                    .texture("north", modLoc("block/brick_pillar"))
                    .texture("east", modLoc("block/brick_pillar"))
                    .texture("south", modLoc("block/brick_pillar"))
                    .texture("west", modLoc("block/brick_pillar"))
                    .texture("particle", modLoc("block/brick_pillar")), false);

            simpleBlockWithItem(AbyssalDecorBlocks.FRAMED_PITCHGLASS.get(), models().getExistingFile(modLoc("block/framed_pitchglass_top")));

            paneBlockWithItem(AbyssalDecorBlocks.FRAMED_PITCHGLASS_PANE.get(), modLoc("block/pitchglasspanesolo"),
                    modLoc("block/pitchglasspanetop"));

            logBlockWithItem(AbyssalDecorBlocks.MOLDY_FROND_BLOCK.get());

            paneBlockWithItem(AbyssalDecorBlocks.ORNATE_DEEPBRONZE_BARS.get(), modLoc("block/ornate_deepbronze_bars"),
                    modLoc("block/bronzebarstop"));

            directionalBlock(AbyssalDecorBlocks.TRASH_BAG.get(), models().withExistingParent("trash_bag", modLoc("custom/trashbag"))
                    .texture("particle", modLoc("block/trash_bag_side"))
                    .texture("0", modLoc("block/trash_bag_side"))
                    .texture("2", modLoc("block/trash_bag_bottom"))
                    .texture("3", modLoc("block/trash_bag_knot"))
            );
            simpleBlockItem(AbyssalDecorBlocks.TRASH_BAG.get(), models().getExistingFile(modLoc("block/trash_bag")));

            iconTexture("mold_fronds", modLoc("block/moldfrondstop"));

            simpleBlockWithItem(AbyssalDecorBlocks.LAVENTINE_GLASS.get(), models().cubeAll("laventine_glass", modLoc("block/laventinepanemid")));

            paneBlockWithItem(AbyssalDecorBlocks.LAVENTINE_GLASS_PANE.get(), modLoc("block/laventinepanemid"),
                    modLoc("block/laventinepanemid"));

            simplestBlockWithItem(AbyssalDecorBlocks.EFFERVESCENT_TILES.get());

            woodBlockWithItem(AbyssalDecorBlocks.SMALL_DEEPBRONZE_PIPES.get(), modLoc("block/small_deepbronze_pipes"));

            bulkheadLampBlock(AbyssalDecorBlocks.BULKHEAD_LAMP.get(), modLoc("custom/bulkheadlamp"));


            //{
            //  "parent": "abyssaldecor:custom/fresnellamp",
            //  "textures": {
            //    "all": "abyssaldecor:block/fresnelside",
            //    "particle": "abyssaldecor:block/fresnelside",
            //    "0": "abyssaldecor:block/fresneltop",
            //    "1": "abyssaldecor:block/fresnelside",
            //    "2": "abyssaldecor:block/fresnelinside"
            //  },
            //  "render_type": "translucent"
            //}
            simpleBlockWithItem(AbyssalDecorBlocks.FRESNEL_LAMP.get(), models().withExistingParent("fresnel_lamp", modLoc("custom/fresnellamp"))
                    .texture("particle", modLoc("block/fresnel_block"))
                    .texture("0", modLoc("block/fresneltop"))
                    .texture("1", modLoc("block/fresnel_block"))
                    .texture("2", modLoc("block/fresnelinside"))
            );

            ironLantern(AbyssalDecorBlocks.IRON_LANTERN.get());

            smallBars(AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS.get(), modLoc("block/small_blood_coral_bars"));

            regularSconce(AbyssalDecorBlocks.DEEPBRONZE_SCONCE.get(),modLoc("block/deepbronze_sconce"));
            regularSconce(AbyssalDecorBlocks.IRON_SCONCE.get(),modLoc("block/iron_sconce"));
            regularSconce(AbyssalDecorBlocks.DULL_IRON_SCONCE.get(),modLoc("block/dull_iron_sconce"));
            regularSconce(AbyssalDecorBlocks.BLOOD_CORAL_SCONCE.get(),modLoc("block/blood_coral_sconce"));
            goldSconce(AbyssalDecorBlocks.GOLD_SCONCE.get(),modLoc("block/gold_sconce"));
            regularSconce(AbyssalDecorBlocks.SEABRASS_SCONCE.get(),modLoc("block/seabrass_sconce"));

            smallCornerBar(AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS_CORNER.get(),modLoc("block/smallbloodcoralbarscorner"));

            smallBars(AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS.get(), modLoc("block/smallpearlbars"));
            smallCornerBar(AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS_CORNER.get(),modLoc("block/smallpearlbars"));

            smallBars(AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS.get(), modLoc("block/smallbarsclean"));
            smallCornerBar(AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS_CORNER.get(),modLoc("block/smallbarscleancorner"));

            smallBars(AbyssalDecorBlocks.SMALL_DULL_IRON_BARS.get(), modLoc("block/smallbarsdull"));
            smallCornerBar(AbyssalDecorBlocks.SMALL_DULL_IRON_BARS_CORNER.get(),modLoc("block/dullironbarscorner"));

            smallBars(AbyssalDecorBlocks.SMALL_SEABRASS_BARS.get(), modLoc("block/smallbarsbrass"));
            smallCornerBar(AbyssalDecorBlocks.SMALL_SEABRASS_BARS_CORNER.get(),modLoc("block/smallbarsbrasscorner"));

            smallBars(AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS.get(), modLoc("block/smallbronzebars"));
            smallCornerBar(AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS_CORNER.get(),modLoc("block/smallbronzebarscorner"));

            smallBars(AbyssalDecorBlocks.SMALL_STONE_BARS.get(), modLoc("block/smallstonebars"));
            smallCornerBar(AbyssalDecorBlocks.SMALL_STONE_BARS_CORNER.get(),modLoc("block/smallstonebarscorner"));

            smallBars(AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS.get(), modLoc("block/smallblackpearlbars"));
            smallCornerBar(AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS_CORNER.get(),modLoc("block/smallblackpearlbars"));

            simpleBlockItem(AbyssalDecorBlocks.CLAM.get(), models().getExistingFile(modLoc("block/clam_closed")));
            simpleBlockItem(AbyssalDecorBlocks.CLAM_WITH_PEARL.get(), models().getExistingFile(modLoc("block/clam_with_pearl_closed")));

            ribbon(AbyssalDecorBlocks.IRON_BARRIER_RIBBON.get(),modLoc("custom/velvetbarrierribbon"),modLoc("block/ironbarrier2"));
            ribbon(AbyssalDecorBlocks.ROPE_BARRIER_RIBBON.get(),modLoc("custom/ropebarrierrope"),modLoc("block/ropebarriertexture2"));
            ribbon(AbyssalDecorBlocks.BARBED_WIRE_RIBBON.get(),modLoc("custom/barbwirewire"),modLoc("block/barbwirebarrier2"));
            moldyStalk(AbyssalDecorBlocks.MOLDY_STALK.get());

            //{
            //  "parent": "block/cross",
            //  "textures": {
            //    "cross": "abyssal_decor:block/moldypuffball",
            //    "particle": "abyssal_decor:block/moldypuffball"
            //  },
            //  "render_type": "cutout_mipped"
            //}
            simpleBlock(AbyssalDecorBlocks.MOLDY_STALK_SPROUT.get(),models().cross("moldy_stalk_sprout",
                    modLoc("block/moldy_stalk")));


            simpleBlock(AbyssalDecorBlocks.MOLDY_FEATHERS.get(),models().cross("moldy_feathers", modLoc("block/moldy_feathers")));
            iconTexture("moldy_feathers",modLoc("block/moldy_feathers"));

            ModelFile mh = models().cross("moldy_hangers", modLoc("block/moldy_hangers"));
            simpleBlock(AbyssalDecorBlocks.MOLDY_HANGERS.get(),mh);
            iconTexture("moldy_hangers",modLoc("block/moldy_hangers"));

            simpleBlock(AbyssalDecorBlocks.MOLDY_HANGERS_PLANT.get(),mh);

            feverblossom(AbyssalDecorBlocks.FEVER_BLOSSOM.get());
            iconTexture("fever_blossom_seeds",modLoc("item/fever_blossom_seeds"));
            iconTexture("fever_blossom",modLoc("item/fever_blossom"));

            horizontalBlock(AbyssalDecorBlocks.WALL_GRIME.get(),models().getExistingFile(modLoc("block/grime")));

            cinnamonBush(AbyssalDecorBlocks.CINNAMON_BUSH.get());
            tallCinnamonBush(AbyssalDecorBlocks.TALL_CINNAMON_BUSH.get());

            industrialLever(AbyssalDecorBlocks.INDUSTRIAL_LEVER.get());

            wisteria(AbyssalDecorBlocks.WISTERIA.get());
            iconTexture("wisteria",modLoc("item/wisteria"));
            wisteria(AbyssalDecorBlocks.ELDER_WISTERIA.get());
            iconTexture("elder_wisteria",modLoc("item/elder_wisteria"));
        }

        public void wisteria(WisteriaBlock block) {
            String name = name(block);
            getVariantBuilder(block).forAllStatesExcept(state -> {
                Direction facing = state.getValue(WisteriaBlock.FACING);
                TriPart triPart = state.getValue(WisteriaBlock.TRI_PART);

                String model = switch (triPart) {
                    case BOTTOM -> "wisteriapurplewallbottom";
                    case MIDDLE -> "wisteriapurplewallmid";
                    case TOP -> "wisteriapurplewall";
                };

                String texture = name+"_"+triPart.getSerializedName();


                ModelFile modelFile = models().withExistingParent(name+"_"+triPart.getSerializedName(),modLoc("custom/"+model))
                        .texture("particle",modLoc("block/"+texture))
                        .texture("0",modLoc("block/"+texture));

                return ConfiguredModel.builder().modelFile(modelFile)
                        .rotationY(((int) facing.toYRot() + 180) % 360).build();
            }, net.starrysock.abyssaldecor.block.WisteriaBlock.WATERLOGGED);
        }

        public void industrialLever(LeverBlock block) {
            String name = name(block);

            ResourceLocation texture = modLoc("block/fusebox");
            ResourceLocation textureOn = modLoc("block/fuseboxlit");

            ModelFile file = models().withExistingParent(name,modLoc("custom/fusebox"))
                    .texture("0",texture)
                    .texture("particle",texture);
            ModelFile fileLit = models().withExistingParent(name+"_on",modLoc("custom/fuseboxlit"))
                    .texture("0",textureOn)
                    .texture("particle",textureOn);

            getVariantBuilder(block).forAllStatesExcept(state -> {
                Direction facing = state.getValue(LeverBlock.FACING);
                AttachFace face = state.getValue(LeverBlock.FACE);
                boolean powered = state.getValue(LeverBlock.POWERED);

                return ConfiguredModel.builder()
                        .modelFile(powered ? fileLit : file)
                        .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                        .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                        .uvLock(false)
                        .build();
            },IndustrialLeverBlock.WATERLOGGED);
        }

        public void cinnamonBush(CinnamonSaplingBlock block) {
            String name = name(block);
            getVariantBuilder(block).forAllStates(state -> {
                int stage = state.getValue(CinnamonSaplingBlock.STAGE);
                ModelFile modelFile = models().cross(name+"_stage"+stage,modLoc("block/"+name+"_stage"+stage));
                return ConfiguredModel.builder().modelFile(modelFile).build();
            });
            iconTexture(name,modLoc("block/"+name+"_stage0"));
        }

        public void tallCinnamonBush(TallCinammonSaplingBlock block) {
            String name = name(block);
            getVariantBuilder(block).forAllStates(state -> {
                int stage = state.getValue(CinnamonSaplingBlock.STAGE);
                DoubleBlockHalf half = state.getValue(DoublePlantBlock.HALF);
                ModelFile modelFile = models().cross(name+"_stage"+stage,modLoc("block/"+name+"_"+half.getSerializedName()+"_stage"+stage));
                return ConfiguredModel.builder().modelFile(modelFile).build();
            });
        }

        public void feverblossom(CropBlock block) {
            String name = name(block);
            getVariantBuilder(block).forAllStatesExcept(state -> {
               int age = state.getValue(CropBlock.AGE);
               //  "parent": "block/cross",
                //  "textures": {
                //    "cross": "abyssaldecor:block/feverblossomcrop1",
                //    "particle": "abyssaldecor:block/feverblossomcrop1"
                //  },
                //  "render_type": "cutout_mipped"
                //}
                ModelFile file = models().cross(name+"_"+age,modLoc("block/feverblossomcrop"+age));
                return ConfiguredModel.builder().modelFile(file).build();
            });

        }

        public void moldyStalk(Block block) {
            getVariantBuilder(block).forAllStatesExcept(state -> {
                TriPart part = state.getValue(MoldyStalkBlock.TRI_PART);
                ModelFile file = switch (part){
                    case TOP -> models().cross("moldy_stalk_top",modLoc("block/moldystalktop"));
                    case MIDDLE -> models().cross("moldy_stalk_middle",modLoc("block/moldystalkmid"));
                    case BOTTOM -> models().cross("moldy_stalk_bottom",modLoc("block/moldystalkbottom"));
                };
                return ConfiguredModel.builder().modelFile(file).build();
            });
            iconTexture("moldy_stalk",modLoc("block/moldy_stalk"));
        }

        public void ribbon(BarrierRibbonBlock block,ResourceLocation model, ResourceLocation texture) {
            String name = name(block);
            //{
            //  "parent": "abyssaldecor:custom/velvetbarrierribbon",
            //  "textures": {
            //    "all": "abyssaldecor:block/velvetbarrier2",
            //    "particle": "abyssaldecor:block/velvetbarrier2",
            //    "1": "abyssaldecor:block/velvetbarrier2"
            //  },
            //  "render_type": "cutout_mipped"
            //}
            horizontalBlock(block,models().withExistingParent(name,model)
                    .texture("0",texture)
                    .texture("particle",texture)
            );
        }

        public void smallCornerBar(SmallBarsCornerBlock block,ResourceLocation texture) {
            //{
            //  "parent": "abyssal_decor:custom/smallbloodcoralbarscorner",
            //  "textures": {
            //    "all": "abyssal_decor:block/smallbloodcoralbarscorner",
            //    "particle": "abyssal_decor:block/smallbloodcoralbarscorner",
            //    "0": "abyssal_decor:block/smallbloodcoralbarscorner"
            //  },
            //  "render_type": "cutout_mipped"
            //}

            //{
            //  "parent": "abyssal_decor:custom/upsidedownbloodcoralbarscorner",
            //  "textures": {
            //    "all": "abyssal_decor:block/smallbloodcoralbarscorner",
            //    "particle": "abyssal_decor:block/smallbloodcoralbarscorner",
            //    "3": "abyssal_decor:block/smallbloodcoralbarscorner"
            //  },
            //  "render_type": "cutout_mipped"
            //}

            String name = name(block);

            ResourceLocation model = modLoc("custom/smallbarscorner");
            ResourceLocation upsideDownModel = modLoc("custom/upsidedownsmallbarscorner");



            getVariantBuilder(block).forAllStatesExcept(state -> {
                CornerDirection cornerDirection = state.getValue(SmallBarsCornerBlock.CORNER);
                Direction vertical = state.getValue(BlockStateProperties.VERTICAL_DIRECTION);


                ModelFile file = models().withExistingParent(name+(vertical == Direction.UP ? "_top" : ""),vertical == Direction.UP ? upsideDownModel : model)
                        .texture("0",texture)
                        .texture("particle",texture);

                return ConfiguredModel.builder()
                        .modelFile(file)
                        .rotationY(cornerDirection.yRotation())
                        .build();
            });
        }

        public void goldSconce(IronSconceBlock block,ResourceLocation texture) {
            sconce(block,texture,modLoc("custom/goldsconce"),modLoc("custom/goldsconceupsidedown"));
        }

        public void regularSconce(IronSconceBlock block,ResourceLocation texture) {
            sconce(block,texture,modLoc("custom/cleanironsconce"),modLoc("custom/upsidedowncleanironsconce"));
        }

        public void sconce(IronSconceBlock block,ResourceLocation texture,ResourceLocation model,ResourceLocation upsideDownModel) {
            //{
            //  "parent": "abyssaldecor:custom/cleanironsconce",
            //  "textures": {
            //    "all": "abyssaldecor:block/clean_iron_sconce",
            //    "particle": "abyssaldecor:block/clean_iron_sconce",
            //    "1": "abyssaldecor:block/clean_iron_sconce"
            //  },
            //  "render_type": "cutout_mipped"
            //}
            String name = name(block);



            ModelFile file = models().withExistingParent(name,model)
                    .texture("0",texture)
                    .texture("particle",texture);

            ModelFile fileUp = models().withExistingParent(name+"_top",upsideDownModel)
                    .texture("0",texture)
                    .texture("particle",texture);

            horizontalBlock(block,state -> state.getValue(BlockStateProperties.VERTICAL_DIRECTION) ==  Direction.UP ? fileUp : file);

            simpleBlockItem(block,file);
        }

        public void smallBars(SmallBarsBlock block,ResourceLocation texture) {
            //{
            //  "parent": "abyssal_decor:custom/smallbloodcoralbars",
            //  "textures": {
            //    "all": "abyssal_decor:block/smallbloodcoralbars",
            //    "particle": "abyssal_decor:block/smallbloodcoralbars",
            //    "2": "abyssal_decor:block/smallbloodcoralbars"
            //  },
            //  "render_type": "cutout_mipped"
            //}
            //{
            //  "parent": "abyssal_decor:custom/upsidedownbloodcoralbars",
            //  "textures": {
            //    "all": "abyssal_decor:block/smallbloodcoralbars",
            //    "particle": "abyssal_decor:block/smallbloodcoralbars",
            //    "0": "abyssal_decor:block/smallbloodcoralbars"
            //  },
            //  "render_type": "cutout_mipped"
            //}

            ModelFile.ExistingModelFile regularFile = models().getExistingFile(modLoc("custom/smallbars"));
            ModelFile.ExistingModelFile upsideDownFile = models().getExistingFile(modLoc("custom/upsidedownsmallbars"));
            String name = name(block);

            getVariantBuilder(block).forAllStates(state -> {
                Direction vertical = state.getValue(BlockStateProperties.VERTICAL_DIRECTION);
                Direction horizontal = state.getValue(HorizontalDirectionalBlock.FACING);
                ModelFile parentFile = vertical == Direction.UP ? upsideDownFile : regularFile;

                ModelFile file = models().withExistingParent(name + (vertical == Direction.UP ? "_top" : ""),parentFile.getLocation())
                        .texture("0",texture)
                        .texture("particle",texture);

                return ConfiguredModel.builder()
                        .modelFile(file)
                        .rotationY(((int) horizontal.toYRot() + 180) % 360)
                        .build();
            });
            iconTexture(name,texture);
        }

        //    public void axisBlock(RotatedPillarBlock block, ModelFile vertical, ModelFile horizontal) {
        //        getVariantBuilder(block)
        //            .partialState().with(RotatedPillarBlock.AXIS, Axis.Y)
        //                .modelForState().modelFile(vertical).addModel()
        //            .partialState().with(RotatedPillarBlock.AXIS, Axis.Z)
        //                .modelForState().modelFile(horizontal).rotationX(90).addModel()
        //            .partialState().with(RotatedPillarBlock.AXIS, Axis.X)
        //                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
        //    }

        public void ironLantern(IronLanternBlock block) {

            String name = name(block);
            ResourceLocation side = blockTexture(block);
            ResourceLocation sideLit = side.withSuffix("_on");
            ResourceLocation top = side;//.withSuffix("_top");
            ResourceLocation topLit = top.withSuffix("_on");

            BlockModelBuilder vertical = models().cubeColumn(name, side, top);
            BlockModelBuilder verticalLit = models().cubeColumn(name+"_lit", sideLit, topLit);

            BlockModelBuilder horizontal = models().cubeColumnHorizontal(name + "_horizontal", side, top);
            BlockModelBuilder horizontalLit = models().cubeColumnHorizontal(name + "_horizontal_lit", sideLit, topLit);

            getVariantBuilder(block).forAllStatesExcept(state -> {
                Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);
                boolean lit = state.getValue(BlockStateProperties.LIT);

                ModelFile file = axis == Direction.Axis.Y ? lit ? verticalLit : vertical : lit ? horizontalLit : horizontal;

                return ConfiguredModel.builder()
                        .modelFile(file)
                        .rotationX(axis != Direction.Axis.Y ? 90 : 0)
                        .rotationY(axis == Direction.Axis.X ? 90 : 0)
                        .uvLock(false)
                        .build();

            } ,BlockStateProperties.WATERLOGGED);

            simpleBlockItem(block,vertical);
        }

        public void hangingSignBlock(CeilingHangingSignBlock signBlock, WallHangingSignBlock wallSignBlock, ResourceLocation texture) {
            ModelFile sign = models().sign(name(signBlock), texture);
            hangingSignBlock(signBlock, wallSignBlock, sign);
        }

        public void hangingSignBlock(CeilingHangingSignBlock signBlock, WallHangingSignBlock wallSignBlock, ModelFile sign) {
            simpleBlock(signBlock, sign);
            simpleBlock(wallSignBlock, sign);
        }

        ItemModelBuilder iconTexture(String path, ResourceLocation texture) {
            return itemModels().singleTexture(path, mcLoc("item/generated"), "layer0", texture);
        }

        void paneBlockWithItem(IronBarsBlock block, ResourceLocation texture, ResourceLocation top) {
            paneBlock(block, texture, top);

            String path = name(block);
            itemModels().singleTexture(path, mcLoc("item/generated"),
                    "layer0", texture);

        }

        void simpleSlab(SlabBlock slabBlock, ResourceLocation texture) {
            String name = name(slabBlock);
            slabBlock(slabBlock, texture, texture);
            simpleBlockItem(slabBlock, models().withExistingParent(name,
                    mcLoc("block/slab")));
        }

        void serpentEye(FaceAttachedHorizontalDirectionalBlock block, ModelFile file) {
            getVariantBuilder(block).forAllStates(state -> {
                Direction facing = state.getValue(ButtonBlock.FACING);
                AttachFace face = state.getValue(ButtonBlock.FACE);

                return ConfiguredModel.builder()
                        .modelFile(file)
                        .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                        .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                        .uvLock(face == AttachFace.WALL)
                        .build();
            });
            simpleBlockItem(block, file);
        }

        void serpentEye(FaceAttachedHorizontalDirectionalBlock block, ModelFile file, boolean uvlock) {
            getVariantBuilder(block).forAllStates(state -> {
                Direction facing = state.getValue(ButtonBlock.FACING);
                AttachFace face = state.getValue(ButtonBlock.FACE);

                return ConfiguredModel.builder()
                        .modelFile(file)
                        .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                        .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                        .uvLock(uvlock)
                        .build();
            });
            simpleBlockItem(block, file);
        }

        void centerable(FaceAttachedHorizontalDirectionalBlock block, ModelFile file, ModelFile centerFile) {
            getVariantBuilder(block).forAllStatesExcept(state -> {
                Direction facing = state.getValue(ButtonBlock.FACING);
                AttachFace face = state.getValue(ButtonBlock.FACE);

                boolean centered = state.getValue(WoodSupportBlock.CENTERED);

                return ConfiguredModel.builder()
                        .modelFile(centered ? centerFile : file)
                        .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                        .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                        .uvLock(false)
                        .build();
            }, BlockStateProperties.WATERLOGGED);
            simpleBlockItem(block, file);
        }

        void trapdoor(TrapDoorBlock block) {
            ResourceLocation trapLoc = BuiltInRegistries.BLOCK.getKey(block);
            trapdoorBlock(block, modLoc("block/" + trapLoc.getPath()), false);
            simpleBlockItem(block, models().getExistingFile(modLoc(name(block) + "_bottom")));
        }

        void logBlockWithItem(RotatedPillarBlock block) {
            logBlock(block);
            simpleBlockItem(block, models().getExistingFile(modLoc(name(block))));
        }

        void simplePaneBlock(IronBarsBlock block) {
            String name = name(block);
            name = name.substring(0, name.length() - 5);
            paneBlock(block, modLoc("block/" + name), modLoc("block/" + name));
            ModelFile generated = itemModels().getExistingFile(mcLoc("item/generated"));
            itemModels().getBuilder(name + "_pane").parent(generated)
                    .texture(
                            "layer0", modLoc("block/" + name));
        }

        //{
        //  "parent": "abyssaldecor:custom/clamemptyclosed",
        //  "textures": {
        //    "all": "abyssaldecor:block/clam1",
        //    "particle": "abyssaldecor:block/clam1",
        //    "0": "abyssaldecor:block/clam1",
        //    "1": "abyssaldecor:block/clam2"
        //  },
        //  "render_type": "cutout_mipped"
        //}
        public void clam(ClamBlock clamBlock) {
            String name = name(clamBlock);
            getVariantBuilder(clamBlock).forAllStatesExcept(state -> {
                boolean open = state.getValue(ClamBlock.OPEN);
                ResourceLocation location = open ? modLoc("custom/clamemptyopen") : modLoc("custom/clamemptyclosed");
                ModelFile file = models().withExistingParent(name + (open ? "_open" : "_closed"), location)
                        .texture("all", modLoc("block/clam1"))
                        .texture("particle", modLoc("block/clam1"))
                        .texture("0", modLoc("block/clam1"))
                        .texture("1", modLoc("block/clam2"));
                return ConfiguredModel.builder().modelFile(file).rotationY(getRotation(state.getValue(ClamBlock.FACING)).y).build();
            });

            simpleBlockItem(clamBlock, new ModelFile.UncheckedModelFile(modLoc(name + "_closed")));
        }

        public void crackedBlock(CrackedBlock block) {
            String name = name(block);
            getVariantBuilder(block).forAllStates(state -> {
                int variant = state.getValue(CrackedBlock.VARIANT);
                ModelFile modelFile = models().cubeAll(name + "_" + variant, modLoc("block/" + name + "_" + variant));
                return ConfiguredModel.builder().modelFile(modelFile).build();
            });
            ModelFile modelFile = models().cubeAll(name, modLoc("block/" + name + "_0"));
            simpleBlockItem(block, modelFile);
        }

        public void mixedBlock(MixedBlock block) {
            String name = name(block);
            getVariantBuilder(block).forAllStates(state -> {
                int variant = state.getValue(MixedBlock.VARIANT);
                ModelFile modelFile = models().cubeAll(name + "_" + variant, modLoc("block/" + name + "_" + variant));
                return ConfiguredModel.builder().modelFile(modelFile).build();
            });
            ModelFile modelFile = models().cubeAll(name, modLoc("block/" + name + "_0"));
            simpleBlockItem(block, modelFile);
        }

        public void woodBlockWithItem(RotatedPillarBlock block, ResourceLocation texture) {
            ModelFile modelFile = models().cubeColumn(name(block), texture, texture);
            axisBlock(block, modelFile, modelFile);
            simpleBlockItem(block, modelFile);
        }

        public void simpleDoorBlock(DoorBlock door) {
            ResourceLocation doorLoc = BuiltInRegistries.BLOCK.getKey(door);
            ResourceLocation top = modLoc("block/" + doorLoc.getPath() + "_top");
            ResourceLocation bottom = modLoc("block/" + doorLoc.getPath() + "_bottom");
            doorBlock(door, bottom, top);
        }

        void doubleBlock(Block block, ModelFile top, ModelFile bottom) {
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

                        ModelFile modelFile = models().getExistingFile(modLoc("block/" + name + "_" + half.getSerializedName()));
                        return ConfiguredModel.builder().modelFile(modelFile).rotationY(getRotation(direction).y).build();
                    }
            );
        }

        void starfish(Block block) {
            getVariantBuilder(block)
                    .forAllStatesExcept(blockState -> {
                        DyeColor color = blockState.getValue(StarfishBlock.COLOR);
                        int count = blockState.getValue(StarfishBlock.COUNT);
                        String s = "block/" + count + "_" + color.getName() + "_starfish";
                        ModelFile file = models().withExistingParent(s, mcLoc("block/lily_pad"))
                                .texture("texture", s).texture("particle", s);

                        Direction orientation = blockState.getValue(AbstractDirectionalBlock.FACING);
                        Vector2i vector2i = getRotation(orientation);
                        return ConfiguredModel.builder().modelFile(file).rotationY(vector2i.y).build();
                    });

            iconTexture("starfish", modLoc("block/1_orange_starfish"));
        }

        void driedStarfish(Block block) {
            getVariantBuilder(block)
                    .forAllStatesExcept(blockState -> {
                        int count = blockState.getValue(StarfishBlock.COUNT);
                        String s = "block/" + count + "_dried_starfish";
                        ModelFile file = models().withExistingParent(s, mcLoc("block/lily_pad"))
                                .texture("texture", s).texture("particle", s);

                        Direction orientation = blockState.getValue(AbstractDirectionalBlock.FACING);
                        Vector2i vector2i = getRotation(orientation);

                        return ConfiguredModel.builder().modelFile(file).rotationY(vector2i.y).build();
                    });
        }

        void barrierPoleBlock(Block block, ResourceLocation modelBottom, ResourceLocation modelTop, ResourceLocation texture,ResourceLocation icon) {
            String name = name(block);
            ModelFile bottom = models().withExistingParent(name + "_bottom", modelBottom)
                    .texture("all", texture).texture("particle", texture).texture("0", texture);

            ModelFile top = models().withExistingParent(name + "_top", modelTop)
                    .texture("all", texture).texture("particle", texture).texture("0", texture);

            getVariantBuilder(block)
                    .forAllStatesExcept(blockState -> {
                        DoubleBlockHalf half = blockState.getValue(BarrierPoleBlock.HALF);
                        return ConfiguredModel.builder().modelFile(half == DoubleBlockHalf.UPPER ? top : bottom).build();
                    }, BlockStateProperties.WATERLOGGED);

            iconTexture(name,icon);
        }

        String name(Block block) {
            return BuiltInRegistries.BLOCK.getKey(block).getPath();
        }

        public void simplestBlockWithItem(Block block) {
            simpleBlockWithItem(block, cubeAll(block));
        }

        public void bulkheadLampBlock(BulkheadLampBlock block, ResourceLocation baseModel) {
            String name = name(block);

            ResourceLocation texture = modLoc("block/bulkhead_lamp_on");

            ModelFile buttonModel = models().withExistingParent(name, baseModel)
                    .texture("particle", texture)
                    .texture("0", texture);


            ModelFile buttonModelLit = models().withExistingParent(name + "_lit", baseModel)
                    .texture("particle", texture)
                    .texture("0", texture);

            getVariantBuilder(block).forAllStates(state -> {
                Direction facing = state.getValue(ButtonBlock.FACING);
                AttachFace face = state.getValue(ButtonBlock.FACE);
                boolean powered = state.getValue(RedstoneLampBlock.LIT);

                return ConfiguredModel.builder()
                        .modelFile(powered ? buttonModelLit : buttonModel)
                        .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                        .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                        .uvLock(false)
                        .build();
            });

            simpleBlockItem(block,buttonModel);
        }


        protected void blockLamp(Block block, ResourceLocation texture0) {
            String name = name(block);
            ResourceLocation texture0Lit = texture0.withSuffix("_lit");
            getVariantBuilder(block).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);

                ResourceLocation texture = lit ? texture0Lit : texture0;

                ModelFile modelFile = models().withExistingParent("block/" + name + (lit ? "_lit" : ""),
                                mcLoc("block/cube_all"))
                        .texture("all", texture)
                        .texture("particle", texture);
                return ConfiguredModel.builder().modelFile(modelFile).build();
            }, BlockStateProperties.WATERLOGGED);
            simpleBlockItem(block, models().getExistingFile(modLoc("block/" + name)));
        }

        protected void lamp(Block block, ResourceLocation model, ResourceLocation texture0) {
            String name = name(block);
            ResourceLocation texture0Lit = texture0.withSuffix("_lit");
            getVariantBuilder(block).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);

                ResourceLocation texture = lit ? texture0Lit : texture0;

                ModelFile modelFile = models().withExistingParent("block/" + name + (lit ? "_lit" : ""),
                                model)
                        .texture("all", texture)
                        .texture("particle", texture)
                        .texture("0", texture);
                return ConfiguredModel.builder().modelFile(modelFile).build();
            }, BlockStateProperties.WATERLOGGED);
        }


        protected void wallLamp(Block block, ResourceLocation model, ResourceLocation texture0) {
            String name = name(block);
            ResourceLocation texture0Lit = texture0.withSuffix("_lit");
            getVariantBuilder(block).forAllStatesExcept(blockState -> {
                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
                Direction orientation = blockState.getValue(HorizontalLampBlock.FACING);

                ResourceLocation texture = lit ? texture0Lit : texture0;

                ModelFile modelFile = models().withExistingParent("block/" + name + (lit ? "_lit" : ""),
                                model)
                        .texture("all", texture)
                        .texture("particle", texture)
                        .texture("0", texture);
                Vector2i vector2i = getRotation(orientation);
                return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).build();
            }, BlockStateProperties.WATERLOGGED);

            // simpleBlock(AbyssalDecorBlocks.FRESNEL_LAMP.get());
        }

        static Vector2i getRotation(Direction direction) {
            return switch (direction) {
                case DOWN -> new Vector2i(180, 0);
                case UP -> new Vector2i();
                case NORTH -> new Vector2i(90, 0);
                case SOUTH -> new Vector2i(90, 180);
                case WEST -> new Vector2i(90, 270);
                case EAST -> new Vector2i(90, 90);
            };
        }
    }
}
