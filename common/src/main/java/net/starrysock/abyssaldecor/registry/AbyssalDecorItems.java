package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.item.*;

import java.util.function.Supplier;

public class AbyssalDecorItems {

    public static final RegistrySupplier<BlockItem> SOLAR_ROD = registerBlockItem(AbyssalDecorBlocks.SOLAR_ROD);
    public static final RegistrySupplier<BlockItem> STELLAR_ROD = registerBlockItem(AbyssalDecorBlocks.STELLAR_ROD);
    public static final RegistrySupplier<BlockItem> TERRESTRIAL_ROD = registerBlockItem(AbyssalDecorBlocks.TERRESTRIAL_ROD);
    public static final RegistrySupplier<BlockItem> LUNAR_ROD = registerBlockItem(AbyssalDecorBlocks.LUNAR_ROD);
    public static final RegistrySupplier<BlockItem> ETHEREAL_ROD = registerBlockItem(AbyssalDecorBlocks.ETHEREAL_ROD);
    public static final RegistrySupplier<BlockItem> HANGING_MOSS = AbyssalDecor.ITEMS.register("hanging_moss",() ->
            new StandingAndWallBlockItem(AbyssalDecorBlocks.HANGING_MOSS.get(),
                    AbyssalDecorBlocks.WALL_HANGING_MOSS.get(),new Item.Properties(),Direction.UP));

    public static final RegistrySupplier<BlockItem> DAFFODIL = registerBlockItem(AbyssalDecorBlocks.DAFFODIL);
    public static final RegistrySupplier<BlockItem> ASTER = registerBlockItem(AbyssalDecorBlocks.ASTER);
    public static final RegistrySupplier<BlockItem> SNAPLEAF = registerBlockItem(AbyssalDecorBlocks.SNAPLEAF);

    public static final RegistrySupplier<ItemNameBlockItem> AMARANTH_SEEDS = registerNamedBlockItem("amaranth_seeds",AbyssalDecorBlocks.AMARANTH);
    public static final RegistrySupplier<Item> AMARANTH_PINNACLE = AbyssalDecor.ITEMS.register("amaranth_pinnacle",() -> new Item(new Item.Properties()));
    public static final RegistrySupplier<BlockItem> AMARANTH_CRATE = registerBlockItem(AbyssalDecorBlocks.AMARANTH_CRATE);
    public static final RegistrySupplier<ItemNameBlockItem> MUCKROOT = registerNamedBlockItem("muckroot",
            AbyssalDecorBlocks.MUCKROOT,new Item.Properties().food(Foods.CARROT));
    public static final RegistrySupplier<Item> COOKED_MUCKROOT = AbyssalDecor.ITEMS.register("cooked_muckroot",() ->
            new CookedMuckrootItem(new Item.Properties().food(Foods.BEETROOT)));
    public static final RegistrySupplier<Item> MUCKROOT_SOUP = AbyssalDecor.ITEMS.register("muckroot_soup",() ->
            new BowlFoodItem(new Item.Properties().stacksTo(1).food(Foods.BEETROOT_SOUP)));

    public static final RegistrySupplier<BlockItem> BARK_ORCHID = registerBlockItem(AbyssalDecorBlocks.BARK_ORCHID);

    public static final RegistrySupplier<BlockItem> WISTERIA = registerBlockItem(AbyssalDecorBlocks.WISTERIA);
    public static final RegistrySupplier<BlockItem> ELDER_WISTERIA = registerBlockItem(AbyssalDecorBlocks.ELDER_WISTERIA);

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
    public static final RegistrySupplier<BlockItem> SCRIMSHAW_ALTAR = registerBlockItem(AbyssalDecorBlocks.SCRIMSHAW_ALTAR);
    public static final RegistrySupplier<BlockItem> FOGHORN = registerBlockItem(AbyssalDecorBlocks.FOGHORN);
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

    public static final RegistrySupplier<BlockItem> TRASH_BAG = registerBlockItem(AbyssalDecorBlocks.TRASH_BAG);

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

