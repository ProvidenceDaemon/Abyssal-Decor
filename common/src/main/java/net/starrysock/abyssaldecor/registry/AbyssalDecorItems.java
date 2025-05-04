package net.starrysock.abyssaldecor.registry;

import dev.architectury.core.item.ArchitecturyRecordItem;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
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
    public static final RegistrySupplier<Item> MUCKROOT_SOUP = AbyssalDecor.ITEMS.register("muckroot_soup",() -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(Foods.BEETROOT_SOUP)));

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
