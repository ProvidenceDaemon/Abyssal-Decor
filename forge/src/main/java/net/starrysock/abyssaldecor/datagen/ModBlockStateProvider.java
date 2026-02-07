package net.starrysock.abyssaldecor.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.block.*;
import net.starrysock.abyssaldecor.block.lamp.BulkheadLampBlock;
import net.starrysock.abyssaldecor.block.lamp.HorizontalLampBlock;
import net.starrysock.abyssaldecor.block.lamp.IronLanternBlock;
import net.starrysock.abyssaldecor.block.properties.*;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.ExtendedBlockFamilies;
import net.starrysock.abyssaldecor.registry.ExtendedBlockFamily;
import org.joml.Vector2i;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AbyssalDecor.MOD_ID, exFileHelper);
    }

    public CTMBlockStateVariantBuilder athenaCTMBlock(Block b) {
        VariantBlockStateBuilder variantBlockStateBuilder = getVariantBuilder(b);
        CTMBlockStateVariantBuilder c = new CTMBlockStateVariantBuilder(b,variantBlockStateBuilder);
        registeredBlocks.put(b,c);
        return c;
    }

    @Override
    protected void registerStatesAndModels() {

        ExtendedBlockFamilies.getAllFamilies().forEach(family -> {

            Block baseBlock = family.getBaseBlock();

            //special cases like pearl and blood coral
            if (!(baseBlock instanceof IronBarsBlock)) {
                simpleBlock(baseBlock);
            }
            ResourceLocation location = BuiltInRegistries.BLOCK.getKey(baseBlock);
            ResourceLocation baseTexture = modLoc("block/" + location.getPath());
            //todo signBlock((StandingSignBlock) family.get(Variant.SIGN),(WallSignBlock) family.get(Variant.WALL_SIGN), baseTexture);

            if (family.exists(ExtendedBlockFamily.Variant.TRAPDOOR)) {
                TrapDoorBlock trapdoor = (TrapDoorBlock) family.get(ExtendedBlockFamily.Variant.TRAPDOOR);
                ResourceLocation trapLoc = BuiltInRegistries.BLOCK.getKey(trapdoor);
                trapdoorBlock(trapdoor, modLoc("block/" + trapLoc.getPath()), true);
            }

            if (family.exists(ExtendedBlockFamily.Variant.DOOR)) {
                DoorBlock door = (DoorBlock) family.get(ExtendedBlockFamily.Variant.DOOR);

                simpleDoorBlock(door);
            }


            if (family.exists(ExtendedBlockFamily.Variant.CHISELED)) {
                Block chiseled = family.get(ExtendedBlockFamily.Variant.CHISELED);
                simplestBlockWithItem(chiseled);
            }

            if (family.exists(ExtendedBlockFamily.Variant.STAIRS)) {
                StairBlock stairBlock = (StairBlock) family.get(ExtendedBlockFamily.Variant.STAIRS);
                stairsBlock(stairBlock, baseTexture);
                SlabBlock slabBlock = (SlabBlock) family.get(ExtendedBlockFamily.Variant.SLAB);
                //Polished blood coral, blood coral bricks, seabrass, riveted seabrass, deepbronze, riveted deepbronze,
                // and starstone slabs have dedicated slab textures

                if (family.customSlabTexture) {
                    ResourceLocation sideTexture = baseTexture.withSuffix("_slab");
                    $slabBlock(slabBlock,sideTexture, baseTexture);
                } else {
                    slabBlock(slabBlock,baseTexture,baseTexture);
                }
            }

            if (family.exists(ExtendedBlockFamily.Variant.BUTTON)) {
                buttonBlock((ButtonBlock) family.get(ExtendedBlockFamily.Variant.BUTTON), baseTexture);
                pressurePlateBlock((PressurePlateBlock) family.get(ExtendedBlockFamily.Variant.PRESSURE_PLATE), baseTexture);
            }

            if (family.exists(ExtendedBlockFamily.Variant.FENCE)) {
                fenceBlock((FenceBlock) family.get(ExtendedBlockFamily.Variant.FENCE), baseTexture);
                fenceGateBlock((FenceGateBlock) family.get(ExtendedBlockFamily.Variant.FENCE_GATE), baseTexture);
            }

            if (family.exists(ExtendedBlockFamily.Variant.SIGN)) {
                StandingSignBlock signBlock = (StandingSignBlock) family.get(ExtendedBlockFamily.Variant.SIGN);
                signBlock(signBlock, (WallSignBlock) family.get(ExtendedBlockFamily.Variant.WALL_SIGN), modLoc("block/" + name(baseBlock)));
                iconTexture(name(signBlock), modLoc("item/" + name(signBlock)));

                //uses stripped logs but whatever
                CeilingHangingSignBlock ceilingHangingSignBlock = (CeilingHangingSignBlock) family.get(ExtendedBlockFamily.Variant.HANGING_SIGN);
                hangingSignBlock(ceilingHangingSignBlock, (WallHangingSignBlock) family.get(ExtendedBlockFamily.Variant.HANGING_WALL_SIGN), modLoc("block/" + name(baseBlock)));
                iconTexture(name(ceilingHangingSignBlock), modLoc("item/" + name(ceilingHangingSignBlock)));
            }

            if (family.exists(ExtendedBlockFamily.Variant.WALL)) {
                WallBlock wallBlock = (WallBlock) family.get(ExtendedBlockFamily.Variant.WALL);
                wallBlock(wallBlock, baseTexture);
                simpleBlockItem(wallBlock, models().wallInventory(BuiltInRegistries.BLOCK.getKey(wallBlock).getPath(), baseTexture));
            }
        });

        simplestBlockWithItem(AbyssalDecorBlocks.INACTIVE_MOLD.get());
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
                            modLoc("custom/frostedceilinglamp" + (lit ? "lit" : "")))
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
            ResourceLocation m = modLoc("custom/ironlampceiling" + (lit ? "lit" : ""));

            ModelFile modelFile = models().withExistingParent("block/iron_lamp" + (lit ? "_lit" : ""), m)
                    .texture("all", modLoc("block/ironlamp1"))
                    .texture("particle", modLoc("block/ironlamp1"))
                    .texture("0", modLoc("block/ironlamp1"))
                    .texture("1", modLoc("block/ironlamp2" + (lit ? "lit" : "")));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        }, BlockStateProperties.WATERLOGGED);

        getVariantBuilder(AbyssalDecorBlocks.WALL_IRON_LAMP.get()).forAllStatesExcept(blockState -> {
            boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
            Direction orientation = blockState.getValue(HorizontalDirectionalBlock.FACING);

            ResourceLocation m = modLoc("custom/wallironlamp" + (lit ? "lit" : ""));

            ModelFile modelFile = models().withExistingParent("block/wall_iron_lamp" + (lit ? "_lit" : ""),
                            m)
                    .texture("particle", modLoc("block/ironlamp1"))
                    .texture("0", modLoc("block/ironlamp1"))
                    .texture("1", modLoc("block/ironlamp2" + (lit ? "lit" : "")));
            Vector2i vector2i = getRotation(orientation);
            return ConfiguredModel.builder().modelFile(modelFile).rotationY(vector2i.y).
                    build();
        }, BlockStateProperties.WATERLOGGED);

        wallLamp(AbyssalDecorBlocks.FLOWER_LAMP.get(), modLoc("custom/flowerlamp"), modLoc("block/flower_lamp"));

        getVariantBuilder(AbyssalDecorBlocks.TUBE_LAMP.get()).forAllStatesExcept(blockState -> {
            boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
            Direction orientation = blockState.getValue(HorizontalDirectionalBlock.FACING);
            Half half = blockState.getValue(BlockStateProperties.HALF);


            ResourceLocation texture = modLoc("block/tubelamp");

            ModelFile modelFile = models().withExistingParent("block/tube_lamp" + (lit ? "_lit" : ""),
                            modLoc("custom/tubelamp" + (lit ? "lit" : "")))
                    .texture("particle", texture)
                    .texture("0", texture);
            return ConfiguredModel.builder().modelFile(modelFile)
                    .rotationX(half == Half.BOTTOM ? 0 : 180)
                    .rotationY((int) (half == Half.TOP ? orientation : orientation.getOpposite()).toYRot())
                    .build();
        });

        simpleBlock(AbyssalDecorBlocks.GRIME_CARPET.get(), models().getExistingFile(modLoc("block/grime_carpet")));
        simplestBlockWithItem(AbyssalDecorBlocks.WISTERIA_PETALS.get());
        simplestBlockWithItem(AbyssalDecorBlocks.ELDER_WISTERIA_PETALS.get());
        simplestBlockWithItem(AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES.get());

        logBlockWithItem(AbyssalDecorBlocks.ANCIENT_BIRCH_LOG.get());
        logBlockWithItem(AbyssalDecorBlocks.STRIPPED_ANCIENT_BIRCH_LOG.get());
        directionalBlockWithItem(AbyssalDecorBlocks.FOXY_PILLAR.get(), modLoc("block/foxy_pillar"), modLoc("block/foxy_pillar_top"));

        lamp(AbyssalDecorBlocks.QUARTZ_LAMP.get(), modLoc("custom/floorgaslamp"), modLoc("block/quartz_lamp"), modLoc("custom/floorgaslampon"));
        wallLamp(AbyssalDecorBlocks.WALL_QUARTZ_LAMP.get(), modLoc("custom/wallgaslamp"), modLoc("block/wall_quartz_lamp"));
        wallLamp(AbyssalDecorBlocks.CEILING_QUARTZ_LAMP.get(), modLoc("custom/ceilingquartzlamp"), modLoc("block/ceiling_quartz_lamp"));
        wallLamp(AbyssalDecorBlocks.JADE_LAMP.get(), modLoc("custom/jadelamp"), modLoc("block/jade_lamp"));
        wallLamp(AbyssalDecorBlocks.WALL_JADE_LAMP.get(), modLoc("custom/walljadelamp"), modLoc("block/wall_jade_lamp"));

        getVariantBuilder(AbyssalDecorBlocks.BLAZE_LAMP.get()).forAllStatesExcept(blockState -> {
            Direction orientation = blockState.getValue(net.starrysock.abyssaldecor.block.lamp.HorizontalLampBlock.FACING);

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

        blockLamp(AbyssalDecorBlocks.JADE_LANTERN.get(), modLoc("block/jade_lantern"));

        //simplestBlockWithItem(AbyssalDecorBlocks.IRON_LANTERN.get());


        //buttonLampBlock(AbyssalDecorBlocks.BULKHEAD_LAMP.get(),);

        barrierPoleBlock(AbyssalDecorBlocks.VELVET_BARRIER.get(), modLoc("custom/velvetbarrierbottom"), modLoc("custom/velvetbarriertop"),
                modLoc("block/velvetbarrier1"), modLoc("item/velvet_barrier"));

        barrierPoleBlock(AbyssalDecorBlocks.IRON_BARRIER.get(), modLoc("custom/ironbarrierbottom"), modLoc("custom/ironbarriertop"),
                modLoc("block/ironbarrier1"), modLoc("item/iron_barrier"));

        barrierPoleBlock(AbyssalDecorBlocks.ROPE_BARRIER.get(), modLoc("custom/ropebarrierpostbottom"), modLoc("custom/ropebarrierposttop"),
                modLoc("block/ropebarriertexture"), modLoc("item/rope_barrier"));

        barrierPoleBlock(AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get(), modLoc("custom/barbwirepostbottom"), modLoc("custom/barbwireposttop"),
                modLoc("block/barbwirebarrier1"), modLoc("item/barbed_wire_barrier"));

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
                .withExistingParent("block/wall_hanging_web", modLoc("custom/hangingweb"))
                .texture("particle", modLoc("block/hanging_web"))
                .texture("0", modLoc("block/hanging_web")));

        doubleBlock(AbyssalDecorBlocks.DANGLING_WEB.get(), models()
                .withExistingParent("block/dangling_web_upper", "block/cross").texture("cross", "block/dangling_web_upper"), models()
                .withExistingParent("block/dangling_web_lower", "block/cross").texture("cross", "block/dangling_web_lower"));

        lionStatue(AbyssalDecorBlocks.WALL_DANGLING_WEB.get());

        paneBlock(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE.get(), modLoc("block/prismarine_crystal_block"), modLoc("block/prismarine_crystal_block"));

        simpleBlock(AbyssalDecorBlocks.FRESNEL_BLOCK.get());
        paneBlock(AbyssalDecorBlocks.FRESNEL_PANE.get(), modLoc("block/fresnel_block"), modLoc("block/fresnel_block"));

        simpleBlock(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get());
        paneBlock(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE.get(), modLoc("block/crystallized_glowstone"), modLoc("block/crystallized_glowstone"));

        simpleBlock(AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE.get());
        paneBlock(AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE.get(), modLoc("block/framed_crystallized_glowstone"),
                modLoc("block/framedglowstonepanetop"));

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
        crackedBlock(AbyssalDecorBlocks.CRACKED_BRICKS.get());
        simplestBlockWithItem(AbyssalDecorBlocks.BRITTLE_TUFF.get());

        trapdoor(AbyssalDecorBlocks.STONE_TRAPDOOR.get());
        trapdoor(AbyssalDecorBlocks.MOSSY_STONE_TRAPDOOR.get());
        trapdoor(AbyssalDecorBlocks.ORNATE_STONE_TRAPDOOR.get());

        logBlockWithItem(AbyssalDecorBlocks.IRON_PILLAR.get());

        logBlockWithItem(AbyssalDecorBlocks.BLOOD_CORAL_PILLAR.get());
        logBlockWithItem(AbyssalDecorBlocks.ROUGH_BLOOD_CORAL.get());
        gildedPillar(AbyssalDecorBlocks.GILDED_BLOOD_CORAL_PILLAR.get());

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

        blockLamp(AbyssalDecorBlocks.SEABRASS_LAMP.get(), modLoc("block/seabrass_lamp"));
        blockLamp(AbyssalDecorBlocks.DEEPBRONZE_LANTERN.get(), modLoc("block/deepbronze_lantern"));
        blockLamp(AbyssalDecorBlocks.BLOOD_LANTERN.get(), modLoc("block/blood_lantern"));
        bloodLamp(AbyssalDecorBlocks.BLOOD_LANTERN_MULTIBLOCK.get());

        paneBlock(AbyssalDecorBlocks.DEEPBRONZE_BARS.get(), modLoc("block/deepbronze_bars"), modLoc("block/bronzebarstop"));


        AbyssalDecorBlocks.WALLPAPERS.forEach(block -> {
            directionalBlockWithItem(block, modLoc("block/" + name(block)));
        });

        simplestBlockWithItem(AbyssalDecorBlocks.LAVENTINE.get());
        //simplestBlockWithItem(AbyssalDecorBlocks.LAVENTINE_GLASS.get());

        //paneBlock(AbyssalDecorBlocks.LAVENTINE_GLASS_PANE.get(),modLoc("block/laventine_glass"),modLoc("block/deepbronze_bars"));

        simplestBlockWithItem(AbyssalDecorBlocks.SMOOTH_STARSTONE.get());

        simplestBlockWithItem(AbyssalDecorBlocks.STARGLASS.get());

        paneBlockWithItem(AbyssalDecorBlocks.STARGLASS_PANE.get(), modLoc("block/starglass"), modLoc("block/starglasspanetop"));

        woodBlockWithItem(AbyssalDecorBlocks.BLACKWOOD_SHINGLES.get(), modLoc("block/blackwood_shingles"));

        simplestBlockWithItem(AbyssalDecorBlocks.MOLDWEAVE.get());

        simpleBlockWithItem(AbyssalDecorBlocks.MOLDWEAVE_CARPET.get(), models().carpet("moldweave_carpet", modLoc("block/moldweave")));

        wallHangingMoss(AbyssalDecorBlocks.WALL_HANGING_MOSS.get());

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

        simplestBlockWithItem(AbyssalDecorBlocks.SEABRASS_PLATING.get());

        logBlockWithItem(AbyssalDecorBlocks.STRIPPED_CINNAMON_LOG.get());
        woodBlockWithItem(AbyssalDecorBlocks.STRIPPED_CINNAMON_WOOD.get(), modLoc("block/stripped_cinnamon_log"));

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

        directionalBlock(AbyssalDecorBlocks.HEART_OF_THE_SEA.get(), models().withExistingParent("heart_of_the_sea",
                        modLoc("custom/tinywhitepearl"))
                .texture("all", modLoc("block/heartoseablock"))
                .texture("particle", modLoc("block/heartoseablock")));

        directionalBlock(AbyssalDecorBlocks.SMALL_NETTED_SERPENT_EYE.get(), models().withExistingParent("small_netted_serpent_eye",
                        modLoc("custom/abyssaldecorsmallblock"))
                .texture("0", modLoc("block/small_netted_serpent_eye"))
                .texture("particle", modLoc("block/small_netted_serpent_eye")));

        simpleBlockItem(AbyssalDecorBlocks.SMALL_NETTED_SERPENT_EYE.get(), models().getExistingFile(modLoc("block/small_netted_serpent_eye")));

        simpleBlockItem(AbyssalDecorBlocks.IRON_BALL.get(), models().getExistingFile(modLoc("block/iron_ball")));

        paneBlockWithItem(AbyssalDecorBlocks.WHITEWOOD_PICKET_FENCE.get(), modLoc("block/whitewood_picket_fence"),
                Datagen.BLANK);

        serpentEye(AbyssalDecorBlocks.BRICK_PILLAR.get(), models().withExistingParent("brick_pillar", mcLoc("block/cube"))
                .texture("down", modLoc("block/brick_pillar_top"))
                .texture("up", modLoc("block/brick_pillar_top"))
                .texture("north", modLoc("block/brick_pillar"))
                .texture("east", modLoc("block/brick_pillar"))
                .texture("south", modLoc("block/brick_pillar"))
                .texture("west", modLoc("block/brick_pillar"))
                .texture("particle", modLoc("block/brick_pillar")), false);

        simplestBlockWithItem(AbyssalDecorBlocks.PITCHGLASS.get());

        paneBlockWithItem(AbyssalDecorBlocks.PITCHGLASS_PANE.get(), modLoc("block/pitchglass"),
                modLoc("block/pitchglass"));

        simpleBlockWithItem(AbyssalDecorBlocks.FRAMED_PITCHGLASS.get(), models().getExistingFile(modLoc("block/framed_pitchglass_top")));


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

        laventineGlass(AbyssalDecorBlocks.LAVENTINE_GLASS.get());

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

        regularSconce(AbyssalDecorBlocks.DEEPBRONZE_SCONCE.get(), modLoc("block/deepbronze_sconce"));
        regularSconce(AbyssalDecorBlocks.IRON_SCONCE.get(), modLoc("block/iron_sconce"));
        regularSconce(AbyssalDecorBlocks.DULL_IRON_SCONCE.get(), modLoc("block/dull_iron_sconce"));
        regularSconce(AbyssalDecorBlocks.BLOOD_CORAL_SCONCE.get(), modLoc("block/blood_coral_sconce"));
        goldSconce(AbyssalDecorBlocks.GOLD_SCONCE.get(), modLoc("block/gold_sconce"));
        regularSconce(AbyssalDecorBlocks.SEABRASS_SCONCE.get(), modLoc("block/seabrass_sconce"));

        smallCornerBar(AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS_CORNER.get(), modLoc("block/smallbloodcoralbarscorner"));

        smallBars(AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS.get(), modLoc("block/smallpearlbars"));
        smallCornerBar(AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS_CORNER.get(), modLoc("block/smallpearlbars"));

        smallBars(AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS.get(), modLoc("block/smallbarsclean"));
        smallCornerBar(AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS_CORNER.get(), modLoc("block/smallbarscleancorner"));

        smallBars(AbyssalDecorBlocks.SMALL_DULL_IRON_BARS.get(), modLoc("block/smallbarsdull"));
        smallCornerBar(AbyssalDecorBlocks.SMALL_DULL_IRON_BARS_CORNER.get(), modLoc("block/dullironbarscorner"));

        smallBars(AbyssalDecorBlocks.SMALL_SEABRASS_BARS.get(), modLoc("block/smallbarsbrass"));
        smallCornerBar(AbyssalDecorBlocks.SMALL_SEABRASS_BARS_CORNER.get(), modLoc("block/smallbarsbrasscorner"));

        smallBars(AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS.get(), modLoc("block/smallbronzebars"));
        smallCornerBar(AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS_CORNER.get(), modLoc("block/smallbronzebarscorner"));

        smallBars(AbyssalDecorBlocks.SMALL_STONE_BARS.get(), modLoc("block/smallstonebars"));
        smallCornerBar(AbyssalDecorBlocks.SMALL_STONE_BARS_CORNER.get(), modLoc("block/smallstonebarscorner"));

        smallBars(AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS.get(), modLoc("block/smallblackpearlbars"));
        smallCornerBar(AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS_CORNER.get(), modLoc("block/smallblackpearlbars"));

        simpleBlockItem(AbyssalDecorBlocks.CLAM.get(), models().getExistingFile(modLoc("block/clam_closed")));
        simpleBlockItem(AbyssalDecorBlocks.CLAM_WITH_PEARL.get(), models().getExistingFile(modLoc("block/clam_with_pearl_closed")));

        ribbon(AbyssalDecorBlocks.IRON_BARRIER_RIBBON.get(), modLoc("custom/velvetbarrierribbon"), modLoc("block/ironbarrier2"));
        ribbon(AbyssalDecorBlocks.ROPE_BARRIER_RIBBON.get(), modLoc("custom/ropebarrierrope"), modLoc("block/ropebarriertexture2"));
        ribbon(AbyssalDecorBlocks.BARBED_WIRE_RIBBON.get(), modLoc("custom/barbwirewire"), modLoc("block/barbwirebarrier2"));
        moldyStalk(AbyssalDecorBlocks.MOLDY_STALK.get());

        //{
        //  "parent": "block/cross",
        //  "textures": {
        //    "cross": "abyssal_decor:block/moldypuffball",
        //    "particle": "abyssal_decor:block/moldypuffball"
        //  },
        //  "render_type": "cutout_mipped"
        //}
        simpleBlock(AbyssalDecorBlocks.MOLDY_STALK_SPROUT.get(), models().cross("moldy_stalk_sprout",
                modLoc("block/moldy_stalk")));


        simpleBlock(AbyssalDecorBlocks.MOLDY_FEATHERS.get(), models().cross("moldy_feathers", modLoc("block/moldy_feathers")));
        iconTexture("moldy_feathers", modLoc("block/moldy_feathers"));

        feverblossom(AbyssalDecorBlocks.FEVER_BLOSSOM.get());
        iconTexture("fever_blossom_seeds", modLoc("item/fever_blossom_seeds"));
        iconTexture("fever_blossom", modLoc("item/fever_blossom"));

        cinnamonBush(AbyssalDecorBlocks.CINNAMON_BUSH.get());
        tallCinnamonBush(AbyssalDecorBlocks.TALL_CINNAMON_BUSH.get());

        industrialLever(AbyssalDecorBlocks.INDUSTRIAL_LEVER.get());

        wisteria(AbyssalDecorBlocks.WISTERIA.get());
        iconTexture("wisteria", modLoc("item/wisteria"));
        wisteria(AbyssalDecorBlocks.ELDER_WISTERIA.get());
        iconTexture("elder_wisteria", modLoc("item/elder_wisteria"));

        horizontalBlock(AbyssalDecorBlocks.VERTICAL_TUBE_LAMP.get(), state -> {
                    boolean lit = state.getValue(RedstoneLampBlock.LIT);
                    return models().withExistingParent("vertical_tube_lamp" + (lit ? "_lit" : ""), modLoc("custom/tubelamp90" + (lit ? "lit" : "")))
                            .texture("particle", modLoc("block/tubelamp"))
                            .texture("0", modLoc("block/tubelamp"));
                }
        );

        spiderCorn(AbyssalDecorBlocks.SPIDERCORN.get());
        spiderCorn(AbyssalDecorBlocks.MUCKROOT.get());

        bogApple(AbyssalDecorBlocks.BOG_APPLE_LEAVES.get());
        simpleBlock(AbyssalDecorBlocks.BLOOD_CORAL_BUD.get(), models().cross("blood_coral_bud", modLoc("block/blood_coral_bud")));
        post(AbyssalDecorBlocks.CINNAMON_POST.get(), modLoc("custom/cinnamonpostbottom"), modLoc("custom/cinnamonpostmid")
                , modLoc("custom/cinnamonposttop"));
        post(AbyssalDecorBlocks.DULL_IRON_POST.get(), modLoc("custom/dullironpostbottom"), modLoc("custom/dullironpostmid")
                , modLoc("custom/dullironposttop"));
        hanger();
        brickCap();
        stackedIronBalls(AbyssalDecorBlocks.STACKED_IRON_BALLS.get());

        curtains(AbyssalDecorBlocks.VELVET_CURTAIN.get());

        curtains(AbyssalDecorBlocks.WOOL_CURTAIN.get());
        gargoyle(AbyssalDecorBlocks.GARGOYLE.get());

        axisBlock(AbyssalDecorBlocks.HEALING_CINNAMON_LOG.get(), modLoc("block/healing_cinnamon_log"), modLoc("block/cinnamonlogtop"));
        simpleBlockItem(AbyssalDecorBlocks.HEALING_CINNAMON_LOG.get(), models().getExistingFile(modLoc("block/healing_cinnamon_log")));
        woodBlockWithItem(AbyssalDecorBlocks.HEALING_CINNAMON_WOOD.get(), modLoc("block/healing_cinnamon_log"));
        directionalBlock(AbyssalDecorBlocks.AMARANTH_CRATE.get(), models().cubeBottomTop("amaranth_crate",
                modLoc("block/amaranthcrate"), modLoc("block/amaranthcratebottom"), modLoc("block/amaranthcratetop")));

        serpentEye(AbyssalDecorBlocks.SHELL.get(), models().withExistingParent("shell", modLoc("custom/shellfloor"))
                .texture("particle", modLoc("block/shelltop"))
                .texture("0", modLoc("block/shelltop"))
                .texture("1", modLoc("block/shellbottom"))
        );
        iconTexture("shell", modLoc("block/shelltop"));
        fancyIronBarsBlock(AbyssalDecorBlocks.GOLD_BARS.get(), modLoc("block/gold_bars_middle"), modLoc("block/gold_bars_top"),
                modLoc("block/gold_bars_top"));

        fancyIronBarsBlock(AbyssalDecorBlocks.FRAMED_PITCHGLASS_PANE.get(), modLoc("block/pitchglasspanemid"),
                modLoc("block/pitchglasspanetop"), modLoc("block/starglasspanetop"));


        fancyIronBarsBlock(AbyssalDecorBlocks.LAVENTINE_GLASS_PANE.get(), modLoc("block/laventinepanemid"), modLoc("block/laventinepanetop"),
                modLoc("block/laventinepanemid"));


        fancierIronBarsBlock(AbyssalDecorBlocks.WHITE_PEARL_BARS.get(), modLoc("block/white_pearl_bars_solo"),
                modLoc("block/white_pearl_bars_bottom"), modLoc("block/white_pearl_bars_middle"),
                modLoc("block/white_pearl_bars_top"),
                modLoc("block/white_pearl_bars_top"), true);

        fancierIronBarsBlock(AbyssalDecorBlocks.DULL_IRON_BARS.get(), modLoc("block/dull_iron_bars_solo"),
                modLoc("block/dull_iron_bars_bottom"), modLoc("block/dull_iron_bars_middle"),
                modLoc("block/dull_iron_bars_top"),
                modLoc("block/dull_iron_bars_solo"), false);

        fancierIronBarsBlock(AbyssalDecorBlocks.BLACK_PEARL_BARS.get(), modLoc("block/black_pearl_bars_solo"),
                modLoc("block/black_pearl_bars_bottom"), modLoc("block/black_pearl_bars_middle"),
                modLoc("block/black_pearl_bars_top"),
                modLoc("block/black_pearl_bars_top"), false);

        fancierIronBarsBlock(AbyssalDecorBlocks.BLOOD_CORAL_BARS.get(), modLoc("block/blood_coral_bars_solo"),
                modLoc("block/blood_coral_bars_bottom"), modLoc("block/blood_coral_bars_middle"),
                modLoc("block/blood_coral_bars_top"),
                modLoc("block/blood_coral_bars_top"), false);

        fancierIronBarsBlock(AbyssalDecorBlocks.SEABRASS_BARS.get(), modLoc("block/seabrass_bars_solo"),
                modLoc("block/seabrass_bars_bottom"), modLoc("block/seabrass_bars_middle"),
                modLoc("block/seabrass_bars_top"),
                modLoc("block/seabrass_bars_top"), false);

        fancyIronBarsBlock(AbyssalDecorBlocks.STONE_BARS.get(), modLoc("block/stone_bars_mid"), modLoc("block/stone_bars_solo"),
                modLoc("block/stone_bars_solo"));

        wallGrime(AbyssalDecorBlocks.WALL_GRIME.get());
        blackMold(AbyssalDecorBlocks.BLACK_MOLD.get());
        verticalConnectedBlock(AbyssalDecorBlocks.FROSTED_GLASS.get(), modLoc("block/frosted_glass"),
                modLoc("block/frosted_glass_bottom"), modLoc("block/frosted_glass_middle"),
                modLoc("block/frosted_glass_top"));

        fancierIronBarsBlock(AbyssalDecorBlocks.FROSTED_GLASS_PANE.get(), modLoc("block/frosted_glass"),
                modLoc("block/frosted_glass_bottom"), modLoc("block/frosted_glass_middle"),
                modLoc("block/frosted_glass_top"),
                modLoc("block/frosted_glass"), false);

        wallLamp(AbyssalDecorBlocks.WALL_BULB_LAMP.get(), modLoc("custom/bulblamp"), modLoc("block/bulblamp"));

        simpleBlockWithItem(AbyssalDecorBlocks.WHITEWOOD_PLANTER.get(), models()
                .cubeBottomTop("whitewood_planter", modLoc("block/whitewoodplanterside"), modLoc("block/whitewoodplanterbottom")
                        , modLoc("block/whitewoodplantertop")));

        directionalBlock(AbyssalDecorBlocks.SEABRASS_CATALYST.get(), models().getExistingFile(modLoc("block/seabrass_catalyst")));
        simpleBlockItem(AbyssalDecorBlocks.SEABRASS_CATALYST.get(), models().getExistingFile(modLoc("block/seabrass_catalyst")));

        simpleBlock(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK.get());
       // athenaCTMBlock(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK.get())
       //         .ctmTexture()
       // ;
    }

    public void $slabBlock(SlabBlock block, ResourceLocation side, ResourceLocation texture) {
        slabBlock(block, models().slab(name(block), side, texture,texture),
                models().slabTop(name(block) + "_top", side, texture, texture),
                models().cubeColumn(name(block) +"_double",side,texture));
    }

    void blackMold(Block block) {
        ModelFile main = models().cubeAll(name(block), modLoc("block/blackmold"));
        simpleBlock(block, ConfiguredModel.allRotations(main, false));
    }

    void wallGrime(WallGrimeBlock block) {
        String name = name(block);
        ModelFile ceiling = models().getExistingFile(modLoc("block/grime_ceiling"));
        ModelFile top = models().getExistingFile(modLoc("block/grime_top"));
        ModelFile midFloor = models().getExistingFile(modLoc("block/grime_mid_floor"));
        ModelFile floor = models().getExistingFile(modLoc("block/grime_floor"));
        ModelFile midCeiling = models().getExistingFile(modLoc("block/grime_mid_ceiling"));
        ModelFile mid = models().getExistingFile(modLoc("block/grime_mid"));
        ModelFile bottom = models().getExistingFile(modLoc("block/grime_bottom"));
        getVariantBuilder(block).forAllStates(state -> {
            int angleOffset = 180;

            ModelFile file = switch (state.getValue(ModBlockStateProperties.WALL_GRIME_TYPE)) {
                case FLOOR -> floor;
                case MIDDLE_FLOOR -> midFloor;
                case MIDDLE -> mid;
                case MIDDLE_CEILING -> midCeiling;
                case TOP -> top;
                case CEILING -> ceiling;
                case BOTTOM -> bottom;
            };

            return ConfiguredModel.builder().modelFile(file)
                    .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) % 360)
                    .build();
        });
    }

    void verticalConnectedBlock(Block block, ResourceLocation paneSolo, ResourceLocation paneBottom,
                                ResourceLocation paneMiddle, ResourceLocation paneTop) {

        String name = name(block);
        ModelFile solo = models().cubeAll(name + "_solo", paneSolo);
        ModelFile bottom = models().cubeColumn(name + "_bottom", paneBottom, paneSolo);
        ModelFile middle = models().cubeColumn(name + "_middle", paneMiddle, paneSolo);
        ModelFile top = models().cubeColumn(name + "_top", paneTop, paneSolo);

        getVariantBuilder(block).forAllStates(state -> {
            ModelFile file = switch (state.getValue(ModBlockStateProperties.VERTICAL_CONNECTION)) {
                case SOLO -> solo;
                case BOTTOM -> bottom;
                case MIDDLE -> middle;
                case TOP -> top;
            };
            return ConfiguredModel.builder().modelFile(file).build();
        });
        simpleBlockItem(block, solo);
    }

    void fancierIronBarsBlock(FancierIronBarsBlock block, ResourceLocation paneSolo, ResourceLocation paneBottom,
                              ResourceLocation paneMiddle, ResourceLocation paneTop, ResourceLocation edge, boolean showTopEdge) {
        fancierPaneBlock(block, paneSolo, paneBottom, paneMiddle, paneTop, edge, showTopEdge);
        iconTexture(name(block), paneSolo);
    }

    public void fancierPaneBlock(FancierIronBarsBlock block, ResourceLocation pane, ResourceLocation paneBottom,
                                 ResourceLocation paneMiddle, ResourceLocation paneTop, ResourceLocation edge, boolean showTopEdge) {
        fancierPaneBlockInternal(block, BuiltInRegistries.BLOCK.getKey(block).toString(), pane, paneBottom, paneMiddle, paneTop, edge, showTopEdge);
    }

    private void fancierPaneBlockInternal(FancierIronBarsBlock block, String baseName, ResourceLocation pane, ResourceLocation paneBottom,
                                          ResourceLocation paneMiddle, ResourceLocation paneTop, ResourceLocation edge, boolean showTopEdge) {
        ResourceLocation edgeTexture = showTopEdge ? edge : Datagen.BLANK;
        ModelFile post = models().panePost(baseName + "_post", pane, edge);
        ModelFile side = models().paneSide(baseName + "_side", pane, edge);
        ModelFile sideAlt = models().paneSideAlt(baseName + "_side_alt", pane, edge);
        ModelFile noSide = models().paneNoSide(baseName + "_noside", pane);
        ModelFile noSideAlt = models().paneNoSideAlt(baseName + "_noside_alt", pane);

        ModelFile postBottom = models().panePost(baseName + "_post_bottom", paneBottom, edgeTexture);
        ModelFile sideBottom = models().paneSide(baseName + "_side_bottom", paneBottom, edgeTexture);
        ModelFile sideAltBottom = models().paneSideAlt(baseName + "_side_alt_bottom", paneBottom, edgeTexture);
        ModelFile noSideBottom = models().paneNoSide(baseName + "_noside_bottom", paneBottom);
        ModelFile noSideAltBottom = models().paneNoSideAlt(baseName + "_noside_alt_bottom", paneBottom);

        ModelFile postMiddle = models().panePost(baseName + "_post_middle", paneMiddle, edgeTexture);
        ModelFile sideMiddle = models().paneSide(baseName + "_side_middle", paneMiddle, edgeTexture);
        ModelFile sideAltMiddle = models().paneSideAlt(baseName + "_side_alt_middle", paneMiddle, edgeTexture);
        ModelFile noSideMiddle = models().paneNoSide(baseName + "_noside_middle", paneMiddle);
        ModelFile noSideAltMiddle = models().paneNoSideAlt(baseName + "_noside_alt_middle", paneMiddle);

        ModelFile postTop = models().panePost(baseName + "_post_top", paneTop, edgeTexture);
        ModelFile sideTop = models().paneSide(baseName + "_side_top", paneTop, edgeTexture);
        ModelFile sideAltTop = models().paneSideAlt(baseName + "_side_alt_top", paneTop, edgeTexture);
        ModelFile noSideTop = models().paneNoSide(baseName + "_noside_top", paneTop);
        ModelFile noSideAltTop = models().paneNoSideAlt(baseName + "_noside_alt_top", paneTop);

        fancierPaneBlock(block, post, side, sideAlt, noSide, noSideAlt,
                postBottom, sideBottom, sideAltBottom, noSideBottom, noSideAltBottom,
                postMiddle, sideMiddle, sideAltMiddle, noSideMiddle, noSideAltMiddle,
                postTop, sideTop, sideAltTop, noSideTop, noSideAltTop);
    }

    public void fancierPaneBlock(FancierIronBarsBlock block, ModelFile post, ModelFile side, ModelFile sideAlt, ModelFile noSide, ModelFile noSideAlt,
                                 ModelFile postBottom, ModelFile sideBottom, ModelFile sideAltBottom, ModelFile noSideBottom, ModelFile noSideAltBottom,
                                 ModelFile postMiddle, ModelFile sideMiddle, ModelFile sideAltMiddle, ModelFile noSideMiddle, ModelFile noSideAltMiddle,
                                 ModelFile postTop, ModelFile sideTop, ModelFile sideAltTop, ModelFile noSideTop, ModelFile noSideAltTop) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block)
                .part().modelFile(post).addModel()
                .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.SOLO).end()
                .part().modelFile(postBottom).addModel()
                .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.BOTTOM).end()
                .part().modelFile(postMiddle).addModel()
                .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.MIDDLE).end()
                .part().modelFile(postTop).addModel()
                .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.TOP).end();
        PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
            Direction dir = e.getKey();
            BooleanProperty property = e.getValue();
            if (dir.getAxis().isHorizontal()) {
                boolean alt = dir == Direction.SOUTH;

                //sides

                builder
                        .part().modelFile(alt || dir == Direction.WEST ? sideAlt : side)
                        .rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
                        .condition(property, true)
                        .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.SOLO)
                        .end()

                        .part().modelFile(alt || dir == Direction.WEST ? sideAltBottom : sideBottom)
                        .rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
                        .condition(property, true)
                        .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.BOTTOM)
                        .end()

                        .part().modelFile(alt || dir == Direction.WEST ? sideAltMiddle : sideMiddle)
                        .rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
                        .condition(property, true)
                        .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.MIDDLE)
                        .end()

                        .part().modelFile(alt || dir == Direction.WEST ? sideAltTop : sideTop)
                        .rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
                        .condition(property, true)
                        .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.TOP)
                        .end()

                        //no sides

                        .part().modelFile(alt || dir == Direction.EAST ? noSideAlt : noSide)
                        .rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
                        .condition(property, false)
                        .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.SOLO)
                        .end()

                        .part().modelFile(alt || dir == Direction.EAST ? noSideAltBottom : noSideBottom)
                        .rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
                        .condition(property, false)
                        .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.BOTTOM)
                        .end()

                        .part().modelFile(alt || dir == Direction.EAST ? noSideAltMiddle : noSideMiddle)
                        .rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
                        .condition(property, false)
                        .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.MIDDLE)
                        .end()

                        .part().modelFile(alt || dir == Direction.EAST ? noSideAltTop : noSideTop)
                        .rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
                        .condition(property, false)
                        .condition(ModBlockStateProperties.VERTICAL_CONNECTION, VerticalConnection.TOP)
                        .end()
                ;
            }
        });
    }


    void fancyIronBarsBlock(FancyIronBarsBlock block, ResourceLocation pane, ResourceLocation paneTop, ResourceLocation edge) {
        fancyPaneBlock(block, pane, paneTop, edge);
        iconTexture(name(block), paneTop);
    }

    public void fancyPaneBlock(FancyIronBarsBlock block, ResourceLocation pane, ResourceLocation paneTop, ResourceLocation edge) {
        fancyPaneBlockInternal(block, BuiltInRegistries.BLOCK.getKey(block).toString(), pane, paneTop, edge);
    }

    private void fancyPaneBlockInternal(FancyIronBarsBlock block, String baseName, ResourceLocation pane, ResourceLocation paneTop, ResourceLocation edge) {
        ModelFile post = models().panePost(baseName + "_post", pane, edge);
        ModelFile side = models().paneSide(baseName + "_side", pane, edge);
        ModelFile sideAlt = models().paneSideAlt(baseName + "_side_alt", pane, edge);
        ModelFile noSide = models().paneNoSide(baseName + "_noside", pane);
        ModelFile noSideAlt = models().paneNoSideAlt(baseName + "_noside_alt", pane);

        ModelFile sideTop = models().paneSide(baseName + "_side_top", paneTop, edge);
        ModelFile sideAltTop = models().paneSideAlt(baseName + "_side_alt_top", paneTop, edge);
        ModelFile noSideTop = models().paneNoSide(baseName + "_noside_top", paneTop);
        ModelFile noSideAltTop = models().paneNoSideAlt(baseName + "_noside_alt_top", paneTop);

        fancyPaneBlock(block, post, side, sideAlt, noSide, noSideAlt, sideTop, sideAltTop, noSideTop, noSideAltTop);
    }

    public void fancyPaneBlock(FancyIronBarsBlock block, ModelFile post, ModelFile side, ModelFile sideAlt, ModelFile noSide, ModelFile noSideAlt,
                               ModelFile sideTop, ModelFile sideAltTop, ModelFile noSideTop, ModelFile noSideAltTop) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block)
                .part().modelFile(post).addModel().end();
        PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
            Direction dir = e.getKey();
            BooleanProperty property = e.getValue();
            if (dir.getAxis().isHorizontal()) {
                boolean alt = dir == Direction.SOUTH;
                builder
                        .part().modelFile(alt || dir == Direction.WEST ? sideAlt : side)
                        .rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
                        .condition(property, true)
                        .condition(FancyIronBarsBlock.VERTICAL_DIRECTION, Direction.DOWN)
                        .end()

                        .part().modelFile(alt || dir == Direction.WEST ? sideAltTop : sideTop)
                        .rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
                        .condition(property, true)
                        .condition(FancyIronBarsBlock.VERTICAL_DIRECTION, Direction.UP)
                        .end()

                        .part().modelFile(alt || dir == Direction.EAST ? noSideAlt : noSide)
                        .rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
                        .condition(property, false)
                        .condition(FancyIronBarsBlock.VERTICAL_DIRECTION, Direction.DOWN)
                        .end()

                        .part().modelFile(alt || dir == Direction.EAST ? noSideAltTop : noSideTop)
                        .rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
                        .condition(property, false)
                        .condition(FancyIronBarsBlock.VERTICAL_DIRECTION, Direction.UP)
                ;
            }
        });
    }


    void laventineGlass(Block block) {

        ModelFile upperModel = models().cubeBottomTop("laventine_glass_top", modLoc("block/laventinepanetop"),
                modLoc("block/laventinepanemid"), modLoc("block/laventinepanemid"));
        ModelFile midModel = models().cubeAll("laventine_glass", modLoc("block/laventinepanemid"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction direction = state.getValue(LaventineGlassBlock.VERTICAL_DIRECTION);
            ModelFile file = direction == Direction.UP ? upperModel : midModel;
            return ConfiguredModel.builder().modelFile(file).build();
        });

        simpleBlockItem(block, upperModel);
    }

    protected void gargoyle(Block block) {//what is ammonite?
        String name = name(block);
        ModelFile base = models().withExistingParent(name + "_base", modLoc("custom/gargoylebase"))
                .texture("0", modLoc("block/" + name))
                .texture("particle", modLoc("block/" + name));
        ModelFile top = models().withExistingParent(name + "_top", modLoc("custom/gargoyletop"))
                .texture("0", modLoc("block/" + name))
                .texture("particle", modLoc("block/" + name));
        horizontalBlock(block, state -> state.getValue(ModBlockStateProperties.PART) == HorizontalPart.BACK ? base : top);
    }


    protected void curtains(Block block) {
        String name = name(block);

        getVariantBuilder(block).forAllStatesExcept(blockState -> {
            VerticalConnection part = blockState.getValue(ModBlockStateProperties.VERTICAL_CONNECTION);
            Direction dir = blockState.getValue(HorizontalDirectionalBlock.FACING);
            ResourceLocation texture = modLoc("block/" + name + "_" + part.getSerializedName());


            ModelFile modelFile = models().withExistingParent("block/" + name + "_" + part.getSerializedName(),
                            modLoc("custom/velvetcurtainsolo"))
                    .texture("all", texture)
                    .texture("particle", texture);


            int y = ((int) dir.toYRot() + 180) % 360;

            return ConfiguredModel.builder().modelFile(modelFile)
                    .rotationY(y)
                    .build();

            //     horizontalBlock(block, models().withExistingParent("block/" + name,
            //                    modLoc("custom/velvetcurtainsolo"))
            //           .texture("all", texture0)
            //          .texture("particle", texture0));
        }, BlockStateProperties.WATERLOGGED);
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + name + "_solo")));
    }

    protected void stackedIronBalls(StackedIronBallsBlock stackedIronBallsBlock) {
        getVariantBuilder(stackedIronBallsBlock).forAllStatesExcept(state -> {
            int count = state.getValue(StackedIronBallsBlock.BALLS);
            ModelFile modelFile = models().getExistingFile(modLoc("block/iron_ball_" + count));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        }, BlockStateProperties.WATERLOGGED);
    }


    //            .partialState().with(RotatedPillarBlock.AXIS, Axis.Y)
    //                .modelForState().modelFile(vertical).addModel()
    //            .partialState().with(RotatedPillarBlock.AXIS, Axis.Z)
    //                .modelForState().modelFile(horizontal).rotationX(90).addModel()
    //            .partialState().with(RotatedPillarBlock.AXIS, Axis.X)
    //                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
    protected void brickCap() {
        getVariantBuilder(AbyssalDecorBlocks.BRICK_CAP.get()).forAllStatesExcept(state -> {
            Direction.Axis value = state.getValue(RotatedPillarBlock.AXIS);
            boolean flipped = state.getValue(BrickCapBlock.FLIPPED);
            ResourceLocation location = modLoc("block/brick_cap").withSuffix(flipped ? "_flipped" : "");
            ModelFile modelFile = models().getExistingFile(location);
            return ConfiguredModel.builder().modelFile(modelFile).rotationX(value != Direction.Axis.Y ? 90 : 0)
                    .rotationY(value == Direction.Axis.X ? 90 : 0).build();
        });
    }

    protected void hanger() {
        Block block = AbyssalDecorBlocks.MOLDY_HANGER.get();

        ModelFile moldyHangerMid = models().cross("moldy_hanger_middle", modLoc("block/moldy_hanger_middle"));
        ModelFile moldyHangerTop = models().cross("moldy_hanger_top", modLoc("block/moldy_hanger_top"));
        ModelFile moldyHangerBottom = models().cross("moldy_hanger_bottom", modLoc("block/moldy_hanger_bottom"));
        ModelFile moldyHangerBerries = models().cross("moldy_hanger_berries", modLoc("block/moldy_hanger_berries"));

        getVariantBuilder(block).forAllStatesExcept(state -> {
            TriPart part = state.getValue(ModBlockStateProperties.TRI_PART_NO_BOTTOM);
            boolean berries = state.getValue(MoldyHangersBlock.BERRIES);


            ModelFile modelFile;

            if (berries) {
                modelFile = moldyHangerBerries;
            } else {
                modelFile = switch (part) {
                    case BOTTOM -> moldyHangerBottom;
                    case MIDDLE -> moldyHangerMid;
                    case TOP -> moldyHangerTop;
                };
            }
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
        iconTexture("moldy_hanger", modLoc("block/moldy_hanger_middle"));

        Block block1 = AbyssalDecorBlocks.INACTIVE_MOLDY_HANGER.get();

        simpleBlock(block1, moldyHangerBottom);
        iconTexture("inactive_moldy_hanger", modLoc("block/moldy_hanger_bottom"));

    }

    protected void post(Block block, ResourceLocation bottom, ResourceLocation middle, ResourceLocation top) {
        //{
        //  "parent": "abyssaldecor:custom/cinnamonpostbottom",
        //  "textures": {
        //    "all": "abyssaldecor:block/cinnamonpost",
        //    "particle": "abyssaldecor:block/cinnamonpost",
        //    "1": "abyssaldecor:block/cinnamonpost"
        //  },
        //  "render_type": "solid"
        //}

        //{
        //  "parent": "abyssaldecor:custom/cinnamonpostmid",
        //  "textures": {
        //    "all": "abyssaldecor:block/cinnamonpost",
        //    "particle": "abyssaldecor:block/cinnamonpost",
        //    "1": "abyssaldecor:block/cinnamonpost"
        //  },
        //  "render_type": "solid"
        //}

        //{
        //  "parent": "abyssaldecor:custom/cinnamonposttop",
        //  "textures": {
        //    "all": "abyssaldecor:block/cinnamonpost",
        //    "particle": "abyssaldecor:block/cinnamonpost",
        //    "1": "abyssaldecor:block/cinnamonpost"
        //  },
        //  "render_type": "solid"
        //}
        String name = name(block);

        getVariantBuilder(block).forAllStatesExcept(blockState -> {

            TriPart part = blockState.getValue(ModBlockStateProperties.TRI_PART);

            ResourceLocation texture = modLoc("block/" + name);

            ResourceLocation parent = switch (part) {
                case BOTTOM -> bottom;
                case MIDDLE -> middle;
                case TOP -> top;
            };

            ModelFile modelFile = models().withExistingParent(name + "_" + part.getSerializedName(), parent)
                    .texture("0", texture)
                    .texture("particle", texture);
            return ConfiguredModel.builder().modelFile(modelFile).build();
        }, BlockStateProperties.WATERLOGGED);
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + name + "_bottom")));
    }

    public void bogApple(CropBlock block) {
        String name = name(block);
        IntegerProperty property = BogAppleLeavesBlock.AGE;
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(property);
            ResourceLocation baseModel = switch (age) {
                case 0 -> modLoc("custom/bogappleleaves");
                case 1 -> modLoc("custom/buddingbogapple");
                case 2 -> modLoc("custom/ripebogapple");
                default -> throw new IllegalStateException("Unexpected value: " + age);
            };

            ModelFile modelFile = models().withExistingParent("bog_apple_leaves_stage" + age, baseModel)
                    .texture("particle", modLoc("block/bogapple1"))
                    .texture("0", modLoc("block/bogapple1"))
                    .texture("1", modLoc("block/bogapple2"));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
    }

    public void spiderCorn(CropBlock block) {
        String name = name(block);
        IntegerProperty property = SpiderCornCropBlock.AGE;
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(property);
            ModelFile modelFile = models().cross(name + "_stage" + age, modLoc("block/" + name + age));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
        iconTexture(name, modLoc("item/" + name));
    }

    public void wallHangingMoss(WallHangingMossBlock block) {

        ModelFile modelFile = models().getExistingFile(modLoc("block/hanging_moss_wall"));
        ModelFile modelFileFlipped = models().getExistingFile(modLoc("block/hanging_moss_wall_flipped"));
        getVariantBuilder(block)
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(state.getValue(BlockStateProperties.VERTICAL_DIRECTION) == Direction.UP ? modelFileFlipped : modelFile)
                        .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                        .build()
                );
