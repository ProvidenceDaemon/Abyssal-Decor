package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sounds.SoundEvent;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class AbyssalDecorSounds {
    public static final RegistrySupplier<SoundEvent> DUSTY_CD = AbyssalDecor.SOUND_EVENTS.register("music.disk.tsm",() -> SoundEvent.createVariableRangeEvent(AbyssalDecor.id("music.disk.tsm")));
    public static void register() {
        AbyssalDecor.SOUND_EVENTS.register();
    }
}
