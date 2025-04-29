package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.starrysock.abyssaldecor.AbyssalDecor;

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
