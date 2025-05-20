package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.item.FloorWallCeilingBlockItem;
import net.starrysock.abyssaldecor.item.SupplierRecordItem;

import java.util.function.Supplier;

public class AbyssalDecorItems {

    public static final RegistrySupplier<BlockItem> SOLAR_ROD = registerBlockItem(AbyssalDecorBlocks.SOLAR_ROD);
    public static final RegistrySupplier<BlockItem> STELLAR_ROD = registerBlockItem(AbyssalDecorBlocks.STELLAR_ROD);
    public static final RegistrySupplier<BlockItem> TERRESTRIAL_ROD = registerBlockItem(AbyssalDecorBlocks.TERRESTRIAL_ROD);
    public static final RegistrySupplier<BlockItem> LUNAR_ROD = registerBlockItem(AbyssalDecorBlocks.LUNAR_ROD);
    public static final RegistrySupplier<BlockItem> ETHEREAL_ROD = registerBlockItem(AbyssalDecorBlocks.ETHEREAL_ROD);
    public static final RegistrySupplier<BlockItem> HANGING_MOSS = registerBlockItem(AbyssalDecorBlocks.HANGING_MOSS);

    public static final RegistrySupplier<BlockItem> DAFFODIL = registerBlockItem(AbyssalDecorBlocks.DAFFODIL);
    public static final RegistrySupplier<BlockItem> ASTER = registerBlockItem(AbyssalDecorBlocks.ASTER);
    public static final RegistrySupplier<BlockItem> SNAPLEAF = registerBlockItem(AbyssalDecorBlocks.SNAPLEAF);

    public static final RegistrySupplier<ItemNameBlockItem> AMARANTH_SEEDS = registerNamedBlockItem("amaranth_seeds",AbyssalDecorBlocks.AMARANTH);
    public static final RegistrySupplier<Item> AMARANTH_PINNACLE = AbyssalDecor.ITEMS.register("amaranth_pinnacle",() -> new Item(new Item.Properties()));
    public static final RegistrySupplier<BlockItem> AMARANTH_CRATE = registerBlockItem(AbyssalDecorBlocks.AMARANTH_CRATE);
    public static final RegistrySupplier<ItemNameBlockItem> MUCKROOT = registerNamedBlockItem("muckroot",
            AbyssalDecorBlocks.MUCKROOT,new Item.Properties().food(Foods.CARROT));
    public static final RegistrySupplier<Item> COOKED_MUCKROOT = AbyssalDecor.ITEMS.register("cooked_muckroot",() ->
            new Item(new Item.Properties().food(Foods.BEETROOT)));
    public static final RegistrySupplier<Item> MUCKROOT_SOUP = AbyssalDecor.ITEMS.register("muckroot_soup",() ->
            new BowlFoodItem(new Item.Properties().stacksTo(1).food(Foods.BEETROOT_SOUP)));

    public static final RegistrySupplier<BlockItem> BARK_ORCHID = registerBlockItem(AbyssalDecorBlocks.BARK_ORCHID);

    //todo wisteria and elder wisteria

    public static final RegistrySupplier<BlockItem> WISTERIA_PETALS = registerBlockItem(AbyssalDecorBlocks.WISTERIA_PETALS);
    public static final RegistrySupplier<BlockItem> ELDER_WISTERIA_PETALS = registerBlockItem(AbyssalDecorBlocks.ELDER_WISTERIA_PETALS);
    public static final RegistrySupplier<BlockItem> ELDER_WISTERIA_LEAVES = registerBlockItem(AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES);

    public static final RegistrySupplier<BlockItem> ANCIENT_BIRCH_LOG =  registerBlockItem(AbyssalDecorBlocks.ANCIENT_BIRCH_LOG);
    public static final RegistrySupplier<BlockItem> STRIPPED_ANCIENT_BIRCH_LOG =  registerBlockItem(AbyssalDecorBlocks.STRIPPED_ANCIENT_BIRCH_LOG);
    public static final RegistrySupplier<BlockItem> FOXY_PILLAR =  registerBlockItem(AbyssalDecorBlocks.FOXY_PILLAR);

    public static final RegistrySupplier<Item> DUSTY_CD = AbyssalDecor.ITEMS.register("dusty_cd",
            () -> new SupplierRecordItem(0,AbyssalDecorSounds.DUSTY_CD.get()
            , new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3600));

    public static final RegistrySupplier<BlockItem> SCRIMSHAW = registerBlockItem(AbyssalDecorBlocks.SCRIMSHAW);
    public static final RegistrySupplier<BlockItem> STARFISH = registerBlockItem(AbyssalDecorBlocks.STARFISH);
    public static final RegistrySupplier<BlockItem> DRIED_STARFISH = registerBlockItem(AbyssalDecorBlocks.DRIED_STARFISH);
    public static final RegistrySupplier<BlockItem> STARLIGHT = registerBlockItem(AbyssalDecorBlocks.STARLIGHT);
    public static final RegistrySupplier<BlockItem> LIFE_PRESERVER = registerBlockItem(AbyssalDecorBlocks.LIFE_PRESERVER);
    public static final RegistrySupplier<BlockItem> WOOD_SUPPORT = registerBlockItem(AbyssalDecorBlocks.WOOD_SUPPORT);
    public static final RegistrySupplier<BlockItem> SHIP_WHEEL = registerBlockItem(AbyssalDecorBlocks.SHIP_WHEEL);
    public static final RegistrySupplier<BlockItem> WO0DEN_DRAGON_HEAD = registerBlockItem(AbyssalDecorBlocks.WOODEN_DRAGON_HEAD);
    public static final RegistrySupplier<BlockItem> WO0DEN_FROG = registerBlockItem(AbyssalDecorBlocks.WOODEN_FROG);
    public static final RegistrySupplier<DoubleHighBlockItem> LION_STATUE = AbyssalDecor.ITEMS.register("lion_statue",() ->
            new DoubleHighBlockItem(AbyssalDecorBlocks.LION_STATUE.get(),new Item.Properties()));
    public static final RegistrySupplier<DoubleHighBlockItem> GARGOYLE = AbyssalDecor.ITEMS.register("gargoyle",() ->
            new DoubleHighBlockItem(AbyssalDecorBlocks.GARGOYLE.get(),new Item.Properties()));

