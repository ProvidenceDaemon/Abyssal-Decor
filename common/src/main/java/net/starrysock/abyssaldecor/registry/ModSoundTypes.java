package net.starrysock.abyssaldecor.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class ModSoundTypes {
    public static final SoundType DEEPBRONZE_BLOCK = new SoundType(1.0F, 1.0F, AbyssalDecorSounds.DEEPBRONZE_BLOCK_BREAK.get(),
            AbyssalDecorSounds.DEEPBRONZE_BLOCK_STEP.get(),
            AbyssalDecorSounds.DEEPBRONZE_BLOCK_BREAK.get(), AbyssalDecorSounds.DEEPBRONZE_BLOCK_STEP.get(), SoundEvents.NETHERITE_BLOCK_FALL);

    public static final SoundType SEABRASS_BLOCK = new SoundType(1.0F, 1.0F, SoundEvents.NETHERITE_BLOCK_BREAK,
            SoundEvents.NETHERITE_BLOCK_STEP, SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT, SoundEvents.NETHERITE_BLOCK_FALL);

    public static final SoundType TRASH_BAG = new SoundType(1.0F, 1.0F,AbyssalDecorSounds.TRASH_BAG_BREAK.get(),
            AbyssalDecorSounds.TRASH_BAG_STEP.get(), AbyssalDecorSounds.TRASH_BAG_BREAK.get()/*place*/,
            AbyssalDecorSounds.TRASH_BAG_STEP.get()/*hit*/, SoundEvents.AZALEA_LEAVES_FALL);

}
