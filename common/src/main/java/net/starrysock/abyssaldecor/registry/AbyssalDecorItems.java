package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.starrysock.abyssaldecor.AbyssalDecor;

import java.util.function.Supplier;

public class AbyssalDecorItems {

    public static final RegistrySupplier<BlockItem> SOLAR_ROD = registerBlockItem("solar_rod",AbyssalDecorBlocks.SOLAR_ROD);
    public static final RegistrySupplier<BlockItem> STELLAR_ROD = registerBlockItem("stellar_rod",AbyssalDecorBlocks.STELLAR_ROD);
    public static final RegistrySupplier<BlockItem> TERRESTRIAL_ROD = registerBlockItem("terrestrial_rod",AbyssalDecorBlocks.TERRESTRIAL_ROD);
    public static final RegistrySupplier<BlockItem> LUNAR_ROD = registerBlockItem("lunar_rod",AbyssalDecorBlocks.LUNAR_ROD);
    public static final RegistrySupplier<BlockItem> ETHEREAL_ROD = registerBlockItem("ethereal_rod",AbyssalDecorBlocks.ETHEREAL_ROD);

    public static void register() {
        AbyssalDecor.ITEMS.register();
    }

    public static <B extends Block> RegistrySupplier<BlockItem> registerBlockItem(String name, Supplier<B> bSupplier) {
       return AbyssalDecor.ITEMS.register(name,() -> new BlockItem(bSupplier.get(),new Item.Properties()));
    }
}