    public static final RegistrySupplier<DoubleHighBlockItem> NITHING_POLE = AbyssalDecor.ITEMS.register("nithing_pole",() ->
            new DoubleHighBlockItem(AbyssalDecorBlocks.NITHING_POLE.get(),new Item.Properties()));

    public static final RegistrySupplier<DoubleHighBlockItem> TELESCOPE = AbyssalDecor.ITEMS.register("telescope",() ->
            new DoubleHighBlockItem(AbyssalDecorBlocks.TELESCOPE.get(),new Item.Properties()));

    public static final RegistrySupplier<BlockItem> HANGING_WEB = AbyssalDecor.ITEMS.register("hanging_web",
            () -> new StandingAndWallBlockItem(AbyssalDecorBlocks.HANGING_WEB.get(),AbyssalDecorBlocks.WALL_HANGING_WEB.get(),new Item.Properties(),Direction.UP));

    public static final RegistrySupplier<BlockItem> DANGLING_WEB = AbyssalDecor.ITEMS.register("dangling_web",
            () -> new StandingAndWallBlockItem(AbyssalDecorBlocks.DANGLING_WEB.get(),AbyssalDecorBlocks.WALL_DANGLING_WEB.get(),new Item.Properties(),Direction.UP));

    public static final RegistrySupplier<BlockItem> PRISMARINE_CRYSTAL_BLOCK = registerBlockItem(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK);
    public static final RegistrySupplier<BlockItem> PRISMARINE_CRYSTAL_PANE = registerBlockItem(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE);

    public static final RegistrySupplier<BlockItem> FRESNEL_BLOCK = registerBlockItem(AbyssalDecorBlocks.FRESNEL_BLOCK);
    public static final RegistrySupplier<BlockItem> FRESNEL_PANE = registerBlockItem(AbyssalDecorBlocks.FRESNEL_PANE);

    public static final RegistrySupplier<BlockItem> CRYSTALLIZED_GLOWSTONE = registerBlockItem(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE);
    public static final RegistrySupplier<BlockItem> CRYSTALLIZED_GLOWSTONE_PANE = registerBlockItem(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE);

    public static final RegistrySupplier<BlockItem> FRAMED_CRYSTALLIZED_GLOWSTONE = registerBlockItem(AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE);
    public static final RegistrySupplier<BlockItem> FRAMED_CRYSTALLIZED_GLOWSTONE_PANE = registerBlockItem(AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE);

    public static final RegistrySupplier<BlockItem> VERMILION_BLOCK = registerBlockItem(AbyssalDecorBlocks.VERMILION_BLOCK);
    public static final RegistrySupplier<BlockItem> VERMILION_PANE = registerBlockItem(AbyssalDecorBlocks.VERMILION_PANE);

    public static final RegistrySupplier<BlockItem> FRAMED_VERMILION_BLOCK = registerBlockItem(AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK);
    public static final RegistrySupplier<BlockItem> FRAMED_VERMILION_PANE = registerBlockItem(AbyssalDecorBlocks.FRAMED_VERMILION_PANE);

    public static final RegistrySupplier<BlockItem> DESK_BELL = registerBlockItem(AbyssalDecorBlocks.DESK_BELL);

    public static final RegistrySupplier<BlockItem> LIGHTBULB = registerBlockItem(AbyssalDecorBlocks.LIGHTBULB);
    public static final RegistrySupplier<BlockItem> WALL_BULB_LAMP = registerBlockItem(AbyssalDecorBlocks.WALL_BULB_LAMP);
    public static final RegistrySupplier<BlockItem> TUBE_LAMP = registerBlockItem(AbyssalDecorBlocks.TUBE_LAMP);
    public static final RegistrySupplier<StandingAndWallBlockItem> IRON_LAMP = AbyssalDecor.ITEMS
            .register("iron_lamp",() -> new StandingAndWallBlockItem(AbyssalDecorBlocks.IRON_LAMP.get(),AbyssalDecorBlocks.WALL_IRON_LAMP.get()
                    ,new Item.Properties(), Direction.UP));

    public static final RegistrySupplier<BlockItem> FLOWER_LAMP = registerBlockItem(AbyssalDecorBlocks.FLOWER_LAMP);

    public static final RegistrySupplier<BlockItem> FROSTED_LAMP = registerBlockItem(AbyssalDecorBlocks.FROSTED_LAMP);

    public static final RegistrySupplier<FloorWallCeilingBlockItem> QUARTZ_LAMP = AbyssalDecor.ITEMS.register("quartz_lamp",() ->
            new FloorWallCeilingBlockItem(AbyssalDecorBlocks.QUARTZ_LAMP.get(),AbyssalDecorBlocks.WALL_QUARTZ_LAMP.get(),
                    AbyssalDecorBlocks.CEILING_QUARTZ_LAMP.get(),new Item.Properties()));

