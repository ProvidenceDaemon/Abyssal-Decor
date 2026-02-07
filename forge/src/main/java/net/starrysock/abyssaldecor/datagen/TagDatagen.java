package net.starrysock.abyssaldecor.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TagDatagen {

    static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(output, lookupProvider, helper);
        generator.addProvider(true,new ModFluidTagProvider(output, lookupProvider, helper));
        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true,new ModItemTagsProvider(output,lookupProvider,blockTagsProvider.contentsGetter(),helper));
        generator.addProvider(true, new ModPaintingTagsProvider(output, lookupProvider, helper));
    }


    public static class ModBlockTagProvider extends BlockTagsProvider {

        public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, AbyssalDecor.MOD_ID, existingFileHelper);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void addTags(HolderLookup.Provider arg) {
            //mod tags
            tag(ModTags.Blocks.MUCKROOT_GROWABLE).addTag(BlockTags.DIRT).add(Blocks.FARMLAND);
            tag(ModTags.Blocks.AMARANTH_GROWABLE).addTag(BlockTags.DIRT);
            tag(ModTags.Blocks.SPIDERCORN_GROWABLE).addTag(BlockTags.BASE_STONE_OVERWORLD);

            tag(ModTags.Blocks.BLACKWOOD_LOGS).add(AbyssalDecorBlocks.BLACKWOOD_LOG.get(),AbyssalDecorBlocks.STRIPPED_BLACKWOOD_LOG.get(),
                    AbyssalDecorBlocks.BLACKWOOD_WOOD.get());//no stripped blackwood wood?

            tag(ModTags.Blocks.CINNAMON_LOGS).add(AbyssalDecorBlocks.CINNAMON_LOG.get(),AbyssalDecorBlocks.HEALING_CINNAMON_LOG.get(),
                    AbyssalDecorBlocks.CUT_CINNAMON_LOG.get(),
                    AbyssalDecorBlocks.STRIPPED_CINNAMON_LOG.get(),AbyssalDecorBlocks.HEALING_CINNAMON_WOOD.get(),
                    AbyssalDecorBlocks.CUT_CINNAMON_WOOD.get(),
                    AbyssalDecorBlocks.CINNAMON_WOOD.get());

            tag(ModTags.Blocks.WHITEWOOD_LOGS).add(AbyssalDecorBlocks.WHITEWOOD_LOG.get(),AbyssalDecorBlocks.WHITEWOOD_WOOD.get());

            tag(ModTags.Blocks.MOLD_SPREADABLES)
                    .addTags(BlockTags.DIRT);

            tag(ModTags.Blocks.MOLD_IMMUNE).add(Blocks.DEEPSLATE,Blocks.DEEPSLATE_COAL_ORE,Blocks.DEEPSLATE_IRON_ORE,
                    AbyssalDecorBlocks.MOLDY_STARSTONE.get(),AbyssalDecorBlocks.MOLDIER_STARSTONE.get());

            //vanilla tags
            tag(BlockTags.CROPS).add(AbyssalDecorBlocks.SPIDERCORN.get());
            tag(BlockTags.FLOWERS).add(AbyssalDecorBlocks.FLOWERING_CINNAMON_LEAVES.get());

            tag(BlockTags.LEAVES).add(AbyssalDecorBlocks.CINNAMON_LEAVES.get(),AbyssalDecorBlocks.FLOWERING_CINNAMON_LEAVES.get());
            tag(BlockTags.LOGS_THAT_BURN).addTag(ModTags.Blocks.CINNAMON_LOGS);

            tag(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS).add(AbyssalDecorBlocks.INACTIVE_MOLD.get(), AbyssalDecorBlocks.BLACK_MOLD.get(),
                    AbyssalDecorBlocks.MOLDY_STARSTONE.get(),AbyssalDecorBlocks.MOLDIER_STARSTONE.get(),
                    AbyssalDecorBlocks.POROUS_MOLD.get(),
                    AbyssalDecorBlocks.BLACKWOOD_LOG.get(),
                    AbyssalDecorBlocks.BLACKWOOD_WOOD.get()
            );


            tag(ModTags.Blocks.MOLDY_STALK_PLANTABLE_ON).addTag(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS).add(AbyssalDecorBlocks.MOLDY_STALK.get(),
                    AbyssalDecorBlocks.MOLDY_STALK_SPROUT.get());

            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(AbyssalDecorBlocks.FOGHORN.get()
                    , AbyssalDecorBlocks.DESK_BELL.get(),
                    AbyssalDecorBlocks.SOLAR_ROD.get(),
                    AbyssalDecorBlocks.STELLAR_ROD.get(),
                    AbyssalDecorBlocks.TERRESTRIAL_ROD.get(),
                    AbyssalDecorBlocks.LUNAR_ROD.get(),
                    AbyssalDecorBlocks.ETHEREAL_ROD.get(),
                    AbyssalDecorBlocks.SCRIMSHAW.get(),AbyssalDecorBlocks.SCRIMSHAW_ALTAR.get()
                    ,AbyssalDecorBlocks.FLOWER_LAMP.get(), AbyssalDecorBlocks.FROSTED_LAMP.get(), AbyssalDecorBlocks.TUBE_LAMP.get(),
                    AbyssalDecorBlocks.SEAGLASS_LAMP.get(), AbyssalDecorBlocks.BLAZE_LAMP.get(), AbyssalDecorBlocks.DRIED_STARFISH.get(),
                    AbyssalDecorBlocks.STARLIGHT.get(), AbyssalDecorBlocks.RAINBOW_LAMP.get(), AbyssalDecorBlocks.LION_STATUE.get(),
                    AbyssalDecorBlocks.GARGOYLE.get(), AbyssalDecorBlocks.TELESCOPE.get(), AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK.get(),
                    AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE.get(), AbyssalDecorBlocks.FRESNEL_BLOCK.get(), AbyssalDecorBlocks.FRESNEL_PANE.get()
                    , AbyssalDecorBlocks.FRESNEL_LAMP.get(), AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE.get(), AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE.get(),
                    AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE.get(), AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE.get(),
                    AbyssalDecorBlocks.VERMILION_BLOCK.get(), AbyssalDecorBlocks.VERMILION_PANE.get(), AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK.get(),
                    AbyssalDecorBlocks.FRAMED_VERMILION_PANE.get(), AbyssalDecorBlocks.WHITE_PEARL.get(), AbyssalDecorBlocks.WHITE_PEARL_BLOCK.get(),
                    AbyssalDecorBlocks.CUT_WHITE_PEARL_BLOCK.get(), AbyssalDecorBlocks.CHISELED_WHITE_PEARL.get(), AbyssalDecorBlocks.WHITE_PEARL_PILLAR.get(),
                    AbyssalDecorBlocks.WHITE_PEARL_SLAB.get(), AbyssalDecorBlocks.WHITE_PEARL_STAIRS.get(), AbyssalDecorBlocks.WHITE_PEARL_WALL.get(),
                    AbyssalDecorBlocks.WHITE_PEARL_BRICKS.get(), AbyssalDecorBlocks.WHITE_PEARL_BRICK_SLAB.get(), AbyssalDecorBlocks.WHITE_PEARL_BRICK_STAIRS.get(),
                    AbyssalDecorBlocks.WHITE_PEARL_BRICK_WALL.get(), AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_BLOCK.get(), AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_SLAB.get(),
                    AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_STAIRS.get(), AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_WALL.get(), AbyssalDecorBlocks.WHITE_PEARL_DOOR.get(),
                    AbyssalDecorBlocks.WHITE_PEARL_TRAPDOOR.get(), AbyssalDecorBlocks.WHITE_PEARL_BARS.get(), AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS.get(),
                    AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS_CORNER.get(), AbyssalDecorBlocks.WHITE_PEARL_TILES.get(),
                    AbyssalDecorBlocks.CRACKED_PEARL_TILES.get(), AbyssalDecorBlocks.MIXED_PEARL_TILES.get(), AbyssalDecorBlocks.STARRY_PEARL_TILES.get(),
                    AbyssalDecorBlocks.CLAM.get(), AbyssalDecorBlocks.CLAM_WITH_PEARL.get(), AbyssalDecorBlocks.PEARLY_GLASS.get(),
                    AbyssalDecorBlocks.SUNNY_PEARLY_GLASS.get(), AbyssalDecorBlocks.AZURE_PEARLY_GLASS.get(), AbyssalDecorBlocks.VERDANT_PEARLY_GLASS.get(),
                    AbyssalDecorBlocks.WHITEWOOD_PEARLY_GLASS.get(), AbyssalDecorBlocks.BLACKWOOD_PEARLY_GLASS.get(), AbyssalDecorBlocks.PEARLY_GLASS_PANE.get(),
                    AbyssalDecorBlocks.SUNNY_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.AZURE_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.VERDANT_PEARLY_GLASS_PANE.get(),
                    AbyssalDecorBlocks.WHITEWOOD_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.BLACKWOOD_PEARLY_GLASS_PANE.get(), AbyssalDecorBlocks.MIXED_BRICKS.get(),
                    AbyssalDecorBlocks.MOSSY_MIXED_BRICKS.get(), AbyssalDecorBlocks.BRITTLE_TUFF.get(), AbyssalDecorBlocks.STONE_TRAPDOOR.get(),
                    AbyssalDecorBlocks.MOSSY_STONE_TRAPDOOR.get(), AbyssalDecorBlocks.ORNATE_STONE_TRAPDOOR.get(), AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS_CORNER.get(),
                    AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS.get(), AbyssalDecorBlocks.IRON_SCONCE.get(), AbyssalDecorBlocks.ORNATE_IRON_BARS.get(),
                    AbyssalDecorBlocks.CORRUGATED_IRON.get(), AbyssalDecorBlocks.IRON_PILLAR.get(), AbyssalDecorBlocks.IRON_DUCT.get(), AbyssalDecorBlocks.IRON_PUSH_DOOR.get(),
                    AbyssalDecorBlocks.IRON_VENT_TRAPDOOR.get(), AbyssalDecorBlocks.IRON_PANEL.get(), AbyssalDecorBlocks.IRON_PANEL_STAIRS.get(),
                    AbyssalDecorBlocks.IRON_PANEL_SLAB.get(), AbyssalDecorBlocks.IRON_PANEL_WALL.get(),
                    AbyssalDecorBlocks.DULL_IRON_BARS.get(), AbyssalDecorBlocks.SMALL_DULL_IRON_BARS_CORNER.get(), AbyssalDecorBlocks.SMALL_DULL_IRON_BARS.get(),
                    AbyssalDecorBlocks.DULL_IRON_SCONCE.get(), AbyssalDecorBlocks.ORNATE_DULL_IRON_BARS.get(), AbyssalDecorBlocks.DULL_IRON_BAR_DOOR.get(),
                    AbyssalDecorBlocks.DULL_IRON_BAR_TRAPDOOR.get(), AbyssalDecorBlocks.DULL_IRON_POST.get(), AbyssalDecorBlocks.GOLD_SCONCE.get(),
                    AbyssalDecorBlocks.GOLD_BARS.get(), AbyssalDecorBlocks.BLOOD_CORAL_BUD.get(), AbyssalDecorBlocks.POLISHED_BLOOD_CORAL.get(),
                    AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL.get(), AbyssalDecorBlocks.BLOOD_CORAL_BRICKS.get(), AbyssalDecorBlocks.ROUGH_BLOOD_CORAL.get(),
                    AbyssalDecorBlocks.BLOOD_CORAL_PILLAR.get(), AbyssalDecorBlocks.GILDED_BLOOD_CORAL_PILLAR.get(),
                    AbyssalDecorBlocks.BLOOD_LANTERN.get(), AbyssalDecorBlocks.BLOOD_CORAL_SCONCE.get(), AbyssalDecorBlocks.BLOOD_CORAL_DOOR.get(),
                    AbyssalDecorBlocks.BLOOD_CORAL_TRAPDOOR.get(), AbyssalDecorBlocks.BLOOD_CORAL_BARS.get(),
                    AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_SLAB.get(), AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_STAIRS.get(), AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_WALL.get(),
                    AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_SLAB.get(), AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_STAIRS.get(),
                    AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_WALL.get(), AbyssalDecorBlocks.BLOOD_CORAL_BRICK_SLAB.get(),
                    AbyssalDecorBlocks.BLOOD_CORAL_BRICK_STAIRS.get(), AbyssalDecorBlocks.BLOOD_CORAL_BRICK_WALL.get(),
                    AbyssalDecorBlocks.ROUGH_JADE.get(), AbyssalDecorBlocks.POLISHED_JADE.get(), AbyssalDecorBlocks.JADE_BRICKS.get(),
                    AbyssalDecorBlocks.JADE_PILLAR.get(), AbyssalDecorBlocks.GILDED_JADE_PILLAR.get(), AbyssalDecorBlocks.JADE_LANTERN.get(),
                    AbyssalDecorBlocks.QUARTZ_BONE.get(), AbyssalDecorBlocks.FLAKY_SCALES.get(), AbyssalDecorBlocks.SERPENT_SCALES.get(), AbyssalDecorBlocks.SERPENT_SCALE_SLAB.get(),
                    AbyssalDecorBlocks.SERPENT_SKIN.get(), AbyssalDecorBlocks.DAMAGED_SERPENT_SCALES.get(), AbyssalDecorBlocks.DAMAGED_SERPENT_SCALE_SLAB.get(),
                    AbyssalDecorBlocks.DAMAGED_SERPENT_SKIN.get(), AbyssalDecorBlocks.SERPENT_FLESH.get(), AbyssalDecorBlocks.SERPENT_FLESH_SLAB.get(),
                    AbyssalDecorBlocks.RIBBED_SERPENT_VEINS.get(), AbyssalDecorBlocks.SERPENT_EYE.get(), AbyssalDecorBlocks.SMALL_SERPENT_EYES.get(),
                    AbyssalDecorBlocks.VELVET_BARRIER.get(), AbyssalDecorBlocks.IRON_BARRIER.get(), AbyssalDecorBlocks.AMMONITE.get(),
                    AbyssalDecorBlocks.RAW_SEABRASS_BLOCK.get(), AbyssalDecorBlocks.SEABRASS_BLOCK.get(), AbyssalDecorBlocks.RIVETED_SEABRASS.get(),
                    AbyssalDecorBlocks.SEABRASS_TRIM.get(), AbyssalDecorBlocks.SEABRASS_PILLAR.get(), AbyssalDecorBlocks.LARGE_SEABRASS_PIPE.get(),
                    AbyssalDecorBlocks.SMALL_SEABRASS_PIPES.get(), AbyssalDecorBlocks.SEABRASS_TILES.get(), AbyssalDecorBlocks.SEABRASS_LAMP.get(),
                    AbyssalDecorBlocks.SEABRASS_STAIRS.get(), AbyssalDecorBlocks.SEABRASS_SLAB.get(), AbyssalDecorBlocks.RIVETED_SEABRASS_SLAB.get(),
                    AbyssalDecorBlocks.SEABRASS_WALL.get(), AbyssalDecorBlocks.SEABRASS_BUTTON.get(), AbyssalDecorBlocks.SEABRASS_PRESSURE_PLATE.get(),
                    AbyssalDecorBlocks.SEABRASS_CHAIN.get(), AbyssalDecorBlocks.SEABRASS_TRAPDOOR.get(), AbyssalDecorBlocks.SEABRASS_DOOR.get(),
                    AbyssalDecorBlocks.SEABRASS_SCONCE.get(), AbyssalDecorBlocks.SEABRASS_BARS.get(), AbyssalDecorBlocks.ORNATE_SEABRASS_BARS.get(),
                    AbyssalDecorBlocks.SMALL_SEABRASS_BARS.get(), AbyssalDecorBlocks.SMALL_SEABRASS_BARS_CORNER.get(), AbyssalDecorBlocks.SEABRASS_CATALYST.get(),
                    AbyssalDecorBlocks.DEEPBRONZE_BLOCK.get(), AbyssalDecorBlocks.RIVETED_DEEPBRONZE.get(), AbyssalDecorBlocks.DEEPBRONZE_PILLAR.get(),
                    AbyssalDecorBlocks.DEEPBRONZE_TRIM.get(), AbyssalDecorBlocks.LARGE_DEEPBRONZE_PIPE.get(), AbyssalDecorBlocks.DEEPBRONZE_PLATING.get(),
                    AbyssalDecorBlocks.DEEPBRONZE_BARS.get(), AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS.get(), AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS_CORNER.get(),
                    AbyssalDecorBlocks.DEEPBRONZE_BEAM.get(), AbyssalDecorBlocks.DEEPBRONZE_SCONCE.get(), AbyssalDecorBlocks.DEEPBRONZE_BUTTON.get(),
                    AbyssalDecorBlocks.DEEPBRONZE_PRESSURE_PLATE.get(), AbyssalDecorBlocks.DEEPBRONZE_CHAIN.get(), AbyssalDecorBlocks.DEEPBRONZE_STAIRS.get(),
                    AbyssalDecorBlocks.DEEPBRONZE_SLAB.get(), AbyssalDecorBlocks.RIVETED_DEEPBRONZE_STAIRS.get(),
                    AbyssalDecorBlocks.RIVETED_DEEPBRONZE_SLAB.get(), AbyssalDecorBlocks.RIVETED_DEEPBRONZE_WALL.get(), AbyssalDecorBlocks.DEEPBRONZE_TRAPDOOR.get(),
                    AbyssalDecorBlocks.DEEPBRONZE_DOOR.get(), AbyssalDecorBlocks.LAVENTINE.get(), AbyssalDecorBlocks.LAVENTINE_GLASS.get(),
                    AbyssalDecorBlocks.LAVENTINE_GLASS_PANE.get(), AbyssalDecorBlocks.STARSTONE.get(), AbyssalDecorBlocks.SMOOTH_STARSTONE.get(),
                    AbyssalDecorBlocks.GILDED_STARSTONE.get(), AbyssalDecorBlocks.STARSTONE_PILLAR.get(), AbyssalDecorBlocks.POLISHED_STARSTONE.get(),
                    AbyssalDecorBlocks.POLISHED_STARSTONE_STAIRS.get(), AbyssalDecorBlocks.POLISHED_STARSTONE_SLAB.get(), AbyssalDecorBlocks.POLISHED_STARSTONE_WALL.get(),
                    AbyssalDecorBlocks.CHISELED_STARSTONE.get(), AbyssalDecorBlocks.STARSTONE_TILES.get(), AbyssalDecorBlocks.LAPIS_PILLAR.get(),
                    AbyssalDecorBlocks.GILDED_LAPIS_PILLAR.get(), AbyssalDecorBlocks.STARGLASS.get(), AbyssalDecorBlocks.STARGLASS_PANE.get(),
                    AbyssalDecorBlocks.ABYSSAL_LANTERN.get(), AbyssalDecorBlocks.PITCHGLASS.get(), AbyssalDecorBlocks.PITCHGLASS_PANE.get(),
                    AbyssalDecorBlocks.FRAMED_PITCHGLASS.get(), AbyssalDecorBlocks.FRAMED_PITCHGLASS_PANE.get(), AbyssalDecorBlocks.MOLDY_STARSTONE.get(),
                    AbyssalDecorBlocks.MOLDIER_STARSTONE.get(), AbyssalDecorBlocks.GILDED_BLACK_PEARL.get(), AbyssalDecorBlocks.GILDED_BLACK_PEARL_DOOR.get(),
                    AbyssalDecorBlocks.GILDED_BLACK_PEARL_TRAPDOOR.get(), AbyssalDecorBlocks.BLACK_PEARL.get(), AbyssalDecorBlocks.BLACK_PEARL_BLOCK.get(),
                    AbyssalDecorBlocks.CUT_BLACK_PEARL_BLOCK.get(), AbyssalDecorBlocks.CHISELED_BLACK_PEARL.get(), AbyssalDecorBlocks.BLACK_PEARL_PILLAR.get(),
                    AbyssalDecorBlocks.BLACK_PEARL_STAIRS.get(),
                    AbyssalDecorBlocks.BLACK_PEARL_SLAB.get(), AbyssalDecorBlocks.BLACK_PEARL_WALL.get(), AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_BLOCK.get(),
                    AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_STAIRS.get(), AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_SLAB.get(),
                    AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_WALL.get(), AbyssalDecorBlocks.BLACK_PEARL_BRICKS.get(), AbyssalDecorBlocks.BLACK_PEARL_BRICK_SLAB.get(),
                    AbyssalDecorBlocks.BLACK_PEARL_BRICK_STAIRS.get(), AbyssalDecorBlocks.BLACK_PEARL_BRICK_WALL.get(), AbyssalDecorBlocks.BLACK_PEARL_BARS.get(),
                    AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS.get(), AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS_CORNER.get(), AbyssalDecorBlocks.ECHO_SHARD_BLOCK.get(),
                    AbyssalDecorBlocks.POLISHED_ECHO_SHARD_BLOCK.get(), AbyssalDecorBlocks.CRACKED_BRICKS.get(), AbyssalDecorBlocks.CHISELED_BRICK.get(),
                    AbyssalDecorBlocks.BRICK_MOSAIC.get(), AbyssalDecorBlocks.BRICK_CAP.get(), AbyssalDecorBlocks.BRICK_CORNERSTONE.get(), AbyssalDecorBlocks.BRICK_PILLAR.get(),
                    AbyssalDecorBlocks.BRICK_TRAPDOOR.get(), AbyssalDecorBlocks.EFFERVESCENT_PILLAR.get(), AbyssalDecorBlocks.POLISHED_EFFERVESCENCE.get(),
                    AbyssalDecorBlocks.EFFERVESCENT_TILES.get(), AbyssalDecorBlocks.EFFERVESCENCE.get(), AbyssalDecorBlocks.IRON_BALL.get(),
                    AbyssalDecorBlocks.JADE_LAMP.get(), AbyssalDecorBlocks.WALL_JADE_LAMP.get(), AbyssalDecorBlocks.WALL_JADE_LAMP.get(),
                    AbyssalDecorBlocks.WALL_BULB_LAMP.get(),
                    AbyssalDecorBlocks.HEART_OF_THE_SEA.get(),

                    AbyssalDecorBlocks.WALL_QUARTZ_LAMP.get(), AbyssalDecorBlocks.QUARTZ_LAMP.get(),
                    AbyssalDecorBlocks.DRIED_STARFISH.get(), AbyssalDecorBlocks.DULL_IRON_BARS.get(), AbyssalDecorBlocks.WALL_IRON_LAMP.get(),
                    AbyssalDecorBlocks.IRON_LAMP.get(), AbyssalDecorBlocks.CEILING_QUARTZ_LAMP.get(),
                    AbyssalDecorBlocks.DULL_IRON_POST.get(), AbyssalDecorBlocks.SEABRASS_BARS.get(), AbyssalDecorBlocks.BRICK_CAP.get(),
                    AbyssalDecorBlocks.MOSSY_MIXED_BRICKS.get(),
                    AbyssalDecorBlocks.FROSTED_GLASS.get(), AbyssalDecorBlocks.FROSTED_GLASS_PANE.get(),  AbyssalDecorBlocks.IRON_LANTERN.get(),
                    AbyssalDecorBlocks.BULKHEAD_LAMP.get(), AbyssalDecorBlocks.BULKHEAD_LAMP.get(), AbyssalDecorBlocks.SMALL_DEEPBRONZE_PIPES.get(),
                    AbyssalDecorBlocks.SEABRASS_PLATING.get(), AbyssalDecorBlocks.DEEPBRONZE_TILES.get(), AbyssalDecorBlocks.ORNATE_DEEPBRONZE_BARS.get(),
                    AbyssalDecorBlocks.DEEPBRONZE_LANTERN.get(),
                    AbyssalDecorBlocks.STONE_BARS.get(), AbyssalDecorBlocks.SMALL_STONE_BARS.get(), AbyssalDecorBlocks.SMALL_STONE_BARS_CORNER.get(),
                    AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS.get(), AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS_CORNER.get());

            tag(BlockTags.MINEABLE_WITH_SHOVEL).add(AbyssalDecorBlocks.BLACK_MOLD.get(),AbyssalDecorBlocks.INACTIVE_MOLD.get());

            tag(BlockTags.MINEABLE_WITH_AXE);

            tag(BlockTags.PLANKS).add(AbyssalDecorBlocks.WHITEWOOD_PLANKS.get());

            tag(BlockTags.REPLACEABLE_BY_TREES).add(AbyssalDecorBlocks.TALL_CINNAMON_BUSH.get());

            tag(BlockTags.SLABS).add(AbyssalDecorBlocks.WHITE_PEARL_SLAB.get(), AbyssalDecorBlocks.WHITE_PEARL_BRICK_SLAB.get(),
                    AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_SLAB.get());

            tag(BlockTags.STAIRS).add(AbyssalDecorBlocks.WHITE_PEARL_STAIRS.get(), AbyssalDecorBlocks.WHITE_PEARL_BRICK_STAIRS.get(),
                    AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_STAIRS.get());

            tag(BlockTags.WALLS).add(AbyssalDecorBlocks.WHITE_PEARL_WALL.get(), AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_WALL.get()
                    ,AbyssalDecorBlocks.WHITE_PEARL_BRICK_WALL.get()
                    ,AbyssalDecorBlocks.IRON_PANEL_WALL.get()

                    ,AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_WALL.get()
                    ,AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_WALL.get()
                    ,AbyssalDecorBlocks.BLOOD_CORAL_BRICK_WALL.get()

                    ,AbyssalDecorBlocks.SEABRASS_WALL.get()
                    ,AbyssalDecorBlocks.DEEPBRONZE_WALL.get()
                    ,AbyssalDecorBlocks.RIVETED_DEEPBRONZE_WALL.get()
                    ,AbyssalDecorBlocks.POLISHED_STARSTONE_WALL.get()

                    ,AbyssalDecorBlocks.BLACK_PEARL_WALL.get(), AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_WALL.get()
                    ,AbyssalDecorBlocks.BLACK_PEARL_BRICK_WALL.get()
            );

            tag(BlockTags.WOODEN_BUTTONS).add(AbyssalDecorBlocks.WHITEWOOD_BUTTON.get());

            tag(BlockTags.WOODEN_SLABS).add(AbyssalDecorBlocks.WHITEWOOD_SLAB.get());
            tag(BlockTags.WOODEN_STAIRS).add(AbyssalDecorBlocks.WHITEWOOD_STAIRS.get());


            this.tag(BlockTags.STANDING_SIGNS).add(AbyssalDecorBlocks.BLACKWOOD_SIGN.get(), AbyssalDecorBlocks.CINNAMON_SIGN.get(), AbyssalDecorBlocks.WHITEWOOD_SIGN.get());
            this.tag(BlockTags.WALL_SIGNS).add(AbyssalDecorBlocks.BLACKWOOD_WALL_SIGN.get(), AbyssalDecorBlocks.CINNAMON_WALL_SIGN.get(),
                    AbyssalDecorBlocks.WHITEWOOD_WALL_SIGN.get());
            this.tag(BlockTags.CEILING_HANGING_SIGNS).add(AbyssalDecorBlocks.BLACKWOOD_HANGING_SIGN.get(), AbyssalDecorBlocks.CINNAMON_HANGING_SIGN.get(),
                    AbyssalDecorBlocks.WHITEWOOD_HANGING_SIGN.get());
            this.tag(BlockTags.WALL_HANGING_SIGNS).add(AbyssalDecorBlocks.BLACKWOOD_WALL_HANGING_SIGN.get(), AbyssalDecorBlocks.CINNAMON_WALL_HANGING_SIGN.get(),
                    AbyssalDecorBlocks.WHITEWOOD_WALL_HANGING_SIGN.get());

        }
    }

    public static class ModFluidTagProvider extends FluidTagsProvider {


        public ModFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, provider, AbyssalDecor.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            tag(ModTags.Fluids.SUPPORTS_BOG_APPLES).addTag(FluidTags.WATER);
        }
    }

    static class ModItemTagsProvider extends ItemTagsProvider {

        public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTags, AbyssalDecor.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            copy(ModTags.Blocks.BLACKWOOD_LOGS,ModTags.Items.BLACKWOOD_LOGS);
            copy(ModTags.Blocks.CINNAMON_LOGS,ModTags.Items.CINNAMON_LOGS);
            copy(ModTags.Blocks.WHITEWOOD_LOGS,ModTags.Items.WHITEWOOD_LOGS);
        }
    }

    static class ModPaintingTagsProvider extends PaintingVariantTagsProvider {

        public ModPaintingTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, provider, AbyssalDecor.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            AbyssalDecor.PAINTING_VARIANTS.forEach(s -> tag(PaintingVariantTags.PLACEABLE).add(s.getKey()));
        }
    }
}
