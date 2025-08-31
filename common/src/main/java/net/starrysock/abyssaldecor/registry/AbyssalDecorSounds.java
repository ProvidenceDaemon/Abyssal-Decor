package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sounds.SoundEvent;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class AbyssalDecorSounds {
    public static final RegistrySupplier<SoundEvent> DUSTY_CD =
            AbyssalDecor.SOUND_EVENTS.register("music.disk.tsm",() -> SoundEvent.createVariableRangeEvent(AbyssalDecor.id("music.disk.tsm")));

    public static final RegistrySupplier<SoundEvent> LAMP_ON =
            AbyssalDecor.SOUND_EVENTS.register("lamp_on",() -> SoundEvent.createVariableRangeEvent(AbyssalDecor.id("lamp_on")));

    public static final RegistrySupplier<SoundEvent> LAMP_OFF =
            AbyssalDecor.SOUND_EVENTS.register("lamp_off",() -> SoundEvent.createVariableRangeEvent(AbyssalDecor.id("lamp_off")));

    public static void register() {
        AbyssalDecor.SOUND_EVENTS.register();
    }
}
