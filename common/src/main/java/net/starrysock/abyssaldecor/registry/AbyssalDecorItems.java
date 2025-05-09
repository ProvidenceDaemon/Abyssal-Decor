package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.item.FloorWallCeilingBlockItem;
import net.starrysock.abyssaldecor.item.SupplierRecordItem;

import java.util.function.Supplier;

public class AbyssalDecorItems {

    public static final RegistrySupplier<BlockItem> SOLAR_ROD = registerBlockItem("solar_rod",AbyssalDecorBlocks.SOLAR_ROD);
    public static final RegistrySupplier<BlockItem> STELLAR_ROD = registerBlockItem("stellar_rod",AbyssalDecorBlocks.STELLAR_ROD);
    public static final RegistrySupplier<BlockItem> TERRESTRIAL_ROD = registerBlockItem("terrestrial_rod",AbyssalDecorBlocks.TERRESTRIAL_ROD);
    public static final RegistrySupplier<BlockItem> LUNAR_ROD = registerBlockItem("lunar_rod",AbyssalDecorBlocks.LUNAR_ROD);
    public static final RegistrySupplier<BlockItem> ETHEREAL_ROD = registerBlockItem("ethereal_rod",AbyssalDecorBlocks.ETHEREAL_ROD);
    public static final RegistrySupplier<BlockItem> HANGING_MOSS = registerBlockItem("hanging_moss",AbyssalDecorBlocks.HANGING_MOSS);

    public static final RegistrySupplier<BlockItem> DAFFODIL = registerBlockItem("daffodil",AbyssalDecorBlocks.DAFFODIL);
    public static final RegistrySupplier<BlockItem> ASTER = registerBlockItem("aster",AbyssalDecorBlocks.ASTER);
    public static final RegistrySupplier<BlockItem> SNAPLEAF = registerBlockItem("snapleaf",AbyssalDecorBlocks.SNAPLEAF);

    public static final RegistrySupplier<ItemNameBlockItem> AMARANTH_SEEDS = registerNamedBlockItem("amaranth_seeds",AbyssalDecorBlocks.AMARANTH);
    public static final RegistrySupplier<Item> AMARANTH_PINNACLE = AbyssalDecor.ITEMS.register("amaranth_pinnacle",() -> new Item(new Item.Properties()));
    public static final RegistrySupplier<BlockItem> AMARANTH_CRATE = registerBlockItem("amaranth_crate",AbyssalDecorBlocks.AMARANTH_CRATE);
    public static final RegistrySupplier<ItemNameBlockItem> MUCKROOT = registerNamedBlockItem("muckroot",AbyssalDecorBlocks.MUCKROOT,new Item.Properties().food(Foods.CARROT));
    public static final RegistrySupplier<Item> COOKED_MUCKROOT = AbyssalDecor.ITEMS.register("cooked_muckroot",() -> new Item(new Item.Properties().food(Foods.BEETROOT)));
    public static final RegistrySupplier<Item> MUCKROOT_SOUP = AbyssalDecor.ITEMS.register("muckroot_soup",() ->
            new BowlFoodItem(new Item.Properties().stacksTo(1).food(Foods.BEETROOT_SOUP)));

    public static final RegistrySupplier<BlockItem> BARK_ORCHID = registerBlockItem("bark_orchid",AbyssalDecorBlocks.BARK_ORCHID);

    //todo wisteria and elder wisteria

    public static final RegistrySupplier<BlockItem> WISTERIA_PETALS = registerBlockItem("wisteria_petals",AbyssalDecorBlocks.WISTERIA_PETALS);
    public static final RegistrySupplier<BlockItem> ELDER_WISTERIA_PETALS = registerBlockItem("elder_wisteria_petals",AbyssalDecorBlocks.ELDER_WISTERIA_PETALS);
    public static final RegistrySupplier<BlockItem> ELDER_WISTERIA_LEAVES = registerBlockItem("elder_wisteria_leaves",AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES);

    public static final RegistrySupplier<BlockItem> ANCIENT_BIRCH_LOG =  registerBlockItem("ancient_birch_log",AbyssalDecorBlocks.ANCIENT_BIRCH_LOG);
    public static final RegistrySupplier<BlockItem> STRIPPED_ANCIENT_BIRCH_LOG =  registerBlockItem("stripped_ancient_birch_log",AbyssalDecorBlocks.STRIPPED_ANCIENT_BIRCH_LOG);
    public static final RegistrySupplier<BlockItem> FOXY_PILLAR =  registerBlockItem("foxy_pillar",AbyssalDecorBlocks.FOXY_PILLAR);

