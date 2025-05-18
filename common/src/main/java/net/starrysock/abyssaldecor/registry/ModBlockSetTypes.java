package net.starrysock.abyssaldecor.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {
    public static final BlockSetType WHITEWOOD = BlockSetType.register(new BlockSetType("whitewood"));
    public static final BlockSetType BLACKWOOD = BlockSetType.register(new BlockSetType("blackwood"));
    public static final BlockSetType WHITE_PEARL = BlockSetType.register(new BlockSetType("white_pearl", true, SoundType.STONE,
            SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));

}