//            horizontalBlock(AbyssalDecorBlocks.WALL_HANGING_MOSS.get(), models().getExistingFile(modLoc("block/hanging_moss_wall")));

    }

    public void wisteria(WisteriaBlock block) {
        String name = name(block);
        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction facing = state.getValue(WisteriaBlock.FACING);
            TriPart triPart = state.getValue(ModBlockStateProperties.TRI_PART);

            String model = switch (triPart) {
                case BOTTOM -> "wisteriapurplewallbottom";
                case MIDDLE -> "wisteriapurplewallmid";
                case TOP -> "wisteriapurplewall";
            };

            String texture = name + "_" + triPart.getSerializedName();


            ModelFile modelFile = models().withExistingParent(name + "_" + triPart.getSerializedName(), modLoc("custom/" + model))
                    .texture("particle", modLoc("block/" + texture))
                    .texture("0", modLoc("block/" + texture));

            return ConfiguredModel.builder().modelFile(modelFile)
                    .rotationY(((int) facing.toYRot() + 180) % 360).build();
        }, WisteriaBlock.WATERLOGGED);
    }

    public void industrialLever(LeverBlock block) {
        String name = name(block);

        ResourceLocation texture = modLoc("block/fusebox");
        ResourceLocation textureOn = modLoc("block/fuseboxlit");

        ModelFile file = models().withExistingParent(name, modLoc("custom/fusebox"))
                .texture("0", texture)
                .texture("particle", texture);
        ModelFile fileLit = models().withExistingParent(name + "_on", modLoc("custom/fuseboxlit"))
                .texture("0", textureOn)
                .texture("particle", textureOn);

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
        }, IndustrialLeverBlock.WATERLOGGED);
    }

    public void cinnamonBush(CinnamonSaplingBlock block) {
        String name = name(block);
        getVariantBuilder(block).forAllStates(state -> {
            int stage = state.getValue(CinnamonSaplingBlock.STAGE);
            ModelFile modelFile = models().cross(name + "_stage" + stage, modLoc("block/" + name + "_stage" + stage));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
        iconTexture(name, modLoc("block/" + name + "_stage0"));
    }

    public void tallCinnamonBush(TallCinammonSaplingBlock block) {
        String name = name(block);
        getVariantBuilder(block).forAllStates(state -> {
            int stage = state.getValue(CinnamonSaplingBlock.STAGE);
            DoubleBlockHalf half = state.getValue(DoublePlantBlock.HALF);
            ModelFile modelFile = models().cross(name + "_" + half.getSerializedName() + "_stage" + stage,
                    modLoc("block/" + name + "_" + half.getSerializedName() + "_stage" + stage));
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
            ModelFile file = models().cross(name + "_" + age, modLoc("block/feverblossomcrop" + age));
            return ConfiguredModel.builder().modelFile(file).build();
        });

    }

    public void moldyStalk(Block block) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            TriPart part = state.getValue(ModBlockStateProperties.TRI_PART);
            ModelFile file = switch (part) {
                case TOP -> models().cross("moldy_stalk_top", modLoc("block/moldystalktop"));
                case MIDDLE -> models().cross("moldy_stalk_middle", modLoc("block/moldystalkmid"));
                case BOTTOM -> models().cross("moldy_stalk_bottom", modLoc("block/moldystalkbottom"));
            };
            return ConfiguredModel.builder().modelFile(file).build();
        }, MoldyStalkBlock.ACTIVE);
        iconTexture("moldy_stalk", modLoc("block/moldy_stalk"));
    }

    public void ribbon(BarrierRibbonBlock block, ResourceLocation model, ResourceLocation texture) {
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
        horizontalBlock(block, models().withExistingParent(name, model)
                .texture("0", texture)
                .texture("particle", texture)
        );
    }

    public void smallCornerBar(SmallBarsCornerBlock block, ResourceLocation texture) {
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


            ModelFile file = models().withExistingParent(name + (vertical == Direction.UP ? "_top" : ""), vertical == Direction.UP ? upsideDownModel : model)
                    .texture("0", texture)
                    .texture("particle", texture);

            return ConfiguredModel.builder()
                    .modelFile(file)
                    .rotationY(cornerDirection.yRotation())
                    .build();
        }, BlockStateProperties.WATERLOGGED);
    }

    public void goldSconce(SconceBlock block, ResourceLocation texture) {
        sconce(block, texture, modLoc("custom/goldsconce"), modLoc("custom/goldsconceupsidedown"));
    }

    public void regularSconce(SconceBlock block, ResourceLocation texture) {
        sconce(block, texture, modLoc("custom/cleanironsconce"), modLoc("custom/upsidedowncleanironsconce"));
    }

    public void sconce(SconceBlock block, ResourceLocation texture, ResourceLocation model, ResourceLocation upsideDownModel) {
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


        ModelFile file = models().withExistingParent(name, model)
                .texture("0", texture)
                .texture("particle", texture);

        ModelFile fileUp = models().withExistingParent(name + "_top", upsideDownModel)
                .texture("0", texture)
                .texture("particle", texture);

        horizontalBlock(block, state -> state.getValue(BlockStateProperties.VERTICAL_DIRECTION) == Direction.UP ? fileUp : file);

        simpleBlockItem(block, file);
    }

    public void smallBars(SmallBarsBlock block, ResourceLocation texture) {
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

        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction vertical = state.getValue(BlockStateProperties.VERTICAL_DIRECTION);
            Direction horizontal = state.getValue(HorizontalDirectionalBlock.FACING);
            ModelFile parentFile = vertical == Direction.UP ? upsideDownFile : regularFile;

            ModelFile file = models().withExistingParent(name + (vertical == Direction.UP ? "_top" : ""), parentFile.getLocation())
                    .texture("0", texture)
                    .texture("particle", texture);

            return ConfiguredModel.builder()
                    .modelFile(file)
                    .rotationY(((int) horizontal.toYRot() + 180) % 360)
                    .build();
        }, BlockStateProperties.WATERLOGGED);
        iconTexture(name, texture);
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
        BlockModelBuilder verticalLit = models().cubeColumn(name + "_lit", sideLit, topLit);

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

        }, BlockStateProperties.WATERLOGGED);

        simpleBlockItem(block, vertical);
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
        trapdoorBlock(block, modLoc("block/" + trapLoc.getPath()), true);
        simpleBlockItem(block, models().getExistingFile(modLoc(name(block) + "_bottom")));
    }

    void gildedPillar(RotatedPillarBlock block) {

        //{
        //  "parent": "block/cube",
        //  "textures": {
        //    "down": "abyssal_decor:block/bloodcoralpillarornatetop",
        //    "up": "abyssal_decor:block/bloodcoralpillarornatetop",
        //    "north": "abyssal_decor:block/bloodcoralpillarornatesideflipped",
        //    "east": "abyssal_decor:block/bloodcoralpillarornatesideflipped",
        //    "south": "abyssal_decor:block/bloodcoralpillarornateside",
        //    "west": "abyssal_decor:block/bloodcoralpillarornateside",
        //    "particle": "abyssal_decor:block/bloodcoralpillarornatetop"
        //  },
        //  "render_type": "solid"
        //}
        ResourceLocation side = modLoc("block/gilded_blood_coral_pillar");
        ResourceLocation sideFlipped = modLoc("block/gilded_blood_coral_pillar_flipped");
        ResourceLocation end = modLoc("block/gilded_blood_coral_pillar_top");
        ModelFile modelFile = models().cube(name(block), end, end, sideFlipped, side, sideFlipped, side).texture("particle",end);
        axisBlock(block, modelFile, modelFile);

        simpleBlockItem(block, models().getExistingFile(modLoc(name(block))));
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
        boolean pearl = clamBlock.hasPearl;
        ResourceLocation base = modLoc("custom/clam").withSuffix(pearl ? "pearl" : "empty");
        getVariantBuilder(clamBlock).forAllStatesExcept(state -> {
            boolean open = state.getValue(ClamBlock.OPEN);
            ResourceLocation location = base.withSuffix(open ? "open" : "closed");

            ModelFile file = models().withExistingParent(name + (open ? "_open" : "_closed"), location)
                    .texture("all", modLoc("block/clam1"))
                    .texture("particle", modLoc("block/clam1"))
                    .texture("0", modLoc("block/clam1"))
                    .texture("1", modLoc("block/clam2"));
            return ConfiguredModel.builder().modelFile(file).rotationY(getRotation(state.getValue(ClamBlock.FACING)).y).build();
        }, BlockStateProperties.WATERLOGGED);

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

    public void directionalBlockWithItem(DirectionalBlock block, ResourceLocation texture) {
        ModelFile modelFile = models().cubeColumn(name(block), texture, texture);
        directionalBlock(block, modelFile);
        simpleBlockItem(block, modelFile);
    }


    public void directionalBlockWithItem(DirectionalBlock block, ResourceLocation texture, ResourceLocation topTexture) {
        ModelFile modelFile = models().cubeColumn(name(block), texture, topTexture);
        directionalBlock(block, modelFile);
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
        directionalBlock(block, state -> {
            DyeColor color = state.getValue(StarfishBlock.COLOR);
            int count = state.getValue(StarfishBlock.COUNT);
            String s = "block/" + count + "_" + color.getName() + "_starfish";
            ModelFile file = models().withExistingParent(s, modLoc("custom/starfishdried1"))
                    .texture("0", s).texture("particle", s);
            return file;
        });
        iconTexture("starfish", modLoc("block/1_orange_starfish"));
    }

    void driedStarfish(Block block) {
        directionalBlock(block, state -> {
            int count = state.getValue(StarfishBlock.COUNT);
            String s = "block/" + count + "_dried_starfish";
            ModelFile file = models().withExistingParent(s, modLoc("custom/starfishdried1"))
                    .texture("0", s).texture("particle", s);
            return file;
        });
    }

    void barrierPoleBlock(Block block, ResourceLocation modelBottom, ResourceLocation modelTop, ResourceLocation texture, ResourceLocation icon) {
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

        iconTexture(name, icon);
    }

    String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public void simplestBlockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    public void bulkheadLampBlock(BulkheadLampBlock block, ResourceLocation baseModel) {
        String name = name(block);

        ResourceLocation baseModelLit = baseModel.withSuffix("lit");
        ResourceLocation texture = modLoc("block/bulkhead_lamp_on");

        ModelFile buttonModel = models().withExistingParent(name, baseModel)
                .texture("particle", texture)
                .texture("0", texture);


        ModelFile buttonModelLit = models().withExistingParent(name + "_lit", baseModelLit)
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

        simpleBlockItem(block, buttonModel);
    }

    protected void bloodLamp(Block block) {
        String name = name(block);

        getVariantBuilder(block).forAllStatesExcept(blockState -> {
            boolean lit = blockState.getValue(RedstoneLampBlock.LIT);

            TriPart part = blockState.getValue(ModBlockStateProperties.TRI_PART);

            ResourceLocation texture = modLoc("block/blood_lantern_" + part.getSerializedName());

            texture = lit ? texture.withSuffix("_lit") : texture;

            ResourceLocation textureEnd = modLoc("block/blood_lantern");

            textureEnd = lit ? textureEnd.withSuffix("_lit") : textureEnd;

            ModelFile modelFile = models().cubeColumn(name + "_" + part.getSerializedName() + (lit ? "_lit" : ""), texture, textureEnd);
            return ConfiguredModel.builder().modelFile(modelFile).build();
        }, BlockStateProperties.WATERLOGGED);
        simpleBlockItem(block, models().getExistingFile(modLoc("block/blood_lantern")));
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
        lamp(block, model, texture0, model);
    }

    protected void lamp(Block block, ResourceLocation model, ResourceLocation texture0, ResourceLocation modelLit) {
        String name = name(block);
        ResourceLocation texture0Lit = texture0.withSuffix("_lit");
        getVariantBuilder(block).forAllStatesExcept(blockState -> {
            boolean lit = blockState.getValue(RedstoneLampBlock.LIT);

            ResourceLocation texture = lit ? texture0Lit : texture0;

            ModelFile modelFile = models().withExistingParent("block/" + name + (lit ? "_lit" : ""),
                            lit ? modelLit : model)
                    .texture("particle", texture)
                    .texture("0", texture);
            return ConfiguredModel.builder().modelFile(modelFile).build();
        }, BlockStateProperties.WATERLOGGED);
    }


    protected void wallLamp(Block block, ResourceLocation model, ResourceLocation texture0) {
        String name = name(block);
        ResourceLocation litModelM = model.withSuffix("lit");
        ResourceLocation texture0Lit = texture0.withSuffix("_lit");
        getVariantBuilder(block).forAllStatesExcept(blockState -> {
            boolean lit = blockState.getValue(RedstoneLampBlock.LIT);
            Direction orientation = blockState.getValue(HorizontalLampBlock.FACING);

            ResourceLocation texture = lit ? texture0Lit : texture0;

            ResourceLocation m = lit ? litModelM : model;

            ModelFile modelFile = models().withExistingParent("block/" + name + (lit ? "_lit" : ""),
                            m)
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