    public static final RegistrySupplier<Item> DUSTY_CD = AbyssalDecor.ITEMS.register("dusty_cd",
            () -> new SupplierRecordItem(0,AbyssalDecorSounds.DUSTY_CD.get()
            , new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3600));

    public static final RegistrySupplier<BlockItem> SCRIMSHAW = registerBlockItem("scrimshaw",AbyssalDecorBlocks.SCRIMSHAW);
    public static final RegistrySupplier<BlockItem> STARFISH = registerBlockItem("starfish",AbyssalDecorBlocks.STARFISH);
    public static final RegistrySupplier<BlockItem> DRIED_STARFISH = registerBlockItem("dried_starfish",AbyssalDecorBlocks.DRIED_STARFISH);
    public static final RegistrySupplier<BlockItem> STARLIGHT = registerBlockItem("starlight",AbyssalDecorBlocks.STARLIGHT);
    public static final RegistrySupplier<BlockItem> LIFE_PRESERVER = registerBlockItem("life_preserver",AbyssalDecorBlocks.LIFE_PRESERVER);
    public static final RegistrySupplier<BlockItem> WOOD_SUPPORT = registerBlockItem("wood_support",AbyssalDecorBlocks.WOOD_SUPPORT);
    public static final RegistrySupplier<BlockItem> SHIP_WHEEL = registerBlockItem("ship_wheel",AbyssalDecorBlocks.SHIP_WHEEL);
    public static final RegistrySupplier<BlockItem> WO0DEN_DRAGON_HEAD = registerBlockItem("wooden_dragon_head",AbyssalDecorBlocks.WOODEN_DRAGON_HEAD);
    public static final RegistrySupplier<BlockItem> WO0DEN_FROG = registerBlockItem("wooden_frog",AbyssalDecorBlocks.WOODEN_FROG);
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

    public static final RegistrySupplier<BlockItem> PRISMARINE_CRYSTAL_BLOCK = registerBlockItem("prismarine_crystal_block",AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK);
    public static final RegistrySupplier<BlockItem> PRISMARINE_CRYSTAL_PANE = registerBlockItem("prismarine_crystal_pane",AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE);

    public static final RegistrySupplier<BlockItem> FRESNEL_BLOCK = registerBlockItem("fresnel_block",AbyssalDecorBlocks.FRESNEL_BLOCK);
    public static final RegistrySupplier<BlockItem> FRESNEL_PANE = registerBlockItem("fresnel_pane",AbyssalDecorBlocks.FRESNEL_PANE);

    public static final RegistrySupplier<BlockItem> CRYSTALLIZED_GLOWSTONE = registerBlockItem("crystallized_glowstone",AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE);
    public static final RegistrySupplier<BlockItem> CRYSTALLIZED_GLOWSTONE_PANE = registerBlockItem("crystallized_glowstone_pane",AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE);

    public static final RegistrySupplier<BlockItem> FRAMED_CRYSTALLIZED_GLOWSTONE = registerBlockItem("framed_crystallized_glowstone",AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE);
    public static final RegistrySupplier<BlockItem> FRAMED_CRYSTALLIZED_GLOWSTONE_PANE = registerBlockItem("framed_crystallized_glowstone_pane",AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE);

    public static final RegistrySupplier<BlockItem> VERMILION_BLOCK = registerBlockItem("vermilion_block",AbyssalDecorBlocks.VERMILION_BLOCK);
    public static final RegistrySupplier<BlockItem> VERMILION_PANE = registerBlockItem("vermilion_pane",AbyssalDecorBlocks.VERMILION_PANE);

    public static final RegistrySupplier<BlockItem> FRAMED_VERMILION_BLOCK = registerBlockItem("framed_vermilion_block",AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK);
    public static final RegistrySupplier<BlockItem> FRAMED_VERMILION_PANE = registerBlockItem("framed_vermilion_pane",AbyssalDecorBlocks.FRAMED_VERMILION_PANE);

    public static final RegistrySupplier<BlockItem> DESK_BELL = registerBlockItem("desk_bell",AbyssalDecorBlocks.DESK_BELL);

    public static final RegistrySupplier<BlockItem> LIGHTBULB = registerBlockItem("lightbulb",AbyssalDecorBlocks.LIGHTBULB);
    public static final RegistrySupplier<BlockItem> WALL_BULB_LAMP = registerBlockItem("wall_bulb_lamp",AbyssalDecorBlocks.WALL_BULB_LAMP);
    public static final RegistrySupplier<BlockItem> TUBE_LAMP = registerBlockItem("tube_lamp",AbyssalDecorBlocks.TUBE_LAMP);
    public static final RegistrySupplier<StandingAndWallBlockItem> IRON_LAMP = AbyssalDecor.ITEMS
            .register("iron_lamp",() -> new StandingAndWallBlockItem(AbyssalDecorBlocks.IRON_LAMP.get(),AbyssalDecorBlocks.WALL_IRON_LAMP.get(),new Item.Properties(), Direction.UP));

