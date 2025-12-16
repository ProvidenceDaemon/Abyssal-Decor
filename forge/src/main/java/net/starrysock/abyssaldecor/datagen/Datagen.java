package net.starrysock.abyssaldecor.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.starrysock.abyssaldecor.*;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ExtendedBlockFamilies;
import net.starrysock.abyssaldecor.registry.ExtendedBlockFamily.Variant;

public class Datagen {
    public static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeServer()) {
            generator.addProvider(true, ModLootTableProvider.create(output));
            generator.addProvider(true, new ModDataPackProvider(output, lookupProvider));
            generator.addProvider(true,new AbyssalDecorRecipeProvider(output));
            TagDatagen.gather(event);
        }


        if (event.includeClient()) {
            ModBlockStateProvider blockStateProvider = new ModBlockStateProvider(output, helper);
            generator.addProvider(true,blockStateProvider);
            generator.addProvider(true, new ModItemModelProvider(output, helper));
            generator.addProvider(true, new ModLangProvider(output));
            generator.addProvider(true,new ModSoundDefinitions(output,helper));

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

                if (!(baseBlock instanceof IronBarsBlock)) {
                    simpleBlockItem(family.getBaseBlock().asItem());
                }


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


            generatedItem(AbyssalDecorItems.LE_FISHE_AU_CHOCOLAT.get());
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

    public static final ResourceLocation BLANK = AbyssalDecor.id("block/blanktexture16x16");

}
