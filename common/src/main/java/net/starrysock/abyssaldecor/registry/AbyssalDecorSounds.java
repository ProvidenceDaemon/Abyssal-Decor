package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sounds.SoundEvent;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class AbyssalDecorSounds {
    public static final RegistrySupplier<SoundEvent> DUSTY_CD = registrySupplier("music.disk.tsm");

    public static final RegistrySupplier<SoundEvent> LAMP_ON = registrySupplier("lamp_on");

    public static final RegistrySupplier<SoundEvent> LAMP_OFF = registrySupplier("lamp_off");

    public static final RegistrySupplier<SoundEvent> INDUSTRIAL_LEVER_ON = registrySupplier("industrial_lever_on");

    public static final RegistrySupplier<SoundEvent> INDUSTRIAL_LEVER_OFF = registrySupplier("industrial_lever_off");

    public static final RegistrySupplier<SoundEvent> TUBE_LAMP_ON = registrySupplier("tube_lamp_on");

    public static final RegistrySupplier<SoundEvent> TUBE_LAMP_OFF = registrySupplier("tube_lamp_off");

    public static final RegistrySupplier<SoundEvent> DEEPBRONZE_BLOCK_BREAK = registrySupplier("block.deepbronze_block.break");

    public static final RegistrySupplier<SoundEvent> DEEPBRONZE_BLOCK_STEP = registrySupplier("block.deepbronze_block.step");

    public static final RegistrySupplier<SoundEvent> SEABRASS_BLOCK_BREAK = registrySupplier("block.seabrass_block.break");

    public static final RegistrySupplier<SoundEvent> SEABRASS_BLOCK_STEP = registrySupplier("block.seabrass_block.step");

    public static final RegistrySupplier<SoundEvent> TRASH_BAG_BREAK = registrySupplier("block.trash_bag.break");

    public static final RegistrySupplier<SoundEvent> TRASH_BAG_STEP = registrySupplier("block.trash_bag.step");

   // public static final RegistrySupplier<SoundEvent> DEEPBRONZE_BLOCK_PLACE = AbyssalDecor.SOUND_EVENTS.register("deepbronze_block_place",
  //          () -> SoundEvent.createVariableRangeEvent(AbyssalDecor.id("deepbronze_block_break")));

    public static RegistrySupplier<SoundEvent> registrySupplier(String name) {
        return AbyssalDecor.SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(AbyssalDecor.id(name)));
    }

    public static void register() {
        AbyssalDecor.SOUND_EVENTS.register();
    }
}