    public static final RegistrySupplier<BlockItem> FLOWER_LAMP = registerBlockItem("flower_lamp",AbyssalDecorBlocks.FLOWER_LAMP);

    public static final RegistrySupplier<BlockItem> FROSTED_LAMP = registerBlockItem("frosted_lamp",AbyssalDecorBlocks.FROSTED_LAMP);

    public static final RegistrySupplier<FloorWallCeilingBlockItem> QUARTZ_LAMP = AbyssalDecor.ITEMS.register("quartz_lamp",() ->
            new FloorWallCeilingBlockItem(AbyssalDecorBlocks.QUARTZ_LAMP.get(),AbyssalDecorBlocks.WALL_QUARTZ_LAMP.get(),
                    AbyssalDecorBlocks.CEILING_QUARTZ_LAMP.get(),new Item.Properties()));

    public static final RegistrySupplier<StandingAndWallBlockItem> JADE_LAMP = AbyssalDecor.ITEMS.register("jade_lamp",() ->
            new StandingAndWallBlockItem(AbyssalDecorBlocks.JADE_LAMP.get(),AbyssalDecorBlocks.WALL_JADE_LAMP.get(),new Item.Properties(),Direction.DOWN));

    public static final RegistrySupplier<BlockItem> SEAGLASS_LAMP = registerBlockItem("seaglass_lamp",AbyssalDecorBlocks.SEAGLASS_LAMP);
    public static final RegistrySupplier<BlockItem> BLAZE_LAMP = registerBlockItem("blaze_lamp",AbyssalDecorBlocks.BLAZE_LAMP);

    //////

    public static final RegistrySupplier<BlockItem> SEABRASS_ORE = registerBlockItem("seabrass_ore",AbyssalDecorBlocks.SEABRASS_ORE);
    public static final RegistrySupplier<BlockItem> FRESNEL_LAMP = registerBlockItem("fresnel_lamp",AbyssalDecorBlocks.FRESNEL_LAMP);
    public static final RegistrySupplier<BlockItem> RAINBOW_LAMP = registerBlockItem("rainbow_lamp",AbyssalDecorBlocks.RAINBOW_LAMP);
    public static final RegistrySupplier<BlockItem> BULKHEAD_LAMP = registerBlockItem("bulkhead_lamp",AbyssalDecorBlocks.BULKHEAD_LAMP);

    public static final RegistrySupplier<BlockItem> ABYSSAL_LANTERN = registerBlockItem("abyssal_lantern",AbyssalDecorBlocks.ABYSSAL_LANTERN);
    //public static final RegistrySupplier<BlockItem> BLOOD_LANTERN = registerBlockItem("blood_lantern",AbyssalDecorBlocks.BLOOD_LANTERN);
    public static final RegistrySupplier<BlockItem> IRON_LANTERN = registerBlockItem("iron_lantern",AbyssalDecorBlocks.IRON_LANTERN);
    public static final RegistrySupplier<BlockItem> JADE_LANTERN = registerBlockItem("jade_lantern",AbyssalDecorBlocks.JADE_LANTERN);

    public static final RegistrySupplier<BlockItem> VELVET_BARRIER = registerBlockItem("velvet_barrier",AbyssalDecorBlocks.VELVET_BARRIER);
    public static final RegistrySupplier<BlockItem> IRON_BARRIER = registerBlockItem("iron_barrier",AbyssalDecorBlocks.IRON_BARRIER);
    public static final RegistrySupplier<BlockItem> ROPE_BARRIER = registerBlockItem("rope_barrier",AbyssalDecorBlocks.ROPE_BARRIER);
    public static final RegistrySupplier<BlockItem> BARBED_WIRE_BARRIER = registerBlockItem("barbed_wire_barrier",AbyssalDecorBlocks.BARBED_WIRE_BARRIER);

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
        return AbyssalDecor.ITEMS.register(name,() -> new BlockItem(bSupplier.get(),properties));
    }

    public static <B extends Block> RegistrySupplier<BlockItem> registerBlockItem(String name, Supplier<B> bSupplier) {
       return registerBlockItem(name,bSupplier,new Item.Properties());
    }
}
