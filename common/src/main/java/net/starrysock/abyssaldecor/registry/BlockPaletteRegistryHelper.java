package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;

public class BlockPaletteRegistryHelper {

    public static HashMap<String, RegistrySupplier<Block>> paletteBlocks = new HashMap<>();

    public static HashMap<String, RegistrySupplier<Item>> paletteItems = new HashMap<>();

    /*public static void init(Map<String, PaletteType> toRegister) {
        for (String key : toRegister.keySet()) {
            PaletteType type = toRegister.get(key);
            for (String variant : type.getVariants()) {
                String name = key + variant;
                String trueVariant = getBaseVariant(variant);
                BlockSetType blockSetType = AbyssalBlockSetType.byName(key).toBlockSetType();
                RegistrySupplier<Block> block = switch (trueVariant) {
                    case "_button" -> AbyssalDecor.BLOCKS.register(name, () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON),blockSetType , 10, true));
                    case "_pressure_plate" -> AbyssalDecor.BLOCKS.register(name, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.STONE_PRESSURE_PLATE), blockSetType));
                    case "_lamp", "_lantern" -> AbyssalDecor.BLOCKS.register(name, () -> new InteractibleRedstoneLampBlock(type.getProperties()));
                    case "_beam", "_large_pipe", "_pillar", "_log", "_wood" -> AbyssalDecor.BLOCKS.register(name, () -> new RotatedPillarBlock(type.getProperties()));
                    case "_bars", "_ornate_bars" -> AbyssalDecor.BLOCKS.register(name, () -> new IronBarsBlock(type.getProperties()));
                    case "_chain" -> AbyssalDecor.BLOCKS.register(name, () -> new ChainBlock(type.getProperties()));
                    case "_door" -> AbyssalDecor.BLOCKS.register(name, () -> {
                        return new DoorBlock(type.getProperties(), blockSetType);
                    });
                    case "_trapdoor" -> AbyssalDecor.BLOCKS.register(name, () -> new TrapDoorBlock(type.getProperties(), blockSetType));
                    case "_wall" -> AbyssalDecor.BLOCKS.register(name, () -> new WallBlock(type.getProperties()));
                    case "_sconce" -> AbyssalDecor.BLOCKS.register(name, () -> new SconceBlock(type.getProperties()));
                    case "_pipe" -> AbyssalDecor.BLOCKS.register(name, () -> new InteractiblePipeBlock(type.getProperties()));
                    case "_slab" -> AbyssalDecor.BLOCKS.register(name, () -> new SlabBlock(type.getProperties()));
                    case "_stairs" -> AbyssalDecor.BLOCKS.register(name, () -> new StairBlock(Blocks.SANDSTONE_STAIRS.defaultBlockState(), type.getProperties()));
                    case "_fence" -> AbyssalDecor.BLOCKS.register(name, () -> new FenceBlock(type.getProperties()));
                    case "_small_bars" -> AbyssalDecor.BLOCKS.register(name, () -> new SmallBarsBlock(type.getProperties()));
                    default -> AbyssalDecor.BLOCKS.register(name, () -> new Block(type.getProperties()));
                };
                RegistrySupplier<Item> item = AbyssalDecor.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().arch$tab(AbyssalCreativeTabs.PALETTE_TAB)));
                paletteBlocks.put(name, block);
                paletteItems.put(name, item);
            }
        }
    }*/

    public static String getBaseVariant(String name) {
        if (name.contains("pipe") || name.contains("pressure")) {
            return name;
        } else if (name.contains("small_bars")) {
            return "_small_bars";
        }
        return name.substring(name.lastIndexOf('_'));
    }
}