    public static final RegistrySupplier<StandingAndWallBlockItem> JADE_LAMP = AbyssalDecor.ITEMS.register("jade_lamp",() ->
            new StandingAndWallBlockItem(AbyssalDecorBlocks.JADE_LAMP.get(),AbyssalDecorBlocks.WALL_JADE_LAMP.get(),new Item.Properties(),Direction.DOWN));

    public static final RegistrySupplier<BlockItem> SEAGLASS_LAMP = registerBlockItem(AbyssalDecorBlocks.SEAGLASS_LAMP);
    public static final RegistrySupplier<BlockItem> BLAZE_LAMP = registerBlockItem(AbyssalDecorBlocks.BLAZE_LAMP);

    //////

    public static final RegistrySupplier<BlockItem> SEABRASS_ORE = registerBlockItem(AbyssalDecorBlocks.SEABRASS_ORE);
    public static final RegistrySupplier<BlockItem> FRESNEL_LAMP = registerBlockItem(AbyssalDecorBlocks.FRESNEL_LAMP);
    public static final RegistrySupplier<BlockItem> RAINBOW_LAMP = registerBlockItem(AbyssalDecorBlocks.RAINBOW_LAMP);
    public static final RegistrySupplier<BlockItem> BULKHEAD_LAMP = registerBlockItem(AbyssalDecorBlocks.BULKHEAD_LAMP);

    public static final RegistrySupplier<BlockItem> ABYSSAL_LANTERN = registerBlockItem(AbyssalDecorBlocks.ABYSSAL_LANTERN);
    //public static final RegistrySupplier<BlockItem> BLOOD_LANTERN = registerBlockItem("blood_lantern",AbyssalDecorBlocks.BLOOD_LANTERN);
    public static final RegistrySupplier<BlockItem> IRON_LANTERN = registerBlockItem(AbyssalDecorBlocks.IRON_LANTERN);
    public static final RegistrySupplier<BlockItem> JADE_LANTERN = registerBlockItem(AbyssalDecorBlocks.JADE_LANTERN);

    public static final RegistrySupplier<BlockItem> VELVET_BARRIER = registerBlockItem(AbyssalDecorBlocks.VELVET_BARRIER);
    public static final RegistrySupplier<BlockItem> IRON_BARRIER = registerBlockItem(AbyssalDecorBlocks.IRON_BARRIER);
    public static final RegistrySupplier<BlockItem> ROPE_BARRIER = registerBlockItem(AbyssalDecorBlocks.ROPE_BARRIER);
    public static final RegistrySupplier<BlockItem> BARBED_WIRE_BARRIER = registerBlockItem(AbyssalDecorBlocks.BARBED_WIRE_BARRIER);

    ///////////////////////////////