    public static final RegistrySupplier<BlockItem> WHITEWOOD_PLANTER = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_PLANTER);

    public static final RegistrySupplier<BlockItem> WHITEWOOD_PICKET_FENCE = registerBlockItem(AbyssalDecorBlocks.WHITEWOOD_PICKET_FENCE);

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

    public static final RegistrySupplier<BlockItem> SMALL_WHITE_PEARL_BARS = registerBlockItem(AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS);

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

    public static final RegistrySupplier<BlockItem> SMALL_CLEAN_IRON_BARS = registerBlockItem(AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS);

    public static final RegistrySupplier<BlockItem> IRON_SCONCE = registerBlockItem(AbyssalDecorBlocks.IRON_SCONCE);
    public static final RegistrySupplier<BlockItem> ORNATE_IRON_BARS = registerBlockItem(AbyssalDecorBlocks.ORNATE_IRON_BARS);

    public static final RegistrySupplier<BlockItem> CORRUGATED_IRON = registerBlockItem(AbyssalDecorBlocks.CORRUGATED_IRON);
    public static final RegistrySupplier<BlockItem> IRON_PILLAR = registerBlockItem(AbyssalDecorBlocks.IRON_PILLAR);
    public static final RegistrySupplier<BlockItem> IRON_DUCT = registerBlockItem(AbyssalDecorBlocks.IRON_DUCT);
    public static final RegistrySupplier<DoubleHighBlockItem> IRON_PUSH_DOOR = registerDoorItem(AbyssalDecorBlocks.IRON_PUSH_DOOR);

    public static final RegistrySupplier<BlockItem> IRON_VENT_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.IRON_VENT_TRAPDOOR);

    //////////////////////////

    public static final RegistrySupplier<BlockItem> IRON_PANEL_BLOCK = registerBlockItem(AbyssalDecorBlocks.IRON_PANEL);

    public static final RegistrySupplier<BlockItem> IRON_PANEL_STAIRS = registerBlockItem(AbyssalDecorBlocks.IRON_PANEL_STAIRS);
    public static final RegistrySupplier<BlockItem> IRON_PANEL_SLAB = registerBlockItem(AbyssalDecorBlocks.IRON_PANEL_SLAB);
    public static final RegistrySupplier<BlockItem> IRON_PANEL_WALL = registerBlockItem(AbyssalDecorBlocks.IRON_PANEL_WALL);

    public static final RegistrySupplier<BlockItem> INDUSTRIAL_LEVER = registerBlockItem(AbyssalDecorBlocks.INDUSTRIAL_LEVER);

    public static final RegistrySupplier<BlockItem> DULL_IRON_BARS = registerBlockItem(AbyssalDecorBlocks.DULL_IRON_BARS);
    public static final RegistrySupplier<BlockItem> SMALL_DULL_IRON_BARS = registerBlockItem(AbyssalDecorBlocks.SMALL_DULL_IRON_BARS);
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
    public static final RegistrySupplier<BlockItem> GILDED_BLOOD_CORAL_PILLAR = registerBlockItem(AbyssalDecorBlocks.GILDED_BLOOD_CORAL_PILLAR);
    public static final RegistrySupplier<BlockItem> BLOOD_LANTERN = registerBlockItem(AbyssalDecorBlocks.BLOOD_LANTERN);
    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_SCONCE = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_SCONCE);

    //blood coral lantern

    public static final RegistrySupplier<DoubleHighBlockItem> BLOOD_CORAL_DOOR = registerDoorItem(AbyssalDecorBlocks.BLOOD_CORAL_DOOR);
    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> BLOOD_CORAL_BARS = registerBlockItem(AbyssalDecorBlocks.BLOOD_CORAL_BARS);
    public static final RegistrySupplier<BlockItem> SMALL_BLOOD_CORAL_BARS = registerBlockItem(AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS);

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
    public static final RegistrySupplier<BlockItem> SEABRASS_PLATING = registerBlockItem(AbyssalDecorBlocks.SEABRASS_PLATING);
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
    public static final RegistrySupplier<BlockItem> SMALL_SEABRASS_BARS = registerBlockItem(AbyssalDecorBlocks.SMALL_SEABRASS_BARS);

    public static final RegistrySupplier<BlockItem> SEABRASS_CATALYST = registerBlockItem(AbyssalDecorBlocks.SEABRASS_CATALYST);

    ////////////////////////////////

    public static final RegistrySupplier<Item> DEEPBRONZE_INGOT = AbyssalDecor.ITEMS.register("deepbronze_ingot", AbyssalDecorItems::simpleItem);
    public static final RegistrySupplier<Item> DEEPBRONZE_NUGGET = AbyssalDecor.ITEMS.register("deepbronze_nugget", AbyssalDecorItems::simpleItem);

    public static final RegistrySupplier<BlockItem> DEEPBRONZE_BLOCK = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_BLOCK);
    public static final RegistrySupplier<BlockItem> RIVETED_DEEPBRONZE = registerBlockItem(AbyssalDecorBlocks.RIVETED_DEEPBRONZE);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_TRIM = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_TRIM);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_PILLAR = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_PILLAR);
    public static final RegistrySupplier<BlockItem> LARGE_DEEPBRONZE_PIPE = registerBlockItem(AbyssalDecorBlocks.LARGE_DEEPBRONZE_PIPE);
    public static final RegistrySupplier<BlockItem> SMALL_DEEPBRONZE_PIPES = registerBlockItem(AbyssalDecorBlocks.SMALL_DEEPBRONZE_PIPES);

    public static final RegistrySupplier<BlockItem> DEEPBRONZE_PLATING = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_PLATING);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_TILES = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_TILES);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_BARS = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_BARS);
    public static final RegistrySupplier<BlockItem> ORNATE_DEEPBRONZE_BARS = registerBlockItem(AbyssalDecorBlocks.ORNATE_DEEPBRONZE_BARS);
    public static final RegistrySupplier<BlockItem> DEEPBRONZE_LANTERN = registerBlockItem(AbyssalDecorBlocks.DEEPBRONZE_LANTERN);
    public static final RegistrySupplier<BlockItem> SMALL_DEEPBRONZE_BARS = registerBlockItem(AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS);
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

    public static final ColorFamily<BlockItem> WALLPAPERS = ColorFamily.createAndRegister(
            AbyssalDecor.ITEMS,color -> new BlockItem(AbyssalDecorBlocks.WALLPAPERS.getEntry(color).get(), new Item.Properties()),"wallpaper");

    public static final RegistrySupplier<BlockItem> LAVENTINE = registerBlockItem(AbyssalDecorBlocks.LAVENTINE);
    public static final RegistrySupplier<BlockItem> LAVENTINE_GLASS = registerBlockItem(AbyssalDecorBlocks.LAVENTINE_GLASS);
    public static final RegistrySupplier<BlockItem> LAVENTINE_GLASS_PANE = registerBlockItem(AbyssalDecorBlocks.LAVENTINE_GLASS_PANE);

    public static final RegistrySupplier<BlockItem> STARSTONE = registerBlockItem(AbyssalDecorBlocks.STARSTONE);
    public static final RegistrySupplier<BlockItem> SMOOTH_STARSTONE = registerBlockItem(AbyssalDecorBlocks.SMOOTH_STARSTONE);
    public static final RegistrySupplier<BlockItem> POLISHED_STARSTONE = registerBlockItem(AbyssalDecorBlocks.POLISHED_STARSTONE);

    public static final RegistrySupplier<BlockItem> POLISHED_STARSTONE_SLAB = registerBlockItem(AbyssalDecorBlocks.POLISHED_STARSTONE_SLAB);
    public static final RegistrySupplier<BlockItem> POLISHED_STARSTONE_STAIRS = registerBlockItem(AbyssalDecorBlocks.POLISHED_STARSTONE_STAIRS);
    public static final RegistrySupplier<BlockItem> POLISHED_STARSTONE_WALL = registerBlockItem(AbyssalDecorBlocks.POLISHED_STARSTONE_WALL);

    public static final RegistrySupplier<BlockItem> CHISELED_STARSTONE = registerBlockItem(AbyssalDecorBlocks.CHISELED_STARSTONE);

    public static final RegistrySupplier<BlockItem> STARSTONE_PILLAR = registerBlockItem(AbyssalDecorBlocks.STARSTONE_PILLAR);
    public static final RegistrySupplier<BlockItem> GILDED_STARSTONE = registerBlockItem(AbyssalDecorBlocks.GILDED_STARSTONE);

    public static final RegistrySupplier<BlockItem> STARSTONE_TILES = registerBlockItem(AbyssalDecorBlocks.STARSTONE_TILES);

    public static final RegistrySupplier<BlockItem> LAPIS_PILLAR = registerBlockItem(AbyssalDecorBlocks.LAPIS_PILLAR);
    public static final RegistrySupplier<BlockItem> GILDED_LAPIS_PILLAR = registerBlockItem(AbyssalDecorBlocks.GILDED_LAPIS_PILLAR);

    public static final RegistrySupplier<BlockItem> STARGLASS = registerBlockItem(AbyssalDecorBlocks.STARGLASS);
    public static final RegistrySupplier<BlockItem> STARGLASS_PANE = registerBlockItem(AbyssalDecorBlocks.STARGLASS_PANE);

    //////////////////////////

    public static final RegistrySupplier<BlockItem> GRIME = AbyssalDecor.ITEMS.register("grime",
            () -> new StandingAndWallBlockItem(AbyssalDecorBlocks.GRIME_CARPET.get(),AbyssalDecorBlocks.WALL_GRIME.get(),new Item.Properties(),Direction.UP){
        @Override
        public String getDescriptionId() {
                    return this.getOrCreateDescriptionId();
                }
    });

    public static final RegistrySupplier<BlockItem> BLACK_MOLD = registerBlockItem(AbyssalDecorBlocks.BLACK_MOLD);
    public static final RegistrySupplier<BlockItem> BLACK_MOLD_CARPET = registerBlockItem(AbyssalDecorBlocks.BLACK_MOLD_CARPET);

    public static final RegistrySupplier<BlockItem> INACTIVE_MOLD = registerBlockItem(AbyssalDecorBlocks.INACTIVE_MOLD);

    public static final RegistrySupplier<BlockItem> MOLDWEAVE = registerBlockItem(AbyssalDecorBlocks.MOLDWEAVE);
    public static final RegistrySupplier<BlockItem> MOLDWEAVE_CARPET = registerBlockItem(AbyssalDecorBlocks.MOLDWEAVE_CARPET);

    public static final RegistrySupplier<BlockItem> BLACKENED_SAND = registerBlockItem(AbyssalDecorBlocks.BLACKENED_SAND);

    public static final RegistrySupplier<BlockItem> PITCHGLASS = registerBlockItem(AbyssalDecorBlocks.PITCHGLASS);
    public static final RegistrySupplier<BlockItem> PITCHGLASS_PANE = registerBlockItem(AbyssalDecorBlocks.PITCHGLASS_PANE);

    public static final RegistrySupplier<BlockItem> FRAMED_PITCHGLASS = registerBlockItem(AbyssalDecorBlocks.FRAMED_PITCHGLASS);
    public static final RegistrySupplier<BlockItem> FRAMED_PITCHGLASS_PANE = registerBlockItem(AbyssalDecorBlocks.FRAMED_PITCHGLASS_PANE);

    public static final RegistrySupplier<BlockItem> MOLDY_FROND_BLOCK = registerBlockItem(AbyssalDecorBlocks.MOLDY_FROND_BLOCK);


    public static final RegistrySupplier<BlockItem> MOLDY_STALK = registerBlockItem(AbyssalDecorBlocks.MOLDY_STALK);

    public static final RegistrySupplier<BlockItem> MOLDY_SPROUTS = registerBlockItem(AbyssalDecorBlocks.MOLDY_SPROUTS);

    public static final RegistrySupplier<BlockItem> MOLDY_FUZZ = registerBlockItem(AbyssalDecorBlocks.MOLDY_FUZZ);

    public static final RegistrySupplier<BlockItem> MOLDY_HANGERS = registerBlockItem(AbyssalDecorBlocks.MOLDY_HANGER);



    public static final RegistrySupplier<BlockItem> MOLD_FRONDS = registerBlockItem(AbyssalDecorBlocks.MOLD_FRONDS);

    public static final RegistrySupplier<BlockItem> MOLDY_FEATHERS = registerBlockItem(AbyssalDecorBlocks.MOLDY_FEATHERS);

    public static final RegistrySupplier<ItemNameBlockItem> FEVER_BLOSSOM_SEEDS =
            registerNamedBlockItem("fever_blossom_seeds",AbyssalDecorBlocks.FEVER_BLOSSOM);

    public static final RegistrySupplier<Item> FEVER_BLOSSOM = AbyssalDecor.ITEMS.register("fever_blossom",
            () -> new FeverBlossomItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).alwaysEat().build())));

    public static final RegistrySupplier<Item> FEVER_BLOSSOM_TEA = AbyssalDecor.ITEMS.register("fever_blossom_tea",() ->
            new FeverBlossomTeaItem(new Item.Properties().food((new FoodProperties.Builder()).nutrition(2)
                            .effect(new MobEffectInstance(AbyssalDecorMobEffects.SPORE_PROTECTION.get(),2400),1)
                    .saturationMod(2).fast().build())));

    public static final RegistrySupplier<BlockItem> MOLDY_STARSTONE = registerBlockItem(AbyssalDecorBlocks.MOLDY_STARSTONE);

    public static final RegistrySupplier<BlockItem> MOLDIER_STARSTONE = registerBlockItem(AbyssalDecorBlocks.MOLDIER_STARSTONE);

    public static final RegistrySupplier<BlockItem> POROUS_MOLD = registerBlockItem(AbyssalDecorBlocks.POROUS_MOLD);

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

    public static final RegistrySupplier<BlockItem> BLACKWOOD_SHINGLES = registerBlockItem(AbyssalDecorBlocks.BLACKWOOD_SHINGLES);

    /////////////////////////////

    public static final RegistrySupplier<BlockItem> PAPER_STACK = registerBlockItem(AbyssalDecorBlocks.PAPER_STACK);
    public static final RegistrySupplier<BlockItem> BOOK_BLOCK = registerBlockItem(AbyssalDecorBlocks.BOOK_BLOCK);
    public static final RegistrySupplier<BlockItem> MOLDY_BOOK_BLOCK = registerBlockItem(AbyssalDecorBlocks.MOLDY_BOOK_BLOCK);

    public static final RegistrySupplier<InfiniteBlockItem> BOTTOMLESS_BAG_OF_DIRT = bagOf("dirt",() -> Blocks.DIRT);
    public static final RegistrySupplier<InfiniteBlockItem> BOTTOMLESS_BAG_OF_COBBLESTONE = bagOf("cobblestone",() -> Blocks.COBBLESTONE);
    public static final RegistrySupplier<InfiniteBlockItem> BOTTOMLESS_BAG_OF_SNOW = bagOf("snow",() -> Blocks.SNOW);
    public static final RegistrySupplier<InfiniteBlockItem> BOTTOMLESS_BAG_OF_NETHERRACK = bagOf("netherrack",() -> Blocks.NETHERRACK);

    static RegistrySupplier<InfiniteBlockItem>  bagOf(String suffix,Supplier<Block> block) {
        return AbyssalDecor.ITEMS.register("bottomless_bag_of_"+suffix,() -> new InfiniteBlockItem(block.get(),new Item.Properties()));
    }

    /////////////////////////////////


    public static final RegistrySupplier<BlockItem> BLACK_PEARL = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_BLOCK = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_BLOCK);

    ///////

    public static final RegistrySupplier<BlockItem> BLACK_PEARL_STAIRS = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_STAIRS);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_SLAB = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_SLAB);
    public static final RegistrySupplier<BlockItem> CHISELED_BLACK_PEARL = registerBlockItem(AbyssalDecorBlocks.CHISELED_BLACK_PEARL);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_WALL = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_WALL);
    public static final RegistrySupplier<BlockItem> BLACK_PEARL_BARS = registerBlockItem(AbyssalDecorBlocks.BLACK_PEARL_BARS);
    public static final RegistrySupplier<BlockItem> SMALL_BLACK_PEARL_BARS = registerBlockItem(AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS);

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

    public static final RegistrySupplier<BlockItem> GILDED_BLACK_PEARL = registerBlockItem(AbyssalDecorBlocks.GILDED_BLACK_PEARL);
    public static final RegistrySupplier<BlockItem> GILDED_BLACK_PEARL_DOOR = registerBlockItem(AbyssalDecorBlocks.GILDED_BLACK_PEARL_DOOR);
    public static final RegistrySupplier<BlockItem> GILDED_BLACK_PEARL_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.GILDED_BLACK_PEARL_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> ECHO_SHARD_BLOCK = registerBlockItem(AbyssalDecorBlocks.ECHO_SHARD_BLOCK);
    public static final RegistrySupplier<BlockItem> POLISHED_ECHO_SHARD_BLOCK = registerBlockItem(AbyssalDecorBlocks.POLISHED_ECHO_SHARD_BLOCK);

    ////////////////////////

    public static final RegistrySupplier<BlockItem> CINNAMON_LOG = registerBlockItem(AbyssalDecorBlocks.CINNAMON_LOG);
    public static final RegistrySupplier<BlockItem> HEALING_CINNAMON_LOG = registerBlockItem(AbyssalDecorBlocks.HEALING_CINNAMON_LOG);
    public static final RegistrySupplier<BlockItem> HEALING_CINNAMON_WOOD = registerBlockItem(AbyssalDecorBlocks.HEALING_CINNAMON_WOOD);
    public static final RegistrySupplier<BlockItem> STRIPPED_CINNAMON_LOG = registerBlockItem(AbyssalDecorBlocks.STRIPPED_CINNAMON_LOG);
    public static final RegistrySupplier<BlockItem> STRIPPED_CINNAMON_WOOD = registerBlockItem(AbyssalDecorBlocks.STRIPPED_CINNAMON_WOOD);
    public static final RegistrySupplier<BlockItem> CINNAMON_WOOD = registerBlockItem(AbyssalDecorBlocks.CINNAMON_WOOD);
    public static final RegistrySupplier<BlockItem> CINNAMON_PLANKS = registerBlockItem(AbyssalDecorBlocks.CINNAMON_PLANKS);
    public static final RegistrySupplier<BlockItem> CINNAMON_STAIRS = registerBlockItem(AbyssalDecorBlocks.CINNAMON_STAIRS);
    public static final RegistrySupplier<BlockItem> CINNAMON_SLAB = registerBlockItem(AbyssalDecorBlocks.CINNAMON_SLAB);
    public static final RegistrySupplier<BlockItem> CINNAMON_BUTTON = registerBlockItem(AbyssalDecorBlocks.CINNAMON_BUTTON);
    public static final RegistrySupplier<BlockItem> CINNAMON_TRIM = registerBlockItem(AbyssalDecorBlocks.CINNAMON_TRIM);
    public static final RegistrySupplier<DoubleHighBlockItem> CINNAMON_DOOR = registerDoorItem(AbyssalDecorBlocks.CINNAMON_DOOR);
    public static final RegistrySupplier<BlockItem> CINNAMON_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.CINNAMON_TRAPDOOR);
    public static final RegistrySupplier<BlockItem> CINNAMON_PRESSURE_PLATE = registerBlockItem(AbyssalDecorBlocks.CINNAMON_PRESSURE_PLATE);
    public static final RegistrySupplier<BlockItem> CINNAMON_FENCE = registerBlockItem(AbyssalDecorBlocks.CINNAMON_FENCE);
    public static final RegistrySupplier<BlockItem> CINNAMON_FENCE_GATE = registerBlockItem(AbyssalDecorBlocks.CINNAMON_FENCE_GATE);
    public static final RegistrySupplier<BlockItem> CINNAMON_SIGN = AbyssalDecor.ITEMS.register("cinnamon_sign",() ->
            new SignItem(new Item.Properties().stacksTo(16), AbyssalDecorBlocks.CINNAMON_SIGN.get(), AbyssalDecorBlocks.CINNAMON_WALL_SIGN.get()));
    public static final RegistrySupplier<BlockItem> CINNAMON_HANGING_SIGN = AbyssalDecor.ITEMS.register("cinnamon_hanging_sign",
            () -> new HangingSignItem(AbyssalDecorBlocks.CINNAMON_HANGING_SIGN.get(),
                    AbyssalDecorBlocks.CINNAMON_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistrySupplier<BlockItem> CINNAMON_POST = registerBlockItem(AbyssalDecorBlocks.CINNAMON_POST);
    public static final RegistrySupplier<BlockItem> CINNAMON_PANELING = registerBlockItem(AbyssalDecorBlocks.CINNAMON_PANELING);

    public static final RegistrySupplier<BlockItem> CINNAMON_LEAVES = registerBlockItem(AbyssalDecorBlocks.CINNAMON_LEAVES);
    public static final RegistrySupplier<BlockItem> FLOWERING_CINNAMON_LEAVES = registerBlockItem(AbyssalDecorBlocks.FLOWERING_CINNAMON_LEAVES);

    public static final RegistrySupplier<BlockItem> CINNAMON_BUSH = registerBlockItem(AbyssalDecorBlocks.CINNAMON_BUSH);

    public static final RegistrySupplier<Item> CINNAMON_STICK = AbyssalDecor.ITEMS.register("cinnamon_stick",() -> simpleItem());
    public static final RegistrySupplier<Item> CINNAMON_APPLE = AbyssalDecor.ITEMS.register("cinnamon_apple",() -> simpleItem());
    public static final RegistrySupplier<Item> CINNAMON_ROLL = AbyssalDecor.ITEMS.register("cinnamon_roll",() -> simpleItem());
    public static final RegistrySupplier<Item> CINNAMON_TEA = AbyssalDecor.ITEMS.register("cinnamon_tea",() -> simpleItem());

    public static final RegistrySupplier<BlockItem> CINNAMON_SHINGLES = registerBlockItem(AbyssalDecorBlocks.CINNAMON_SHINGLES);

    public static final RegistrySupplier<BlockItem> CINNAMON_SHINGLE_SLAB = registerBlockItem(AbyssalDecorBlocks.CINNAMON_SHINGLE_SLAB);
    public static final RegistrySupplier<BlockItem> CINNAMON_SHINGLE_STAIRS = registerBlockItem(AbyssalDecorBlocks.CINNAMON_SHINGLE_STAIRS);

    public static final RegistrySupplier<BlockItem> MOSSY_CINNAMON_SHINGLES = registerBlockItem(AbyssalDecorBlocks.MOSSY_CINNAMON_SHINGLES);

    public static final RegistrySupplier<BlockItem> MOSSY_CINNAMON_SHINGLE_SLAB = registerBlockItem(AbyssalDecorBlocks.MOSSY_CINNAMON_SHINGLE_SLAB);
    public static final RegistrySupplier<BlockItem> MOSSY_CINNAMON_SHINGLE_STAIRS = registerBlockItem(AbyssalDecorBlocks.MOSSY_CINNAMON_SHINGLE_STAIRS);

    public static final RegistrySupplier<BlockItem> CRACKED_BRICKS = registerBlockItem(AbyssalDecorBlocks.CRACKED_BRICKS);

    public static final RegistrySupplier<BlockItem> CHISELED_BRICK = registerBlockItem(AbyssalDecorBlocks.CHISELED_BRICK);

    public static final RegistrySupplier<BlockItem> BRICK_MOSAIC = registerBlockItem(AbyssalDecorBlocks.BRICK_MOSAIC);

    public static final RegistrySupplier<BlockItem> BRICK_CAP = registerBlockItem(AbyssalDecorBlocks.BRICK_CAP);


    public static final RegistrySupplier<BlockItem> BRICK_CORNERSTONE = registerBlockItem(AbyssalDecorBlocks.BRICK_CORNERSTONE);

    public static final RegistrySupplier<BlockItem> BRICK_PILLAR = registerBlockItem(AbyssalDecorBlocks.BRICK_PILLAR);

    public static final RegistrySupplier<BlockItem> BRICK_TRAPDOOR = registerBlockItem(AbyssalDecorBlocks.BRICK_TRAPDOOR);

    public static final RegistrySupplier<BlockItem> BOG_APPLE_LEAVES =  registerBlockItem(AbyssalDecorBlocks.BOG_APPLE_LEAVES);
    public static final RegistrySupplier<Item> BOG_APPLE =  AbyssalDecor.ITEMS.register("bog_apple",() -> simpleItem());
    public static final RegistrySupplier<Item> BOG_ROLL =  AbyssalDecor.ITEMS.register("bog_roll",() -> simpleItem());
    public static final RegistrySupplier<Item> CANDY_BOG_APPLE =  AbyssalDecor.ITEMS.register("candy_bog_apple",() -> simpleItem());

    public static final RegistrySupplier<Item> BOG_APPLE_PIE =  AbyssalDecor.ITEMS.register("bog_apple_pie",() -> simpleItem());
    public static final RegistrySupplier<BlockItem> SPIDERCORN =  registerBlockItem(AbyssalDecorBlocks.SPIDERCORN);
    public static final RegistrySupplier<Item> POPPED_SPIDERCORN =  AbyssalDecor.ITEMS.register("popped_spidercorn",() -> simpleItem());

    public static final RegistrySupplier<Item> SPIDERCORN_TORTILLA =  AbyssalDecor.ITEMS.register("spidercorn_tortilla",() -> simpleItem());
    public static final RegistrySupplier<Item> CAVE_TACO =  AbyssalDecor.ITEMS.register("cave_taco",() -> simpleItem());
    public static final RegistrySupplier<Item> TOASTED_AMARANTH_SEEDS =  AbyssalDecor.ITEMS.register("toasted_amaranth_seeds",() -> simpleItem());


    public static final RegistrySupplier<BlockItem> EFFERVESCENT_PILLAR = registerBlockItem(AbyssalDecorBlocks.EFFERVESCENT_PILLAR);

    public static final RegistrySupplier<BlockItem> EFFERVESCENCE = registerBlockItem(AbyssalDecorBlocks.EFFERVESCENCE);

    public static final RegistrySupplier<BlockItem> EFFERVESCENT_TILES = registerBlockItem(AbyssalDecorBlocks.EFFERVESCENT_TILES);

    public static final RegistrySupplier<BlockItem> POLISHED_EFFERVESCENCE = registerBlockItem(AbyssalDecorBlocks.POLISHED_EFFERVESCENCE);

    public static final RegistrySupplier<BlockItem> STONE_BARS = registerBlockItem(AbyssalDecorBlocks.STONE_BARS);
    public static final RegistrySupplier<BlockItem> SMALL_STONE_BARS = registerBlockItem(AbyssalDecorBlocks.SMALL_STONE_BARS);

    public static final RegistrySupplier<BlockItem> FROSTED_GLASS = registerBlockItem(AbyssalDecorBlocks.FROSTED_GLASS);
    public static final RegistrySupplier<BlockItem> FROSTED_GLASS_PANE = registerBlockItem(AbyssalDecorBlocks.FROSTED_GLASS_PANE);


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