    public static final RegistrySupplier<BlockItem> WHITEWOOD_LOG = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_LOG);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_WOOD = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_WOOD);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_PLANKS = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_PLANKS);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_STAIRS = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_STAIRS);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_SLAB = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_SLAB);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_BUTTON = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_BUTTON);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_TRIM = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_TRIM);
    public static final RegistrySupplier<DoubleHighBlockItem> WHITEWOOD_DOOR = registerDoorItem(AbyssalDecorBlocks.WHITEWOOD_DOOR);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_TRAPDOOR);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_PRESSURE_PLATE = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_PRESSURE_PLATE);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_FENCE = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_FENCE);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_FENCE_GATE = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_FENCE_GATE);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_SIGN = AbyssalDecor.ITEMS.register("whitewood_sign",() ->
            new SignItem(new Item.Properties().stacksTo(16), AbyssalDecorBlocks.WHITEWOOD_SIGN.get(), AbyssalDecorBlocks.WHITEWOOD_WALL_SIGN.get()));
    public static final RegistrySupplier<BlockItem> WHITEWOOD_HANGING_SIGN = AbyssalDecor.ITEMS.register("whitewood_hanging_sign",
            () -> new HangingSignItem(AbyssalDecorBlocks.WHITEWOOD_HANGING_SIGN.get(),
                    AbyssalDecorBlocks.WHITEWOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistrySupplier<BlockItem> WHITE_PEARL = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL);
    public static final RegistrySupplier<BlockItem> WHITE_PEARL_BLOCK = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_BLOCK);

    ///////

    public static final RegistrySupplier<BlockItem> WHITE_PEARL_STAIRS = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_STAIRS);
    public static final RegistrySupplier<BlockItem> WHITE_PEARL_SLAB = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_SLAB);
    public static final RegistrySupplier<BlockItem> CHISELED_WHITE_PEARL = registerBlockItem(AbyssalDecorBlocks.CHISELED_WHITE_PEARL);
    public static final RegistrySupplier<BlockItem> WHITE_PEARL_WALL = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_WALL);

    public static final RegistrySupplier<BlockItem> WHITE_PEARL_BARS = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_BARS);
    public static final RegistrySupplier<BlockItem> WHITE_PEARL_DOOR = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_DOOR);
    public static final RegistrySupplier<BlockItem> WHITE_PEARL_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> WHITE_PEARL_PILLAR = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_PILLAR);
    public static final RegistrySupplier<BlockItem> CUT_WHITE_PEARL_BLOCK = registerBlockItem(AbyssalDecorBlocks.CUT_WHITE_PEARL_BLOCK);

    ///////

    public static final RegistrySupplier<BlockItem> WHITE_PEARL_BRICKS = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_BRICKS);

    public static final RegistrySupplier<BlockItem> WHITE_PEARL_BRICK_STAIRS = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_BRICK_STAIRS);
    public static final RegistrySupplier<BlockItem> WHITE_PEARL_BRICK_SLAB = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_BRICK_SLAB);
    public static final RegistrySupplier<BlockItem> WHITE_PEARL_BRICK_WALL = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_BRICK_WALL);

    ///////

    public static final RegistrySupplier<BlockItem> SMOOTH_WHITE_PEARL_BLOCK = registerBlockItem(AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_BLOCK);

    public static final RegistrySupplier<BlockItem> SMOOTH_WHITE_PEARL_STAIRS = registerBlockItem(AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_STAIRS);
    public static final RegistrySupplier<BlockItem> SMOOTH_WHITE_PEARL_SLAB = registerBlockItem(AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_SLAB);
    public static final RegistrySupplier<BlockItem> SMOOTH_WHITE_PEARL_WALL = registerBlockItem(AbyssalDecorBlocks.SMOOTH_WHITE_PEARL_WALL);

    public static final RegistrySupplier<BlockItem> WHITE_PEARL_TILES = registerBlockItem(AbyssalDecorBlocks.WHITE_PEARL_TILES);
    public static final RegistrySupplier<BlockItem> CRACKED_PEARL_TILES = registerBlockItem(AbyssalDecorBlocks.CRACKED_PEARL_TILES);
    public static final RegistrySupplier<BlockItem> MIXED_PEARL_TILES = registerBlockItem(AbyssalDecorBlocks.MIXED_PEARL_TILES);
    public static final RegistrySupplier<BlockItem> STARRY_PEARL_TILES = registerBlockItem(AbyssalDecorBlocks.STARRY_PEARL_TILES);

    public static final RegistrySupplier<BlockItem> CLAM = registerBlockItem(AbyssalDecorBlocks.CLAM);
    public static final RegistrySupplier<BlockItem> CLAM_WITH_PEARL = registerBlockItem(AbyssalDecorBlocks.CLAM_WITH_PEARL);
    public static final RegistrySupplier<Item> SHELL = AbyssalDecor.ITEMS.register("shell",() -> new Item(new Item.Properties()));

    /////////////////////////////////////

    public static final RegistrySupplier<BlockItem> PEARLY_GLASS = registerBlockItem(AbyssalDecorBlocks.PEARLY_GLASS);
    public static final RegistrySupplier<BlockItem> SUNNY_PEARLY_GLASS = registerBlockItem(AbyssalDecorBlocks.SUNNY_PEARLY_GLASS);
    public static final RegistrySupplier<BlockItem> AZURE_PEARLY_GLASS = registerBlockItem(AbyssalDecorBlocks.AZURE_PEARLY_GLASS);
    public static final RegistrySupplier<BlockItem> VERDANT_PEARLY_GLASS = registerBlockItem(AbyssalDecorBlocks.VERDANT_PEARLY_GLASS);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_PEARLY_GLASS = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_PEARLY_GLASS);

    public static final RegistrySupplier<BlockItem> BLACKWOOD_PEARLY_GLASS = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_PEARLY_GLASS);
    public static final RegistrySupplier<BlockItem> PEARLY_GLASS_PANE = registerBlockItem(AbyssalDecorBlocks.PEARLY_GLASS_PANE);
    public static final RegistrySupplier<BlockItem> SUNNY_PEARLY_GLASS_PANE = registerBlockItem(AbyssalDecorBlocks.SUNNY_PEARLY_GLASS_PANE);
    public static final RegistrySupplier<BlockItem> AZURE_PEARLY_GLASS_PANE = registerBlockItem(AbyssalDecorBlocks.AZURE_PEARLY_GLASS_PANE);
    public static final RegistrySupplier<BlockItem> VERDANT_PEARLY_GLASS_PANE = registerBlockItem(AbyssalDecorBlocks.VERDANT_PEARLY_GLASS_PANE);
    public static final RegistrySupplier<BlockItem> WHITEWOOD_PEARLY_GLASS_PANE = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_PEARLY_GLASS_PANE);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_PEARLY_GLASS_PANE = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_PEARLY_GLASS_PANE);

    public static final RegistrySupplier<BlockItem> MIXED_BRICKS = registerBlockItem(AbyssalDecorBlocks.MIXED_BRICKS);
    public static final RegistrySupplier<BlockItem> MOSSY_MIXED_BRICKS = registerBlockItem(AbyssalDecorBlocks.MOSSY_MIXED_BRICKS);
    public static final RegistrySupplier<BlockItem> BRITTLE_TUFF = registerBlockItem(AbyssalDecorBlocks.BRITTLE_TUFF);

    public static final RegistrySupplier<BlockItem> STONE_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.STONE_TRAPDOOR);
    public static final RegistrySupplier<BlockItem> MOSSY_STONE_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.MOSSY_STONE_TRAPDOOR);
    public static final RegistrySupplier<BlockItem> ORNATE_STONE_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.ORNATE_STONE_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> IRON_SCONCE = registerBlockItem(AbyssalDecorBlocks.IRON_SCONCE);
    public static final RegistrySupplier<BlockItem> ORNATE_IRON_BARS = registerBlockItem(AbyssalDecorBlocks.ORNATE_IRON_BARS);

    public static final RegistrySupplier<BlockItem> CORRUGATED_IRON = registerBlockItem(AbyssalDecorBlocks.CORRUGATED_IRON);
    public static final RegistrySupplier<BlockItem> IRON_PILLAR = registerBlockItem(AbyssalDecorBlocks.IRON_PILLAR);
    public static final RegistrySupplier<BlockItem> IRON_DUCT = registerBlockItem(AbyssalDecorBlocks.IRON_DUCT);
    public static final RegistrySupplier<DoubleHighBlockItem> IRON_PUSH_DOOR = registerDoorItem(AbyssalDecorBlocks.IRON_PUSH_DOOR);

    //////////////////////////

    public static final RegistrySupplier<BlockItem> IRON_PANEL_BLOCK = registerBlockItem(AbyssalDecorBlocks.IRON_PANEL);

    public static final RegistrySupplier<BlockItem> IRON_PANEL_STAIRS = registerBlockItem(AbyssalDecorBlocks.IRON_PANEL_STAIRS);
    public static final RegistrySupplier<BlockItem> IRON_PANEL_SLAB = registerBlockItem(AbyssalDecorBlocks.IRON_PANEL_SLAB);
    public static final RegistrySupplier<BlockItem> IRON_PANEL_WALL = registerBlockItem(AbyssalDecorBlocks.IRON_PANEL_WALL);

    public static final RegistrySupplier<BlockItem> DULL_IRON_BARS = registerBlockItem(AbyssalDecorBlocks.DULL_IRON_BARS);
    public static final RegistrySupplier<BlockItem> DULL_IRON_SCONCE = registerBlockItem(AbyssalDecorBlocks.DULL_IRON_SCONCE);
    public static final RegistrySupplier<BlockItem> ORNATE_DULL_IRON_BARS = registerBlockItem(AbyssalDecorBlocks.ORNATE_DULL_IRON_BARS);
    public static final RegistrySupplier<DoubleHighBlockItem> DULL_IRON_BAR_DOOR = registerDoorItem(AbyssalDecorBlocks.DULL_IRON_BAR_DOOR);
    public static final RegistrySupplier<BlockItem> DULL_IRON_BAR_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.DULL_IRON_BAR_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> IRON_BALL = registerBlockItem(AbyssalDecorBlocks.IRON_BALL);
    public static final RegistrySupplier<BlockItem> DULL_IRON_POST = registerBlockItem(AbyssalDecorBlocks.DULL_IRON_POST);

    public static final RegistrySupplier<BlockItem> GOLD_SCONCE = registerBlockItem(AbyssalDecorBlocks.GOLD_SCONCE);
    public static final RegistrySupplier<BlockItem> GOLD_BARS = registerBlockItem(AbyssalDecorBlocks.GOLD_BARS);
    
    /////////////////////////////

    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_BUD = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_BUD);
    public static final RegistrySupplier<BlockItem> POLISHED_BLOOD_CORAL = registerBlockItem(AbyssalDecorBlocks.POLISHED_BLOOD_CORAL);
    public static final RegistrySupplier<BlockItem> SMOOTH_BLOOD_CORAL = registerBlockItem(AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL);
    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_BRICKS = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_BRICKS);
    public static final RegistrySupplier<BlockItem> ROUGH_BLOOD_CORAL = registerBlockItem(AbyssalDecorBlocks.ROUGH_BLOOD_CORAL);

    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_PILLAR = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_PILLAR);
    public static final RegistrySupplier<BlockItem> GLIDED_BLOOD_CORAL_PILLAR = registerBlockItem(AbyssalDecorBlocks.GLIDED_BLOOD_CORAL_PILLAR);
    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_SCONCE = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_SCONCE);

    //blood coral lantern

    public static final RegistrySupplier<DoubleHighBlockItem> BLOOD_CORAL_DOOR = registerDoorItem(AbyssalDecorBlocks.BLOOD_CORAL_DOOR);
    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_BARS = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_BARS);

    public static final RegistrySupplier<BlockItem> POLISHED_BLOOD_CORAL_SLAB = registerBlockItem(AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_SLAB);
    public static final RegistrySupplier<BlockItem> POLISHED_BLOOD_CORAL_STAIRS = registerBlockItem(AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_STAIRS);
    public static final RegistrySupplier<BlockItem> POLISHED_BLOOD_CORAL_WALL = registerBlockItem(AbyssalDecorBlocks.POLISHED_BLOOD_CORAL_WALL);

    public static final RegistrySupplier<BlockItem> SMOOTH_BLOOD_CORAL_SLAB = registerBlockItem(AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_SLAB);
    public static final RegistrySupplier<BlockItem> SMOOTH_BLOOD_CORAL_STAIRS = registerBlockItem(AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_STAIRS);
    public static final RegistrySupplier<BlockItem> SMOOTH_BLOOD_CORAL_WALL = registerBlockItem(AbyssalDecorBlocks.SMOOTH_BLOOD_CORAL_WALL);

    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_BRICK_SLAB = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_BRICK_SLAB);
    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_BRICK_STAIRS = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_BRICK_STAIRS);
    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_BRICK_WALL = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_BRICK_WALL);

    ///////////////////////////////

    public static final RegistrySupplier<BlockItem> ROUGH_JADE = registerBlockItem(AbyssalDecorBlocks.ROUGH_JADE);
    public static final RegistrySupplier<BlockItem> POLISHED_JADE = registerBlockItem(AbyssalDecorBlocks.POLISHED_JADE);
    public static final RegistrySupplier<BlockItem> JADE_BRICKS = registerBlockItem(AbyssalDecorBlocks.JADE_BRICKS);
    public static final RegistrySupplier<BlockItem> JADE_PILLAR = registerBlockItem(AbyssalDecorBlocks.JADE_PILLAR);
    public static final RegistrySupplier<BlockItem> GILDED_JADE_PILLAR = registerBlockItem(AbyssalDecorBlocks.GILDED_JADE_PILLAR);

    public static final RegistrySupplier<BlockItem> QUARTZ_BONE = registerBlockItem(AbyssalDecorBlocks.QUARTZ_BONE);
    public static final RegistrySupplier<BlockItem> BONEROT = registerBlockItem(AbyssalDecorBlocks.BONEROT);
    public static final RegistrySupplier<BlockItem> FLAKY_SCALES = registerBlockItem(AbyssalDecorBlocks.FLAKY_SCALES);

    public static final RegistrySupplier<BlockItem> SLUMP_LIGHT = registerBlockItem(AbyssalDecorBlocks.SLUMP_LIGHT);
    public static final RegistrySupplier<BlockItem> SHORTGILLS = registerBlockItem(AbyssalDecorBlocks.SHORTGILLS);

    public static final RegistrySupplier<BlockItem> SERPENT_SCALES = registerBlockItem(AbyssalDecorBlocks.SERPENT_SCALES);
    public static final RegistrySupplier<BlockItem> SERPENT_SCALE_SLAB = registerBlockItem(AbyssalDecorBlocks.SERPENT_SCALE_SLAB);
    public static final RegistrySupplier<BlockItem> SERPENT_SKIN = registerBlockItem(AbyssalDecorBlocks.SERPENT_SKIN);

    public static final RegistrySupplier<BlockItem> DAMAGED_SERPENT_SCALES = registerBlockItem(AbyssalDecorBlocks.DAMAGED_SERPENT_SCALES);
    public static final RegistrySupplier<BlockItem> DAMAGED_SERPENT_SCALE_SLAB = registerBlockItem(AbyssalDecorBlocks.DAMAGED_SERPENT_SCALE_SLAB);
    public static final RegistrySupplier<BlockItem> DAMAGED_SERPENT_SKIN = registerBlockItem(AbyssalDecorBlocks.DAMAGED_SERPENT_SKIN);

    public static final RegistrySupplier<BlockItem> SERPENT_FLESH = registerBlockItem(AbyssalDecorBlocks.SERPENT_FLESH);
    public static final RegistrySupplier<BlockItem> SERPENT_FLESH_SLAB = registerBlockItem(AbyssalDecorBlocks.SERPENT_FLESH_SLAB);

    public static final RegistrySupplier<BlockItem> RIBBED_SERPENT_VEINS = registerBlockItem(AbyssalDecorBlocks.RIBBED_SERPENT_VEINS);
    public static final RegistrySupplier<BlockItem> SERPENT_EYE = registerBlockItem(AbyssalDecorBlocks.SERPENT_EYE);
    public static final RegistrySupplier<BlockItem> SMALL_SERPENT_EYES = registerBlockItem(AbyssalDecorBlocks.SMALL_SERPENT_EYES);

    public static final RegistrySupplier<BlockItem> NETTED_SERPENT_EYE = registerBlockItem(AbyssalDecorBlocks.NETTED_SERPENT_EYE);
    public static final RegistrySupplier<BlockItem> SMALL_NETTED_SERPENT_EYE = registerBlockItem(AbyssalDecorBlocks.SMALL_NETTED_SERPENT_EYE);

    public static final RegistrySupplier<BlockItem> VELVET = registerBlockItem(AbyssalDecorBlocks.VELVET);
    public static final RegistrySupplier<BlockItem> GILDED_VELVET = registerBlockItem(AbyssalDecorBlocks.GILDED_VELVET);

    public static final RegistrySupplier<BlockItem> VELVET_CARPET = registerBlockItem(AbyssalDecorBlocks.VELVET_CARPET);
    public static final RegistrySupplier<BlockItem> GILDED_VELVET_CARPET = registerBlockItem(AbyssalDecorBlocks.GILDED_VELVET_CARPET);

    public static final RegistrySupplier<BlockItem> VELVET_CURTAIN = registerBlockItem(AbyssalDecorBlocks.VELVET_CURTAIN);
    public static final RegistrySupplier<BlockItem> WOOL_CURTAIN = registerBlockItem(AbyssalDecorBlocks.WOOL_CURTAIN);

    public static final RegistrySupplier<BlockItem> AMMONITE = registerBlockItem(AbyssalDecorBlocks.AMMONITE);
    public static final RegistrySupplier<Item> RAW_SEABRASS = AbyssalDecor.ITEMS.register("raw_seabrass", AbyssalDecorItems::simpleItem);
    public static final RegistrySupplier<BlockItem> RAW_SEABRASS_BLOCK = registerBlockItem(AbyssalDecorBlocks.RAW_SEABRASS_BLOCK);

    public static final RegistrySupplier<Item> SEABRASS_INGOT = AbyssalDecor.ITEMS.register("seabrass_ingot", AbyssalDecorItems::simpleItem);
    public static final RegistrySupplier<Item> SEABRASS_NUGGET = AbyssalDecor.ITEMS.register("seabrass_nugget", AbyssalDecorItems::simpleItem);

    public static final RegistrySupplier<BlockItem> SEABRASS_BLOCK = registerBlockItem(AbyssalDecorBlocks.SEABRASS_BLOCK);
    public static final RegistrySupplier<BlockItem> RIVETED_SEABRASS = registerBlockItem(AbyssalDecorBlocks.RIVETED_SEABRASS);
    public static final RegistrySupplier<BlockItem> SEABRASS_TRIM = registerBlockItem(AbyssalDecorBlocks.SEABRASS_TRIM);
    public static final RegistrySupplier<BlockItem> SEABRASS_PILLAR = registerBlockItem(AbyssalDecorBlocks.SEABRASS_PILLAR);
    public static final RegistrySupplier<BlockItem> LARGE_SEABRASS_PIPE = registerBlockItem(AbyssalDecorBlocks.LARGE_SEABRASS_PIPE);
    public static final RegistrySupplier<BlockItem> SMALL_SEABRASS_PIPES = registerBlockItem(AbyssalDecorBlocks.SMALL_SEABRASS_PIPES);
    public static final RegistrySupplier<BlockItem> SEABRASS_TILES = registerBlockItem(AbyssalDecorBlocks.SEABRASS_TILES);
    public static final RegistrySupplier<BlockItem> SEABRASS_LAMP = registerBlockItem(AbyssalDecorBlocks.SEABRASS_LAMP);

    public static final RegistrySupplier<BlockItem> SEABRASS_STAIRS = registerBlockItem(AbyssalDecorBlocks.SEABRASS_STAIRS);
    public static final RegistrySupplier<BlockItem> SEABRASS_SLAB = registerBlockItem(AbyssalDecorBlocks.SEABRASS_SLAB);
    public static final RegistrySupplier<BlockItem> SEABRASS_WALL = registerBlockItem(AbyssalDecorBlocks.SEABRASS_WALL);
    public static final RegistrySupplier<BlockItem> SEABRASS_BUTTON = registerBlockItem(AbyssalDecorBlocks.SEABRASS_BUTTON);
    public static final RegistrySupplier<BlockItem> SEABRASS_PRESSURE_PLATE = registerBlockItem(AbyssalDecorBlocks.SEABRASS_PRESSURE_PLATE);

    public static final RegistrySupplier<BlockItem> SEABRASS_DOOR = registerBlockItem(AbyssalDecorBlocks.SEABRASS_DOOR);
    public static final RegistrySupplier<BlockItem> SEABRASS_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.SEABRASS_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> RIVETED_SEABRASS_SLAB = registerBlockItem(AbyssalDecorBlocks.RIVETED_SEABRASS_SLAB);

    public static final RegistrySupplier<BlockItem> SEABRASS_CHAIN = registerBlockItem(AbyssalDecorBlocks.SEABRASS_CHAIN);

    public static final RegistrySupplier<BlockItem> SEABRASS_SCONCE = registerBlockItem(AbyssalDecorBlocks.SEABRASS_SCONCE);
    public static final RegistrySupplier<BlockItem> SEABRASS_BARS = registerBlockItem(AbyssalDecorBlocks.SEABRASS_BARS);
    public static final RegistrySupplier<BlockItem> ORNATE_SEABRASS_BARS = registerBlockItem(AbyssalDecorBlocks.ORNATE_SEABRASS_BARS);

    ////////////////////////////////

    public static final RegistrySupplier<Item> DEEPBRONZE_INGOT = AbyssalDecor.ITEMS.register("deepbronze_ingot", AbyssalDecorItems::simpleItem);
    public static final RegistrySupplier<Item> DEEPBRONZE_NUGGET = AbyssalDecor.ITEMS.register("deepbronze_nugget", AbyssalDecorItems::simpleItem);

    public static final RegistrySupplier<BlockItem> DEEPBRONZE_BLOCK = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_BLOCK);
    public static final RegistrySupplier<BlockItem> RIVETED_DEEPBRONZE = registerBlockItem(AbyssalDecorBlocks.RIVETED_DEEPBRONZE);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_TRIM = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_TRIM);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_PILLAR = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_PILLAR);
    public static final RegistrySupplier<BlockItem> LARGE_DEEPBRONZE_PIPE = registerBlockItem(AbyssalDecorBlocks.LARGE_DEEPBRONZE_PIPE);

    public static final RegistrySupplier<BlockItem> DEEPBRONZE_PLATING = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_PLATING);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_BARS = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_BARS);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_BEAM = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_BEAM);

    public static final RegistrySupplier<BlockItem> DEEPBRONZE_STAIRS = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_STAIRS);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_SLAB = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_SLAB);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_WALL = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_WALL);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_BUTTON = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_BUTTON);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_PRESSURE_PLATE = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_PRESSURE_PLATE);

    public static final RegistrySupplier<BlockItem> DEEPBRONZE_DOOR = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_DOOR);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> RIVETED_DEEPBRONZE_SLAB = registerBlockItem(AbyssalDecorBlocks.RIVETED_DEEPBRONZE_SLAB);
    public static final RegistrySupplier<BlockItem> RIVETED_DEEPBRONZE_STAIRS = registerBlockItem(AbyssalDecorBlocks.RIVETED_DEEPBRONZE_STAIRS);
    public static final RegistrySupplier<BlockItem> RIVETED_DEEPBRONZE_WALL = registerBlockItem(AbyssalDecorBlocks.RIVETED_DEEPBRONZE_WALL);

    public static final RegistrySupplier<BlockItem> DEEPBRONZE_CHAIN = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_CHAIN);

    public static final RegistrySupplier<BlockItem> DEEPBRONZE_SCONCE = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_SCONCE);


    static Item simpleItem() {
        return new Item(new Item.Properties());
    }

    //////////////////////////////

    public static final RegistrySupplier<BlockItem> BLACKWOOD_LOG = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_LOG);
    public static final RegistrySupplier<BlockItem> STRIPPED_BLACKWOOD_LOG = registerBlockItem(AbyssalDecorBlocks.STRIPPED_BLACKWOOD_LOG);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_WOOD = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_WOOD);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_PLANKS = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_PLANKS);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_STAIRS = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_STAIRS);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_SLAB = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_SLAB);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_BUTTON = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_BUTTON);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_TRIM = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_TRIM);
    public static final RegistrySupplier<DoubleHighBlockItem> BLACKWOOD_DOOR = registerDoorItem(AbyssalDecorBlocks.BLACKWOOD_DOOR);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_TRAPDOOR);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_PRESSURE_PLATE = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_PRESSURE_PLATE);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_FENCE = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_FENCE);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_FENCE_GATE = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_FENCE_GATE);
    public static final RegistrySupplier<BlockItem> BLACKWOOD_SIGN = AbyssalDecor.ITEMS.register("blackwood_sign",() ->
            new SignItem(new Item.Properties().stacksTo(16), AbyssalDecorBlocks.BLACKWOOD_SIGN.get(), AbyssalDecorBlocks.BLACKWOOD_WALL_SIGN.get()));
    public static final RegistrySupplier<BlockItem> BLACKWOOD_HANGING_SIGN = AbyssalDecor.ITEMS.register("blackwood_hanging_sign",
            () -> new HangingSignItem(AbyssalDecorBlocks.BLACKWOOD_HANGING_SIGN.get(),
                    AbyssalDecorBlocks.BLACKWOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));


    /////////////////////////////////


    public static final RegistrySupplier<BlockItem> BLACK_PEARL = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_BLOCK = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_BLOCK);

    ///////

    public static final RegistrySupplier<BlockItem> BLACK_PEARL_STAIRS = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_STAIRS);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_SLAB = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_SLAB);
    public static final RegistrySupplier<BlockItem> CHISELED_BLACK_PEARL = registerBlockItem(AbyssalDecorBlocks.CHISELED_BLACK_PEARL);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_WALL = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_WALL);

    public static final RegistrySupplier<BlockItem> BLACK_PEARL_PILLAR = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_PILLAR);
    public static final RegistrySupplier<BlockItem> CUT_BLACK_PEARL_BLOCK = registerBlockItem(AbyssalDecorBlocks.CUT_BLACK_PEARL_BLOCK);

    ///////

    public static final RegistrySupplier<BlockItem> BLACK_PEARL_BRICKS = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_BRICKS);

    public static final RegistrySupplier<BlockItem> BLACK_PEARL_BRICK_STAIRS = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_BRICK_STAIRS);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_BRICK_SLAB = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_BRICK_SLAB);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_BRICK_WALL = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_BRICK_WALL);

    ///////

    public static final RegistrySupplier<BlockItem> SMOOTH_BLACK_PEARL_BLOCK = registerBlockItem(AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_BLOCK);

    public static final RegistrySupplier<BlockItem> SMOOTH_BLACK_PEARL_STAIRS = registerBlockItem(AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_STAIRS);
    public static final RegistrySupplier<BlockItem> SMOOTH_BLACK_PEARL_SLAB = registerBlockItem(AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_SLAB);
    public static final RegistrySupplier<BlockItem> SMOOTH_BLACK_PEARL_WALL = registerBlockItem(AbyssalDecorBlocks.SMOOTH_BLACK_PEARL_WALL);

    public static void register() {
        AbyssalDecor.ITEMS.register();
    }

    public static <B extends Block> RegistrySupplier<ItemNameBlockItem> registerNamedBlockItem(String name, Supplier<B> bSupplier) {
        return registerNamedBlockItem(name,bSupplier,new Item.Properties());
    }

    public static <B extends Block> RegistrySupplier<ItemNameBlockItem> registerNamedBlockItem(String name, Supplier<B> bSupplier, Item.Properties properties) {
        return AbyssalDecor.ITEMS.register(name,() -> new ItemNameBlockItem(bSupplier.get(),properties));
    }

    public static <B extends Block> RegistrySupplier<BlockItem> registerBlockItem(String name, Supplier<B> bSupplier, Item.Properties properties) {
        RegistrySupplier<BlockItem> register = AbyssalDecor.ITEMS.register(name, () -> new BlockItem(bSupplier.get(), properties));
        return register;
    }


    public static <B extends Block> RegistrySupplier<BlockItem> registerBlockItem(String name, Supplier<B> bSupplier) {
       return registerBlockItem(name,bSupplier,new Item.Properties());
    }

    public static <B extends Block> RegistrySupplier<BlockItem> registerBlockItem(RegistrySupplier<B> bSupplier) {
        return registerBlockItem(bSupplier.getId().getPath(),bSupplier,new Item.Properties());
    }

    public static <B extends DoorBlock> RegistrySupplier<DoubleHighBlockItem> registerDoorItem(RegistrySupplier<B> bSupplier) {
        return AbyssalDecor.ITEMS.register(bSupplier.getId().getPath(),() -> new DoubleHighBlockItem(bSupplier.get(),new Item.Properties()));
    }

}
